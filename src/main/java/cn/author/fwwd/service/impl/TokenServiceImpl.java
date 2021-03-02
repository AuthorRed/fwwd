package cn.author.fwwd.service.impl;


import cn.author.fwwd.dao.model.User;
import cn.author.fwwd.service.TokenService;
import cn.author.fwwd.vo.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * token存到redis的实现类<br>
 * 普通token，uuid
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

	/**
	 * token过期秒数
	 */
	@Value("${token.expire.seconds}")
	private Integer expireSeconds;

	private static final Long MINUTES_30 = 30 * 60 * 1000L;

	public static final String TOKEN_USER_ERROR = "TOKEN_USER_ERROR";

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;


	@Override
	public Token saveToken(User loginUser) {
		String token = UUID.randomUUID().toString();

		loginUser.setToken(token);
		cacheLoginUser(loginUser);
		return new Token(token, loginUser.getLoginTime());
	}

	private void cacheLoginUser(User loginUser) {
		loginUser.setLoginTime(System.currentTimeMillis());
		loginUser.setExpireTime(loginUser.getLoginTime() + expireSeconds * 1000);
		// 缓存
		redisTemplate.boundValueOps(getTokenKey(loginUser.getToken())).set(loginUser, expireSeconds, TimeUnit.SECONDS);
	}

	/**
	 * 更新缓存的用户信息
	 */
	@Override
	public void refresh(User loginUser) {
		cacheLoginUser(loginUser);
	}

	@Override
	public User getLoginUser(String token){
		User loginUser = (User)redisTemplate.boundValueOps(getTokenKey(token)).get();
		if (loginUser != null) {
			loginUser = checkLoginTime(loginUser);
		}else{
			throw new RuntimeException(TOKEN_USER_ERROR);
		}
		return loginUser;
	}
	/**
	 * 校验时间<br>
	 * 过期时间与当前时间对比，临近过期30分钟内的话，自动刷新缓存
	 *
	 * @param loginUser
	 * @return
	 */
	private User checkLoginTime(User loginUser) {
		long expireTime = loginUser.getExpireTime();
		long currentTime = System.currentTimeMillis();
		if (expireTime - currentTime <= MINUTES_30) {
			String token = loginUser.getToken();
			loginUser.setToken(token);
			refresh(loginUser);
		}
		return loginUser;
	}
	@Override
	public boolean deleteToken(String token) {
		String key = getTokenKey(token);
		User loginUser = (User)redisTemplate.opsForValue().get(key);
		if (loginUser != null) {
			redisTemplate.delete(key);
			// 退出日志
			log.info(loginUser.getUid()+"退出");
			return true;
		}
		return false;
	}

	private String getTokenKey(String token) {
		return "tokens:" + token;
	}

}

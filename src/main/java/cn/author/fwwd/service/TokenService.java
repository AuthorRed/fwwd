package cn.author.fwwd.service;

import cn.author.fwwd.dao.model.User;
import cn.author.fwwd.vo.Token;
/**
 * Token管理器<br>
 * 可存储到redis或者数据库<br>
 * 具体可看实现类<br>
 * 默认基于redis，实现类为 TokenServiceImpl<br>
 */
public interface TokenService {

	Token saveToken(User loginUser);

	void refresh(User loginUser);

	User getLoginUser(String token);

	boolean deleteToken(String token);

}

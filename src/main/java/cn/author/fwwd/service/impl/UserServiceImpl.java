package cn.author.fwwd.service.impl;

import cn.author.fwwd.Utils.DateUtils;
import cn.author.fwwd.Utils.HashUtils;
import cn.author.fwwd.config.PropertiesConfig;
import cn.author.fwwd.dao.mapper.RefreshTokenMapper;
import cn.author.fwwd.dao.mapper.UserMapper;
import cn.author.fwwd.dao.model.RefreshToken;
import cn.author.fwwd.dao.model.User;
import cn.author.fwwd.enums.RoleType;
import cn.author.fwwd.enums.ServiceID;
import cn.author.fwwd.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PropertiesConfig config;
    @Autowired
    private RefreshTokenMapper refreshTokenMapper;
    @Override
    public User refreshTokenLogin(String refreshToken){
        if(StringUtils.isBlank(refreshToken)){
            return null;
        }
        RefreshToken dbToken = refreshTokenMapper.selectByPrimaryKey(Long.valueOf(refreshToken));
        if(null!=dbToken && StringUtils.isNotBlank(dbToken.getUid())){
            return loadUserByUsername(dbToken.getUid());
        }
        return null;
    }


    @Override
    public User loadUserByUsername(String uid){
        User user = userMapper.selectByUID(uid);
        return clearPwdSaltHash(user);
    }

    @Override
    public int countUID(String uid) {
        return userMapper.countUID(uid);
    }

    @Override
    public User usernamePwdLogin(String username, String pwd) {
        username = username.trim();
        if(StringUtils.isBlank(username)){
            return null;
        }
        User user = userMapper.selectByUID(username);
        if(null == user || null ==  user.getPwd()){
            return null;
        }
        String encodingPWD = encodingPWD(pwd, user.getSalt());
        if(null!=encodingPWD&&encodingPWD.equalsIgnoreCase(user.getPwd())){
            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setId(DateUtils.getSerialId(config.getServerId(),ServiceID.REFRESH_TOKEN.getCode()));
            refreshToken.setUid(username);
            refreshToken.setUpdateTime(new Date());
            refreshTokenMapper.insertSelective(refreshToken);
            user.setRefresh_token(String.valueOf(refreshToken.getId()));
            return clearPwdSaltHash(user);
        }
        return null;
    }
    @Override
    public User register(User user) throws Exception {
        if(StringUtils.isBlank(user.getUid())||StringUtils.isBlank(user.getUid())){
            throw new RuntimeException("用户名和密码不能为空!");
        }
        int count = userMapper.countUID(user.getUid());
        if(0!=count){
            throw new RuntimeException("用户名已存在");
        }
        Long id = DateUtils.getSerialId(config.getServerId(), ServiceID.USER.getCode());
        user.setId(id);

        String uuid = UUID.randomUUID().toString();
        String salt = uuid.replaceAll("-", "");
        user.setSalt(salt);
        user.setRole(StringUtils.isBlank(user.getRole())? RoleType.BUYER.getCode():user.getRole());
        user.setPwd(encodingPWD(user.getPwd(),salt));
        user.setHash(HashUtils.getUidHash(user.getUid()));
        userMapper.insertSelective(user);
        user.setSalt(null);
        user.setPwd(null);
        return user;
    }
    private User clearPwdSaltHash(User user){
        if(null ==user){
            return  null;
        }
        user.setSalt(null);
        user.setPwd(null);
        user.setHash(null);
        return user;
    }
    private String encodingPWD(String pwd,String salt){
        String raw = pwd+salt;
        if(StringUtils.isBlank(raw)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(raw.getBytes());
    }
}

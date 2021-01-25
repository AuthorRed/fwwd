package cn.author.fwwd.service.impl;

import cn.author.fwwd.dao.mapper.UserMapper;
import cn.author.fwwd.dao.model.User;
import cn.author.fwwd.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.SimpleIdGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

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

//        String raw = pwd+user.getSalt();
//        if(StringUtils.isBlank(raw)){
//            return null;
//        }
//        String md5Password = DigestUtils.md5DigestAsHex(raw.getBytes());
        if(null!=encodingPWD&&encodingPWD.equalsIgnoreCase(user.getPwd())){
            user.setSalt(null);
            return user;
        }
        return null;
    }
    private String encodingPWD(String pwd,String salt){
        String raw = pwd+salt;
        if(StringUtils.isBlank(raw)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(raw.getBytes());
    }
    @Override
    public User register(User user) {
        if(StringUtils.isBlank(user.getUid())||StringUtils.isBlank(user.getUid())){
            throw new RuntimeException("用户名和密码不能为空!");
        }
        int count = userMapper.countUID(user.getUid());
        if(0!=count){
            throw new RuntimeException("用户名已存在");
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        String strDate = sdf.format(date);
        Long id = Long.valueOf(strDate);
        user.setId(id);

        String uuid = UUID.randomUUID().toString();
        String salt = uuid.replaceAll("-", "");
        user.setSalt(salt);
        user.setRole("admin");
        user.setPwd(encodingPWD(user.getPwd(),salt));
        userMapper.insertSelective(user);
        user.setSalt(null);
        user.setPwd(null);
        return user;
    }
}

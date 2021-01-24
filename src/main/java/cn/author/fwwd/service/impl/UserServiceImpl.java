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
        if(null == user ||null ==  user.getPwd()){
            return null;
        }
        String raw = pwd+user.getSalt();
        if(StringUtils.isBlank(raw)){
            return null;
        }
        String md5Password = DigestUtils.md5DigestAsHex(raw.getBytes());
        if(null!=md5Password&&md5Password.equalsIgnoreCase(user.getPwd())){
            return user;
        }
        return null;
    }

    @Override
    public void register(User user) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        String strDate = sdf.format(date);
        Long id = Long.valueOf(strDate);
        user.setId(id);

        String uuid = UUID.randomUUID().toString();
        String salt = uuid.replaceAll("-", "");
        user.setSalt(salt);
        user.setRole("admin");
        if(StringUtils.isBlank(user.getUid())||StringUtils.isBlank(user.getUid())){
            throw new RuntimeException("用户名和密码不能为空!");
        }
        userMapper.insertSelective(user);
    }
}

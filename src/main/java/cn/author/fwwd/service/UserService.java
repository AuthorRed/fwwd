package cn.author.fwwd.service;

import cn.author.fwwd.dao.model.User;

public interface UserService {

    User loadUserByUsername(String uid);

    int countUID(String uid);

    User usernamePwdLogin(String username, String pwd);

    User register(User user)  throws Exception ;
}

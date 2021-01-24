package cn.author.fwwd.service;

import cn.author.fwwd.dao.model.User;

public interface UserService {
    public int countUID(String uid);

    public User usernamePwdLogin(String username, String pwd);

    public void register(User user);
}

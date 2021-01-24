package cn.author.fwwd.dao.mapper;

import cn.author.fwwd.dao.model.User;

public interface UserMapper {

    int countUID(String uid);

    User selectByUID(String uid);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
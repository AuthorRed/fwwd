package cn.author.fwwd.service;

import cn.author.fwwd.dao.model.Commodity;
import cn.author.fwwd.dao.model.User;

public interface CommodityService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Commodity record);

}

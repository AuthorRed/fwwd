package cn.author.fwwd.service;

import cn.author.fwwd.dao.model.Commodity;

import java.util.List;

public interface CommodityService {
    List<Commodity> pageList(Commodity commodity);

    int insertInBatch(Integer num,Integer page);

    int deleteByPrimaryKey(Long id);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Commodity record);

}

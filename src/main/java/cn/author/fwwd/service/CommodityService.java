package cn.author.fwwd.service;

import cn.author.fwwd.dao.model.Commodity;
import cn.author.fwwd.vo.CommodityVO;

import java.util.List;

public interface CommodityService {
    public List<CommodityVO> pageList(Commodity commodity);

    int insertInBatch(Integer num,Integer page);

    int deleteByPrimaryKey(Long id);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Commodity record);

}

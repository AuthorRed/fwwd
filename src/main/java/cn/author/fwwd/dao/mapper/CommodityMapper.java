package cn.author.fwwd.dao.mapper;

import cn.author.fwwd.dao.model.Commodity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommodityMapper {
    Integer count(Commodity commodity);

    List<Commodity> list(Commodity commodity);

    int insertInBatch(List<Commodity> list);

    int deleteByPrimaryKey(Long id);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);
}
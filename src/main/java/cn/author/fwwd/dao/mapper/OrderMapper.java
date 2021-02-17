package cn.author.fwwd.dao.mapper;

import cn.author.fwwd.dao.model.Order;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int updateStatusByPrimaryKey(Order record);
}
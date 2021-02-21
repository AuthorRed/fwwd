package cn.author.fwwd.dao.mapper;

import cn.author.fwwd.dao.model.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderDetail record);

    int insertInBatch(List<OrderDetail> list);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
}
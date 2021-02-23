package cn.author.fwwd.dao.mapper;

import cn.author.fwwd.dao.model.BuyerOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BuyerOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BuyerOrder record);

    int insertSelective(BuyerOrder record);

    BuyerOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BuyerOrder record);

    int updateByPrimaryKey(BuyerOrder record);

    int updateStatusByOrderId(BuyerOrder record);

    List<Long> list(BuyerOrder record);
}
package cn.author.fwwd.dao.mapper;

import cn.author.fwwd.dao.model.SellerOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SellerOrderMapper {
    int deleteByPrimaryKey(Long id);



    int insertSelective(SellerOrder record);

    SellerOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerOrder record);



    int updateStatusByOrderId(SellerOrder record);

    List<Long> list(SellerOrder record);
}
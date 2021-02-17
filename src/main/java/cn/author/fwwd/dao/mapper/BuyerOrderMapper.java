package cn.author.fwwd.dao.mapper;

import cn.author.fwwd.dao.model.BuyerOrder;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface BuyerOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BuyerOrder record);

    int insertSelective(BuyerOrder record);

    BuyerOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BuyerOrder record);

    int updateByPrimaryKey(BuyerOrder record);

    int updateStatusByPrimaryKey(BuyerOrder record);

    List<Long> list(BuyerOrder record);
}
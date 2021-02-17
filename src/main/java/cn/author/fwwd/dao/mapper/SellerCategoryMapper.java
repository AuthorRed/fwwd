package cn.author.fwwd.dao.mapper;

import cn.author.fwwd.dao.model.SellerCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SellerCategoryMapper {

    List<String> list(@Param("sellerUid") String sellerUid);

    int countSellerCategoryDuplicate(@Param("sellerUid") String sellerUid,@Param("categroy") String categroy);

    int deleteByPrimaryKey(Long id);

    int insertSelective(SellerCategory record);

    SellerCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerCategory record);


}
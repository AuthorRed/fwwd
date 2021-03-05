package cn.author.fwwd.dao.mapper;

import cn.author.fwwd.dao.model.RefreshToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RefreshTokenMapper {
    int deleteByPrimaryKey(String refreshToken);

    int deleteByUid(@Param("uid") String uid);

    int insert(RefreshToken record);

    int insertSelective(RefreshToken record);

    RefreshToken selectByPrimaryKey(String refreshToken);

    int updateByPrimaryKeySelective(RefreshToken record);

    int updateByUidSelective(RefreshToken record);

    int updateByPrimaryKey(RefreshToken record);
}
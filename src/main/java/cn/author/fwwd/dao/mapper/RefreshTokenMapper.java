package cn.author.fwwd.dao.mapper;

import cn.author.fwwd.dao.model.RefreshToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefreshTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RefreshToken record);

    int insertSelective(RefreshToken record);

    RefreshToken selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RefreshToken record);

    int updateByPrimaryKey(RefreshToken record);
}
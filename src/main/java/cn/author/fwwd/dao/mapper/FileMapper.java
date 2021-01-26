package cn.author.fwwd.dao.mapper;

import cn.author.fwwd.dao.model.File;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);
}
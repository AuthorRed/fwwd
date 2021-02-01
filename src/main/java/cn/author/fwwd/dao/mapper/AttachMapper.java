package cn.author.fwwd.dao.mapper;

import cn.author.fwwd.dao.model.Attach;

import java.util.List;

public interface AttachMapper {
    List<Attach> selectByFid(Long id);

    int deleteByPrimaryKey(Long id);

    int insert(Attach record);

    int insertSelective(Attach record);

    Attach selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Attach record);

    int updateByPrimaryKey(Attach record);
}
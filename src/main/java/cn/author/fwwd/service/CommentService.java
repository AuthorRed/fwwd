package cn.author.fwwd.service;

import cn.author.fwwd.dao.model.Comment;
import cn.author.fwwd.vo.CommentVO;

import java.util.List;

public interface CommentService {

    List<Comment> selectByParentId(Comment comment);

    List<CommentVO> selectByCommodityId(Comment comment);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

}

package cn.author.fwwd.service.impl;

import cn.author.fwwd.Utils.DateUtils;
import cn.author.fwwd.config.PropertiesConfig;
import cn.author.fwwd.dao.mapper.*;
import cn.author.fwwd.dao.model.*;
import cn.author.fwwd.enums.ServiceID;
import cn.author.fwwd.service.CommentService;
import cn.author.fwwd.service.FileService;
import cn.author.fwwd.service.TokenService;
import cn.author.fwwd.service.UserService;
import cn.author.fwwd.vo.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private PropertiesConfig config;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private FileService fileService;

    @Override
    public List<Comment> selectByParentId(Comment comment){
        return commentMapper.selectByParentId(comment);
    }
    @Override
    public List<CommentVO> selectByCommodityId(Comment comment){
        List<Comment> comments = commentMapper.selectByCommodityId(comment);
        ArrayList<CommentVO> commentVO = new ArrayList<>();
        for (Comment item : comments) {
            CommentVO vo = new CommentVO();
            vo.setComment(item);
            if(item.getHasAttach()==1){
                List<Attach> itemAttaches = fileService.selectByFid(item.getId());
                vo.setAttaches(itemAttaches);
            }
            commentVO.add(vo);
        }
        return commentVO;
    }
    @Override
    public int insertSelective(Comment comment){
        if(null==comment.getId()){
            comment.setId(DateUtils.getSerialId(config.getServerId(), ServiceID.COMMENT.getCode()));
        }
        if(null==comment.getParentId()){
            comment.setParentId(0l);
        }
        if(null==comment.getRate()){
            comment.setRate(1);
        }
        if(null == comment.getHasAttach() || 1!=comment.getHasAttach()){
            comment.setHasAttach(0);
        }
        comment.setInterestCount(0);
        comment.setStatus(1);
        return commentMapper.insertSelective(comment);
    }
    @Override
    public Comment selectByPrimaryKey(Long id){
        return commentMapper.selectByPrimaryKey(id);
    }
    @Override
    public int updateByPrimaryKeySelective(Comment record){
        return commentMapper.updateByPrimaryKeySelective(record);
    }
}

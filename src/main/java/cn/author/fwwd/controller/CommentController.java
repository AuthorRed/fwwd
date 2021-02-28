package cn.author.fwwd.controller;

import cn.author.fwwd.Utils.DateUtils;
import cn.author.fwwd.common.PageBean;
import cn.author.fwwd.common.ResultMsg;
import cn.author.fwwd.config.PropertiesConfig;
import cn.author.fwwd.dao.model.Comment;
import cn.author.fwwd.dao.model.Order;
import cn.author.fwwd.dao.model.OrderDetail;
import cn.author.fwwd.dao.model.User;
import cn.author.fwwd.enums.ServiceID;
import cn.author.fwwd.service.CommentService;
import cn.author.fwwd.service.OrderService;
import cn.author.fwwd.service.TokenService;
import cn.author.fwwd.vo.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
@Slf4j
public class CommentController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PropertiesConfig config;
    @GetMapping("getCommentId")
    public ResultMsg getCommentId(){
        ResultMsg resultMsg = null;
        try {
            Long commentId = DateUtils.getSerialId(config.getServerId(), ServiceID.COMMENT.getCode());
            resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("commentId",commentId);
        }catch (Exception e){
            log.error("获取商品id失败:",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }
    @RequestMapping("getById")
    public ResultMsg getById(Long id){
        ResultMsg resultMsg = null;
        try {
            Comment comment = commentService.selectByPrimaryKey(id);
            resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("comment",comment);
        }catch (Exception e){
            log.error("评价查询出错,",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }
    @RequestMapping("listByCommodityId")
    public ResultMsg listByCommodityId(Comment comment){
        ResultMsg resultMsg = null;
        try {
            List<CommentVO> commentVO = commentService.selectByCommodityId(comment);
            resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("commentVO",commentVO);
        }catch (Exception e){
            log.error("备注查询出错,",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }

    @PostMapping("save")
    public ResultMsg save(Comment comment){
        ResultMsg resultMsg = null;
        try {
            String token = comment.getToken();
            User loginUser = tokenService.getLoginUser(token);
            if(null ==token || null == loginUser){
                throw new RuntimeException("获取用户信息出错!");
            }
            comment.setUid(loginUser.getUid());
            comment.setNickName(loginUser.getNickName());
            comment.setHeadImg(loginUser.getHeadImg());
            int i = commentService.insertSelective(comment);
            resultMsg = ResultMsg.success();
        }catch (Exception e){
            log.error("添加评论失败:",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }



}

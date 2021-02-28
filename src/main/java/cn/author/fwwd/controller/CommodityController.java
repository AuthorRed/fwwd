package cn.author.fwwd.controller;

import cn.author.fwwd.Utils.DateUtils;
import cn.author.fwwd.common.ResultMsg;
import cn.author.fwwd.config.PropertiesConfig;
import cn.author.fwwd.dao.model.Attach;
import cn.author.fwwd.dao.model.Comment;
import cn.author.fwwd.dao.model.Commodity;
import cn.author.fwwd.enums.ServiceID;
import cn.author.fwwd.service.CommentService;
import cn.author.fwwd.service.CommodityService;
import cn.author.fwwd.service.FileService;
import cn.author.fwwd.vo.CommentVO;
import cn.author.fwwd.vo.CommodityVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("commodity")
@Slf4j
public class CommodityController {
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private FileService fileService;
    @Autowired
    private PropertiesConfig config;
    @Autowired
    private CommentService commentService;
    @GetMapping("list")
    public ResultMsg list(Commodity commodity){
        ResultMsg resultMsg = null;
        try {
            List<Commodity> list  = commodityService.pageList(commodity);
            resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("list",list);
        }catch (Exception e){
            log.error("获取商品列表失败:",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }
    @PostMapping("addCommodity")
    public ResultMsg addCommodity(Commodity commodity){
        ResultMsg resultMsg = null;
        try {
            commodityService.insertSelective(commodity);
            resultMsg = ResultMsg.success();
        }catch (Exception e){
            log.error("添加商品失败:",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }

    @GetMapping("getCommodity/{id}")
    public ResultMsg getCommodity(@PathVariable Long id){
        ResultMsg resultMsg = null;
        try {
            Commodity commodity = commodityService.selectByPrimaryKey(id);
            CommodityVO commodityVO = new CommodityVO();
            commodityVO.setCommodity(commodity);
            List<Attach> attaches = fileService.selectByFid(id);

            Comment comment = new Comment();
            comment.setCommodityId(id);
            comment.setPage(1);
            comment.setRows(3);
            List<CommentVO> commentVO = commentService.selectByCommodityId(comment);
            resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("commodity",commodity);
            resultMsg.getExtenal().put("attaches",attaches);
            resultMsg.getExtenal().put("commentVO",commentVO);
        }catch (Exception e){
            log.error("获取商品失败:",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }

    @GetMapping("getCommodityId")
    public ResultMsg getCommodityId(Long id){
        ResultMsg resultMsg = null;
        try {
            Long commodityId = DateUtils.getSerialId(config.getServerId(), ServiceID.COMMODITY.getCode());
            resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("commodityId",commodityId);
        }catch (Exception e){
            log.error("获取商品id失败:",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }
    @GetMapping("batchInsert")
    public ResultMsg getCommodityId(Integer num,Integer page){
        ResultMsg resultMsg = null;
        try {
            commodityService.insertInBatch(num,page);
            resultMsg = ResultMsg.success();
        }catch (Exception e){
            log.error("失败:",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }


}

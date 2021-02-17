package cn.author.fwwd.controller;

import cn.author.fwwd.Utils.DateUtils;
import cn.author.fwwd.common.ResultMsg;
import cn.author.fwwd.config.PropertiesConfig;
import cn.author.fwwd.dao.model.Attach;
import cn.author.fwwd.dao.model.Commodity;
import cn.author.fwwd.enums.ServiceID;
import cn.author.fwwd.service.CommodityService;
import cn.author.fwwd.service.FileService;
import cn.author.fwwd.vo.CommodityVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("list")
    public ResultMsg list(Commodity commodity){
        ResultMsg resultMsg = null;
        try {
            List<CommodityVO> list  = commodityService.pageList(commodity);
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

    @GetMapping("getCommodity")
    public ResultMsg getCommodity(Long id){
        ResultMsg resultMsg = null;
        try {
            Commodity commodity = commodityService.selectByPrimaryKey(id);
            CommodityVO commodityVO = new CommodityVO();
            commodityVO.setCommodity(commodity);
            List<Attach> attaches = fileService.selectByFid(id);
            resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("commodity",commodity);
            resultMsg.getExtenal().put("attaches",attaches);
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

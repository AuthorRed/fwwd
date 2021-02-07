package cn.author.fwwd.controller;

import cn.author.fwwd.common.ResultMsg;
import cn.author.fwwd.dao.model.Attach;
import cn.author.fwwd.dao.model.Commodity;
import cn.author.fwwd.dao.model.User;
import cn.author.fwwd.service.CommodityService;
import cn.author.fwwd.service.FileService;
import cn.author.fwwd.service.UserService;
import cn.author.fwwd.vo.CommodityVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("a")
public class ShopController {
    @Autowired
    private UserService userService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private FileService fileService;

    @RequestMapping("{id}")
    public ResultMsg path(@PathVariable Long id){
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



}

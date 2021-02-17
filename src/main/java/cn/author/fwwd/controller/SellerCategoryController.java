package cn.author.fwwd.controller;

import cn.author.fwwd.common.ResultMsg;
import cn.author.fwwd.dao.model.SellerCategory;
import cn.author.fwwd.service.SellerCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sellerOrder")
@Slf4j
public class SellerCategoryController {
    @Autowired
    private SellerCategoryService sellerCategoryService;

    @RequestMapping("list")
    public ResultMsg list(String sellerUid){
        ResultMsg resultMsg = null;
        try {
            List<String> list = sellerCategoryService.list(sellerUid);
            resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("list",list);
        }catch (Exception e){
            log.error("查询用户类别列表出错",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }

    @PostMapping("saveSellerCategory")
    public ResultMsg saveSellerCategory(SellerCategory category){
        ResultMsg resultMsg = null;
        try {
            sellerCategoryService.saveSellerCategory(category);
            resultMsg = ResultMsg.success();
        }catch (Exception e){
            log.error("保持失败:",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }

}

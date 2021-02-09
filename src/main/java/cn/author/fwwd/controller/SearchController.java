package cn.author.fwwd.controller;

import cn.author.fwwd.common.PageBean;
import cn.author.fwwd.common.ResultMsg;
import cn.author.fwwd.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("search")
@Slf4j
public class SearchController {
    @Autowired
    private SearchService searchService;
    @RequestMapping("index")
    public ResultMsg index(String keyWord, PageBean pageBean){
        ResultMsg resultMsg = null;
        try {
            List<Map<String, Object>> list = searchService.homePageSearch(keyWord,pageBean);
            resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("list",list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }



}

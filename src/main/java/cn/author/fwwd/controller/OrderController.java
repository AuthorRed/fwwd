package cn.author.fwwd.controller;

import cn.author.fwwd.common.PageBean;
import cn.author.fwwd.common.ResultMsg;
import cn.author.fwwd.dao.model.Order;
import cn.author.fwwd.dao.model.User;
import cn.author.fwwd.service.OrderService;
import cn.author.fwwd.service.TokenService;
import cn.author.fwwd.service.UserService;
import cn.author.fwwd.vo.Token;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private OrderService orderService;


    @RequestMapping("getById")
    public ResultMsg getById(Long id){
        ResultMsg resultMsg = null;
        try {
            Order order = orderService.getOrderById(id);
            resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("order",order);
        }catch (Exception e){
            log.error("订单查询出错,",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }
    @RequestMapping("list")
    public ResultMsg list(String token, Integer status, PageBean pageBean){
        ResultMsg resultMsg = null;
        try {
            List<Order> list = orderService.getPageList(token, status, pageBean);
            resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("list",list);
        }catch (Exception e){
            log.error("订单查询出错,",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }

    @PostMapping("save")
    public ResultMsg save(String token,Order order){
        ResultMsg resultMsg = null;
        try {
            orderService.saveOrder(token,order);
            resultMsg = ResultMsg.success();
        }catch (Exception e){
            log.error("注册失败:",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }

    @PostMapping("update")
    public ResultMsg update(String token,Long id,Integer status){
        ResultMsg resultMsg = null;
        try {
            orderService.updateOrderStatus(token,id,status);
            resultMsg = ResultMsg.success();
        }catch (Exception e){
            log.error("注册失败:",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }

}
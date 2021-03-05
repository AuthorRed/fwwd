package cn.author.fwwd.controller;

import cn.author.fwwd.common.PageBean;
import cn.author.fwwd.common.ResultMsg;
import cn.author.fwwd.dao.model.Order;
import cn.author.fwwd.dao.model.OrderDetail;
import cn.author.fwwd.filter.TokenFilter;
import cn.author.fwwd.service.OrderService;
import cn.author.fwwd.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("getOrderDetailByOrderId")
    public ResultMsg getOrderDetailByOrderId(Long id){
        ResultMsg resultMsg = null;
        try {
            List<OrderDetail> list = orderService.getOrderDetailByOrderId(id);
            resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("list",list);
        }catch (Exception e){
            log.error("订单详情查询出错,",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }
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
    public ResultMsg list(String orderType, HttpServletRequest request, Integer status, PageBean pageBean){
        ResultMsg resultMsg = null;
        try {
            String token = TokenFilter.getToken(request);
            List<Order> list = orderService.getPageList(orderType,token, status, pageBean);
            resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("list",list);
        }catch (Exception e){
            log.error("订单查询出错,",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }

    @PostMapping("save")
    public ResultMsg save(@RequestBody Order order, HttpServletRequest request){
        ResultMsg resultMsg = null;
        try {
            String token = TokenFilter.getToken(request);
            order.setToken(token);
            orderService.saveOrder(order.getToken(),order);
            resultMsg = ResultMsg.success();
        }catch (Exception e){
            log.error("下单失败:",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }

    @PostMapping("update")
    public ResultMsg update( HttpServletRequest request,Long id,Integer status){
        ResultMsg resultMsg = null;
        try {
            String token = TokenFilter.getToken(request);
            orderService.updateOrderStatus(token,id,status);
            resultMsg = ResultMsg.success();
        }catch (Exception e){
            log.error("注册失败:",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }

}

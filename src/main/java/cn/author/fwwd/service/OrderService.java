package cn.author.fwwd.service;

import cn.author.fwwd.common.PageBean;
import cn.author.fwwd.dao.model.Order;
import cn.author.fwwd.dao.model.OrderDetail;
import cn.author.fwwd.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    List<OrderDetail>  getOrderDetailByOrderId(Long id);

    void saveOrder(String token,Order order);

    Order getOrderById(Long id);

    void updateOrderStatus(String token,Long id,Integer status);

    List<Order> getPageList(String orderType, String token, Integer status, PageBean pageBean);
}

package cn.author.fwwd.service.impl;

import cn.author.fwwd.Utils.DateUtils;
import cn.author.fwwd.common.PageBean;
import cn.author.fwwd.config.PropertiesConfig;
import cn.author.fwwd.dao.mapper.BuyerOrderMapper;
import cn.author.fwwd.dao.mapper.OrderDetailMapper;
import cn.author.fwwd.dao.mapper.OrderMapper;
import cn.author.fwwd.dao.mapper.SellerOrderMapper;
import cn.author.fwwd.dao.model.*;
import cn.author.fwwd.enums.OrderStatus;
import cn.author.fwwd.enums.RoleType;
import cn.author.fwwd.enums.ServiceID;
import cn.author.fwwd.service.OrderService;
import cn.author.fwwd.service.TokenService;
import cn.author.fwwd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private BuyerOrderMapper buyerOrderMapper;
    @Autowired
    private SellerOrderMapper sellerOrderMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private PropertiesConfig config;
    @Autowired
    private TokenService tokenService;


    @Override
    public void saveOrder(String token,Order order){
        validateOrder(token,order);
        Long id = DateUtils.getSerialId(config.getServerId(), ServiceID.ORDER.getCode());
        Integer orderStatus = OrderStatus.WAIT_SELLER_ACCEPT.getCode();
        Date now = new Date();
        order.setId(id);
        order.setUpdateTime(now);
        order.setAddTime(now);
        order.setStatus(orderStatus);

        List<OrderDetail> list = order.getList();
        for (OrderDetail detail : list) {
            detail.setId(DateUtils.getSerialId(config.getServerId(),ServiceID.ORDER_DETAIL.getCode()));
            detail.setOrderId(id);
        }
        orderDetailMapper.insertInBatch(list);

        SellerOrder sellerOrder = new SellerOrder(DateUtils.getSerialId(config.getServerId(), ServiceID.SELLER_ORDER.getCode()), id, order.getSellerUid(), orderStatus);
        sellerOrder.setSellerId(userService.loadUserByUsername(order.getSellerUid()).getId());
        BuyerOrder buyerOrder = new BuyerOrder(DateUtils.getSerialId(config.getServerId(), ServiceID.BUYER_ORDER.getCode()), id, order.getBuyerUid(), orderStatus);
        buyerOrder.setBuyerId(userService.loadUserByUsername(order.getBuyerUid()).getId());
        orderMapper.insertSelective(order);
        sellerOrderMapper.insertSelective(sellerOrder);
        buyerOrderMapper.insertSelective(buyerOrder);
    }
    @Override
    public Order getOrderById(Long id){
        if(null==id || id<=0){
            log.error("出现空ID查询的情况");
            return null;
        }
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateOrderStatus(String token,Long id,Integer status){
        if(null==status || status<=0 || id<=0 || null==id){
            log.error("出现空ID查询的情况");
        }
        User loginUser = tokenService.getLoginUser(token);
        Order orderDB = getOrderById(id);
        if(null ==orderDB ||null ==loginUser){
            throw new RuntimeException("订单信息错误，更新失败!");
        }
        if(orderDB.getStatus()>=status){
            throw new RuntimeException("更新状态顺序非法!");
        }
        if(OrderStatus.WAIT_SELLER_ACCEPT.getCode().equals(status)) {
            if(!loginUser.getUid().equalsIgnoreCase(orderDB.getSellerUid())){
                throw new RuntimeException("接单需要卖家确认");
            }
        }else if (OrderStatus.WAIT_SELLER_CONFIRM_PAYMENT.getCode().equals(status)){
            if(!loginUser.getUid().equalsIgnoreCase(orderDB.getSellerUid())){
                throw new RuntimeException("接单需要卖家确认");
            }
        }else if (OrderStatus.WAIT_SELLER_DISPATCH.getCode().equals(status)){
            if(!loginUser.getUid().equalsIgnoreCase(orderDB.getSellerUid())){
                throw new RuntimeException("接单需要卖家确认");
            }
        }else if (OrderStatus.PROBLEM.getCode().equals(status)){
            if(!loginUser.getUid().equalsIgnoreCase(orderDB.getSellerUid())){
                throw new RuntimeException("接单需要卖家确认");
            }
        }
        Date now = new Date();
        Order order = new Order();
        order.setId(id);
        order.setStatus(status);
        order.setUpdateTime(now);
        SellerOrder sellerOrder = new SellerOrder();
        sellerOrder.setOrderId(id);
        sellerOrder.setStatus(status);

        BuyerOrder buyerOrder = new BuyerOrder();
        buyerOrder.setOrderId(id);
        buyerOrder.setStatus(status);

        orderMapper.updateStatusByPrimaryKey(order);
        sellerOrderMapper.updateStatusByOrderId(sellerOrder);
        buyerOrderMapper.updateStatusByOrderId(buyerOrder);

    }
    @Override
    public List<Order> getPageList(String orderType,String token, Integer status, PageBean pageBean){
        if(StringUtils.isBlank(token)){
            throw new RuntimeException("订单查询请登录!");
        }
        User loginUser = tokenService.getLoginUser(token);
        if(null==loginUser){
            throw new RuntimeException("用户会话失效，请重新登录!");
        }
        List<Long> orderIdList = null;
        if(RoleType.SELLER.getCode().equalsIgnoreCase(orderType)){
            SellerOrder sellerOrder = new SellerOrder();
            sellerOrder.setSellerUid(loginUser.getUid());
            sellerOrder.setPage(pageBean.getPage());
            sellerOrder.setRows(pageBean.getRows());
            sellerOrder.setStatus(status);
            orderIdList = sellerOrderMapper.list(sellerOrder);
        }else{
            BuyerOrder buyerOrder = new BuyerOrder();
            buyerOrder.setBuyerUid(loginUser.getUid());
            buyerOrder.setPage(pageBean.getPage());
            buyerOrder.setRows(pageBean.getRows());
            buyerOrder.setStatus(status);
            orderIdList = buyerOrderMapper.list(buyerOrder);
        }
        if(CollectionUtils.isEmpty(orderIdList)){
            return null;
        }
        ArrayList<Order> orders = new ArrayList<Order>(orderIdList.size());
        for (Long id : orderIdList) {
            Order order = orderMapper.selectByPrimaryKey(id);
            orders.add(order);
        }
        return orders;
    }
    private void validateOrder(String token,Order order){
        User loginUser = tokenService.getLoginUser(token);
//        if(null==loginUser || !RoleType.BUYER.getCode().equalsIgnoreCase(loginUser.getRole())){
//            throw new RuntimeException("商家账号不能下单!");
//        }
        if(order.getAmount()<1){
            throw new RuntimeException("订单数量不能小于'1'");
        }
        if(order.getList().size()<1){
            throw new RuntimeException("订单详情数量不能小于'1'");
        }
//        if(null==order.getBuyerAddressId()||order.getBuyerAddressId()<1){
//            throw new RuntimeException("订单缺少用户收货地址");
//        }
        if(StringUtils.isBlank(order.getBuyerUid())){
            throw new RuntimeException("订单缺少用户信息，请重新登录下单");
        }
//        if(null==order.getCommodityId()||order.getCommodityId()<1){
//            throw new RuntimeException("订单缺少商品信息");
//        }
        if(StringUtils.isBlank(order.getSellerUid())){
            throw new RuntimeException("订单缺少商户信息");
        }
    }
}

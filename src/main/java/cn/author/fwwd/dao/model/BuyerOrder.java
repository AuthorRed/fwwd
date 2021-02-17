package cn.author.fwwd.dao.model;

import cn.author.fwwd.common.PageBean;

public class BuyerOrder extends PageBean {
    private Long id;

    private Long orderId;

    private String buyerUid;

    private Integer status;

    private Long buyerId;


    public BuyerOrder() {
    }

    public BuyerOrder(Long id, Long orderId, String buyerUid, Integer status) {
        this.id = id;
        this.orderId = orderId;
        this.buyerUid = buyerUid;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(String buyerUid) {
        this.buyerUid = buyerUid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }
}
package cn.author.fwwd.dao.model;

import cn.author.fwwd.common.PageBean;

public class SellerOrder extends PageBean {
    private Long id;

    private Long orderId;

    private String sellerUid;

    private Integer status;

    private Long sellerId;


    public SellerOrder() {
    }

    public SellerOrder(Long id, Long orderId, String sellerUid, Integer status) {
        this.id = id;
        this.orderId = orderId;
        this.sellerUid = sellerUid;
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

    public String getSellerUid() {
        return sellerUid;
    }

    public void setSellerUid(String sellerUid) {
        this.sellerUid = sellerUid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
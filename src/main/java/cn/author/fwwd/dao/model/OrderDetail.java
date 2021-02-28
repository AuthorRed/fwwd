package cn.author.fwwd.dao.model;

import java.math.BigDecimal;

public class OrderDetail {
    private Long id;

    private Long orderId;

    private String commodityId;

    private Integer num;

    private BigDecimal buyerPay;

    private String remark;

    private String commodityDesc;

    private String title;

    private Long headImg;

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

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId == null ? null : commodityId.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getBuyerPay() {
        return buyerPay;
    }

    public void setBuyerPay(BigDecimal buyerPay) {
        this.buyerPay = buyerPay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCommodityDesc() {
        return commodityDesc;
    }

    public void setCommodityDesc(String commodityDesc) {
        this.commodityDesc = commodityDesc == null ? null : commodityDesc.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getHeadImg() {
        return headImg;
    }

    public void setHeadImg(Long headImg) {
        this.headImg = headImg;
    }
}
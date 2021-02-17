package cn.author.fwwd.dao.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Long id;

    private Long commodityId;

    private String sellerUid;

    private String buyerUid;

    private Long buyerAddressId;

    private String description;

    private String remark;

    private BigDecimal price;

    private Integer amount;

    private String unit;

    private Integer status;

    private BigDecimal buyerPay;

    private BigDecimal sellerRec;

    private Date updateTime;

    private Date addTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }


    public String getSellerUid() {
        return sellerUid;
    }

    public void setSellerUid(String sellerUid) {
        this.sellerUid = sellerUid;
    }

    public String getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(String buyerUid) {
        this.buyerUid = buyerUid;
    }

    public Long getBuyerAddressId() {
        return buyerAddressId;
    }

    public void setBuyerAddressId(Long buyerAddressId) {
        this.buyerAddressId = buyerAddressId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getBuyerPay() {
        return buyerPay;
    }

    public void setBuyerPay(BigDecimal buyerPay) {
        this.buyerPay = buyerPay;
    }

    public BigDecimal getSellerRec() {
        return sellerRec;
    }

    public void setSellerRec(BigDecimal sellerRec) {
        this.sellerRec = sellerRec;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
package cn.author.fwwd.dao.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
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

    private List<OrderDetail> list;

    private String token;

}
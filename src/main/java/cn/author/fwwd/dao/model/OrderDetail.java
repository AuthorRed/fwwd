package cn.author.fwwd.dao.model;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderDetail {
    private Long id;

    private Long orderId;

    private String commodityId;

    private Integer num;

    private BigDecimal buyerPay;

    private String remark;

    private String commodityDesc;


}
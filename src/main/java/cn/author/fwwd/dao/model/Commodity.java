package cn.author.fwwd.dao.model;

import cn.author.fwwd.common.PageBean;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Commodity extends PageBean {
    private Long id;

    private String title;

    private String seller;

    private Long sellerId;

    private Integer status;

    private BigDecimal price;

    private String unit;

    private Date updateTime;

    private Integer page;

    private Integer rows;

}
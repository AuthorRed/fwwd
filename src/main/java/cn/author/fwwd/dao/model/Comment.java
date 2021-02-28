package cn.author.fwwd.dao.model;

import cn.author.fwwd.common.PageBean;
import lombok.Data;

import java.util.Date;
@Data
public class Comment  extends PageBean {
    private Long id;

    private Long parentId;

    private Long commodityId;

    private String text;

    private Date addTime;

    private String uid;

    private String nickName;

    private String headImg;

    private Integer status;

    private Integer rate;

    private Integer interestCount;

    private String quantity;

    private String token;

    private Integer hasAttach;



}
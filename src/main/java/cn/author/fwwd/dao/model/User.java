package cn.author.fwwd.dao.model;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private Long id;

    private String uid;

    private String pwd;

    private String salt;

    private String role;

    private String nickName;

    private String phone;

    private Byte status;

    private String email;

    private Date updateTime;

    private String updatePerson;

    private Integer hash;

    private String headImg;

    private String token;

    private String refreshToken;

    private Long loginTime;

    private Long expireTime;


}
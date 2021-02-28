package cn.author.fwwd.dao.model;

import lombok.Data;

import java.util.Date;
@Data
public class Attach {
    private Long id;

    private String url;

    private String host;

    private Long fid;

    private Integer fidHash;

    private Byte displayOrder;

    private String fileName;

    private Byte status;

    private Byte type;

    private Date uploadTime;

    private String uploadUid;

    private String category;

    private String token;
}
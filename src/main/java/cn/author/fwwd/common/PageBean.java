package cn.author.fwwd.common;

import lombok.Data;

@Data
public class PageBean {
    private Integer page;
    private Integer rows;
    private String status;

    public static PageBean buildParams(PageBean pageBean){
        Integer page = pageBean.getPage();
        if (page == null || page < 1) {
            pageBean.setPage(1);
        }
        Integer rows = pageBean.getRows();
        if (rows == null || rows < 1) {
            pageBean.setRows(10);
        }
        return pageBean;
    }


}

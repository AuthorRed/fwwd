package cn.author.fwwd.common;




public class PageBean {
    private Integer page;
    private Integer rows;

    public Integer getPage() {
        if(null==this.page || this.page<1){ this.page=1;}
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        if(null==this.rows || this.rows<1){ this.rows=10; }
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}

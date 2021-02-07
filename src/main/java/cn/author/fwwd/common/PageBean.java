package cn.author.fwwd.common;




public class PageBean {
    private Integer page;
    private Integer offSet;
    private Integer rows;

    public Integer getOffSet() {
        int offSet = this.getPage()*this.getRows()-this.getRows();
        this.setOffSet(offSet);
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }

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

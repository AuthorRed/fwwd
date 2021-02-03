package cn.author.fwwd.enums;



public enum CommodityStatus {
    ADDED("ADD",1),
    PUBLISHED("PUBLISHED",2),
    REMOVED("REMOVED",-1);
    private String name;
    private Integer code;

    CommodityStatus(String file, Integer l) {
        this.name = file;
        this.code = l;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

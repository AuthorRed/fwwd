package cn.author.fwwd.enums;



public enum ServiceID {
    COMMENT("COMMENT",71l,1),
    FILE("FILE",11l,1),
    COMMODITY("COMMODITY",12l,1),
    USER("USER",31l,1),
    ORDER("ORDER",51l,1),
    ORDER_DETAIL("ORDER_DETAIL",55l,1),
    SELLER_ORDER("SELLER_ORDER",52l,1),
    BUYER_ORDER("BUYER_ORDER",53l,1),
    SELLER_CATEGORY("SELLER_CATEGORY",61l,1);

    private String name;
    private long code;
    private Integer tableCount;

    ServiceID(String file, long l,Integer tableCount) {
        this.name = file;
        this.code = l;
        this.tableCount = tableCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public Integer getTableCount() {
        return tableCount;
    }

    public void setTableCount(Integer tableCount) {
        this.tableCount = tableCount;
    }
}

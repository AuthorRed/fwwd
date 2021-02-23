package cn.author.fwwd.enums;



public enum OrderStatus {
    WAIT_SELLER_ACCEPT("WAIT_SELLER_ACCEPT",10),
    WAIT_BUYER_PAY("WAIT_BUYER_PAY",50),
    WAIT_SELLER_CONFIRM_PAYMENT("WAIT_SELLER_CONFIRM_PAYMENT",50),
    WAIT_SELLER_DISPATCH("WAIT_SELLER_DISPATCH",50),
    WAIT_ORDER_DELIVER("WAIT_ORDER_DELIVER",50),
    FINISHED("FINISHED",50),
    PROBLEM("PROBLEM",50);
    private String name;
    private Integer code;

    OrderStatus(String file, Integer l) {
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

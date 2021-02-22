package cn.author.fwwd.enums;



public enum OrderStatus {
    PLACED("CLIENT_PLACE_ORDER",10),
    ACCEPT("SELLER_ACCEPT",20),
    PAID("CLIENT_PAID",30),
    FINISHED("FINISHED",40),
    COMMENTED("CLIENT_COMMENTED",50),
    //问题单
    PROBLEM("PROBLEM",60);
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

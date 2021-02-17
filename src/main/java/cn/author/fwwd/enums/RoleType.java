package cn.author.fwwd.enums;



public enum RoleType {
    BUYER("BUYER","buyer"),
    SELLER("SELLER","seller"),
    ADMIN("ADMIN","admin");
    private String name;
    private String code;

    RoleType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

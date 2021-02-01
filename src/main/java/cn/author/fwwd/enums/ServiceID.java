package cn.author.fwwd.enums;



public enum ServiceID {
    FILE("file",11l),
    COMMODITY("commodity",12l);
    private String name;
    private long code;

    ServiceID(String file, long l) {
        this.name = file;
        this.code = l;
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
}

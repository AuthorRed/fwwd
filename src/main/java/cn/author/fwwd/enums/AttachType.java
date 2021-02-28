package cn.author.fwwd.enums;



public enum AttachType {
    HEAD_IMG("HEAD_IMG",new Byte("10")),
    SWIPE_IMG("SWIPE_IMG",new Byte("20")),
    DETAIL_IMG("DETAIL_IMG",new Byte("30"));
    private String name;
    private Byte code;

    AttachType(String name, Byte code) {
        this.name = name;
        this.code = code;
    }


}

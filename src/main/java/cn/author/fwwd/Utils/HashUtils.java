package cn.author.fwwd.Utils;

public class HashUtils {
    private static final Integer count = 1;

    public static Integer getIntHash(String str) throws Exception{
        str=str.trim();
        if(str.length()>3){
            str=str.substring(0,3);
        }
        if(null==str ||str.length()<1){
            return 0;
        }
        byte[] bytes = str.getBytes("utf-8");
        int intHash = 0;
        if(bytes.length>=3){
            intHash = bytes[0] * 100000 + bytes[1] * 100+ bytes[2];
        }else {
            intHash = bytes[0];
        }
        return Math.abs(intHash%count);

    }

    public static String getStrHash(String str)throws Exception{
        Integer intHash = getIntHash(str);
        return String.valueOf(intHash);
    }
}

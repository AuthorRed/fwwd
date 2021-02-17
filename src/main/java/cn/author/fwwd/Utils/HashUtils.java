package cn.author.fwwd.Utils;

import cn.author.fwwd.enums.ServiceID;

public class HashUtils {
    private static final Integer count = 6;



    public static Integer getIntHash(Long number, ServiceID serviceID) throws Exception{
        Integer count = serviceID.getTableCount();
        System.out.println(count);
        if(0 == count){
            return 0;
        }
        Long abs = (Long)Math.abs(number % count);
        return  abs.intValue();

    }
    public static Integer getIntHash(String str,ServiceID serviceID) throws Exception{
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
        Integer tableCount = serviceID.getTableCount();
        if(0==tableCount){
            tableCount=1;
        }
        return Math.abs(intHash% tableCount);
    }

    /**
     * 用uid计算出来存到数据库中的hash值
     * @param str uid
     * @return
     * @throws Exception
     */
    public static Integer getUidHash(String str) throws Exception{
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
        return Math.abs(intHash);
    }


    /*public enum ModulePartition{
        ORDER("ORDER",10),
        FILE("FILE",20);

        private String module;
        private Integer count;

        ModulePartition (String module,Integer count){
            this.module = module;
            this.count = count;
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }*/
}

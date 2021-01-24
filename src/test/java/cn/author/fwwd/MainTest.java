package cn.author.fwwd;

import cn.author.fwwd.Utils.HashUtils;

import java.util.UUID;

public class MainTest {
    public static void main(String[] args) throws Exception{
//        testEncoding();
//        String str = "新东方";
        System.out.println("取模>>>"+-4%8);
//        System.out.println("length>>>"+str.length());
//        System.out.println("substring>>>"+str.substring(0,3));
//        System.out.println("start>>>"+System.currentTimeMillis());
//        System.out.println(HashUtils.getStrHash("186"));
//        for (int i = 0; i < 100000; i++) {
//
//        }
//        System.out.println("end>>>"+System.currentTimeMillis());
    }

    public static void testEncoding()throws Exception{
        //255 255 255
        String str = "今天是个好日子！";
        byte[] utf8 = str.getBytes("utf-8");
        byte[] uTF = str.getBytes("UTF-8");
        byte[] gbk = str.getBytes("GBK");
        for (byte b : utf8) {
            System.out.print(Math.abs(b) +", ");
        }
        System.out.println();

        for (int i = 0; i < uTF.length-1; i++) {
            System.out.println(Math.abs(uTF[i])+Math.abs(uTF[i+1]) );
        }
    }
}

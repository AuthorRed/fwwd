package cn.author.fwwd;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.nio.charset.Charset;

public class MainTest {
    public static void main(String[] args) {


    }

    private static void testByteChartset(){
        String str = "我";
        char firstChar = str.charAt(0);
        char lastChar = str.charAt(str.length()-1);
//        System.out.println("firstChar:"+firstChar);
//        System.out.println("lastChar:"+lastChar);
        byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
        for (byte b : bytes) {
            System.out.print(b + ",");
        }
        System.out.println();

        for (byte b : bytes) {
            int hash = Math.abs(b) % 100;
            System.out.print(hash + ",");
        }
    }
}

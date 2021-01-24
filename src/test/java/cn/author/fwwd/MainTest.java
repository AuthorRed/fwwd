package cn.author.fwwd;

import cn.author.fwwd.Utils.HashUtils;

import java.util.UUID;

public class MainTest {

    public static void testEncoding() throws Exception {
        //255 255 255
        String str = "今天是个好日子！";
        byte[] utf8 = str.getBytes("utf-8");
        byte[] uTF = str.getBytes("UTF-8");
        byte[] gbk = str.getBytes("GBK");
        for (byte b : utf8) {
            System.out.print(Math.abs(b) + ", ");
        }
        System.out.println();

        for (int i = 0; i < uTF.length - 1; i++) {
            System.out.println(Math.abs(uTF[i]) + Math.abs(uTF[i + 1]));

        }
    }
}
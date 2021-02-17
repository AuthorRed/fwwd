package cn.author.fwwd;

import cn.author.fwwd.Utils.DateUtils;
import cn.author.fwwd.Utils.HashUtils;
import cn.author.fwwd.enums.ServiceID;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import static org.springframework.test.context.transaction.TestTransaction.start;

@Slf4j
public class MainTest {

    public static void main(String[] args) throws Exception{
//        for (int i = 0; i < 100000; i++) {
//            Integer ii = i;
//            String intHash = String.valueOf(HashUtils.getIntHash(ii.longValue(), ServiceID.ORDER));
//            System.out.println("param:"+ii+"  分表数:"+ServiceID.ORDER.getTableCount() +" 结果:"+intHash);
//        }
        testDateUtils();
    }

    private static void testDateUtils()throws Exception{
        //        System.currentTimeMillis() * 10000
        log.info("start");
        HashSet<Long> longs = new HashSet<>();
        for (int i = 0; i <10000 ; i++) {
//            new Thread(() ->
//                    longs.add(DateUtils.getSerialId(11l,22l))
//            ).start();
            System.out.println(DateUtils.getSerialId(11l,22l));

        }
        Thread.sleep(500);
        System.out.println(longs.size());
        log.info("end");
    }
    private static void testEncoding() throws Exception {
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
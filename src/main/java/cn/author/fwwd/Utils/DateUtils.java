package cn.author.fwwd.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 借鉴雪花算法，性能很差，获取一个分布式ID18位的long数字，前13位为时间戳，第14位为序列号，
 * 第15，16位为服务号，第17，18位为机器号；
 * 不能有空位导致顺序不对；
 */
public  class DateUtils {

    private static Logger logger	= LoggerFactory.getLogger(DateUtils.class);
    private static volatile AtomicInteger idCount = new AtomicInteger(1);
    private static volatile AtomicLong previousMillis = new AtomicLong(System.currentTimeMillis());

    public static synchronized final Long getSerialId(Long server,Long service){
        long serialId = 0l;
        long currentTimeMillis = System.currentTimeMillis();

        try {
            if(currentTimeMillis > previousMillis.longValue()){
                idCount.set(1);
            }else {
                if(idCount.intValue()>=9){
                    Thread.sleep(1);
                    idCount.set(1);
                    currentTimeMillis = System.currentTimeMillis();
                }else{
                    idCount.incrementAndGet();
                }
            }
            previousMillis.set(currentTimeMillis);

//            Thread.sleep(1);
            //123456789012300000
            long part1 = currentTimeMillis * 100000;
            //123456789012310000
            long part2 = idCount.intValue() * 10000;
            //123456789012311100
            long part3 = server * 100;
            //123456789012311111
//            long part4 = service;
            serialId = part1 + part2 + part3 + service;
        }catch (Exception e){
            logger.error("创建ID出错:",e);
        }
        return serialId;
    }
}

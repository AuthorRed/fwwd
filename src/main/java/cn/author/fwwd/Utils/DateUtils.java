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
    private static volatile AtomicInteger idCount = new AtomicInteger(0);
    private static volatile AtomicLong previousMillis = new AtomicLong(System.currentTimeMillis());


    public static synchronized final Long getSerialId(Long server,Long service){
        long serialId = 0l;
        long currentTimeMillis = System.currentTimeMillis();

        try {
            if(idCount.incrementAndGet()>=10){
                idCount.set(0);
                if(currentTimeMillis<=previousMillis.longValue()){
                    Thread.sleep(1);
                    currentTimeMillis = System.currentTimeMillis();
                }
            }
            previousMillis.set(currentTimeMillis);



//            Thread.sleep(1);
            //123456789012300000
            long timestamp = currentTimeMillis * 100000;
            //123456789012311000
            long serverPart = server * 1000;
            //123456789012311110
            long servicePart = service * 10;
            //123456789012311111
            long serial = idCount.intValue();
            serialId = timestamp + serverPart + servicePart + serial;
        }catch (Exception e){
            logger.error("创建ID出错:",e);
        }
        return serialId;
    }
}

package cn.author.fwwd.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 借鉴雪花算法，获取一个分布式ID18位的long数字，前13位为时间戳，第14位为序列号，
 * 第15，16位为服务号，第17，18位为机器号；
 * 不能有空位导致顺序不对；
 */
public class DateUtils {

    private static Logger logger	= LoggerFactory.getLogger(DateUtils.class);
    private static final Lock lock = new ReentrantLock();
    private static AtomicInteger idCount = new AtomicInteger();
    public static final String getSerialId(String prefix)
    {
        String serialId = null;
        try {
            lock.lock();
            Thread.sleep(1);
            Date date = new Date();
            //mysql bigint最大支持19位数字
            //时间戳是13位
            long millis = System.currentTimeMillis() * 100000;
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmssS");
            int count = idCount.incrementAndGet();
            if(count>9){
                idCount.set(1);
                count = 1;
            }
            long countLong = count*10000;
            serialId = sdf.format(date) + String.valueOf(count);
        }catch (Exception e){
            logger.error("创建ID出错:",e);
        }finally {
            lock.unlock();
        }
        return serialId;
    }
}

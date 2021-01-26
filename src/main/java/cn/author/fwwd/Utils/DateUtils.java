package cn.author.fwwd.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
            System.out.println(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmssS");
            int count = idCount.incrementAndGet();
            if(count>999){
                idCount.set(0);
                count = 0;
            }
            serialId = sdf.format(date) + String.valueOf(count);
        }catch (Exception e){
            logger.error("创建ID出错:",e);
        }finally {
            lock.unlock();
        }
        return serialId;
    }
}

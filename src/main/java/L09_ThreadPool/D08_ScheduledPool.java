package L09_ThreadPool;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/14 0:14
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/14 0:14
 */
public class D08_ScheduledPool {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, 0, 1000,TimeUnit.MILLISECONDS);
    }
}

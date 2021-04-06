package L06_Lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/6 22:06
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/6 22:06
 */
public class D01_ReadWriteLock {



    public static void read(Lock lock, CountDownLatch latch) {
        try {
            lock.lock();
            Thread.sleep(1000);
            // Do read operation
            System.out.println("read over!");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, CountDownLatch latch) {
        try {
            lock.lock();
            Thread.sleep(1000);
            //Do write operation
            System.out.println("write over!");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

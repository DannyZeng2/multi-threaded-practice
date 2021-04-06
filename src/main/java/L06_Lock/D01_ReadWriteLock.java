package L06_Lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/6 22:06
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/6 22:06
 */
public class D01_ReadWriteLock {

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    static CountDownLatch latch = new CountDownLatch(20);

    public static void read(Lock lock) {
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

    public static void write(Lock lock) {
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





    public static void main(String[] args) {
        Runnable readR = ()-> read(readLock);
        Runnable writeR = ()->write(writeLock);

        long startTime = System.currentTimeMillis();
        for(int i=0; i<18; i++) new Thread(readR).start();
        for(int i=0; i<2; i++) new Thread(writeR).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("程序执行时间：" + (endTime-startTime));


    }
}

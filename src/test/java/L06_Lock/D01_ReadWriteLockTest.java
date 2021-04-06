package L06_Lock;


import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/6 22:43
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/6 22:43
 */
public class D01_ReadWriteLockTest {

    @Test
    public void test_reentrantLock() {
        D01_ReadWriteLock d01 = new D01_ReadWriteLock();

        CountDownLatch latch = new CountDownLatch(20);

        ReentrantLock reentrantLock = new ReentrantLock();

        long startTime = System.currentTimeMillis();
        for(int i=0; i<18; i++) new Thread(() -> d01.read(reentrantLock,latch)).start();
        for(int i=0; i<2; i++) new Thread(() -> d01.write(reentrantLock,latch)).start();

        // 利用CountDownLatch，等待上面线程运行完再执行下面代码
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("程序执行时间：" + (endTime-startTime)); // 20044ms
    }

    @Test
    public void test_readWriteLock() {
        D01_ReadWriteLock d01 = new D01_ReadWriteLock();

         CountDownLatch latch = new CountDownLatch(20);

         ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
         Lock readLock = readWriteLock.readLock();
         Lock writeLock = readWriteLock.writeLock();

        long startTime = System.currentTimeMillis();
        for(int i=0; i<18; i++) new Thread(() -> d01.read(readLock,latch)).start();
        for(int i=0; i<2; i++) new Thread(() -> d01.write(writeLock,latch)).start();

        // 利用CountDownLatch，等待上面线程运行完再执行下面代码
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("程序执行时间：" + (endTime-startTime)); // 3032ms
    }




}
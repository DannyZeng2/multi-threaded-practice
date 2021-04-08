package L04_Atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/29 21:59
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/29 21:59
 */
public class D02_AtomicVsSyncVsLongAdder {
    static AtomicLong count1 = new AtomicLong(0L);
    static LongAdder count2 = new LongAdder();


    static void testAtomic(Thread[] threads) throws InterruptedException {


        for(int i=0; i<threads.length; i++) {
            threads[i] =
                    new Thread(()-> {
                        for(int k=0; k<300000; k++) count1.incrementAndGet();
                    });
        }

        long start = System.currentTimeMillis();

        for(Thread t : threads ) t.start();

        for (Thread t : threads) t.join();

        long end = System.currentTimeMillis();

        System.out.println("Atomic:" + count1.get() + ", 程序运行时间:" + (end-start) + "ms");
    }

    static void testLongAdder(Thread[] threads) throws InterruptedException {
        for(int i=0; i<threads.length; i++) {
            threads[i] =
                    new Thread(()-> {
                        for(int k=0; k<300000; k++) count2.increment();
                    });
        }

        long start = System.currentTimeMillis();

        for(Thread t : threads ) t.start();

        for (Thread t : threads) t.join();

        long end = System.currentTimeMillis();

        System.out.println("LongAdder:" + count2.longValue() + ", 程序运行时间:" + (end-start) + "ms");
    }

    public static void main(String[] args) throws Exception {
        Thread[] threads1 = new Thread[5000];
        Thread[] threads2 = new Thread[5000];

        testAtomic(threads1);
        testLongAdder(threads2);
    }
}

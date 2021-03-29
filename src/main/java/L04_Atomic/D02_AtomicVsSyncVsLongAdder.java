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
    static long count2 = 0L;
    static LongAdder count3 = new LongAdder();


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

        System.out.println("Atomic: " + count1.get() + " time " + (end-start));
    }

    static void testSynchronized(Thread[] threads) throws InterruptedException {
        Object lock = new Object();

        for(int i=0; i<threads.length; i++) {
            threads[i] =
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            for (int k = 0; k < 300000; k++)
                                synchronized (lock) {
                                    count2++;
                                }
                        }
                    });
        }

        long start = System.currentTimeMillis();

        for(Thread t : threads ) t.start();

        for (Thread t : threads) t.join();

        long end = System.currentTimeMillis();

        System.out.println("Sync: " + count2 + " time " + (end-start));
    }


    static void testLongAdder(Thread[] threads) throws InterruptedException {
        for(int i=0; i<threads.length; i++) {
            threads[i] =
                    new Thread(()-> {
                        for(int k=0; k<300000; k++) count3.increment();
                    });
        }

        long start = System.currentTimeMillis();

        for(Thread t : threads ) t.start();

        for (Thread t : threads) t.join();

        long end = System.currentTimeMillis();

        System.out.println("LongAdder: " + count3.longValue() + " time " + (end-start));
    }

    public static void main(String[] args) throws Exception {
        Thread[] threads1 = new Thread[1000];
        Thread[] threads2 = new Thread[1000];
        Thread[] threads3 = new Thread[1000];

        testAtomic(threads1);

        testSynchronized(threads2);

        testLongAdder(threads3);

    }
}

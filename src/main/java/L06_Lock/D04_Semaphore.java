package L06_Lock;

import java.util.concurrent.Semaphore;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/7 20:56
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/7 20:56
 */
public class D04_Semaphore {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore  =new Semaphore(10);

        Thread[] threads = new Thread[100];

        for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+" start...");
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName()+ " end...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }


        long start = System.currentTimeMillis();

        for(Thread t : threads ) t.start();

        for (Thread t : threads) t.join();

        long end = System.currentTimeMillis();

        System.out.println("执行时间"+(end-start)+ "ms");


    }
}

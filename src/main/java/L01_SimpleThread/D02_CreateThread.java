package L01_SimpleThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/27 17:49
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/27 17:49
 * 创建线程的4种方式: 1. Thread 2.Runnable 3.Callable 4.Executors.newCacheThread
 */
public class D02_CreateThread {
    static class T1 extends Thread {
        @Override
        public void run() {
            System.out.println("Hi T1...");
        }
    }

    static class R1 implements Runnable {
        @Override
        public void run() {
            System.out.println("Hi R1...");
        }
    }

    static class C1 implements Callable<String>{

        @Override
        public String call(){
            try {
                TimeUnit.SECONDS.sleep(2);
                return "Hi C1...";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi C2...";
        }
    }


    /**
     * Result:
     * Hi T1...
     * Hi R1...
     * (waiting for 2s)
     * Hi C1...
     * Hi T2...
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new T1().start();
        new Thread(new R1()).start();
        FutureTask<String> futureTask = new FutureTask<>(new C1());
        new Thread(futureTask, "c01").start();
        while (!futureTask.isDone()) {

        }
        System.out.println(futureTask.get());

        new Thread(() -> {
            System.out.println("Hi T2...");
        }).start();
    }

}

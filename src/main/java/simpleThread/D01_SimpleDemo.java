package simpleThread;

import java.util.concurrent.TimeUnit;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/27 17:09
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/27 17:09
 */
public class D01_SimpleDemo {
     static class T1 extends Thread {
        @Override
        public void run() {
            for (int i=0;i<3;i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                    System.out.println("T1...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) {
            //new T1().run(); // T1 T1 T1 main main main  先运行T1.run再运行main方法
            new T1().start(); //main T1 main T1 main T1   T1.run和main方法同时运行
            for (int i=0;i<3;i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                    System.out.println("main...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

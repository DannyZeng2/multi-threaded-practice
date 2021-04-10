package L03_Volatile;

import java.util.concurrent.TimeUnit;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/28 19:25
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/28 19:25
 */
public class VolatileDemo {

    volatile boolean running = true;

    void m1() {
        System.out.println("m1 start...");
        while (running) {

        }
        System.out.println("m1 end...");
    }

    public static void main(String[] args) {
        VolatileDemo demo = new VolatileDemo();
        new Thread(demo::m1).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demo.running = false;
        }).start();
    }
 }

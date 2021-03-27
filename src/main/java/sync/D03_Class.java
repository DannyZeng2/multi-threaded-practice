package sync;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/27 22:48
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/27 22:48
 */
public class D03_Class {

    synchronized static void m1() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + ": count=" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void m2() {
        synchronized (D03_Class.class) {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(200);
                    System.out.println(Thread.currentThread().getName() + ": count=" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        D03_Class d1 = new D03_Class();
        D03_Class d2 = new D03_Class();
        new Thread(() -> {
            d1.m1();
        }).start();

        new Thread(() -> {
            d2.m1();
        }).start();
    }


}

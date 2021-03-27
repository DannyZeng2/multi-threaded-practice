package simpleThread;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/27 18:18
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/27 18:18
 */
public class D03_Sleep {

    private static Object obj = new Object();

    static void testSleep() {
        new Thread(() -> {
            synchronized (obj){
                for (int i =0;i<10;i++) {
                    try {
                        Thread.sleep(2000);
                        System.out.println("T1...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (obj){
                for (int i =0;i<10;i++) {
                    try {
                        Thread.sleep(2000);
                        System.out.println("T2...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }).start();

        new Thread(() -> {
            for (int i =0;i<10;i++) {
                try {
                    Thread.sleep(500);
                    System.out.println("T3...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        testSleep();
        //testYield();
        //testJoin();
    }
}

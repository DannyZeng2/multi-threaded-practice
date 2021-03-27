package simpleThread;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/27 20:11
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/27 20:11
 */
public class D04_Yield {

    static void testYield() {
        new Thread(() -> {
            for (int i =0;i<10;i++) {
                System.out.println("T1...");
                if(i%3==0) {
                    Thread.yield();
                    System.out.println("T1 yield");
                }
            }
        }).start();
        new Thread(() -> {
            for (int i =0;i<10;i++) {
                System.out.println("T2...");
                if(i%3==0) {
                    Thread.yield();
                    System.out.println("T2 yield");
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        testYield();
    }
}

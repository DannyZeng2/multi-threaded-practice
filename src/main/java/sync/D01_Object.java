package sync;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/27 22:21
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/27 22:21
 */
public class D01_Object {
    private static int count = 10;

    private static  Object obj = new Object();

     void m1() {
         synchronized (obj){
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
        D01_Object d1 = new D01_Object();
        D01_Object d2 = new D01_Object();
        new Thread(() -> {
            d1.m1();
        }).start();

        new Thread(() -> {
            d2.m1();
        }).start();

    }
}

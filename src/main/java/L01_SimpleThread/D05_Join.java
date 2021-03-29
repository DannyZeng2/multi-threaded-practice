package L01_SimpleThread;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/27 20:11
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/27 20:11
 */
public class D05_Join {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(2000);
                    System.out.println("T1...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(2000);
                    System.out.println("T2...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(2000);
                    System.out.println("T3...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });


        t3.start();
        t3.join();

        t1.start();
        t1.join();

        t2.start();
        t2.join();

    }
}

package L06_Lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/8 20:11
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/8 20:11
 */
public class D07_LockSupport {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (i == 5) {
                    LockSupport.park();
                }
                try {
                    Thread.sleep(1000);
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LockSupport.unpark(thread);
    }
}

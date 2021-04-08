package L06_Lock;

import java.util.concurrent.Exchanger;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/7 21:47
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/7 21:47
 */
public class D05_Exchanger {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->{
            String s = "T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "t1").start();


        new Thread(()->{
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "t2").start();


    }
}

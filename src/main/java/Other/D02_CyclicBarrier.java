package Other;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/2 0:32
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/2 0:32
 */
public class D02_CyclicBarrier {
    private static int count = 0;

    static void  testCyclicBarrier() {
        CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("满20人，出发!!!");
            }
        });

        for (int i = 0; i < 100; i++) {

            new Thread(() ->{
                try {
                    System.out.println("报数："+count++);
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }

    public static void main(String[] args) {
        testCyclicBarrier();
    }
}

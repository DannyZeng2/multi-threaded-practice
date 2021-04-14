package L09_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/12 23:33
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/12 23:33
 */
public class D04_FixedThreadPool {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 6; i++) {

            service.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);

        service.shutdownNow();
        System.out.println(service.isShutdown());
        System.out.println(service.isTerminated());
        System.out.println(service);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(service.isShutdown());
        System.out.println(service.isTerminated());
        System.out.println(service);




    }
}

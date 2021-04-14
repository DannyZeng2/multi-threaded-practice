package L09_ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/13 0:06
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/13 0:06
 */
public class D05_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<String> future = service.submit(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName();
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(future.get());

        System.out.println(future.isDone());
    }

}

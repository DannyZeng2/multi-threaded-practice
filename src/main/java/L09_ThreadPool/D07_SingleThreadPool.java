package L09_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/14 0:13
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/14 0:13
 */
public class D07_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for(int i=0; i<5; i++) {
            final int j = i;
            service.execute(()->{

                System.out.println(j + " " + Thread.currentThread().getName());
            });
        }

    }
}

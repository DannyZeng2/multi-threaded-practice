package L09_ThreadPool;

import java.util.concurrent.*;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/12 22:48
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/12 22:48
 */
public class D03_Callable   {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> c = new Callable() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "Callable thread...";
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(c); //异步


        System.out.println(future.get()); // 阻塞

        System.out.println("Main thread...");

        service.shutdown();
    }
}

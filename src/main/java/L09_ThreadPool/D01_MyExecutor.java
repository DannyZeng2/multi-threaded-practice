package L09_ThreadPool;

import java.util.concurrent.Executor;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/11 15:48
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/11 15:48
 */
public class D01_MyExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }

    public static void main(String[] args) {
        D01_MyExecutor executor = new D01_MyExecutor();
        executor.execute(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("aaa");
        });
        executor.execute(()->{
            System.out.println("bbb");
        });


    }
}

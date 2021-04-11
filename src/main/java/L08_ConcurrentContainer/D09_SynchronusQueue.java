package L08_ConcurrentContainer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/11 0:16
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/11 0:16
 */
public class D09_SynchronusQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa"); //阻塞等待消费者消费
        //strs.put("bbb");
        //strs.add("aaa");
        System.out.println(strs.size());
    }
}

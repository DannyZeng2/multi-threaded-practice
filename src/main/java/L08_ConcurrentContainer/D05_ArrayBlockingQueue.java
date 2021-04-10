package L08_ConcurrentContainer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/10 22:01
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/10 22:01
 */
public class D05_ArrayBlockingQueue {
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }

        //strs.put("aaa"); //满了就会等待，程序阻塞
        //strs.add("aaa"); //满了再加会报异常
        //strs.offer("aaa"); //满了再加不报异常，返回false
        strs.offer("aaa", 1, TimeUnit.SECONDS); //给定时间如果加不进去，返回false

        System.out.println(strs);
    }

}

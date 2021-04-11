package L08_ConcurrentContainer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/10 21:00
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/10 21:00
 */
public class D02_ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<>(); // 单向队列
        // ConcurrentLinkedDeque 双向队列

        for (int i = 0; i < 10; i++) {
            queue.offer("a"+i);
        }
        System.out.println(queue);
        System.out.println(queue.size());

        System.out.println(queue.peek());
        System.out.println(queue.size());

        System.out.println(queue.poll());
        System.out.println(queue.size());
    }

}

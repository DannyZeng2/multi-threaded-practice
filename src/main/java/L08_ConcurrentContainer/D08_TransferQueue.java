package L08_ConcurrentContainer;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/11 0:15
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/11 0:15
 */
public class D08_TransferQueue {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                queue.transfer("aaa");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

package L08_ConcurrentContainer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/10 20:21
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/10 20:21
 */
public class D03_CopyOnWriteArrayList {

    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        ((ArrayList<String>) list).ensureCapacity(10000000);
        List<String> list = new CopyOnWriteArrayList<>();

        CountDownLatch latch = new CountDownLatch(100);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    list.add("item" + j);
                }
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(list.size());
    }
}

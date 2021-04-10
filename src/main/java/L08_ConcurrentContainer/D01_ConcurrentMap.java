package L08_ConcurrentContainer;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/10 17:29
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/10 17:29
 */
public class D01_ConcurrentMap {
    public static void main(String[] args) {
         //Map<String, String> map = new ConcurrentHashMap<>(); //14728
        //Map<String, String> map = new ConcurrentSkipListMap<>();  // 高并发并且排序
        Map<String, String> map = new Hashtable<>();  //15378
        //Map<String, String> map = new HashMap<>();
       // Map<String, String> map = Collections.synchronizedMap(new HashMap<>()); //14466
        //TreeMap
        Random r = new Random();
        Thread[] ths = new Thread[500];
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for(int i=0; i<ths.length; i++) {
           new Thread(()->{
                for(int j=0; j<10000; j++) {
                    map.put(UUID.randomUUID().toString(), "a" + r.nextInt(100000));
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


        System.out.println(map.size());

    }
}

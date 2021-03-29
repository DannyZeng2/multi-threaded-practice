package L04_Atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/29 20:42
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/29 20:42
 */
public class D01_AtomicInteger {

    AtomicInteger count = new AtomicInteger(0);
    //int count = 0;


     void m1() {
        for (int i = 0; i < 100; i++) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count.incrementAndGet();
            //count++;
        }
    }

    public static void main(String[] args) {
        D01_AtomicInteger t = new D01_AtomicInteger();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m1,"thread" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach(o -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);

    }


}

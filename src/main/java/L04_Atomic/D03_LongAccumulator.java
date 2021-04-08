package L04_Atomic;

import java.util.Random;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/7 23:10
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/7 23:10
 */
public class D03_LongAccumulator{
    public static void main(String[] args) throws InterruptedException {
        LongAccumulator accumulator = new LongAccumulator(Long::max, Long.MIN_VALUE);
        Thread[] ts = new Thread[1000];

        for (int i = 0; i < 1000; i++) {
            ts[i] = new Thread(() -> {
                Random random = new Random();
                long value = random.nextLong();
                accumulator.accumulate(value); // 比较value和上一次的比较值，然后存储较大者
            });
            ts[i].start();
        }
        for (int i = 0; i < 1000; i++) {
            ts[i].join();
        }
        System.out.println(accumulator.longValue());
    }
}

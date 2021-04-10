package Questions;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/10 11:31
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/10 11:31
 */
public class Q2_ProducerAndConsumer<T> {

    private final static int MAX = 10;
    final private LinkedList<T> lists = new LinkedList<>();
    private int count = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition producer = lock.newCondition();
    private final Condition consumer = lock.newCondition();

    void put(T t) {
        lock.lock();
        try {

            while (lists.size() == MAX) {
                producer.await();
            }
            lists.add(t);
            count++;
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    T get() {
        T t = null;
        lock.lock();
        try {

            while (lists.size() == 0) {
                consumer.await();
            }
            t = lists.removeFirst();
            count--;
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {

        Q2_ProducerAndConsumer<String> container = new Q2_ProducerAndConsumer<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    String item = container.get();
                    System.out.println("Consuming " + item);
                }
            }, " consumer" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    container.put("resource-" + j);
                    System.out.println("Producing resource-" + j);
                }
            }, "producer" + i).start();
        }


    }


}

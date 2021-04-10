package Questions;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/9 23:21
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/9 23:21
 *
 *  面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 *  能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *  使用wait和notify/notifyAll来实现
 *  使用Lock和Condition来实现
 *  对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 */

public class Q1_ProducerAndConsumer<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    private final static int MAX = 10;
    private int count = 0;


    synchronized void put(T t){

        while(lists.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lists.add(t);
        count++;
        this.notifyAll();


    }

    synchronized T get() {
        T t = null;
        while(lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
         }
        t = lists.removeFirst();
        count --;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {

        Q1_ProducerAndConsumer<String> container = new Q1_ProducerAndConsumer<>();



        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    String item = container.get();
                    System.out.println("Consuming " + item);
                }
            }," consumer" +i).start();
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
            },"producer" + i).start();
        }


    }
}

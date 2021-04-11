package L08_ConcurrentContainer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/11 14:21
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/11 14:21
 */
public class D10_PriorityQueue {
    public static void main(String[] args) {
       BlockingQueue<User> q = new PriorityBlockingQueue<>(10);

        q.add(new User("danny",4));
        q.add(new User("eric",2));
        q.add(new User("matthew",1));
        q.add(new User("bill",5));
        q.add(new User("carlos",3));

        for (int i = 0; i < 5; i++) {
            System.out.println(q.poll());
        }
    }
}

class User implements Comparable<User> {
    public final String name;
    public final int priority;

    public User(String name, int number) {
        this.name = name;
        this.priority = number;
    }

    public String toString() {
        return "姓名:" + name + " - 优先级:" + priority;
    }

    @Override
    public int compareTo(User o) {
        return this.priority - o.priority;
    }
}


package sync;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/27 22:43
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/27 22:43
 */
public class D02_This {


    void m1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(200);
                    System.out.println(Thread.currentThread().getName() + ": count=" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    synchronized void m2() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + ": count=" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        D02_This d1 = new D02_This();
        D02_This d2 = new D02_This();
        new Thread(() -> {
            d1.m1();
        }).start();

        new Thread(() -> {
            d2.m1();
        }).start();
    }
}


class T implements Runnable {

    private int count = 100;

    @Override
    public /*synchronized*/ void run() {
        try {
            count--;
            System.out.println(Thread.currentThread().getName() + ": count=" + count);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "Thread" + i).start();
        }
    }
}

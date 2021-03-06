package L05_ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock用于替代synchronized
 * 由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 * 这里是复习synchronized最原始的语义
 *
 * 使用reentrantlock可以完成同样的功能
 * 需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 * 使用syn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 */
public class D02_ReentrantLock {

    Lock lock = new ReentrantLock();

    void m1() {
        lock.lock();
        System.out.println("m1...");
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1);
                    System.out.println(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                m2();
                lock.unlock();
            }

    }

    void m2() {
        lock.lock();
        System.out.println("m2...");
        lock.unlock();
    }

    public static void main(String[] args) {
        D02_ReentrantLock t = new D02_ReentrantLock();
        new Thread(t::m1,"m1").start();
        //new Thread(t::m2,"m2").start();
    }
}

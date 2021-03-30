package L05_ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/30 23:41
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/30 23:41
 * ReentrantLock还可以指定为公平锁
 */
public class D05_FairySync extends Thread{
    private static ReentrantLock lock=new ReentrantLock(true); //参数为true表示为公平锁，请对比输出结果
    public void run() {
        for(int i=0; i<100; i++) {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }finally{
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        D05_FairySync rl=new D05_FairySync();
        Thread th1=new Thread(rl);
        Thread th2=new Thread(rl);
        th1.start();
        th2.start();
    }
}

package L02_Synchronized;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/28 13:44
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/28 13:44
 * @Description: 同步和非同步方法是否可以同时调用 （可以）
 */
public class D04_SyncAndNoSync {

    synchronized void sync() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + ": count=" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

     void noSync() {
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

        D04_SyncAndNoSync t = new D04_SyncAndNoSync();
        new Thread(t::sync, "t1").start();
        new Thread(t::noSync, "t2").start();
    }

}

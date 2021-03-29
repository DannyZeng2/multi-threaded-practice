package L02_Synchronized;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/28 16:18
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/28 16:18
 * @Description: synchronized 异常会释放锁，其他线程就有机会进到同步区，并拿到同步数据
 */
public class D07_Exception {

    private int count = 0;

    synchronized void m1() {
        while (count<10) {
            count++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": count=" + count);

            if(count ==5) {
                try {
                    count = count/0;
                } catch (Exception e) {

                }

            }
        }
    }

    public static void main(String[] args) {
        D07_Exception t = new D07_Exception();
        new Thread(t::m1,"t1").start();
        new Thread(t::m1,"t2").start();
    }
}

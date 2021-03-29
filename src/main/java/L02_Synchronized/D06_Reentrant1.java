package L02_Synchronized;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/28 14:40
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/28 14:40
 * @Description: synchronized是可重入锁
 */
public class D06_Reentrant1 {

    synchronized void m1() {
        System.out.println("m1 start...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end...");
    }

    synchronized void m2() {
        System.out.println("m2 start...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end...");
    }

    public static void main(String[] args) {
        D06_Reentrant1 t = new D06_Reentrant1();
        new Thread(t::m1,"m1").start();
    }
}

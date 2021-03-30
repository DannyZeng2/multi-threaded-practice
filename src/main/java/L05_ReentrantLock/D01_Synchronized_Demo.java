package L05_ReentrantLock;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/30 22:58
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/30 22:58
 */
public class D01_Synchronized_Demo {
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
        D01_Synchronized_Demo t = new D01_Synchronized_Demo();
        new Thread(t::m1,"m1").start();
    }

}

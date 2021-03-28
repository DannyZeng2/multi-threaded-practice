package sync;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/28 14:57
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/28 14:57
 * @Description: synchronized是可重入锁
 */
public class D06_Reentrant2 {

    synchronized void m1() {
        System.out.println("m1 start...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1 end...");
    }
}

class Child extends D06_Reentrant2 {
    synchronized void m2() {
        System.out.println("m2 start...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.m1();
        System.out.println("m2 end...");
    }

    public static void main(String[] args) {
        Child child = new Child();
        new Thread(child::m2).start();
    }
}

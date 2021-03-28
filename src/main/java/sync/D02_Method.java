package sync;
/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/27 22:21
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/27 22:21
 * @Description: synchronized可以保证原子性和可见性，volatile只能保证可见性
 */
public class D02_Method implements Runnable {

    private /*volatile*/ int count = 100;

    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + ": count=" + count);
    }

    public static void main(String[] args) {
        D02_Method t = new D02_Method();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "Thread" + i).start();
        }
    }
}
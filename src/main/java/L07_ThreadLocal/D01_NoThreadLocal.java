package L07_ThreadLocal;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/10 16:10
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/10 16:10
 */
public class D01_NoThreadLocal {
    volatile static Person person = new Person();

    public static void main(String[] args) {

        new Thread(() -> {
            person.setName("danny");

        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println(person.getName());
        }).start();

    }
}



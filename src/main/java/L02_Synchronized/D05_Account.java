package L02_Synchronized;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/3/28 14:29
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/3/28 14:29
 * @Description: 写加锁，读不加锁可能会引起什么问题（脏读）
 */
public class D05_Account {
    private String name;
    private double balance;

    public synchronized void set(String name,double balance) {
        this.name = name;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance = balance;
    }

    public /*synchronized*/ double getBalance(String name) {
        return this.balance;
    }

    public static void main(String[] args) {
        D05_Account account = new D05_Account();

        new Thread(() -> account.set("111",2000)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance("111"));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance("111"));

    }

}

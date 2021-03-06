package Questions;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/10 17:14
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/10 17:14
 *
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 *
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 *
 * 就算操作A和B都是同步的，但A和B组成的复合操作也未必是同步的，仍然需要自己进行同步
 * 就像这个程序，判断size和进行remove必须是一整个的原子操作
 *
 */
public class Q4_TicketSeller {

    static Queue<String> tickets = new ConcurrentLinkedQueue<>();
    
    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("ticket-编号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                while (tickets.size()!= 0) {
                    String ticket = tickets.poll();
                    System.out.println(Thread.currentThread().getName()+ "已售出票："+ticket);
                }
            },"售票员" + i).start();
        }


    }
}

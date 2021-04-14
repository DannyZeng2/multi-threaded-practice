package L09_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/12 22:46
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/12 22:46
 */
public class D02_ExecutorService {
    public static void main(String[] args) {
        ExecutorService e = Executors.newCachedThreadPool();
    }
}

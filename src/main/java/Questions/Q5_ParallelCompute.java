package Questions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/13 20:24
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/13 20:24
 */
public class Q5_ParallelCompute {

    public static boolean isPrime(int num) {
        if(num ==2) {
            return true;
        }
        for(int i = 2; i<=num/2; i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> getPrimeList(int start,int end) {
        List <Integer> result = new ArrayList<>();
        for(int i = start;i<=end;i++) {
            if(isPrime(i)) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 单线程执行
        long time1 = System.currentTimeMillis();
        List<Integer> primeList = getPrimeList(1, 400000);
        System.out.println(primeList.size());
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);

        // 利用线程池同步执行
        ExecutorService service = Executors.newFixedThreadPool(4);
        MyTask task1 = new MyTask(1,200000);
        MyTask task2 = new MyTask(200001,280000);
        MyTask task3 = new MyTask(280001,350000);
        MyTask task4 = new MyTask(350001,400000);

        List<Integer> result = new ArrayList<>();

        Future<List<Integer>> f1 = service.submit(task1);
        Future<List<Integer>> f2 = service.submit(task2);
        Future<List<Integer>> f3 = service.submit(task3);
        Future<List<Integer>> f4 = service.submit(task4);

        List<Integer> result1 = f1.get();
        List<Integer> result2 = f2.get();
        List<Integer> result3 = f3.get();
        List<Integer> result4 = f4.get();

        result.addAll(result1);
        result.addAll(result2);
        result.addAll(result3);
        result.addAll(result4);

        System.out.println(result.size());

        long time3 = System.currentTimeMillis();
        System.out.println(time3 - time2);

    }

    static class MyTask implements Callable<List<Integer>> {
        private int start;
        private int end;

        MyTask(int start,int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public List<Integer> call() throws Exception {
            return getPrimeList(start,end);
        }
    }


}

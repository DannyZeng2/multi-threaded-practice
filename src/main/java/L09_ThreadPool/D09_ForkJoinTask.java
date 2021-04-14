package L09_ThreadPool;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/14 19:57
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/14 19:57
 */
    public class D09_ForkJoinTask extends RecursiveTask<Long> {

    private int start;
    private int end;
    private static int NUMS_SIZE = 10000000;

    private static int[] nums= new int[NUMS_SIZE];

    private final int MAX_NUM = 50000;
    
    
    static {
        for (int i = 1; i <= NUMS_SIZE; i++) {
            nums[i-1] = i ;
        }
    }

    public D09_ForkJoinTask(int start,int end) {
        this.start = start;
        this.end = end;

    }

    @Override
    protected Long compute() {

        if(end-start<=50000) {
            long sum = 0;
            for (int i =start; i < end; i++) {
                sum += nums[i];
            }
            return sum;
        }

        int middle = start + (end-start)/2;

        D09_ForkJoinTask subTask1 = new D09_ForkJoinTask(start,middle);
        D09_ForkJoinTask subTask2 = new D09_ForkJoinTask(middle,end);

        subTask1.fork();
        subTask2.fork();

        return  subTask1.join() + subTask2.join();
    }


    public static void main(String[] args) throws IOException {
        // Arrays.stream(nums).sum()
        long t1 = System.currentTimeMillis();
        long[] nums= new long[NUMS_SIZE];
        for (int i = 1; i <= NUMS_SIZE; i++) {
            nums[i-1] = i ;
        }
        System.out.println(Arrays.stream(nums).sum());
        long t2 = System.currentTimeMillis();
        System.out.println(t2 -t1);

        // ForkJoin
        ForkJoinPool pool = new ForkJoinPool();
        D09_ForkJoinTask task = new D09_ForkJoinTask(0,nums.length);
        pool.execute(task);

        Long result = task.join();
        System.out.println(result);
        long t3 = System.currentTimeMillis();

        System.out.println(t3-t2);
        System.in.read();

    }
}

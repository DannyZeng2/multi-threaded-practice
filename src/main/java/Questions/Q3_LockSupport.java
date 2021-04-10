package Questions;

import java.util.concurrent.locks.LockSupport;

/**
 * @version: 1.0.0
 * @Author: Danny Zeng
 * @Date: 2021/4/10 13:47
 * @LastEditors: Danny Zeng
 * @LastEditTime: 2021/4/10 13:47
 *
 * 定义两个数组[0-9][1-h],打印a0b2c3...h9
 */
public class Q3_LockSupport {

    private static char[] nums = "01234565789".toCharArray();
    private static char[] letters = "abcdeghijh".toCharArray();
    static Thread letterThread = null;
    static Thread numThread = null;

    public static void main(String[] args) {

        letterThread = new Thread(() -> {
            for (int i = 0; i < letters.length; i++) {
                System.out.printf(String.valueOf(letters[i]));
                LockSupport.unpark(numThread);
                LockSupport.park();

            }
        });


        numThread = new Thread(() -> {
            for (int i = 0; i < nums.length; i++) {
                LockSupport.park();
                System.out.printf(String.valueOf(nums[i]));
                LockSupport.unpark(letterThread);
            }
        });

        letterThread.start();

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        numThread.start();


    }



}

package huawei;

import java.util.Scanner;


// 正整数A和正整数B 的最小公倍数是指 能被A和B整除的最小的正整数值，设计一个算法，求输入A和B的最小公倍数。

public class HJ108_最小公倍数 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            String[] ss = s.split(" ");
            System.out.println(new Solution().resolve(Integer.valueOf(ss[0]), Integer.valueOf(ss[1])));
        }
    }



    static class Solution {

        //
        public int resolve(int a, int b) {
            return a * b / maxCommonDivisor(a, b);
        }

        // 最大公约数
        // 方法1. 直接从 min(a, b) -> 1 依次开始找公约数
        // 方法2. 辗转相除法求最大公约数
        //    两个正整数a和b（a>b），它们的最大公约数等于a除以b的余数c和b之间的最大公约数。
        //    比如10和25，25除以10商2余5，那么10和25的最大公约数，等同于10和5的最大公约数。
        //    直至余数为0,也就是这两个数相等时，其中任一数为最大公约数。
        private int maxCommonDivisor(int a, int b) {

            int min = Math.min(a, b);
            int max = Math.max(a, b);

            while (max % min != 0) {

                int tempMin = max % min;
                max = min;
                min = tempMin;
            }
            return min;
        }


    }

}

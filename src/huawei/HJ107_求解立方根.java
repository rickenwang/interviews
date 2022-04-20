package huawei;

import java.util.Scanner;


//计算一个浮点数的立方根，不使用库函数。
//        保留一位小数。

public class HJ107_求解立方根 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            System.out.println(new Solution().resolve(Double.valueOf(s)));
        }
    }



    // 二分查找
    // 值的范围必然在 [0, max(v, 1)] 之间，负数情况另行考虑
    static class Solution {


        //
        public String resolve(double v) {

            // 简化
            double absV = Math.abs(v);

            double p = 0;
            while (p < absV || p <= 1) {

                if (cube(p) > absV) {
                    break;
                }
                p += 0.1;
            }

            if (Math.abs(cube(p) - absV) > Math.abs(cube(p-0.1) - absV)) {
                p -= 0.1;
            }

            if (v < 0) {
                p = -p;
            }

            return String.format("%.1f", p);
        }

        private double cube(double x) {
            return x*x*x;
        }
    }

}

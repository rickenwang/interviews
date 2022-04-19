package huawei;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// 给定一个正整数N代表火车数量，0<N<10，
// 接下来输入火车入站的序列，一共N辆火车，每辆火车以数字1-9编号，
// 火车站只有一个方向进出，同时停靠在火车站的列车中，只有后进站的出站了，先进站的才能出站。
//
// 要求输出所有火车出站的方案，以字典序排序输出。

public class HJ77_火车站进站 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            String[] ss = s.split(" ");
            int[] is = new int[ss.length];
            for (int i = 0; i < ss.length; i++) {
                is[i] = Integer.valueOf(ss[i]);
            }
            System.out.println(new Solution().solve(is));
        }
    }


    static class Solution {


        private String[] solve(String[] ids) {

            //
            List<String> recorder = new LinkedList<>();



        }

        /**
         *
         * @param stack 车站现有的火车
         * @param route 记录当前已经开出的火车
         * @param recorder 记录完整路径
         */
        private void dfs(List<String> stack, String route, List<String> recorder) {





        }


    }

}

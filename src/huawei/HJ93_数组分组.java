package huawei;


import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// 输入int型数组，询问该数组能否分成两组，使得两组中各元素加起来的和相等，并且，
// 所有5的倍数必须在其中一个组中，所有3的倍数在另一个组中（不包括5的倍数），
// 不是5的倍数也不是3的倍数能放在任意一组，
// 可以将数组分为空数组，能满足以上条件，输出true；不满足时输出false。

public class HJ93_数组分组 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            String s1 = in.nextLine();
            String[] ss = s1.split(" ");
            int[] is = new int[ss.length];
            for (int i = 0; i < ss.length; i++) {
                is[i] = Integer.valueOf(ss[i]);
            }
            System.out.println(new Solution().couldEqual(is));
        }
    }



    // 通过双指针的方式解决
    static class Solution {


        // 这里计算的是最长递增数字子串，和题目要求有点不一样
        public boolean couldEqual(int[] is) {

            int total = 0;
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < is.length; i++) {
                if (is[i] % 5 == 0) {
                    total += is[i];
                } else if (is[i] % 3 == 0) {
                    total -= is[i];
                } else {
                    list.add(is[i]);
                }
            }
            return couldEqual(list.toArray(new Integer[0]), 0, total);
        }


        private boolean couldEqual(Integer[] is, int start, int total) {

            if (is.length == 0) {
                return total == 0;
            }

            if (start == is.length - 1) {
                return total == is[start] || total == -is[start];
            }
            return couldEqual(is, start + 1, total - is[start]) || couldEqual(is, start + 1, total + is[start]);
        }
    }

}

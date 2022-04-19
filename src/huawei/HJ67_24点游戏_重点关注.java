package huawei;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//   给出4个1-10的数字，通过加减乘除运算，得到数字为24就算胜利,
//   除法指实数除法运算,运算符仅允许出现在两个数字之间,本题对数字选取顺序无要求，但每个数字仅允许使用一次，且需考虑括号运算
//   此题允许数字重复，如3 3 4 4为合法输入，此输入一共有两个3，但是每个数字只允许使用一次，则运算过程中两个3都被选取并进行对应的计算操作。

// 输入描述：
//        读入4个[1,10]的整数，数字允许重复，测试用例保证无异常数字。
//
//        输出描述：
//        对于每组案例，输出一行表示能否得到24点，能输出true，不能输出false
//
// 示例1
// 输入：
//        7 2 1 10
//
// 输出：
//        true
public class HJ67_24点游戏_重点关注 {

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
            System.out.println(new Solution().judgePoint24(is));
        }
    }


    // 解题思路参考：https://leetcode-cn.com/problems/24-game/solution/24-dian-you-xi-by-leetcode-solution/
    //
    //
    //
    public static boolean calculate24(int[] numbers) {


        return false;
    }


    // leetcode 官方解法
    static class Solution {
        static final int TARGET = 24;
        static final double EPSILON = 1e-6;
        static final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

        public boolean judgePoint24(int[] nums) {
            List<Double> list = new ArrayList<Double>();
            for (int num : nums) {
                list.add((double) num);
            }
            return solve(list);
        }

        public boolean solve(List<Double> list) {
            if (list.size() == 0) {
                return false;
            }
            if (list.size() == 1) {
                return Math.abs(list.get(0) - TARGET) < EPSILON;
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i != j) {
                        List<Double> list2 = new ArrayList<Double>();
                        for (int k = 0; k < size; k++) {
                            if (k != i && k != j) {
                                list2.add(list.get(k));
                            }
                        }
                        for (int k = 0; k < 4; k++) {
                            if (k < 2 && i > j) {
                                continue;
                            }
                            if (k == ADD) {
                                list2.add(list.get(i) + list.get(j));
                            } else if (k == MULTIPLY) {
                                list2.add(list.get(i) * list.get(j));
                            } else if (k == SUBTRACT) {
                                list2.add(list.get(i) - list.get(j));
                            } else if (k == DIVIDE) {
                                if (Math.abs(list.get(j)) < EPSILON) {
                                    continue;
                                } else {
                                    list2.add(list.get(i) / list.get(j));
                                }
                            }
                            if (solve(list2)) {
                                return true;
                            }
                            list2.remove(list2.size() - 1);
                        }
                    }
                }
            }
            return false;
        }
    }

}

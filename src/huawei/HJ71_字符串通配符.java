package huawei;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//   问题描述：在计算机中，通配符一种特殊语法，广泛应用于文件搜索、数据库、正则表达式等领域。现要求各位实现字符串通配符的算法。
//   要求：
//   实现如下2个通配符：
//   *：匹配0个或以上的字符（注：能被*和?匹配的字符仅由英文字母和数字0到9组成，下同）
//   ？：匹配1个字符
//
//注意：匹配时不区分大小写。
public class HJ71_字符串通配符 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String regx = scanner.nextLine();
            String string = scanner.nextLine();
            //做相应的替换
            regx = regx.replaceAll("\\?", "[0-9A-Za-z]{1}");
            regx = regx.replaceAll("\\*", "[0-9A-Za-z]{0,}");
            boolean result = string.matches(regx);
            System.out.println(result);
        }
    }



    public static boolean calculate24(int[] numbers) {


        return false;
    }


    // leetcode 官方解法
    // https://leetcode-cn.com/problems/24-game/solution/24-dian-you-xi-by-leetcode-solution/
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

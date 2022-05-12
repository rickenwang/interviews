package 算法.huawei;

// Catcher是MCA国的情报员，他工作时发现敌国会用一些对称的密码进行通信，
// 比如像这些ABBA，ABA，A，123321，但是他们有时会在开始或结束时加入一些无关的字符以防止别国破解。
// 比如进行下列变化 ABBA->12ABBA,ABA->ABAKK,123321->51233214　。
// 因为截获的串太长了，而且存在多种可能的情况
// （abaaab可看作是aba,或baaab的加密形式），
// Cathcer的工作量实在是太大了，他只能向电脑高手求助，你能帮Catcher找出最长的有效密码串吗？


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// 这个就是最长回文子序列
public class HJ32_密码截取_最长回文子序列_动态规划 {




    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String a = in.nextLine();
            System.out.println(maxSubString(a));
        }
    }

    // f(i, j) 表示起点为 i, 终点为 j 的最大回文子串
    // f(i, j) = f(i+1, j-1) + 2 如果 a[i]==a[j]
    // f(i, j) = max(f(i+1, j), f(i, j-1)) 如果 a[i]!=a[j]
    private static int maxConverse(String s) {


        return -1;
    }

    private static char[] converse(char[] nums) {
        char[] converse = new char[nums.length];
        for (int i = 0; i < nums.length; i++) {
            converse[i] = nums[nums.length-i-1];
        }
        return converse;
    }

    public static int maxSubString(String a) {

        char[] as = a.toCharArray();
        char[] bs = converse(as);
        int[][] f = new int[as.length][bs.length];

        Map<String, List<Integer>> recorder = new HashMap<>();

        int max = 0;
        for (int i = 0; i < bs.length; i++) {
            f[0][i] = as[0] == bs[i]? 1:0;
            if (max < f[0][i]) {
                max = f[0][i];
            }
        }
        for (int i = 0; i < as.length; i++) {
            f[i][0] = as[i] == bs[0]? 1:0;
            if (max < f[i][0]) {
                max = f[i][0];
            }
        }

        for (int i = 1; i < as.length; i++) {
            for (int j = 1; j < bs.length; j++) {

                if (as[i] == bs[j]) {
                    f[i][j] = f[i-1][j-1] + 1;
                } else {
                    f[i][j] = 0;
                }

                if (max < f[i][j]) {
                    max = f[i][j];
                }
            }
        }
        return max;
    }



}

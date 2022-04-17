package huawei;


import java.util.Scanner;

// 描述  最长公共子串
// 可以使用动态规划：
// f(m, n) = f(m-1, n-1) + 1  if a[m]==b[n]
// f(m, n) = 0  if a[m]!=b[n]

public class HJ75_最长公共子串 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String a = in.nextLine();
            String b = in.nextLine();
            System.out.println(maxSubString(a, b));
        }
    }


    // 对于字符序列 a 和 b
    // f[i][j] 表示以第 i 个字符结尾的 a 子串和以第 j 个字符结尾的 b 子串的最长公共子串的长度，那么有：
    // f[i][j] = f[i-1][j-1] + 1 如果 a[i]==b[j]
    public static int maxSubString(String a, String b) {

        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
        int[][] f = new int[as.length][bs.length];


        for (int i = 0; i < bs.length; i++) {
            f[0][i] = as[0] == bs[i]? 1:0;
        }
        for (int i = 0; i < as.length; i++) {
            f[i][0] = as[i] == bs[0]? 1:0;
        }

        for (int i = 1; i < as.length; i++) {
            for (int j = 1; j < bs.length; j++) {

                if (as[i] == bs[j]) {
                    f[i][j] = f[i-1][j-1] + 1;
                } else {
                    f[i][j] = 0;
                }
            }
        }

        // return max;
        return -1;
    }

}

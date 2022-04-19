package 动态规划;


import java.util.Scanner;

// 描述  最长公共子串
// 可以使用动态规划：
// f(m, n) = f(m-1, n-1) + 1  if a[m]==b[n]
// f(m, n) = 0  if a[m]!=b[n]

public class 最长公共子序列 {

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
    // f[i][j] 表示以第 i 个字符结尾的 a 子串和以第 j 个字符结尾的 b 子串的最长公共子序列的长度，那么有：
    // f[i][j] = f[i-1][j-1] + 1 如果 a[i]==b[j]
    // f[i][j] = max(f[i-1][j], f[i][j-1]) 如果 a[i]!=b[j]
    public static int maxSubString(String a, String b) {

        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
        int[][] f = new int[as.length+1][bs.length+1];

        for (int i = 1; i <= as.length; i++) {
            for (int j = 1; j <= bs.length; j++) {

                // 注意这里 as[i-1] 表示的是第 i 个字符
                if (as[i-1] == bs[j-1]) {
                    f[i][j] = f[i-1][j-1] + 1;
                } else {
                    f[i][j] = Math.max(f[i-1][j], f[i][j-1]);
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for (int i = 1; i <= as.length; i++) {
            for (int j = 1; j <= bs.length; j++) {
                result = Math.max(result, f[i][j]);
            }
        }

        return result;
    }

}

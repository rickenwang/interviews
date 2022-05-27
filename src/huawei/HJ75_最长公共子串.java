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

    // 最长公共子串
    // f[i, j] 表示 s1 第 i 个字符结尾，s2 第 j 个字符结尾的最长公共子串
    // f[i, j] = s1[i]==s[j]? f[i-1, j-1]+1: 0
    // 取 max

    // 最长公共子序列
    // f[i, j] = s1[i]==s[j]? f[i-1, j-1]+1: max(s1[i-1][j], s1[i][j-1])
    private static int max(String s1, String s2) {

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[][] f = new int[s1.length()][s2.length()];
        int result = 0;

        for (int i = 0; i < c1.length; i++) {

            for (int j = 0; j < c2.length; j++) {
                f[i][j] = c1[i] == c2[j] ? (i>0&&j>0? f[i-1][j-1]: 0) + 1: 0;

                result = Math.max(result, f[i][j]);
            }

        }
        return result;
    }

    // 对于字符序列 a 和 b
    // f[i][j] 表示以第 i 个字符结尾的 a 子串和以第 j 个字符结尾的 b 子串的最长公共子串的长度，那么有：
    // f[i][j] = f[i-1][j-1] + 1 如果 a[i]==b[j]
    // f[i][j] = 0 如果 a[i]!=b[j]
    public static int maxSubString(String a, String b) {

        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
        int[][] f = new int[as.length+1][bs.length+1];

        for (int i = 1; i <= as.length; i++) {
            for (int j = 1; j <= bs.length; j++) {

                if (as[i-1] == bs[j-1]) {
                    f[i][j] = f[i-1][j-1] + 1;
                } else {
                    f[i][j] = 0;
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

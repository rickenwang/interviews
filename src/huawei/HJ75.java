package huawei;


import java.util.Scanner;

// 描述  最长公共子串
// 可以使用动态规划：
// f(m, n) = f(m-1, n-1) + 1  if a[m]==b[n]
// f(m, n) = 0  if a[m]!=b[n]

public class HJ75 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String a = in.nextLine();
            String b = in.nextLine();
            System.out.println(maxSubString(a, b));
        }
    }


    public static int maxSubString(String a, String b) {

        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
        int[][] f = new int[as.length][bs.length];

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

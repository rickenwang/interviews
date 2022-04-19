package huawei;


import java.util.Scanner;

// 请计算n*m的棋盘格子（n为横向的格子数，m为竖向的格子数）从棋盘左上角出发沿着边缘线从左上角走到右下角，
// 总共有多少种走法，要求不能走回头路，即：只能往右和往下走，不能往左和往上走。

public class HJ91_走方格的方案数 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            String[] ss = s.split(" ");
            System.out.println(step(Integer.valueOf(ss[0]), Integer.valueOf(ss[1])));
        }
    }


    // 对于 n * m 的棋盘
    //
    // f[i][j] 从 [0,0] 到 [i,j] 的所有走法。
    // f[i][j] = max(f[i][j-1], f[i-1][j]) + 1

    public static int step(int m, int n) {

        int[][] f = new int[m+1][n+1];

        for (int i = 1; i <= n; i++) {
            f[0][i] = 1;
        }
        for (int i = 1; i <= m; i++) {
            f[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                f[i][j] = f[i][j-1] + f[i-1][j];
            }
        }
        return f[m][n];
    }

}

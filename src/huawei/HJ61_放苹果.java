package huawei;


import java.util.Scanner;

//  把m个同样的苹果放在n个同样的盘子里，允许有的盘子空着不放，问共有多少种不同的分法？
//  注意：如果有7个苹果和3个盘子，（5，1，1）和（1，5，1）被视为是同一种分法。

public class HJ61_放苹果 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            String[] ss = s.split(" ");
            System.out.println(countApplePosition(Integer.valueOf(ss[0]), Integer.valueOf(ss[1])));
        }
    }


    // 对于 m 个苹果放在 n 个盘子
    //
    // f[i][j] 表示 i 个苹果放在 j 个盘子的总方法数
    // f[i][j] = sum(f[i][j-1], f[i-j][j])    i>=j
    // f[i][j] = f[i][i]   i<j
    public static int countApplePosition(int m, int n) {

        int[][] f = new int[m+1][n+1];

        for (int i = 0; i <= n; i++) {
            f[0][i] = 1;
        }

        // 初始值
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (i < j) {
                    f[i][j] = f[i][i];
                } else {
                    f[i][j] = f[i][j-1] + f[i-j][j];
                }
            }
        }

        return f[m][n];
    }

}

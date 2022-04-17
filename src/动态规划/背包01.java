package 动态规划;

public class 背包01 {

    //
    public static void main(String[] args) {


        System.out.println(simplePackage(3, 10, new int[]{1,2,3}, new int[]{1,2,3}));
    }


    // 分析
    // f[i, w] = max(f[i-1, w], f[i-1, w-ws[i]] + vs[i])
    // 其中 f[i, w] 表示前 i 件物品，限重 w 时的最多价值
    // f[0, w] 表示第1件物品限重 w 时的最多价值。
    private static int simplePackage(int n, int weight, int[] ws, int[] vs) {

        int[][] f = new int[n][weight];
        for (int w = 0; w < weight; w++) {
            if (w >= ws[0]) {
                f[0][w] = vs[0];
            }
        }
        for (int i = 1; i < n; i++) {
            for (int w = 0; w < weight; w++) {
                if (w >= ws[i]) {
                    f[i][w] = Math.max(f[i-1][w], f[i-1][w-ws[i]] + vs[i]);
                } else {
                    f[i][w] = f[i-1][w];
                }
            }
        }
        return f[n][weight];
    }

}

package honor;

// 小明装修需要n（1<=n<=200）颗钉子，但是五金店没有散装钉子卖，
// 只有两种盒装包装的，一种包装4颗，一种包装有9颗，
// 请问小明最少需要买多少盒钉子才能刚好买够n颗？

// 简单算法：9 颗钉子尽量多，然后每次减少 1 个 9盒，尝试用 4 整除
// 动态规划：f(n) = Min(f(n-4), f(n-9)) + 1
public class BuyDing {


    public static void main(String[] args) {
        System.out.println(countDing(20));
    }


    // f(n) = Min(f(n-4) + 1, f(n-9) + 1)
    private static int countDing(int n) {

        int[] records = new int[n + 1];
        for (int i = 1; i <= n; i++) {

            if (i < 4) {
                records[i] = -1;
            } else if (i < 9) {
                records[i] = records[i-4] != -1? records[i-4] + 1: -1;
            } else {
                int r4 = records[i-4];
                int r9 = records[i-9];
                if (r4 == -1 && r9 == -1) {
                    records[i] = -1;
                } else if (r4 != -1 && r9 == -1) {
                    records[i] = r4 + 1;
                } else if (r4 == -1 && r9 != -1) {
                    records[i] = r9 + 1;
                } else {
                    records[i] = Math.min(r4, r9) + 1;
                }
            }
        }

        return records[n];
    }

}

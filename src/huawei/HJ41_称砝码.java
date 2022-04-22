package huawei;

import java.util.Scanner;

// 现有n种砝码，重量互不相等，分别为 m1,m2,m3…mn ；
// 每种砝码对应的数量为 x1,x2,x3...xn 。
// 现在要用这些砝码去称物体的重量(放在同一侧)，问能称出多少种不同的重量。
//
//  注： 称重重量包括 0

// 砝码重量为 w[i]，个数为 c[i]
//
// 显然最多能称重 max 是容易计算的，初始化一个 boolean[max+1] 的的数组，表示对应下标的重量是否能称出；
// 每次放置砝码时，依次置位能称出的对应位置
//
// f[i][j] 表示第 i 个砝码放置 j 个带来的额外重量增量，
//

public class HJ41_称砝码 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            int n = Integer.valueOf(s);
            int[] ws = new int[n];
            int[] cs = new int[n];
            String[] wss = in.nextLine().split(" ");
            String[] css = in.nextLine().split(" ");
            for (int i = 0; i < n; i ++) {
                ws[i] = Integer.valueOf(wss[i]);
            }
            for (int i = 0; i < n; i ++) {
                cs[i] = Integer.valueOf(css[i]);
            }
            System.out.println(weights(ws, cs));
        }
    }


    private static int weights(int[] ws, int[] cs) {

        int max = 0;
        for (int i = 0; i < ws.length; i++) {
            max += ws[i] * cs[i];
        }
        boolean[] canWeights = new boolean[max + 1];
        canWeights[0] = true;

        // i 表示第 i+1 个砝码
        // j 表示第 i+1 个砝码共放置 j+1 个
        for (int i = 0; i < ws.length; i++) {

            for (int j = 0; j < cs[i]; j++) {

                // 注意这里要先记录当前状态
                boolean[] temp = new boolean[max + 1];
                System.arraycopy(canWeights, 0, temp, 0, canWeights.length);

                for (int k = 0; k < canWeights.length; k++) {
                    if (temp[k]) {
                        canWeights[k+ws[i]] = true;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < canWeights.length; i++) {
            if (canWeights[i]) {
                result++;
            }
        }
        return result;
    }
}

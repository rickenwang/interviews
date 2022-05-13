package huawei;


import java.util.Scanner;
// Redraiment是走梅花桩的高手。Redraiment可以选择任意一个起点，
// 从前到后，但只能从低处往高处的桩子走。他希望走的步数最多，
// 你能替Redraiment研究他最多走的步数吗？
public class HJ103_最长上升子序列 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String c = in.nextLine();
            String[] ss = in.nextLine().split(" ");
            int[] is = new int[ss.length];
            for (int i = 0; i < ss.length; i++) {
                is[i] = Integer.valueOf(ss[i]);
            }
            System.out.println(min(is));
        }
    }


    // 最长上升子序列
    // f[i] = max(f[j] + a[i]>a[j]? 1+a[j]:1)  0<j<i
    private static int min(int[] cs) {

        int[] f = new int[cs.length];
        f[0] = 1;

        for (int i = 1; i < cs.length; i++) {

            int max = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                max = Math.max(max, (cs[i] > cs[j]? f[j] + 1:1));
            }
            f[i] = max;
        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < f.length; i++) {
            result = Math.max(result, f[i]);
        }
        return result;
    }

    // 最长上升子串
    // f[i] = a[i] > a[i-1] ? f[i-1]+1: 1
    private static int min2(int[] cs) {

        int[] f = new int[cs.length];
        f[0] = 1;

        for (int i = 1; i < cs.length; i++) {

            int max = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                max = Math.max(max, (cs[i] > cs[j]? f[j] + 1:1));
            }
            f[i] = max;
        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < f.length; i++) {
            result = Math.max(result, f[i]);
        }
        return result;
    }


    // f[i] 表示以第 i 个字符结尾的最长上升子序列
    // 那么有
    // f[i] = f[i-1] + 1;   f[i]>f[i-1]
    // f[i] = f[i-1]; f[i] <= f[i-1]
    // 最终结尾为 max(f[i])
    private static int maxUpperSubString(int[] cs) {
        int[] f = new int[cs.length];
        f[0] = 1;

        // i 表示第 i-1 个字符，从 0 开始
        for (int i = 1; i < f.length; i++) {

            f[i] = 1;
            for (int j = 0; j < i; j++) {
                int fj = cs[i] > cs[j]? f[j] + 1: 1;
                f[i] = Math.max(fj, f[i]);
            }
        }
        int result = 1;
        for (int i = 0; i < f.length; i++) {
            result = Math.max(result, f[i]);
        }
        return result;
    }

}

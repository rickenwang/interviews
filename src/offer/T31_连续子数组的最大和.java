package offer;

import sun.nio.cs.ext.MacHebrew;

public class T31_连续子数组的最大和 {


    public static void main(String[] args) {

        int[] arr = new int[] {
                1, -2, 3, 10, -4, 7, 2, -5
        };
        System.out.print(max(arr));

    }


    // f[i] 表示以第 i 结尾的最大和
    // f[i] = max(a[i], f[i-1]+a[i])
    private static int max(int[] arr) {

        int[] f = new int[arr.length];
        f[0] = arr[0];
        int result = f[0];

        for (int i = 1; i < arr.length; i++) {
            f[i] = Math.max(f[i-1] + arr[i], arr[i]);
            result = Math.max(result, f[i]);
        }
        return result;
    }


}

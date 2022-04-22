package huawei;


import java.util.LinkedList;
import java.util.Scanner;

//  杨辉三角

public class HJ53_杨辉三角 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String a = in.nextLine();
            System.out.println(yangHui(Integer.valueOf(a)));
        }
    }


    // f[i][j] = f[i-1][j-2] + f[i-1][j-1] + f[i-1][j]
    // -1, -1, (2, 3, 2, 4), (2, 3, 2, 4)
    private static int yangHui(int n) {


        if (n == 1 || n == 2) {
            return -1;
        }
        int[] results = new int[] {
                2, 3, 2, 4
        };

        return results[(n-3) % 4];
    }
}

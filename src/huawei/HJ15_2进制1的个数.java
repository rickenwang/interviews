package huawei;

import java.util.Scanner;

//
// 输入一个 int 型的正整数，计算出该 int 型数据在内存中存储时 1 的个数。

public class HJ15_2进制1的个数 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            countBit1(Integer.valueOf(s));
        }

    }

    private static void countBit1(int s) {

        int count = 0;
        while (s > 0) {
            if (s % 2 == 1) {
                count++;
            }
            s = s >> 1;
        }
        System.out.println(count);
    }
}

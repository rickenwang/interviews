package huawei;

//
// 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
//小张手上有n个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水。

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class HJ22_汽水瓶 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            List<Integer> cs = new LinkedList<>();
            String s = in.nextLine();
            if (!s.equals("0")) {
                cs.add(Integer.valueOf(s));
            } else {
                break;
            }
            countAll(cs);
        }


    }

    private static void countAll(List<Integer> cs) {
        for (Integer i: cs) {
            count(i);
        }
    }

    // f[n] 表示有 n 个空瓶时，最多可以换取的汽水数
    // 那么假设 n >=3，那么用 3 个空瓶换回一个汽水后的状态为 f[n-2] + 1【表示有 n-2 个瓶子，多喝了一瓶汽水】，所以
    // f[n] = f[n-2] + 1
    private static void count(int c) {

        int[] f = new int[c+1];
        f[1] = 0;
        f[2] = 1;
        for (int i = 3; i <= c; i++) {
            f[i] = f[i-2] + 1;
        }
        System.out.println(f[c]);
    }

}

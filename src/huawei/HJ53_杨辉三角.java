package huawei;


import java.util.LinkedList;
import java.util.Scanner;

//  Levenshtein 距离，又称编辑距离，指的是两个字符串之间，由一个转换成另一个所需的最少编辑操作次数。
//  许可的编辑操作包括：
//  1. 将一个字符替换成另一个字符，
//  2. 插入一个字符，
//  3. 删除一个字符。
//
//  编辑距离的算法是首先由俄国科学家 Levenshtein 提出的，故又叫 Levenshtein Distance 。
//
//        例如：
//
//        字符串A: abcdefg
//
//        字符串B: abcdef
//
//        通过增加或是删掉字符 ”g” 的方式达到目的。这两种方案都需要一次操作。把这个操作所需要的次数定义为两个字符串的距离。
//
//        要求：
//
//        给定任意两个字符串，写出一个算法计算它们的编辑距离。

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

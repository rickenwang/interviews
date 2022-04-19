package huawei;


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

public class HJ52_计算字符串的编辑距离 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String a = in.nextLine();
            String b = in.nextLine();
            System.out.println(stringDistance(a, b));
        }
    }


    // 对于字符串 a 和 b
    // f[i][j] 表示以第 i 个字符结尾的 a 子串和以第 j 个字符结尾的 b 子串的编辑距离
    // f[i][j] = f[i-1][j-1]   如果 a[i]==b[j]
    // f[i][j] = min(f[i-1][j], f[i][j-1], f[i-1][j-1])   如果 a[i]!=b[j]  // 分别对应删除 a[i], 删除 b[j] 和修改
    public static int stringDistance(String a, String b) {

        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
        int[][] f = new int[as.length+1][bs.length+1];

        // 初始值
        for (int i = 1; i <= as.length; i++) {
            f[i][0] = i;
        }
        for (int i = 1; i <= bs.length; i++) {
            f[0][i] = i;
        }

        for (int i = 1; i <= as.length; i++) {
            for (int j = 1; j <= bs.length; j++) {

                // 注意这里 as[i-1] 表示的是第 i 个字符
                if (as[i-1] == bs[j-1]) {
                    f[i][j] = f[i-1][j-1];
                } else {
                    f[i][j] = Math.min(Math.min(f[i-1][j], f[i][j-1]), f[i-1][j-1]) + 1;
                }
            }
        }

        return f[as.length][bs.length];
    }

}

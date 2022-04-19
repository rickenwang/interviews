package huawei;


import java.math.BigInteger;
import java.util.Scanner;

// 输入两个用字符串 str 表示的整数，求它们所表示的数之和。

public class HJ57_高精度整数加法 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String a = in.nextLine();
            String b = in.nextLine();
            System.out.println(add(a, b));
        }
    }


    // 对于字符序列 a 和 b
    // f[i][j] 表示以第 i 个字符结尾的 a 子串和以第 j 个字符结尾的 b 子串的最长公共子串的长度，那么有：
    // f[i][j] = f[i-1][j-1] + 1 如果 a[i]==b[j]
    // f[i][j] = 0 如果 a[i]!=b[j]
    public static String add(String a, String b) {

        BigInteger i1 = new BigInteger(a);
        BigInteger i2 = new BigInteger(b);
        return i1.add(i2).toString();
    }

}

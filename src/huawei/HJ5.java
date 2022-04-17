package huawei;


import java.util.*;

//描述
//        写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
//
//        数据范围：保证结果在 1 \le n \le 2^{31}-1 \1≤n≤2
//        31
//        −1
//        输入描述：
//        输入一个十六进制的数值字符串。
//
//        输出描述：
//        输出该数值的十进制字符串。不同组的测试用例用\n隔开。
//
//        示例1
//        输入：
//        0xAA
//        复制
//        输出：
//        170

public class HJ5 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            System.out.println(convert16To10(s));
        }
    }

    public static long convert16To10(String s) {

        char[] cs = s.substring(2).toLowerCase().toCharArray();
        long result = 0;
        for (char c: cs) {
            int delta = 0;
            if (c <= '9' && c >= '0') {
                delta = c - '0';
            } else if (c >= 'a' && c <= 'z') {
                delta = c - 'a' + 10;
            }
            result = result * 16 + delta;
        }
        return result;
    }

}

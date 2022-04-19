package huawei;

import java.util.Scanner;

//
//  输入一个字符串，请按长度为8拆分每个输入字符串并进行输出；
//  长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
//
public class HJ4_字符串分隔 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            split8(s);
        }
    }

    private static void split8(String s) {

        int len = s.length();
        int extra = len % 8 == 0? 0: 8-len%8;
        for (int i = 0; i < extra; i++) {
            s += "0";
        }

        for (int i = 0; i < s.length()/8; i++) {
            System.out.println(s.substring(i*8, i*8+8));
        }
    }

}

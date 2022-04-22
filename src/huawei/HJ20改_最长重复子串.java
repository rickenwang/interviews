package huawei;

//
//

import java.util.Scanner;

public class HJ20改_最长重复子串 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            System.out.println(maxRepeatSubStr(s));
        }
    }

    // 1. 最简单的解法就是 O(n3) 应该还可以接受吧
    private static int maxRepeatSubStr(String s) {

        char[] cs = s.toCharArray();

        for (int len = s.length() / 2; len > 0; len--) {
            for (int i = 0; i <= s.length() - len * 2; i++) {
                for (int j = i + len; j <= cs.length - len; j++) {
                    if (s.substring(i, i+len).equals(s.substring(j, j+len))) {
                        return len;
                    }
                }
            }
        }
        return 0;
    }
}

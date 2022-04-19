package huawei;

//
// 输入一个 int 型的正整数，计算出该 int 型数据在内存中存储时 1 的个数。

import java.util.Scanner;

public class HJ20_密码验证合格程序 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            System.out.println(verifyPassword(s)? "OK":"NG");
        }


    }

    private static boolean verifyPassword(String password) {

        return password.length() > 8 && !hasCommonSub3(password) && has3ClassChar(password);
    }

    private static boolean has3ClassChar(String password) {
        boolean hasLowerLetter = false;
        boolean hasUpperLetter = false;
        boolean hasNumber = false;
        boolean hasOther = false;

        char[] cs = password.toCharArray();
        for (char c: cs) {

            if (c <= 'z' && c >= 'a') {
                hasLowerLetter = true;
            } else if (c <= 'Z' && c >= 'A') {
                hasUpperLetter = true;
            } else if (c <= '9' && c >= '0') {
                hasNumber = true;
            } else {
                hasOther = true;
            }
        }
        int classCount = 0;
        if (hasLowerLetter) {
            classCount++;
        }
        if (hasUpperLetter) {
            classCount++;
        }
        if (hasNumber) {
            classCount++;
        }
        if (hasOther) {
            classCount++;
        }

        return classCount >= 3;
    }


    // 是否有长度为3的重复子串，无公共部分
    private static boolean hasCommonSub3(String s) {

        char[] cs = s.toCharArray();
        if (cs.length < 6) {
            return false;
        }

        for (int i = 0; i <= cs.length - 6; i++) {
            for (int j = i + 3; j <= cs.length - 3; j++) {
                if (s.substring(i, i+3).equals(s.substring(j, j+3))) {
                    return true;
                }
            }
        }
        return false;
    }
}

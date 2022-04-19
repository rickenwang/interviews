package huawei;

//
// 输入一个 int 型的正整数，计算出该 int 型数据在内存中存储时 1 的个数。

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

    private static void count(int c) {

        int[] f = new int[c+1];
        f[1] = 0;
        f[2] = 1;
        for (int i = 3; i <= c; i++) {
            f[i] = f[i-2] + 1;
        }
        System.out.println(f[c]);
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

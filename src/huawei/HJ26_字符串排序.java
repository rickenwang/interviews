package huawei;

//
// 输入一个 int 型的正整数，计算出该 int 型数据在内存中存储时 1 的个数。

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class HJ26_字符串排序 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            System.out.println(order(s));
        }
    }


    private static String order(String s) {

        //
        LinkedList<Character> orderS = new LinkedList<>();
        char[] cs = s.toCharArray();

        char[] signCs = new char[cs.length];
        for (int i = 0; i < cs.length; i++) {
            if (!(cs[i] <= 'Z' && cs[i] >= 'A') && !(cs[i] <= 'z' && cs[i] >= 'a')) {
                signCs[i] = cs[i];
            } else {
                orderS.add(cs[i]);
            }
        }

        orderS.sort(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                int i1 = 0;
                int i2 = 0;
                if (o1 <= 'Z' && o1 >= 'A') {
                    i1 = o1 - 'A';
                } else {
                    i1 = o1 - 'a';
                }
                if (o2 <= 'Z' && o2 >= 'A') {
                    i2 = o2 - 'A';
                } else {
                    i2 = o2 - 'a';
                }
                return i1 - i2 ;
            }
        });
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (signCs[i] > 0) {
                result.append(signCs[i]);
            } else {
                result.append(orderS.removeFirst());
            }

        }
        return result.toString();
    }


}

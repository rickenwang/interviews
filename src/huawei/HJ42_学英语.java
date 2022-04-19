package huawei;

import java.util.Scanner;

// 现有n种砝码，重量互不相等，分别为 m1,m2,m3…mn ；
// 每种砝码对应的数量为 x1,x2,x3...xn 。
// 现在要用这些砝码去称物体的重量(放在同一侧)，问能称出多少种不同的重量。
//
//  注： 称重重量包括 0

// 砝码重量为 w[i]，个数为 c[i]
//
// 显然最多能称重 max 是容易计算的，初始化一个 boolean[max+1] 的的数组，表示对应下标的重量是否能称出；
// 每次放置砝码时，依次置位能称出的对应位置
//
// f[i][j] 表示第 i 个砝码放置 j 个带来的额外重量增量，
//

public class HJ42_学英语 {

    private static String hundred = "hundred";
    private static String thousand = "thousand";
    private static String million = "million";
    private static String and = " and ";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            int n = Integer.valueOf(s.replaceAll(",", ""));
            System.out.println(translate(n).replaceAll("  ", " "));
        }
    }


    private static String translate(int number) {

        if (number < 20) {
            return number0_19(number);
        } else if (number < 100) {

            int b1 = number / 10;
            int b2 = number % 10;
            return numberTenMultiple(b1 * 10) + " " + (b2 != 0? number0_19(b2): "");
        } else if (number < 1000) {
            int b1 = number / 100;
            return number0_19(b1) + " " + hundred + ((number % 100 != 0)? and + translate(number % 100): "");
        } else if (number < 10_000) {
            int b1 = number / 1000;
            return number0_19(b1) + " " + thousand + ((number % 1000 != 0)? " " + translate(number % 1000): "");
        } else if (number < 1_000_000) {
            return translate(number / 1000) + " " + thousand + ((number % 1000 != 0)? " " + translate(number % 1000): "");
        } else if (number < 10_000_000) {
            return translate(number / 1_000_000) + " " + million + ((number % 1_000_000 != 0)? " " + translate(number % 1_000_000): "");
        }
        return "";
    }

    private static String number0_19(int i) {

        String result = "";
        switch (i) {
            case 0: result = "zero"; break;
            case 1: result = "one"; break;
            case 2: result = "two"; break;
            case 3: result = "three"; break;
            case 4: result = "four"; break;
            case 5: result = "five"; break;
            case 6: result = "six"; break;
            case 7: result = "seven"; break;
            case 8: result = "eight"; break;
            case 9: result = "nine"; break;
            case 10: result = "ten"; break;
            case 11: result = "eleven"; break;
            case 12: result = "twelve"; break;
            case 13: result = "thirteen"; break;
            case 14: result = "fourteen"; break;
            case 15: result = "fifteen"; break;
            case 16: result = "sixteen"; break;
            case 17: result = "seventeen"; break;
            case 18: result = "eighteen"; break;
            case 19: result = "nineteen"; break;

        }

        return result;
    }

    private static String numberTenMultiple(int i) {

        String result = "";
        switch (i) {
            case 20: result = "twenty"; break;
            case 30: result = "thirty"; break;
            case 40: result = "forty"; break;
            case 50: result = "fifty"; break;
            case 60: result = "sixty"; break;
            case 70: result = "seventy"; break;
            case 80: result = "eighty"; break;
            case 90: result = "ninety"; break;
        }

        return result;
    }
}

package huawei;

import java.util.Scanner;

// essi初学英语，为了快速读出一串数字，编写程序将数字转换成英文：
//
//  具体规则如下:
//  1.在英语读法中三位数字看成一整体，后面再加一个计数单位。从最右边往左数，三位一单位，例如12,345 等
//  2.每三位数后记得带上计数单位 分别是thousand, million, billion.
//  3.公式：百万以下千以上的数 X thousand X, 10亿以下百万以上的数：X million X thousand X, 10 亿以上的数：X billion X million X thousand X. 每个X分别代表三位数或两位数或一位数。
//  4.在英式英语中百位数和十位数之间要加and，美式英语中则会省略，我们这个题目采用加上and，百分位为零的话，这道题目我们省略and

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

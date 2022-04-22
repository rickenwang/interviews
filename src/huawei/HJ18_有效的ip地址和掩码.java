package huawei;

import java.util.LinkedHashMap;
import java.util.Scanner;

//
//

public class HJ18_有效的ip地址和掩码 {


    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        // 注意 hasNext 和 hasNextLine 的区别
//        while (in.hasNextLine()) { // 注意 while 处理多个 case
//            String s = in.nextLine();
//            countBit1(Integer.valueOf(s));
//        }
        System.out.println(isValidCover("127.255.255.0"));

    }

    private static boolean isInRange(String ip, String start, String end) {

        String[] ips = ip.split("\\.");
        String[] starts = start.split("\\.");
        String[] ends = end.split("\\.");

        if (ips.length != 4 || starts.length != 4 || ends.length != 4) {
            throw new IllegalArgumentException("");
        }

        for (int i = 0; i < 4; i++) {


        }
        // LinkedHashMap<> map ;
        return false;
    }

    // 是否为一个有效的掩码，抛出异常说明格式不对
    private static boolean isValidCover(String cover) {

        String[] cs = cover.split("\\.");
        if (cs.length != 4) {
            throw new IllegalArgumentException("");
        }

        long coverL = 0;
        for (String c: cs) {
            coverL = 256 * coverL + Integer.valueOf(c);
        }
        boolean has1 = false;
        for (int i = 0; i < 32; i++) {
            if (coverL % 2 == 1) {
                has1 = true;
            } else if (has1) {
                return false;
            }
            coverL = coverL >> 1;
        }
        return true;

    }
}

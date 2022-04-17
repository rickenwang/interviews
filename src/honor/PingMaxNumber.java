package honor;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * description:有多个整数，求这些数能拼接成为的最大数。
 *
 * 例：9 990 22 987
 *
 * 输出：9->990->987->22
 */

public class PingMaxNumber {


    public static void main(String[] args) {

        List<String> numbers = new LinkedList<>();
        numbers.add("9");
        numbers.add("990");
        numbers.add("22");
        numbers.add("987");
        System.out.println(new PingMaxNumber().pingMaxNumber(numbers));

    }

    public String pingMaxNumber(List<String> numbers) {


        numbers.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return sort(o1, o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String i: numbers) {
            sb.append(i);
        }
        return sb.toString();
    }


    private int sort(String o1, String o2) {

        char[] cs1 = o1.toCharArray();
        char[] cs2 = o2.toCharArray();

        int len = Math.min(cs1.length, cs2.length);
        for (int i = 0; i< len; i++) {
            if (cs1[i] > cs2[i]) {
                return 1;
            } else if (cs1[i] < cs2[i]){
                return -1;
            }
        }
        return cs2.length - cs1.length;
    }

}

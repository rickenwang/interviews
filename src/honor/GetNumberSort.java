package honor;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

// 给一个字符串，获取字符串中的数字并排序，输出字符串。
public class GetNumberSort {

    public static void main(String[] args) {

        System.out.println(new GetNumberSort().filterNumberAndSort("d46dbasud12fdstfr"));

    }


    public String filterNumberAndSort(String s) {
        char[] cs = s.toCharArray();
        List<Integer> list = new LinkedList<>();
        for (char c: cs) {
            if (c <= '9' && c >= '0') {
                list.add(c - '0');
            }
        }
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i: list) {
            sb.append(i);
        }
        return sb.toString();
    }

}

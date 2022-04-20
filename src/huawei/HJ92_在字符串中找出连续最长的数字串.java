package huawei;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//  输入一个字符串，返回其最长的数字子串，以及其长度。若有多个最长的数字子串，则将它们全部输出（按原字符串的相对位置）
//  本题含有多组样例输入。

//   输入：
//        abcd12345ed125ss123058789
//        a8a72a6a5yy98y65ee1r2
//        复制
//        输出：
//        123058789,9
//        729865,2
//        复制
//        说明：
//        样例一最长的数字子串为123058789，长度为9
//        样例二最长的数字子串有72,98,65，长度都为2

public class HJ92_在字符串中找出连续最长的数字串 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            System.out.println(new Solution().findLongest(s));
        }
    }



    // 通过双指针的方式解决
    static class Solution {


        // 这里计算的是最长递增数字子串，和题目要求有点不一样
        public String findLongest(String s) {

            char[] cs = s.toCharArray();
            int start = 0; // 当前子串的起点，包括
            int end = 0; // 当前子串的终点，包括

            List<String> recorder = new LinkedList<>();
            int maxLength = 0;

            // 2 1 3
            // 1 2 3
            for (int i = 0; i < cs.length - 1; i++) {

                boolean add = false;
                if (cs[i+1] > cs[i] && cs[i+1] <= '9' && cs[i+1] >= '0' && cs[i] <= '9' && cs[i] >= '0') {
                    add = true;
                    end++;
                }
                if (!add || i == cs.length - 2) {

                    int len = end - start + 1;
                    if (len > maxLength) {
                        maxLength = len;
                        recorder.clear();
                        recorder.add(s.substring(start, end + 1));
                    } else if (len == maxLength) {
                        recorder.add(s.substring(start, end + 1));
                    }
                    start = i + 1;
                    end = i + 1;
                }
            }


            StringBuilder result = new StringBuilder();
            for (String r: recorder) {
                result.append(r);
            }
            result.append(",").append(recorder.get(0).length());
            return result.toString();
        }
    }

}

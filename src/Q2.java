import java.util.Scanner;

public class Q2 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            System.out.println(new Solution().solve(s));
        }
    }

    static class Solution {

        public boolean solve(String s) {
            return false;
        }
    }



}

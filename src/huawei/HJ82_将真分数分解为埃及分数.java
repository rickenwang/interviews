package huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//   分子为1的分数称为埃及分数。现输入一个真分数(分子比分母小的分数，叫做真分数)，请将该分数分解为埃及分数。如：8/11 = 1/2+1/5+1/55+1/110。
//        注：真分数指分子小于分母的分数，分子和分母有可能gcd不为1！
//        如有多个解，请输出任意一个。

public class HJ82_将真分数分解为埃及分数 {

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        // 注意 hasNext 和 hasNextLine 的区别
//        while (in.hasNextLine()) { // 注意 while 处理多个 case
//            String s = in.nextLine();
//            System.out.println(new Solution().solve(s));
//        }
//    }



    // 解法：
    // 对于真分数  a/b，将 b 拆分为 b=a*p+r，a/b = a/(a*p+r) = (a*p+r-r+a)/((a*p+r)*(p+1)) = 1/(p+1) + (a-r)/((a*p+r)*(p+1)) 继续拆分，直到 a-r = 1
    //
    static class Solution {


        private String solve(String expr) {

            return "";
        }

    }


    // copy
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str = br.readLine())!=null) {
            String[] ss = str.split("/");
            long a = Integer.parseInt(ss[0]);
            long b = Integer.parseInt(ss[1]);
            long t;
            StringBuilder sb = new StringBuilder();
            while(a != 0) {
                t = b/a + (b%a==0?0:1);
                if (b % t == 0 && a >= t) {
                    a -= b/t;
                    sb.append('1').append('/').append(t).append('+');
                } else {
                    a = a * t - b;
                    b = b * t;    // 这里可能溢出，所以都用long
                    if(a!=0)sb.append('1').append('/').append(t).append('+');
                    else sb.append('1').append('/').append(t);
                }
                if(a == 1) {
                    sb.append('1').append('/').append(b);
                    break;
                }
            }
            System.out.println(sb);

        }
    }
}

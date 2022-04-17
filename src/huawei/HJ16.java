package huawei;


import java.util.Scanner;

//描述
//        王强决定把年终奖用于购物，他把想买的物品分为两类：主件与附件，附件是从属于某个主件的，下表就是一些主件与附件的例子：
//        主件	附件
//        电脑	打印机，扫描仪
//        书柜	图书
//        书桌	台灯，文具
//        工作椅	无
//        如果要买归类为附件的物品，必须先买该附件所属的主件，且每件物品只能购买一次。
//        每个主件可以有 0 个、 1 个或 2 个附件。附件不再有从属于自己的附件。
//        王强查到了每件物品的价格（都是 10 元的整数倍），而他只有 N 元的预算。除此之外，他给每件物品规定了一个重要度，用整数 1 ~ 5 表示。他希望在花费不超过 N 元的前提下，使自己的满意度达到最大。
//        满意度是指所购买的每件物品的价格与重要度的乘积的总和，假设设第ii件物品的价格为v[i]v[i]，重要度为w[i]w[i]，共选中了kk件物品，编号依次为j_1,j_2,...,j_kj
//        1
//        ​
//        ,j
//        2
//        ​
//        ,...,j
//        k
//        ​
//        ，则满意度为：v[j_1]*w[j_1]+v[j_2]*w[j_2]+ … +v[j_k]*w[j_k]v[j
//        1
//        ​
//        ]∗w[j
//        1
//        ​
//        ]+v[j
//        2
//        ​
//        ]∗w[j
//        2
//        ​
//        ]+…+v[j
//        k
//        ​
//        ]∗w[j
//        k
//        ​
//        ]。（其中 * 为乘号）
//        请你帮助王强计算可获得的最大的满意度。
//
//
//        输入描述：
//        输入的第 1 行，为两个正整数N，m，用一个空格隔开：
//
//        （其中 N （ N<32000 ）表示总钱数， m （m <60 ）为可购买的物品的个数。）
//
//
//        从第 2 行到第 m+1 行，第 j 行给出了编号为 j-1 的物品的基本数据，每行有 3 个非负整数 v p q
//
//
//        （其中 v 表示该物品的价格（ v<10000 ）， p 表示该物品的重要度（ 1 ~ 5 ）， q 表示该物品是主件还是附件。如果 q=0 ，表示该物品为主件，如果 q>0 ，表示该物品为附件， q 是所属主件的编号）
//
//
//
//
//        输出描述：
//        输出一个正整数，为张强可以获得的最大的满意度。
//        示例1
//        输入：
//        1000 5
//        800 2 0
//        400 5 1
//        300 5 1
//        400 3 0
//        500 2 0
//        复制
//        输出：
//        2200
//        复制
//        示例2
//        输入：
//        50 5
//        20 3 5
//        20 3 5
//        10 3 0
//        10 2 0
//        10 1 0
//        复制
//        输出：
//        130
//        复制
//        说明：
//        由第1行可知总钱数N为50以及希望购买的物品个数m为5；
//        第2和第3行的q为5，说明它们都是编号为5的物品的附件；
//        第4~6行的q都为0，说明它们都是主件，它们的编号依次为3~5；
//        所以物品的价格与重要度乘积的总和的最大值为10*1+20*3+20*3=130


// https://www.nowcoder.com/practice/f9c6f980eeec43ef85be20755ddbeaf4?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D37%26type%3D37&difficulty=&judgeStatus=&tags=&title=&gioEnter=menu

// 分析：一个带条件的 01 背包问题
// v[i] 价格；w[i] 重要度； v[i]*w[i] 满意度；  总钱数 N，满足条件：r > 0 表示依赖的主件编号
// f[i][n] = max(f[i-1][n], f[i-1][n-v[i]] + v[i]*w[i])

public class HJ16 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            System.out.println(simplePackage(1000, 5, new String[] {
                    "800 2 0",
                    "400 5 1",
                    "300 5 1",
                    "400 3 0",
                    "500 2 0",
            }));
        }
    }

    //
    // 满意度 = 价格 * 重要度
    // N 总钱数  M 可购买物品的个数
    // v 物品价格 p 重要度  q 主件的编号，为0表示自身为主件
    public static int simplePackage(int n, int v, String[] lines) {

        int[] vs = new int[n];
        int[] ws = new int[n];
        int[] rs = new int[n];
        for (int i = 0; i < n; i++) {
            String[] cs = lines[i].split(" ");
            vs[i] = Integer.valueOf(cs[0]);
            ws[i] = Integer.valueOf(cs[1]);
            rs[i] = Integer.valueOf(cs[2]);
        }


        int[][] f = new int[n][v];
        for (int i = 0; i < v; i++) {
            // f[0][i] =
        }
        return 0;
    }

}

package 算法.huawei;


import java.util.*;

// https://www.nowcoder.com/practice/f9c6f980eeec43ef85be20755ddbeaf4?tpId=37&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D37%26type%3D37&difficulty=&judgeStatus=&tags=&title=&gioEnter=menu



public class HJ16_购物单_有依赖的背包问题 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            String[] cs = s.split(" ");
            int money = Integer.valueOf(cs[0]);
            int count = Integer.valueOf(cs[1]);
            String[] ss = new String[count];
            for (int i = 0; i < count; i++) {
                ss[i] = in.nextLine();
            }
            System.out.println(groupPackage(money, ss));
        }

    }


    // 分析：一个有依赖的的 01 背包问题，可以转化为分组背包问题
    //
    // 总钱数 N；M 可购买物品的个数
    //
    // v[i] 价格；   w[i] 重要度；
    // 满足条件：r > 0 表示依赖的主件编号
    // v[i]*w[i] 满意度最大
    //
    // f[i][n] = max(f[i-1][n], f[i-1][n-v[i]] + v[i]*w[i])

    // n 总钱数、m 物品数
    // v 物品价格 w 重要度  r 主件的编号，为0表示自身为主件

    public static int groupPackage(int n, String[] lines) {

        // 1. 准备参数

        int count = lines.length;
        int[] vs = new int[count]; // 价格
        int[] ws = new int[count]; // 重要度
        int[] rs = new int[count]; // 依赖关系
        List<Good> goods = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            String[] cs = lines[i].split(" ");
            vs[i] = Integer.valueOf(cs[0]);
            ws[i] = Integer.valueOf(cs[1]);
            rs[i] = Integer.valueOf(cs[2]);
            goods.add(new Good(i+1, vs[i], ws[i], rs[i]));
        }


        // 2. 分组
        Map<Integer, List<Good>> groupGoods = new HashMap<>();
        for (Good good: goods) {
            int groupId = good.rely > 0? good.rely: good.id;
            List<Good> groupGood = groupGoods.computeIfAbsent(groupId, k -> new LinkedList<>());
            groupGood.add(good);
        }

        // 3. 状态转移 f(i, j) 表示是否购买第 i 组，限额 j 的最大效益
        // f(i, j) = max(f(i-1, j))
        int groupCount = groupGoods.size();
        int[][] f = new int[groupCount+1][n+1];
        int i = 0;
        for (Map.Entry<Integer, List<Good>> entry: groupGoods.entrySet()) {
            i++;
            for (int j = 1; j <= n; j++) {

                List<Good> group = entry.getValue();
                Good primary = null;
                Good sub1 = null;
                Good sub2 = null;
                for (Good good: group) {
                    if (good.rely == 0) {
                        primary = good;
                    } else if (sub1 == null) {
                        sub1 = good;
                    } else {
                        sub2 = good;
                    }
                }

                // 不买主件
                int f0 = f[i-1][j];

                // 买主件
                int f1 = primary.price <= j? f[i-1][j- primary.price] + primary.value(): 0;

                // 买主件 + 第一个附件
                int f2 = sub1 != null && (sub1.price + primary.price) <= j? f[i-1][j- primary.price- sub1.price] + primary.value() + sub1.value():0;

                // 买主件 + 第二个附件
                int f3 = sub2 != null && (sub2.price + primary.price) <= j? f[i-1][j-primary.price - sub2.price] + primary.value() + sub2.value():0;

                // 买主件 + 第一个 和 第二个附件
                int f4 = sub1 != null && sub2 != null && (sub1.price + sub2.price + primary.price) <= j?
                        f[i-1][j-primary.price- sub1.price- sub2.price]+ primary.value() + sub1.value() + sub2.value():0;

                f[i][j] = max(f0, f1, f2, f3, f4);
            }
        }


        // 输出答案
        return f[groupCount][n];
    }

    private static int max(int... is) {

        int result = Integer.MIN_VALUE;
        for (int i: is) {
            if (result < i) {
                result = i;
            }
        }
        return result;
    }

    static class Good {

        int id;
        int price;
        int weight;
        int rely;

        public Good(int id, int price, int weight, int rely) {
            this.id = id;
            this.price = price;
            this.weight = weight;
            this.rely = rely;
        }

        private int value() {
            return price * weight;
        }
    }
}

package 算法.honor;

//        部门聚餐，下大雨，要将所有人从食堂运到办公室。
//        现在由大伞和小伞，小伞一次撑2个人，大伞一次撑3个人
//        在最开始时食堂的人有一把小伞，办公室里有n把小伞，m把大伞。
//        从办公室到食堂的往返时间为2小伞，去1h，回1h。
//        每个人一次只能带一把伞。
//        求所有人回到办公室的最短时间。
//        输入：
//        一个数字T，T组数据
//        每组为c n m，代表人数，小伞数，大伞数
//        测试用例：
//        2
//        1 1 1
//        3 1 1
//
//        c=11 n=1 m=2 标答为9

public class SendUmbrella {

    public static void main(String[] args) {

        SendUmbrella test = new SendUmbrella();
        System.out.println(test.count(11, 1, 2));
    }

    private int count(int c, int n, int m) {

        return stepAll(c, n+1, m, c, 1, 0);
    }

    // 0: count  1  0     0  n  m
    // 1: count-2 0  0    2 n+1 m
    // 2:
    private int stepAll(int t_c, int t_n, int t_m, int c, int n, int m) {

        int step = 0;
        while (c > 0) {
            step++;
            int deltaC;
            int deltaN;
            int deltaM;

            if (step % 2 == 1) {
                deltaC = sendPeople(c, n, m);
                deltaN = sendN(c, n, m);
                deltaM = sendM(c, n, m);
            } else {
                deltaC = -backPeople(t_c - c, t_n -n, t_m - m);
                deltaN = -backN(t_c - c, t_n -n, t_m - m);
                deltaM = -backM(t_c - c, t_n -n, t_m - m);
            }
            System.out.println(String.format("deltaC: %d, deltaN: %d, deltaM: %d", deltaC, deltaN, deltaM));
            c -= deltaC;
            n -= deltaN;
            m -= deltaM;

        }
        return step;
    }

    private int sendPeople(int c, int n, int m) {
        return Math.min(c, 2*n + 3*m);
    }

    private int sendN(int c, int n, int m) {
        return Math.max(0, Math.min(c-m, n));
    }

    private int sendM(int c, int n, int m) {
        return Math.min(c, m);
    }

    private int backPeople(int c, int n, int m) {
        return Math.min(c, n + m);
    }

    private int backN(int c, int n, int m) {
        return Math.max(0, Math.min(c-m, n));
    }

    private int backM(int c, int n, int m) {
        return Math.min(c, m);
    }
}

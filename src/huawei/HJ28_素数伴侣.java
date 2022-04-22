package huawei;


// 题目描述
// 若两个正整数的和为素数，则这两个正整数称之为“素数伴侣”，如2和5、6和13，它们能应用于通信加密。
// 现在密码学会请你设计一个程序，从已有的 N （ N 为偶数）个正整数中挑选出若干对组成“素数伴侣”，挑选方案多种多样
// ，例如有4个正整数：2，5，6，13，如果将5和6分为一组中只能得到一组“素数伴侣”，而将2和5、6和13编组将得到两组“素数伴侣”，
// 能组成“素数伴侣”最多的方案称为“最佳方案”，当然密码学会希望你寻找出“最佳方案”。

// 这题太难，先不做，

import java.util.Scanner;
import java.util.*;

public class HJ28_素数伴侣 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int total = scanner.nextInt();
            int[] index = new int[total];
            ArrayList<Integer> jishu = new ArrayList<>();
            ArrayList<Integer> oushu = new ArrayList<>();
            int tmp;
            for (int i = 0; i < total; i++) {
                index[i] = -1;
                tmp = scanner.nextInt();
                if (tmp % 2 == 0) {
                    oushu.add(tmp);
                } else jishu.add(tmp);
            }
            Match match;
            if (jishu.size() < oushu.size()) match = new Match(jishu, oushu);
            else match = new Match(oushu, jishu);
            System.out.println(match.getMaxMatches());

        }
    }

}

class Match {
    ArrayList<Integer> short_array;
    ArrayList<Integer> long_array;
    Prime prime = new Prime();
    int[] index_long;
    int[] index_short;
    boolean[] mark_flag;

    public Match(ArrayList<Integer> short_array, ArrayList<Integer> long_array) {
        this.short_array = short_array;
        this.long_array = long_array;
        index_long = new int[long_array.size()];
        index_short = new int[short_array.size()];
        for (int i = 0; i < long_array.size(); i++) {
            index_long[i] = -1;
        }
        for (int i = 0; i < short_array.size(); i++) {
            index_short[i] = -1;
        }
    }

    int getMaxMatches() {
        int sum = 0;
        for (int i = 0; i < short_array.size(); i++) {
            clear();
            if (findNewPath(i)) {
                sum++;
            }
        }
        return sum;
    }

    void clear() {
        mark_flag = new boolean[short_array.size()];
    }

    private boolean findNewPath(int short_point) {

        int short_point_value = short_array.get(short_point);
        //每个节点只能挪一次，挪第二次代表出现死循环了
        if (mark_flag[short_point]) return false;
        else mark_flag[short_point] = true;
        for (int i = 0; i < long_array.size(); i++) {
            if (prime.isPrime(short_point_value + long_array.get(i))) {
                //没有被占用或者可以挪一挪
                if (index_long[i] == -1 || findNewPath(index_long[i])) {
                    index_long[i] = short_point;
                    index_short[short_point] = i;
                    return true;
                }
            }
        }
        return false;

    }
}

/**
 * 素数判定
 */
class Prime {
    Set<Integer> set = new HashSet<>();

    public boolean isPrime(int x) {
        if (set.contains(x)) return true;
        if (x == 2 || x == 3 || x == 5 || x == 7) {
            set.add(x);
            return true;
        }
        for (int i = 3; i < x / 2; i += 2) {
            if (x % i == 0) {
                return false;
            }
        }
        set.add(x);
        return true;
    }
}
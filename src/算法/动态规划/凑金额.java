package 算法.动态规划;

import java.util.*;

// 一个奇葩国家的钞票面额分别是1、5、11
// 输入 n，求最小钞票数满足刚好 n 元
// n = 15，输出 3
public class 凑金额 {


    public static void main(String[] args) {

        System.out.println(new 凑金额().countCashRecursive(15));
        System.out.println(new 凑金额().countCashCirculation(15));
    }


    // 分析：通过递归可得
    // f(n) = Min(f(n-1), f(n-5), f(n-11)) + 1
    // 另外如何输出具体付款的方式呢？
    private int countCashRecursive(int n) {

        if (n <= 0) {
            return 0;
        } else if (n < 5) {
            return n;
        } else if (n < 11) {
            int f1 = countCashRecursive(n - 1);
            int f5 = countCashRecursive(n - 5);
            return Math.min(f1, f5) + 1;
        } else {
            int f1 = countCashRecursive(n - 1);
            int f5 = countCashRecursive(n - 5);
            int f11 = countCashRecursive(n - 11);
            return Math.min(Math.min(f1, f5), f11) + 1;
        }

    }

    // 分析：通过迭代依次计算 f(1) f(2) ... f(n)，其实这就是动态规划
    // f(n) = Min(f(n-1), f(n-5), f(n-11)) + 1
    //
    private int countCashCirculation(int n) {

        // 记录 cash 数目
        int[] counts = new int[n + 1];
        //
        Map<Integer, List<Integer>> pays = new HashMap<>();

        for (int i = 1; i <= n; i++) {

            // 1 ~ 4  f(n) = f(n-1) = 1
            // 5 ~ 10 f(n) = Min(f(n-1), f(n-5)) + 1
            // 11 ~   f(n) = Min(f(n-1), f(n-5), f(n-11)) + 1

            if (i <= 4) {
                counts[i] = counts[i-1] + 1;

                recordCash(i, 1, pays);
            } else if (i <= 10) {
                counts[i] = Math.min(counts[i-1], counts[i-5]) + 1;

                recordCash(i, counts[i-1] < counts[i-5]? 1:5, pays);
            } else {
                counts[i] = Math.min(Math.min(counts[i-1], counts[i-5]), counts[i-11]) + 1;

                if (counts[i] == counts[i-1]+1) {
                    recordCash(i, 1, pays);
                } else if (counts[i] == counts[i-5]+1) {
                    recordCash(i, 5, pays);
                } else {
                    recordCash(i, 11, pays);
                }

            }
        }
        return counts[n];
    }

    // 记录金额
    private void recordCash(int n, int cash, Map<Integer, List<Integer>> pays) {

        List<Integer> pay = pays.computeIfAbsent(n, k -> new LinkedList<>());
        List<Integer> subPay = pays.computeIfAbsent(n-cash, k -> new ArrayList<>());
        pay.add(cash);
        pay.addAll(subPay);
    }

}

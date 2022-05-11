package 动态规划;

//  最长上升子序列（LIS）问题：
//  给定长度为n的序列a，从a中抽取出一个子序列，这个子序列需要单调递增。
//  问最长上升子序列（LIS）的长度。
//
//  e.g. 1,5,3,4,6,9,7,8 的LIS为 1,3,4,6,7,8，长度为6
//  [4,10,4,3,8,9] -> 3


// leetcode 已通过 https://leetcode-cn.com/problems/longest-increasing-subsequence/submissions/
//
public class 最长上升子序列 {

    public static void main(String[] args) {

        int[] nums = new int[]{4,10,4,3,8,9};
        System.out.println(maxSubLen(nums));
        // add git rebase test
        // git add test1
    }


    // 分析：
    // 简化问题：子串最后一个字符为序列最后一个字符的最长上升子序列
    // f(n) = max(max(f(i) + 1), max(f(j)))  其中 i<n 并且 a_i<a_n; j<n 并且 a_j>=a_n
    // 那么最终的结果是 o(n) = Max{f(i)} 1<=i<=n

    private static int maxSubLen(int[] nums) {

        // git add test1

        int[] records = new int[nums.length];
        records[0] = 1;
        for (int i = 1; i < records.length; i++) {
            records[i] = fn(nums, records, i);
        }

        // 求最大值
        int result = 0;
        for (int i = 0; i < records.length; i++) {
            if (result < records[i]) {
                result = records[i];
            }
        }
        return result;
    }


    private static int fn(int[] cs, int[] record, int n) {

        int result = 1;
        for (int i = 0; i < n; i++) {
            int r_i = cs[n] > cs[i]? record[i] + 1 : 1; // 注意如果这里 cs[n] <= cs[i]，r_i 为 1，因为条件限定了必须带上最后一个字符的最长上升子序列

            //
            if (r_i > result) {
                result = r_i;
            }
        }
        return result;
    }
}

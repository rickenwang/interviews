package huawei;


import java.util.Scanner;

// 输入：
// 124
// 16 103 132 23 211 75 155 82 32 48 79 183 13 91 51 172 109 102 189 121 12 120 116 133 79 120 116 208 47 110 65 187 69 143 140 173 203 35 184 49 245 50 179 63 204 34 218 11 205 100 90 19 145 203 203 215 72 108 58 198 95 116 125 235 156 133 220 236 125 29 235 170 130 165 155 54 127 128 204 62 59 226 233 245 46 3 14 108 37 94 52 97 159 190 143 67 24 204 39 222 245 233 11 80 166 39 224 12 38 13 85 21 47 25 180 219 140 201 11 42 110 209 77 136
// 输出：
// 95
public class HJ24_合唱队_动态规划_最长上升子序列的拓展问题 {


    //
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String snum = in.nextLine();
            int num = Integer.valueOf(snum);
            int[] nums = new int[num];

            String s = in.nextLine();
            String[] ss = s.split(" ");

            for (int i = 0; i < num; i++) {
                nums[i] = Integer.valueOf(ss[i]);
            }
            System.out.println(minOut(nums));
        }
    }

    // 其实就是求最大合唱子序列
    // 之前求过最大递增子序列，由此启发定义:
    // f(i) 为以第 i 个字符为顶点的最大子序列，那么原问题即为 max(f(i))
    // f(i) 等价于求以第 i 个字符为终点的最大上升序列 + 第 i 个字符为起点的最大下降序列 - 1
    // 其中前半部分在最大递增子序列中已经求过了，后半部分可以转化为类前半部分的方式进行求解。


    // 可以使用动态规划: 1.
    private static int minOut(int[] nums) {

        int[] upper = maxSubUpperLen(nums);
        int[] down = converse(maxSubUpperLen(converse(nums)));

        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            maxLen = Math.max(maxLen, upper[i] + down[i]);
        }
        return nums.length - maxLen + 1;
    }


    private static int[] converse(int[] nums) {
        int[] converse = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            converse[i] = nums[nums.length-i-1];
        }
        return converse;
    }

    private static int[] maxSubUpperLen(int[] nums) {

        int[] records = new int[nums.length];
        records[0] = 1;
        for (int i = 1; i < records.length; i++) {
            records[i] = upperFn(nums, records, i);
        }
        return records;
    }


    private static int upperFn(int[] cs, int[] record, int n) {

        int result = 1;
        for (int i = 0; i < n; i++) {
            int r_i = cs[n] > cs[i]? record[i] + 1 : 1; // 注意如果这里 cs[n] <= cs[i]，r_i 为 1，因为条件限定了必须带上最后一个字符的最长上升子序列

            if (r_i > result) {
                result = r_i;
            }
        }
        return result;
    }

}

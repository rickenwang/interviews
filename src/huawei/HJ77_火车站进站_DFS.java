package huawei;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// 给定一个正整数N代表火车数量，0<N<10，
// 接下来输入火车入站的序列，一共N辆火车，每辆火车以数字1-9编号，
// 火车站只有一个方向进出，同时停靠在火车站的列车中，只有后进站的出站了，先进站的才能出站。
//
// 要求输出所有火车出站的方案，以字典序排序输出。

public class HJ77_火车站进站_DFS {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String n = in.nextLine();
            String s = in.nextLine();
            String[] ss = s.split(" ");
            List<String> result = new Solution().solve(ss);
            for (String r: result) {
                System.out.println(r);
            }
        }
    }


    static class Solution {


        private List<String> solve(String[] ids) {

            //
            LinkedList<String> stack = new LinkedList<>();
            LinkedList<String> out = new LinkedList<>();
            LinkedList<String> recorder = new LinkedList<>();

            for (String id: ids) {
                out.addLast(id);
            }

            dfs(stack, out, "", recorder);
            recorder.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            return recorder;
        }

        /**
         *
         * @param stack 车站现有的火车
         * @param out 还未开进车站的火车
         * @param route 记录当前已经开出的火车
         * @param recorder 记录完整路径
         */
        private void dfs(LinkedList<String> stack, LinkedList<String> out, String route, List<String> recorder) {

            // 压栈
            if (!out.isEmpty()) {
                LinkedList<String> newStack = new LinkedList<>(stack);
                LinkedList<String> newOut = new LinkedList<>(out);

                String pushEle = newOut.removeFirst();
                newStack.addLast(pushEle);
                dfs(newStack, newOut, route, recorder);
            }

            // 出栈
            if (!stack.isEmpty()) {
                LinkedList<String> newStack = new LinkedList<>(stack);
                LinkedList<String> newOut = new LinkedList<>(out);

                String popEle = newStack.removeLast();
                dfs(newStack, newOut, (route + " " + popEle).trim(), recorder);
            }

            // 全部的车都处理了，记录路径
            if (stack.isEmpty() && out.isEmpty()) {
                recorder.add(route);
            }
        }

    }

}

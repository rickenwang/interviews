package huawei;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.util.*;

//  Levenshtein 距离，又称编辑距离，指的是两个字符串之间，由一个转换成另一个所需的最少编辑操作次数。
//  许可的编辑操作包括：
//  1. 将一个字符替换成另一个字符，
//  2. 插入一个字符，
//  3. 删除一个字符。
//
//  编辑距离的算法是首先由俄国科学家 Levenshtein 提出的，故又叫 Levenshtein Distance 。
//
//        例如：
//
//        字符串A: abcdefg
//
//        字符串B: abcdef
//
//        通过增加或是删掉字符 ”g” 的方式达到目的。这两种方案都需要一次操作。把这个操作所需要的次数定义为两个字符串的距离。
//
//        要求：
//
//        给定任意两个字符串，写出一个算法计算它们的编辑距离。

public class HJ50_四则运算 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String a = in.nextLine();
            calculate(a);
        }
    }


    private static void calculate(String expr) {
        parseExpr(expr);

    }

    // 计算后缀表达式

    //    从左往右遍历
    //
    //    遇到数字直接放入容器
    //    遇到运算符，将最后两个数字取出，进行该运算，将结果再放入容器
    //
    //    遍历结束后，容器中的数字即为运算结果
    private static int calculateReversePolish() {


        return -1;
    }

    // 将中缀表达式转化为后缀表达式

    //    首先设置运算符的优先级（这样设置也是为了简化程序）：
    //    ”null” 栈顶若为空，假设优先级为0
    //
    //    “(” 优先级设为1
    //    “+-” 优先级设为2
    //    “*/” 优先级设为3
    //
    //    从左向右遍历中缀表达式，
    //    遇到数字直接输出
    //    遇到符号，遇到左括号，直接压栈
    //    遇到右括号，弹栈输出直到弹出左括号（左括号不输出）
    //    遇到运算符，比较栈顶符号，若该运算符优先级大于栈顶，直接压栈；若小于栈顶，弹栈输出直到大于栈顶，然后将改运算符压栈。
    //    最后将符合栈弹栈并输出
    private static String reversePolish(String expr) {

        LinkedList<String> stack = new LinkedList<>();


        // 1. 去掉大括号和中括号
        expr = expr.replaceAll("\\[", "(")
                .replaceAll("\\{", "(")
                .replaceAll("}", ")")
                .replaceAll("]", ")");

        LinkedList<String> elements = parseExpr(expr);

        for (String element: elements) {


        }

        //

        return "";


    }

    // 拆分表达式元素
    // 如 3+2*(1+2*(-4/(8-6)+7)) 拆分
    // 可以分为如下几种类型：
    // 1. 符号；2. 数字；
    private static LinkedList<String> parseExpr(String expr) {

        //
        LinkedList<String> elements = new LinkedList<>();
        char[] els = expr.toCharArray();
        int sp = Integer.MIN_VALUE; // 上一个符号的位置

        for (int i = 0; i < els.length; i++) {

            if (els[i] == '+' || els[i] == '-' || els[i] == '*' || els[i] == '/' || els[i] == '(' || els[i] == ')') {

                if (els[i] == '-' && (els[i - 1] > '9' || els[i - 1] < '0')) {
                    continue;
                }

                if (i > sp + 1 && sp >= 0) {
                    elements.addLast(expr.substring(sp + 1, i));
                }
                elements.addLast(expr.substring(i, i + 1));
                sp = i;
            }
        }
        return elements;
    }

}

package huawei;

import java.util.*;

//
//  输入一个表达式（用字符串表示），求这个表达式的值。
//  保证字符串中的有效字符包括[‘0’-‘9’],‘+’,‘-’, ‘*’,‘/’ ,‘(’， ‘)’,‘[’, ‘]’,‘{’ ,‘}’。且表达式一定合法。

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

// cp
class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();
        int num1 = 0;
        int o1 = 1;
        int num2 = 1;
        int o2 = 1;
        Stack<Integer> stk = new Stack<>();

        for(int i=0; i<n; i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){  //遇到数字则定义num2
                int cur = 0;
                while(i<n && Character.isDigit(s.charAt(i))){
                    cur = cur * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                num2 = o2 == 1 ? num2 * cur : num2 / cur;
            }else if(c=='*' || c=='/'){  //遇到×÷定义o2
                o2 = c == '*' ? 1 : -1;
            }else if(c == '(' || c=='{' || c=='['){  //遇到括号则保存当前结果，并初始化
                stk.push(num1);
                stk.push(o1);
                stk.push(num2);
                stk.push(o2);

                num1 = 0;
                o1 = 1;
                num2 = 1;
                o2 = 1;
            }else if(c == '+' || c=='-'){  //遇到加减，说明可以开始计算，计算num1并对定义其他几个变量
                if(c=='-' && (i==0 || s.charAt(i-1)=='(' || s.charAt(i-1)=='[' || s.charAt(i-1)=='{')){
                    o1 = -1;
                    continue;
                }
                num1 = num1 + o1 * num2;
                o1 = (c == '+' ? 1 : -1);
                num2 = 1;
                o2 = 1;
            }else{  //遇到右括号，则出栈，并计算num2
                int cur = num1 + o1 * num2;
                o2 = stk.pop();
                num2 = stk.pop();
                o1 = stk.pop();
                num1 = stk.pop();

                num2 = o2 == 1 ? num2 * cur : num2 / cur;
            }
        }
        System.out.println(num1 + o1 * num2);
    }
}



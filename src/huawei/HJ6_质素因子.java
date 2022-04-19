package huawei;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//
//
//
// 功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）（如180的质因子为2 2 3 3 5 ）
public class HJ6_质素因子 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            split(Integer.valueOf(s));
        }

    }

    private static void split(int s) {

        int nextPrime = 2;
        List<Integer> primes = new LinkedList<>();

        while(nextPrime > 0) {

            while (s % nextPrime == 0) {
                primes.add(nextPrime);
                s /= nextPrime;
            }
            nextPrime = nextPrime(nextPrime, s);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int prime: primes) {
            stringBuilder.append(prime + " ");
        }
        System.out.println(stringBuilder.substring(0, stringBuilder.length() - 1));
    }


    private static int nextPrime(int start, int end) {

        for (int i = start + 1; i <= end; i++) {
            if (isPrimeNumber(i)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isPrimeNumber(int number) {

        if (number == 1 || number == 2) {
            return true;
        }

        int len = (int) Math.sqrt(number) + 1;
        for (int i = 2; i <= len; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}

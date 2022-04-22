package huawei;

import java.util.ArrayList;
import java.util.Scanner;

// 考生需要模拟实现一个简单的自动售货系统，实现投币、购买商品、退币、查询库存商品及存钱盒信息的功能。
// 系统初始化时自动售货机中商品为6种商品,商品的单价参见1.1规格说明，存钱盒内放置1元、2元、5元、10元钱币，
// 商品数量和钱币张数通过初始化命令设置，参见2.1 系统初始化。

// copy
public class HJ98_自动售货系统 {
        static ArrayList<Good> goods = new ArrayList<Good>();
        static ArrayList<Money> moneys = new ArrayList<Money>();
        static int goodTotal;
        static int moneyTotal;
        public static void main(String[] args) {
            goods.add(new Good("A1", 2));
            goods.add(new Good("A2", 3));
            goods.add(new Good("A3", 4));
            goods.add(new Good("A4", 5));
            goods.add(new Good("A5", 8));
            goods.add(new Good("A6", 6));

            moneys.add(new Money(1));
            moneys.add(new Money(2));
            moneys.add(new Money(5));
            moneys.add(new Money(10));
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] arr = str.split(";");
                for (int i=0; i< arr.length; i++) {
                    if (arr[i].contains("r")) {
                        rStep(arr[i]);
                    } else if (arr[i].contains("p")) {
                        pStep(arr[i]);
                    } else if (arr[i].contains("b")) {
                        bStep(arr[i]);
                    } else if (arr[i].contains("c")) {
                        cStep(arr[i]);
                    } else if (arr[i].contains("q")) {
                        qStep(arr[i]);
                    }
                }
            }

        }
        public static void rStep(String str) {
            String[] msg = str.split(" ");
            String[] goodNum = msg[1].split("-");
            for (int i = 0; i < goodNum.length; i++) {
                int count = Integer.parseInt(goodNum[i]);
                goods.get(i).count = count;
                goodTotal += count;
            }
            String[] moneyNum = msg[2].split("-");
            for (int i = 0; i < moneyNum.length; i++) {
                moneys.get(i).count = Integer.parseInt(moneyNum[i]);
            }
            moneyTotal = 0;//每次初始化，投币余额必须赋0
            System.out.println("S001:Initialization is successful");
        }
        public static void pStep(String str) {
            String[] msg = str.split(" ");
            int num = Integer.parseInt(msg[1]);
            if (num != 1 && num != 2 && num != 5 && num != 10) {
                System.out.println("E002:Denomination error");
                return;
            }
            // 注意金额为2时需进行count*2才是金额总数
            if (num > 2 && moneys.get(0).count + moneys.get(1).count *2 < num) {
                System.out.println("E003:Change is not enough, pay fail");
                return;
            }
            if (goodTotal == 0) {
                System.out.println("E005:All the goods sold out");
                return;
            }
            moneyTotal += num;
            // 注意，投入的金额需添加到对应的钱币盒内
            if (num ==1) {
                moneys.get(0).count+= 1;
            } else if (num ==2) {
                moneys.get(1).count+= 1;
            } else if (num ==5) {
                moneys.get(2).count+= 1;
            } else if (num ==10) {
                moneys.get(3).count+= 1;
            }
            System.out.println("S002:Pay success,balance=" + moneyTotal);
        }
        public static void bStep(String str) {
            String[] msg = str.split(" ");
            String name = msg[1];
            boolean existed = false;
            Good good = null;
            for (int i =0; i< goods.size(); i++) {
                if (goods.get(i).name.equals(name)) {
                    existed = true;
                    good = goods.get(i);
                    break;
                }
            }
            if(!existed) {
                System.out.println("E006:Goods does not exist");
                return;
            }
            if (goodTotal == 0) {
                System.out.println("E007:The goods sold out");
                return;
            }
            if (moneyTotal < good.price) {
                System.out.println("E008:Lack of balance");
                return;
            }
            moneyTotal -= good.price;
            System.out.println("S003:Buy success,balance=" + moneyTotal);
        }
        public static void cStep(String str) {
            if (moneyTotal == 0) {
                System.out.println("E009:Work failure");
                return;
            }
            int tenNum = 0;
            int fiveNum = 0;
            int secNum = 0;
            int oneNum =0;
            while (moneyTotal >= 10 && moneys.get(3).count > 0) {
                moneyTotal -= 10;
                moneys.get(3).count -= 1;
                tenNum++;
            }
            while (moneyTotal >= 5 && moneys.get(2).count > 0) {
                moneyTotal -= 5;
                moneys.get(2).count -= 1;
                fiveNum++;
            }
            while (moneyTotal >= 2 && moneys.get(1).count > 0) {
                moneyTotal -= 2;
                moneys.get(1).count -= 1;
                secNum++;
            }
            while (moneyTotal >= 1 && moneys.get(0).count > 0) {
                moneyTotal -= 1;
                moneys.get(0).count -= 1;
                oneNum++;
            }
            moneyTotal=0;
            System.out.println("1 yuan coin number=" + oneNum);
            System.out.println("2 yuan coin number=" + secNum);
            System.out.println("5 yuan coin number=" + fiveNum);
            System.out.println("10 yuan coin number=" + tenNum);
        }
        public static void qStep(String str) {
            //p0、p1需要判错
            if (!str.contains(" ")) {
                System.out.println("E010:Parameter error");
                return;
            }
            String[] arr = str.split(" ");
            int num = Integer.parseInt(arr[1]);
            if (num < 0 || num > 1) {
                System.out.println("E010:Parameter error");
                return;
            }
            if (num == 0) {
                Good good = null;
                for (int i = 0; i <goods.size(); i++) {
                    good = goods.get(i);
                    System.out.println(good.name + " " + good.price + " " + good.count);
                }
            } else {
                Money money = null;
                for (int i = 0; i< moneys.size(); i++) {
                    money = moneys.get(i);
                    System.out.println(money.id + " yuan coin number=" + money.count);
                }
            }
        }
    }

    class Good {
        String name;
        int price;
        int count;

        public Good(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }

    class Money {
        int id;
        int count;
        public Money(int id) {
            this.id = id;
        }
    }

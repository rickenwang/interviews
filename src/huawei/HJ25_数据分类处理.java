package huawei;

//
// 信息社会，有海量的数据需要分析处理，比如公安局分析身份证号码、 QQ 用户、手机号码、银行帐号等信息及活动记录。
//
// 采集输入大数据和分类规则，通过大数据分类处理程序，将大数据分类输出。

import java.util.*;

// 直接抄的，不具有一般性
//
public class HJ25_数据分类处理 {


    public static void resultData(String[] strR, String[] strI){
        LinkedList<Integer> result = new LinkedList<>();
        Set<Integer> setR = new TreeSet<>();
        for (int i = 1; i < strR.length; i++){
            setR.add(Integer.parseInt(strR[i]));
        }

        for (int str : setR){
            LinkedList<Integer> tmp = new LinkedList<>();
            for (int i = 1; i < strI.length; i++){
                if (strI[i].contains("" + str)){
                    tmp.add(i - 1);
                    tmp.add(Integer.parseInt(strI[i]));
                }
            }
            if (!tmp.isEmpty()){
                result.add(str);
                result.add((tmp.size() / 2));
                result.addAll(tmp);
            }
        }

        System.out.print(result.size() + " ");
        int count = result.size();
        for (int ele : result){
            if (count != 1)
                System.out.print(ele + " ");
            else
                System.out.print(ele);
            count--;

        }
        System.out.println();

    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String I = sc.nextLine();
            String R = sc.nextLine();
            String[] arrR = R.split(" ");
            String[] arrI = I.split(" ");
            resultData(arrR, arrI);
        }
        sc.close();
    }

}

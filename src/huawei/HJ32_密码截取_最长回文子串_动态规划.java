package huawei;

// Catcher是MCA国的情报员，他工作时发现敌国会用一些对称的密码进行通信，
// 比如像这些ABBA，ABA，A，123321，但是他们有时会在开始或结束时加入一些无关的字符以防止别国破解。
// 比如进行下列变化 ABBA->12ABBA,ABA->ABAKK,123321->51233214　。
// 因为截获的串太长了，而且存在多种可能的情况
// （abaaab可看作是aba,或baaab的加密形式），
// Cathcer的工作量实在是太大了，他只能向电脑高手求助，你能帮Catcher找出最长的有效密码串吗？


// qvnkodoirnqvogzowkzocxgfelvjwsesugvrcpzmczedrqhkukzuyreogzueyfxbccwhqtgvjndsisxhqegjjgbrgozclldytitzjiudqxxxbwvuvkoqoyhpuzzpvczwblvmosegthjwvlwobcmemlqbjnidlqnfbrdoqijgnjtoqldndembjxzsujqccwvjyofyzcodbkiptjmbhngslflpjczyxocqdtulbnvwgchzosccjulotscfefyrupoflitkwgdcvrwnxjikejbofgssvjcidbpcxvqtfzdxscecywpkxmcyjcprjennbgtdfoitwrdceiqqpyboutznifqkzmdbvrbmtkxikfmssqrggcqmxbjcxiwllrzwwluxcqovnerjctprqoduzgxqdoviryvuujcrjbgmvbzglbsqrohlwfxogyekfzwznendxwiwvpqjmqjzyzwjgjkeolmufcxgltbgiyllyeunvdsdrmbgrqssmmsbhqsfjtmiwfkmqhomqxfdxhcnvuftdijlxycbfkjuusonzlyntmvhhilgsypseoqbjusguoffuvpmjmieosqvntrtuvkyszrpwxrrnnpyuvutpyyblzxiocbchezguqpmfhymtmsvgrqnqedwznjinrfhsxxhtfrgwssismwziegfhectdpildqyfjfbjdzbbmjvtkwkwtrtjfbtezsbsizbcdjcgzmedssrbelwceecjfntpfbcuydmtuecjxybkeduknhzcikeqxqiriyefpyykjhvfuxkfxqxolpoiwvkriuhnfzwutpupxzkzrophysktubuvgpluylkeomubgkqbxwyddwdsnziflmqhhbdkrjnpbmvvggphwbxqkuhsmtdqgdypmvgnsmteyjuklxptjkisxcbjesgjpgihfseqhnutgbiqbfiegfnbjthesuiuwezthkmbgfuihntejclyuzlxnzzqwkkweprydbmmumlbbntvnehzvzicisrwfurxmpglxfjilweyprbfmibulgfgmbuzcorucrnewjmvqycrjfppyznswcxjiorqzxnoepwzxfnxvxbogfvkhoxqegbmtrbtdwptsmdhfhckbijoqdijqrwevmqgilsqwdsnwissckmroobkmexeqgbxbtcjgpkczxhwczgbzdukokqkniogznvhhhmnileqbffbohrlvjkgrysbrczeeffclxxjfsovybmjujeoivoxtqsbpppslpwdtulzxnnm
// 输出 4

import java.util.*;

// 这个就是最长回文子串
public class HJ32_密码截取_最长回文子串_动态规划 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String a = in.nextLine();
            System.out.println(palindromicLength(a));
        }
    }


    // 反转
    private static char[] converse(char[] nums) {
        char[] converse = new char[nums.length];
        for (int i = 0; i < nums.length; i++) {
            converse[i] = nums[nums.length-i-1];
        }
        return converse;
    }


    // f(i, j) 表示起点为 i, 终点为 j 的最大回文子串，其中 0 <= i < j < n
    // f(i, j) = f(i+1, j-1) + 2 如果 a[i]==a[j]
    // f(i, j) = 0 如果 a[i]!=a[j]
    private static int palindromicLength(String s) {

        char[] cs = s.toCharArray();
        int[][] f = new int[cs.length][cs.length];
        int result = Integer.MIN_VALUE;

        for (int i = 0; i < cs.length; i++) {
            f[i][i] = 1; // 计算 len = 1
            if (i < cs.length -1 && cs[i] == cs[i+1]) {
                f[i][i+1] = 2; // 计算 len == 2
            }
        }

        // 0 <= i+1 < j < n
        for (int len = 3; len <= cs.length; len++) {

            for (int i = 0; i <= cs.length - len; i++) {

                int j = len + i - 1;
                if (cs[i] == cs[j] && f[i+1][j-1] > 0) { // 子串必须是回文，那么父串才可能是回文
                    f[i][j] = f[i+1][j-1] + 2;
                } else {
                    f[i][j] = 0;
                }
            }
        }


        for (int i = 0; i < cs.length; i++) {
            for (int j = 0; j < cs.length; j++) {
                result = Math.max(result, f[i][j]);
            }
        }

        return result;
    }

}

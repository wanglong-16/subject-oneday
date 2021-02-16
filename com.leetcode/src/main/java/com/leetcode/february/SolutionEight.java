package com.leetcode.february;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-16 10:10:55
 * @author: wanglong16@meicai.cn
 */
public class SolutionEight {

    /**
     * 12. 整数转罗马数字
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     *
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     *
     * 示例 1:
     *
     * 输入: 3
     * 输出: "III"
     * 示例 2:
     *
     * 输入: 4
     * 输出: "IV"
     * 示例 3:
     *
     * 输入: 9
     * 输出: "IX"
     * 示例 4:
     *
     * 输入: 58
     * 输出: "LVIII"
     * 解释: L = 50, V = 5, III = 3.
     * 示例 5:
     *
     * 输入: 1994
     * 输出: "MCMXCIV"
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     *
     *
     * 提示：
     *
     * 1 <= num <= 3999
     */
    public String intToRoman(int num) {
        //获得每位上的数值
        List<Integer> numList = new ArrayList<>();
        while (num != 0) {
            numList.add(num % 10);
            num = num / 10;
        }
        StringBuilder sb = new StringBuilder();
        if (!numList.isEmpty()) {
            for (int i = numList.size() - 1; i >=0; i--) {
                sb.append(convertNumToRoman(numList.get(i), i));
            }
        }
        return sb.toString();
    }

    /**
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     */
    private String convertNumToRoman(int num, int pos) {
        Map<Integer, String> numRoman1To10 = new HashMap<Integer, String>() {{
            put(0, "");put(1, "I");put(2, "II");put(3, "III");put(4, "IV");
            put(5, "V");put(6, "VI");put(7, "VII");put(8, "VIII");put(9, "IX");
        }};
        Map<Integer, String> numRoman10To100 = new HashMap<Integer, String>() {{
            put(0, "");put(1, "X");put(2, "XX");put(3, "XXX");put(4, "XL");
            put(5, "L");put(6, "LX");put(7, "LXX");put(8, "LXXX");put(9, "XC");
        }};
        Map<Integer, String> numRoman100To1000 = new HashMap<Integer, String>() {{
            put(0, "");put(1, "C");put(2, "CC");put(3, "CCC");put(4, "CD");
            put(5, "D");put(6, "DC");put(7, "DCC");put(8, "DCCC");put(9, "CM");
        }};
        Map<Integer, String> numRoman1000To3000 = new HashMap<Integer, String>() {{
            put(0, "");put(1, "M");put(2, "MM");put(3, "MMM");
        }};
        switch (pos) {
            case 0:
                return numRoman1To10.get(num);
            case 1:
                return numRoman10To100.get(num);
            case 2:
                return numRoman100To1000.get(num);
            case 3:
                return numRoman1000To3000.get(num);
            default:
                return "";
        }
    }

    /**
     * 561. 数组拆分 I
     * 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
     *
     * 返回该 最大总和 。
     * 示例 1：
     *
     * 输入：nums = [1,4,3,2]
     * 输出：4
     * 解释：所有可能的分法（忽略元素顺序）为：
     * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
     * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
     * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
     * 所以最大总和为 4
     * 示例 2：
     *
     * 输入：nums = [6,2,6,5,1,2]
     * 输出：9
     * 解释：最优的分法为 (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int min = 0;
        for (int i = 1; i < nums.length; i += 2) {
            min += nums[i-1];
        }
        return min;
    }

    /**
     * 389. 找不同
     * 给定两个字符串 s 和 t，它们只包含小写字母。
     *
     * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     *
     * 请找出在 t 中被添加的字母。
     * 示例 1：
     *
     * 输入：s = "abcd", t = "abcde"
     * 输出："e"
     * 解释：'e' 是那个被添加的字母。
     * 示例 2：
     *
     * 输入：s = "", t = "y"
     * 输出："y"
     * 示例 3：
     *
     * 输入：s = "a", t = "aa"
     * 输出："a"
     * 示例 4：
     *
     * 输入：s = "ae", t = "aea"
     * 输出："a"
     * 提示：
     *
     * 0 <= s.length <= 1000
     * t.length == s.length + 1
     * s 和 t 只包含小写字母
     */
    public char findTheDifference(String s, String t) {
        int sAscII = getStrAscII(s);
        int tAscII = getStrAscII(t);
        return (char) (tAscII - sAscII);
    }

    private int getStrAscII(String str) {
        int total = 0;
        for (Character character : str.toCharArray()) {
            total += character;
        }
        return total;
    }
    //位运算
    public char findTheDifferenceV1(String s, String t) {
        int ret = 0;
        for (int i = 0; i < s.length(); ++i) {
            ret ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            ret ^= t.charAt(i);
        }
        return (char) ret;
    }

}

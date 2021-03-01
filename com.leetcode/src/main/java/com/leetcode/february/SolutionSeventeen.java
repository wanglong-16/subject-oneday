package com.leetcode.february;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-28 21:14:40
 * @author: wanglong16@meicai.cn
 */
public class SolutionSeventeen {

    /**
     * 1318. 或运算的最小翻转次数
     * 给你三个正整数 a、b 和 c。
     *
     * 你可以对 a 和 b 的二进制表示进行位翻转操作，返回能够使按位或运算   a OR b == c  成立的最小翻转次数。
     *
     * 「位翻转操作」是指将一个数的二进制表示任何单个位上的 1 变成 0 或者 0 变成 1 。
     * 示例 1：
     * 输入：a = 2, b = 6, c = 5
     * 输出：3
     * 解释：翻转后 a = 1 , b = 4 , c = 5 使得 a OR b == c
     * 示例 2：
     *
     * 输入：a = 4, b = 2, c = 7
     * 输出：1
     * 示例 3：
     * 输入：a = 1, b = 2, c = 3
     * 输出：0
     * 提示：
     *
     * 1 <= a <= 10^9
     * 1 <= b <= 10^9
     * 1 <= c <= 10^9
     */
    public int minFlips(int a, int b, int c) {
        int result = 0;
        for (int i = 0; i < 31; i++) {
            int c0 = c & 1;
            int a0 = a & 1;
            int b0 = b & 1;
            if (c0 == 0) {
                // a0 and b0 = 0
                if (a0 == 1 && b0 == 1) {
                    result += 2;
                } else if (a0 == 1 || b0 == 1) {
                    result ++;
                }
            } else {
                // a0 or b0 at last one is 1
                if (a0 == 0 && b0 == 0) {
                    result ++;
                }
            }
            a >>= 1;
            b >>= 1;
            c >>= 1;
        }
        return result;
    }

    /**
     * 1404. 将二进制表示减到 1 的步骤数
     * 给你一个以二进制形式表示的数字 s 。请你返回按下述规则将其减少到 1 所需要的步骤数：
     *
     * 如果当前数字为偶数，则将其除以 2 。
     *
     * 如果当前数字为奇数，则将其加上 1 。
     *
     * 题目保证你总是可以按上述规则将测试用例变为 1 。
     * 示例 1：
     *
     * 输入：s = "1101"
     * 输出：6
     * 解释："1101" 表示十进制数 13 。
     * Step 1) 13 是奇数，加 1 得到 14
     * Step 2) 14 是偶数，除 2 得到 7
     * Step 3) 7  是奇数，加 1 得到 8
     * Step 4) 8  是偶数，除 2 得到 4
     * Step 5) 4  是偶数，除 2 得到 2
     * Step 6) 2  是偶数，除 2 得到 1
     * 示例 2：
     *
     * 输入：s = "10"
     * 输出：1
     * 解释："10" 表示十进制数 2 。
     * Step 1) 2 是偶数，除 2 得到 1
     * 示例 3：
     *
     * 输入：s = "1"
     * 输出：0
     * 提示：
     * 1 <= s.length <= 500
     * s 由字符 '0' 或 '1' 组成。
     * s[0] == '1'
     */
    public int numStepsV1(String s) {
        int count = 0;
        while (!"1".equals(s)) {
            if (s.charAt(s.length() - 1) == '0') {
                s = divideTwo(s);
            } else {
                s = plusOne(s);
            }
            count ++;
        }
        return count;
    }

    public String divideTwo(String str) {
        return str.substring(0, str.length() - 1);
    }

    public String plusOne(String str) {
        if (str.charAt(str.length() - 1) == '0') {
            //末位等于0 直接加1
            return str.substring(0, str.length() - 1) + "1";
        } else {
            StringBuilder sb = new StringBuilder();
            int firstZero = 0;
            //从末位开始，找到第一个为0的位
            for (int i = str.length() - 1; i >= 0; i--) {
                if (str.charAt(i) == '0') {
                    firstZero = i;
                    break;
                }
                sb.append("0");
            }
            if (firstZero == 0) {
                // 末位为1，全为1则左移1位 其他位全补0，
                sb.append("1");
                return sb.reverse().toString();
            } else {
                // 其他情况从右向左遍历找到第一个0置为1，低位全置0
                return str.substring(0, firstZero) + "1" + sb.reverse().toString();
            }
        }
    }

    public int numSteps(String s) {
        int res = 0;
        boolean isNeedPlus = false;
        for (int i = s.length() - 1; i > 0 ; i--) {
            if (isNeedPlus) {
                if (s.charAt(i) == '0') {
                    res++;
                }
            } else {
                if (s.charAt(i) != '0') {
                    res++;
                    isNeedPlus = true;
                } else {
                    isNeedPlus = false;
                }
            }
            res++;
        }
        if (isNeedPlus) {
            res++;
        }
        return res;
    }

}

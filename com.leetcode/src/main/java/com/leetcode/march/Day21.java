package com.leetcode.march;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-21 22:09:44
 * @author: wanglong16@meicai.cn
 */
public class Day21 {

    /**
     * 6. Z 字形变换
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     *
     * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
     *
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     *
     * 请你实现这个将字符串进行指定行数变换的函数：
     *
     * string convert(string s, int numRows);
     * 示例 1：
     *
     * 输入：s = "PAYPALISHIRING", numRows = 3
     * 输出："PAHNAPLSIIGYIR"
     * 示例 2：
     * 输入：s = "PAYPALISHIRING", numRows = 4
     * 输出："PINALSIGYAHRPI"
     * 解释：
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     * 示例 3：
     *
     * 输入：s = "A", numRows = 1
     * 输出："A"
     * 提示：
     *
     * 1 <= s.length <= 1000
     * s 由英文字母（小写和大写）、',' 和 '.' 组成
     * 1 <= numRows <= 1000
     */
    public String convertV(String s, int numRows) {
        if (numRows >= s.length()) {
            return s;
        }
        int batch = 2 * numRows - 2, cycs = s.length() / batch + 1;
        int cols = cycs * (numRows - 1);
        char[][] chars = new char[numRows][cols];
        for (int i = 0; i < cycs; i++) {
            for (int j = 0; j < batch; j++) {
                char ch = i * batch + j < s.length() ? s.charAt(i * batch + j) : 0;
                if (j < numRows) {
                    chars[j][i * (numRows - 1)] = ch;
                } else {
                    int dem = j % numRows + 1;
                    chars[numRows - dem - 1 ][i * (numRows - 1) + dem] = ch;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i ++) {
            for (int j = 0; j < chars[0].length; j++) {
                if (chars[i][j] != 0) {
                    sb.append(chars[i][j]);
                }
            }
        }
        return sb.toString();
    }

    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }
}

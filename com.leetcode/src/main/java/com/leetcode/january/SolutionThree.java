package com.leetcode.january;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-20 22:37:43
 * @author: wanglong16@meicai.cn
 */
public class SolutionThree {


    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 示例 1：
     *
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * 示例 2：
     *
     * 输入：s = "cbbd"
     * 输出："bb"
     * 1 <= s.length <= 1000
     * s 仅由数字和英文字母（大写和/或小写）组成
     */
    public String longestPalindrome(String s) {
        String max = "";
        for (int start = 0; start < s.length(); start ++) {
            for (int stop = start + 1; stop < s.length(); stop ++) {
                String temp = s.substring(start, stop);
                if (isPalindrome(temp.toCharArray())) {
                    max = max.length() >= temp.length() ? max : temp;
                }
            }
        }
        return max;
    }

    public boolean isPalindrome(char [] chars) {
        if (chars.length <= 1) {
            return chars.length == 1;
        }
        int left = 0, right = chars.length - 1;
        while (left <= right) {
            if (chars[left] == chars[right]) {
                left ++;
                right --;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * dp
     */
    public String longestPalindromeV1(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;
    }
}

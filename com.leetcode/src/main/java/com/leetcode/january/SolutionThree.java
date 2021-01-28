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


    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < s.length(); i ++) {
            if (Character.isLetterOrDigit(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString().equalsIgnoreCase(sb.reverse().toString());
    }


    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例:
     *
     * 输入: [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 进阶:
     *
     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     */
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int temp = 0;
            for (int j = i; j < nums.length; j ++) {
                temp += nums[j];
                if (temp > max) {
                    max = temp;
                }
            }
        }
        return max;
    }

    /**
     * 88. 合并两个有序数组
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     *
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
     * 示例 1：
     *
     * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * 输出：[1,2,2,3,5,6]
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //遍历nums2 比较nums1并插入
        int cursor = 0;
        for (int i = 0; i < n; i ++) {
            int cur = nums2[n];
            for (int j = cursor; j < m + i; j++) {
                if (nums1[j] > cur) {
                    //记下nums1第一个大于当前数的位置
                    cursor = j;
                    // j之后的数据后移
                    for (int k = m + i; k > j; k--) {
                        nums1[k+1] = nums1[k];
                    }
                    nums1[j] = cur;
                    break;
                }
            }
        }
    }

}

package com.leetcode.march;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        if (numRows == 1) {
            return s;
        }

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
                    ret.append(s.charAt(j + cycleLen - i));
                }
            }
        }
        return ret.toString();
    }

    /**
     * 62. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     *
     * 问总共有多少条不同的路径？
     * 示例 1：
     * 输入：m = 3, n = 7
     * 输出：28
     * 示例 2：
     *
     * 输入：m = 3, n = 2
     * 输出：3
     * 解释：
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向下
     * 示例 3：
     *
     * 输入：m = 7, n = 3
     * 输出：28
     * 示例 4：
     *
     * 输入：m = 3, n = 3
     * 输出：6
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0]= 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][n] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 413. 等差数列划分
     * 如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。
     *
     * 例如，以下数列为等差数列:
     *
     * 1, 3, 5, 7, 9
     * 7, 7, 7, 7
     * 3, -1, -5, -9
     * 以下数列不是等差数列。
     *
     * 1, 1, 2, 5, 7
     *
     *
     * 数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0<=P<Q<N 。
     *
     * 如果满足以下条件，则称子数组(P, Q)为等差数组：
     *
     * 元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q 。
     *
     * 函数要返回数组 A 中所有为等差数组的子数组个数。
     * 示例:
     *
     * A = [1, 2, 3, 4]
     *
     * 返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int[] dp = new int[nums.length];
        int total = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i - 1] - nums[i - 2] == nums[i] - nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                total += dp[i];
            }
        }
        return total;
    }

    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < nums.length; i ++) {
            int tar = nums[i];
            integers.add(index[i], tar);
        }
        int[] ans = new int[integers.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = integers.get(i);
        }
        return ans;
    }

    /**
     * 1365. 有多少小于当前数字的数字
     * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
     *
     * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
     *
     * 以数组形式返回答案。
     * 示例 1：
     *
     * 输入：nums = [8,1,2,2,3]
     * 输出：[4,0,1,1,3]
     * 解释：
     * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
     * 对于 nums[1]=1 不存在比它小的数字。
     * 对于 nums[2]=2 存在一个比它小的数字：（1）。
     * 对于 nums[3]=2 存在一个比它小的数字：（1）。
     * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
     * 示例 2：
     *
     * 输入：nums = [6,5,4,8]
     * 输出：[2,1,0,3]
     * 示例 3：
     *
     * 输入：nums = [7,7,7,7]
     * 输出：[0,0,0,0]
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        List<Integer> arr = new ArrayList<>();
        for (Integer integer : nums) {
            arr.add(integer);
        }
        Collections.sort(arr);
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < arr.size(); j++) {
                if (nums[i] == (arr.get(j))) {
                    ans[i] = j;
                    break;
                }
            }
        }
        return ans;
    }

}

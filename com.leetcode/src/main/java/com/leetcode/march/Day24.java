package com.leetcode.march;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-24 21:44:00
 * @author: wanglong16@meicai.cn
 */
public class Day24 {

    /**
     * 1313. 解压缩编码列表
     * 给你一个以行程长度编码压缩的整数列表 nums 。
     * <p>
     * 考虑每对相邻的两个元素 [freq, val] = [nums[2*i], nums[2*i+1]] （其中 i >= 0 ），每一对都表示解压后子列表中有 freq 个值为 val 的元素，你需要从左到右连接所有子列表以生成解压后的列表。
     * <p>
     * 请你返回解压后的列表。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,3,4]
     * 输出：[2,4,4,4]
     * 解释：第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]。
     * 第二对 [3,4] 代表着 4 的出现频次为 3，所以生成数组 [4,4,4]。
     * 最后将它们串联到一起 [2] + [4,4,4] = [2,4,4,4]。
     * 示例 2：
     * <p>
     * 输入：nums = [1,1,2,3]
     * 输出：[1,3,3]
     */
    public int[] decompressRLElistV1(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i += 2) {
            int freq = nums[i];
            int val = nums[i + 1];
            for (int j = 0; j < freq; j++) {
                ans.add(val);
            }
        }
        int[] an = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            an[i] = ans.get(i);
        }
        return an;
    }

    int sumOddLengthSubarrays(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            int left = i + 1, right = arr.length - i,
                    left_even = (left + 1) / 2, right_even = (right + 1) / 2,
                    left_odd = left / 2, right_odd = right / 2;
            res += (left_even * right_even + left_odd * right_odd) * arr[i];
        }
        return res;
    }

    /**
     * LCP 17. 速算机器人
     * 小扣在秋日市集发现了一款速算机器人。店家对机器人说出两个数字（记作 x 和 y），请小扣说出计算指令：
     *
     * "A" 运算：使 x = 2 * x + y；
     * "B" 运算：使 y = 2 * y + x。
     * 在本次游戏中，店家说出的数字为 x = 1 和 y = 0，小扣说出的计算指令记作仅由大写字母 A、B 组成的字符串 s，字符串中字符的顺序表示计算顺序，请返回最终 x 与 y 的和为多少。
     *
     * 示例 1：
     *
     * 输入：s = "AB"
     *
     * 输出：4
     *
     * 解释：
     * 经过一次 A 运算后，x = 2, y = 0。
     * 再经过一次 B 运算，x = 2, y = 2。
     * 最终 x 与 y 之和为 4。
     *
     * 提示：
     *
     * 0 <= s.length <= 10
     * s 由 'A' 和 'B' 组成
     */
    public int calculate(String s) {
        int x = 1, y = 0;
        for (Character ch : s.toCharArray()) {
            if (ch == 'A') {
                x = 2 * x + y;
            }
            if (ch == 'B') {
                y = 2 * y + x;
            }
        }
        return x + y;
    }

    /**
     * 1314. 矩阵区域和
     * 给你一个 m * n 的矩阵 mat 和一个整数 K ，请你返回一个矩阵 answer ，其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和：
     *
     * i - K <= r <= i + K, j - K <= c <= j + K
     * (r, c) 在矩阵内。
     *
     *
     * 示例 1：
     *
     * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
     * 输出：[[12,21,16],[27,45,33],[24,39,28]]
     * 示例 2：
     *
     * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
     * 输出：[[45,45,45],[45,45,45],[45,45,45]]
     *
     */
    public int[][] matrixBlockSumV1(int[][] mat, int K) {
        int rows = mat.length, cols = mat[0].length, rTop, rBottom, cLeft, cRight, temp;
        int[][] ans = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rTop = Math.max(0, i - K);
                rBottom = Math.min(rows - 1, i + K);
                cLeft = Math.max(0, j - K);
                cRight = Math.min(cols - 1, j + K);
                temp = 0;
                for (int k = rTop; k <= rBottom; k++) {
                    for (int l = cLeft; l <= cRight; l++) {
                        temp += mat[k][l];
                    }
                }
                ans[i][j] = temp;
            }
        }
        return ans;
    }

    public int[][] matrixBlockSum(int[][] mat, int K) {
        int row = mat.length, col = mat[0].length;
        int[][] prefix = new int[row + 1][col + 1];
        for (int i = 1; i <= row; ++i) {
            for (int j = 1; j <= col; ++j) {
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int[][] res = new int[row][col];
        int x0, y0, x1, y1;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                x0 = Math.max(0, i - K);
                y0 = Math.max(0, j - K);
                x1 = Math.min(row, i + K + 1);
                y1 = Math.min(col, j + K + 1);
                res[i][j] = prefix[x1][y1] - prefix[x1][y0] - prefix[x0][y1] + prefix[x0][y0];
            }
        }
        return res;
    }

}

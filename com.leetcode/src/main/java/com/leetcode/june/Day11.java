package com.leetcode.june;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-10 23:08:53
 * @author: wanglong16@meicai.cn
 */
public class Day11 {

    /**
     * 1337. 矩阵中战斗力最弱的 K 行
     * 给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。
     *
     * 请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。
     *
     * 如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
     *
     * 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
     *
     *
     *
     * 示例 1：
     *
     * 输入：mat =
     * [[1,1,0,0,0],
     *  [1,1,1,1,0],
     *  [1,0,0,0,0],
     *  [1,1,0,0,0],
     *  [1,1,1,1,1]],
     * k = 3
     * 输出：[2,0,3]
     * 解释：
     * 每行中的军人数目：
     * 行 0 -> 2
     * 行 1 -> 4
     * 行 2 -> 1
     * 行 3 -> 2
     * 行 4 -> 5
     * 从最弱到最强对这些行排序后得到 [2,0,3,1,4]
     * 示例 2：
     *
     * 输入：mat =
     * [[1,0,0,0],
     *  [1,1,1,1],
     *  [1,0,0,0],
     *  [1,0,0,0]],
     * k = 2
     * 输出：[0,2]
     * 解释：
     * 每行中的军人数目：
     * 行 0 -> 1
     * 行 1 -> 4
     * 行 2 -> 1
     * 行 3 -> 1
     * 从最弱到最强对这些行排序后得到 [0,2,3,1]
     *
     *
     * 提示：
     *
     * m == mat.length
     * n == mat[i].length
     * 2 <= n, m <= 100
     * 1 <= k <= m
     * matrix[i][j] 不是 0 就是 1
     */
    public int[] kWeakestRows(int[][] mat, int k) {
        int[][] arr = new int[mat.length][2];
        for (int i = 0; i < mat.length; i++) {
            int jr = 0;
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] != 1) {
                    break;
                }
                jr ++;
            }
            arr[i][0] = i;
            arr[i][1] = jr;
        }
        Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i][0];
        }
        return ans;
    }

    /**
     * 1796. 字符串中第二大的数字
     * 给你一个混合字符串 s ，请你返回 s 中 第二大 的数字，如果不存在第二大的数字，请你返回 -1 。
     *
     * 混合字符串 由小写英文字母和数字组成。
     *
     *
     *
     * 示例 1：
     *
     * 输入：s = "dfa12321afd"
     * 输出：2
     * 解释：出现在 s 中的数字包括 [1, 2, 3] 。第二大的数字是 2 。
     * 示例 2：
     *
     * 输入：s = "abc1111"
     * 输出：-1
     * 解释：出现在 s 中的数字只包含 [1] 。没有第二大的数字。
     *
     *
     * 提示：
     *
     * 1 <= s.length <= 500
     * s 只包含小写英文字母和（或）数字。
     */
    public int secondHighest(String s) {
        Set<Integer> integers = new HashSet<>();
        for (Character chr : s.toCharArray()) {
            if (chr >= '0' && chr <= '9') {
                integers.add(chr - '0');
            }
        }
        List<Integer> list = new ArrayList<>(integers);
        list.sort((a, b) -> b - a);
        if (integers.size() < 2) {
            return -1;
        }
        return list.get(1);
    }

    /**
     * 1060. 有序数组中的缺失元素
     * 现有一个按 升序 排列的整数数组 nums ，其中每个数字都 互不相同 。
     *
     * 给你一个整数 k ，请你找出并返回从数组最左边开始的第 k 个缺失数字。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [4,7,9,10], k = 1
     * 输出：5
     * 解释：第一个缺失数字为 5 。
     * 示例 2：
     *
     * 输入：nums = [4,7,9,10], k = 3
     * 输出：8
     * 解释：缺失数字有 [5,6,8,...]，因此第三个缺失数字为 8 。
     * 示例 3：
     *
     * 输入：nums = [1,2,4], k = 3
     * 输出：6
     * 解释：缺失数字有 [3,5,6,7,...]，因此第三个缺失数字为 6 。
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 5 * 104
     * 1 <= nums[i] <= 107
     * nums 按 升序 排列，其中所有元素 互不相同 。
     * 1 <= k <= 108
     *
     *
     * 进阶：你可以设计一个对数时间复杂度（即，O(log(n))）的解决方案吗？
     */
    public int missingElementV1(int[] nums, int k) {
        int start = nums[0], missTotal = 0, missCurrent;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != start + 1) {
                for (int j = start + 1; j < nums[i]; j++) {
                    missTotal ++;
                    missCurrent = j;
                    if (missTotal >= k) {
                        return missCurrent;
                    }
                }
                if (i == nums.length - 1) {
                    return k - missTotal + nums[nums.length - 1];
                }
            }
            start = nums[i];
        }
        return -1;
    }


    int missing(int idx, int[] nums) {
        return nums[idx] - nums[0] - idx;
    }

    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        if (k > missing(n - 1, nums)) {
            return nums[n - 1] + k - missing(n - 1, nums);
        }
        int left = 0, right = n - 1, pivot;
        while (left != right) {
            pivot = left + (right - left) / 2;
            if (missing(pivot, nums) < k) {
                left = pivot + 1;
            } else {
                right = pivot;
            }
        }
        return nums[left - 1] + k - missing(left - 1, nums);
    }

}

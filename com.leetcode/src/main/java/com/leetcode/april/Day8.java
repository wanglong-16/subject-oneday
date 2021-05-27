package com.leetcode.april;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-04-12 08:06:17
 * @author: wanglong16@meicai.cn
 */
public class Day8 {

    /**
     * 179. 最大数
     * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
     *
     * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [10,2]
     * 输出："210"
     * 示例 2：
     *
     * 输入：nums = [3,30,34,5,9]
     * 输出："9534330"
     * 示例 3：
     *
     * 输入：nums = [1]
     * 输出："1"
     * 示例 4：
     *
     * 输入：nums = [10]
     * 输出："10"
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 109
     */
    public String largestNumberV1(int[] nums) {
        Integer[] integers = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            integers[i] = nums[i];
        }
        Arrays.sort(integers, new MyComparator<>());
        StringBuilder ans = new StringBuilder();
        for (Integer integer : integers) {
            ans.append(integer);
        }
        return ans.toString();
    }

    private class MyComparator<Integer> implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            String left = o1 + String.valueOf(o2);
            String right = o2 + String.valueOf(o1);
            return (int) (Long.parseLong(right) - Long.parseLong(left));
        }
    }

    /**
     *
     */
    public String largestNumber(int[] nums) {
        int n = nums.length;
        // 转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }

        Arrays.sort(numsArr, (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        });

        if (numsArr[0] == 0) {
            return "0";
        }
        StringBuilder ret = new StringBuilder();
        for (int num : numsArr) {
            ret.append(num);
        }
        return ret.toString();
    }

    /**
     * 1708. 长度为 K 的最大子数组
     * 在数组 A 和数组 B 中，对于第一个满足 A[i] != B[i] 的索引 i ，当 A[i] > B[i] 时，数组 A 大于数组 B。
     *
     * 例如，对于索引从 0 开始的数组：
     *
     * [1,3,2,4] > [1,2,2,4] ，因为在索引 1 上， 3 > 2。
     * [1,4,4,4] < [2,1,1,1] ，因为在索引 0 上， 1 < 2。
     * 一个数组的子数组是原数组上的一个连续子序列。
     *
     * 给定一个包含不同整数的整数类型数组 nums ，返回 nums 中长度为 k 的最大子数组。
     *
     *
     *
     * 示例 1:
     *
     * 输入: nums = [1,4,5,2,3], k = 3
     * 输出: [5,2,3]
     * 解释: 长度为 3 的子数组有： [1,4,5]、 [4,5,2] 和 [5,2,3]。
     * 在这些数组中， [5,2,3] 是最大的。
     * Example 2:
     *
     * 输入: nums = [1,4,5,2,3], k = 4
     * 输出: [4,5,2,3]
     * 解释: 长度为 4 的子数组有： [1,4,5,2] 和 [4,5,2,3]。
     * 在这些数组中， [4,5,2,3] 是最大的。
     * 示例 3:
     *
     * 输入: nums = [1,4,5,2,3], k = 1
     * 输出: [5]
     *
     *
     * 提示：
     *
     * 1 <= k <= nums.length <= 105
     * 1 <= nums[i] <= 109
     * nums 中的所有整数都是不同的。
     */
    public int[] largestSubarray(int[] nums, int k) {
        int[] ans = new int[k];
        int maxIdx = 0;
        for (int i = 0; i < nums.length - k; i++) {
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }
        if (maxIdx + k - maxIdx >= 0) {
            System.arraycopy(nums, maxIdx, ans, 0, maxIdx + k - maxIdx);
        }
        return ans;
    }
}

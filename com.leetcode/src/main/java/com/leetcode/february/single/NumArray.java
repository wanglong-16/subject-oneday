package com.leetcode.february.single;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-06 10:21:47
 * @author: wanglong16@meicai.cn
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 303. 区域和检索 - 数组不可变
 * 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
 *
 * 实现 NumArray 类：
 *
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，
 * 包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
 */
public class NumArray {

    List<Integer> arr;

    public NumArray(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        arr = new ArrayList<>(nums.length);
        for (int num : nums) {
            arr.add(num);
        }
    }

    public int sumRange(int i, int j) {
        if (i > arr.size() - 1 || j < 0) {
            return 0;
        }
        int start = Math.max(i, 0), end = Math.min(j, arr.size() - 1), result = 0;
        for (int k = start; k <= end; k++) {
            result += arr.get(k);
        }
        return result;
    }

}

package com.leetcode.may;

import java.util.Arrays;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-05-04 08:43:01
 * @author: wanglong16@meicai.cn
 */
public class Day4 {

    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length], right = new int[nums.length], ans = new int[nums.length];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 1; i < nums.length; i++) {
            left[i] *= nums[i - 1] * left[i - 1];
            right[i] *= nums[nums.length - i] * right[i - 1];
        }
        for (int i = 0; i < nums.length; i++) {
            ans[i] = left[i] * right[i];
        }
        return ans;
    }
}

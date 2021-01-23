package com.leetcode.january;

import java.util.Arrays;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-20 22:37:43
 * @author: wanglong16@meicai.cn
 */
public class SolutionOne {

    /**
     * 数组中有正负数，求出数组中三个数的最大乘积
     * @param nums
     * @return
     */
    public int maximumProduct(int [] nums) {
        //分类讨论：
        //1、找到俩个绝对值最大的负数 + 最大的一个正数 备选
        //2、三个绝对值最大的正数 备选
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);
    }

    /**
     * 线性扫描 得出五个数 最大的三个，最小的俩个
     * @param nums
     * @return
     */
    public int maximumProductV2(int[] nums) {
        // 最小的和第二小的
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        // 最大的、第二大的和第三大的
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }

            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }

}

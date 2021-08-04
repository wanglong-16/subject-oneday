package com.leetcode.august;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-04 08:37:46
 * @author: wanglong16@meicai.cn
 */
public class Day2 {

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int x = nums[i], y = nums[j], z = nums[k];
                    if (x + y > z) {
                        ans ++;
                    }
                }
            }
        }
        return ans;
    }

    public boolean isTriangle(int x, int y, int z) {
        return x + y > z && x + z > y && y + z > x;
    }
}

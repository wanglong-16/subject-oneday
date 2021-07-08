package com.leetcode.july;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-08 08:49:24
 * @author: wanglong16@meicai.cn
 */
public class Day6 {

    public int numSubarraysWithSum(int[] nums, int goal) {
        int left = 0, right = 0, cnt = 0, ans = 0;
        cnt = nums[0];
        while (right < nums.length) {
            if (cnt < goal) {
                right ++;
                cnt += nums[right];
            } else {
                if (cnt == goal) {
                    ans ++;
                } else {
                    left ++;
                    cnt -= nums[left];
                }
            }
        }
        return ans;
    }
}

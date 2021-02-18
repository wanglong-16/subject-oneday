package com.leetcode.february;

import java.util.Arrays;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-18 09:28:57
 * @author: wanglong16@meicai.cn
 */
public class SolutionTen {

    /**
     * 137. 只出现一次的数字 II
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
     *
     * 说明：
     *
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     *
     * 输入: [2,2,3,2]
     * 输出: 3
     * 示例 2:
     *
     * 输入: [0,1,0,1,0,1,99]
     * 输出: 99
     */
    public int singleNumberV1(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        //排序加比较
        int result = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 && nums[0] != nums[1]) {
                result = nums[0];
                break;
            } else if (i == nums.length - 1 && nums[nums.length - 2] != nums[nums.length - 1]) {
                result = nums[nums.length - 1];
                break;
            } else {
                if (nums[i] != nums[i + 1] && nums[i] != nums[i - 1]) {
                    result = nums[i];
                    break;
                }
            }
        }
        return result;
    }

    // 位运算
    public int singleNumber(int[] nums) {
        return 0;
    }
    
}

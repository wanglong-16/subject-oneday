package com.leetcode.february;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-01 21:27:53
 * @author: wanglong16@meicai.cn
 */
public class SolutionTwo {


    /**
     * 283. 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 示例:
     *
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     *
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     */
    public void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                for (int j = i + 1;j < nums.length; j++) {
                    if (nums[j] != 0) {
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                    }
                }
            }
        }
    }

    /**
     * 371. 两整数之和
     * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
     *
     * 示例 1:
     *
     * 输入: a = 1, b = 2
     * 输出: 3
     * 示例 2:
     *
     * 输入: a = -2, b = 3
     * 输出: 1
     */
    public int getSum(int a, int b) {
        //todo
        return 0;
    }


    /**
     * 258. 各位相加
     * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
     *
     * 示例:
     *
     * 输入: 38
     * 输出: 2
     * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
     * 进阶:
     * 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
     */
    public int addDigits(int num) {
        int total = 0;
        while (num != 0) {
            total += num % 10;
            num /= 10;
        }
        if (total < 10) {
            return total;
        } else {
            return addDigits(total);
        }
    }

}

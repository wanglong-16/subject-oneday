package com.leetcode.june;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-08 15:11:32
 * @author: wanglong16@meicai.cn
 */
public class Day8 {

    /**
     * 738. 单调递增的数字
     * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
     *
     * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
     *
     * 示例 1:
     *
     * 输入: N = 10
     * 输出: 9
     * 示例 2:
     *
     * 输入: N = 1234
     * 输出: 1234
     * 示例 3:
     *
     * 输入: N = 332
     * 输出: 299
     * 说明: N 是在 [0, 10^9] 范围内的一个整数。
     *
     * 通过次数36,753提交次数73,292
     */
    public int monotoneIncreasingDigits(int n) {
        StringBuilder sb = new StringBuilder();
        int lastNum, nextNum;
        boolean exNext = false;
        while (n > 0) {
            lastNum = n % 10;
            n /= 10;
            if (n > 0) {
                nextNum = n % 10;
                n /= 10;
                exNext = nextNum >= lastNum;
                sb.append(exNext ? 9 : lastNum);
                n = exNext ? n * 10 + nextNum - 1 : n * 10 + nextNum;
            } else {
                if (exNext) {
                    if (lastNum > 0) {
                        sb.append(lastNum);
                    }
                } else {
                    sb.append(lastNum);
                }
            }
        }
        return Integer.parseInt(sb.reverse().toString());
    }

    /**
     * 面试题 17.21. 直方图的水量
     * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
     *
     *
     *
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。
     *
     * 示例:
     *
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     * 通过次数41,527提交次数65,491
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];
        int maxL = height[0], maxR = height[height.length - 1];
        for (int i = 0; i < height.length; i++) {
            maxL = Math.max(height[i], maxL);
            maxLeft[i] = maxL;
        }
        for (int i = height.length - 1; i >= 0; i--) {
            maxR = Math.max(height[i], maxR);
            maxRight[i] = maxR;
        }
        int ans = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(maxLeft[i - 1], maxRight[i + 1]);
            if (min > height[i]) {
                ans += min - height[i];
            }
        }
        return ans;
    }

    public int trap1(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++left;
            } else {
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Day8 day8 = new Day8();
        System.out.println(day8.trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}

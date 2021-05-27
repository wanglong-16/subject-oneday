package com.leetcode.april;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-04-08 08:04:03
 * @author: wanglong16@meicai.cn
 */
public class Day6 {

    /**
     * 153. 寻找旋转排序数组中的最小值
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     * 若旋转 4 次，则可以得到 [0,1,2,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     *
     * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [3,4,5,1,2]
     * 输出：1
     * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
     * 示例 2：
     *
     * 输入：nums = [4,5,6,7,0,1,2]
     * 输出：0
     * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
     * 示例 3：
     *
     * 输入：nums = [11,13,15,17]
     * 输出：11
     * 解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
     *
     *
     * 提示：
     *
     * n == nums.length
     * 1 <= n <= 5000
     * -5000 <= nums[i] <= 5000
     * nums 中的所有整数 互不相同
     * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
     */
    public int findMin(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return nums[i];
            }
        }
        return nums[0];
    }

    /**
     * 1217. 玩筹码
     * 数轴上放置了一些筹码，每个筹码的位置存在数组 chips 当中。
     *
     * 你可以对 任何筹码 执行下面两种操作之一（不限操作次数，0 次也可以）：
     *
     * 将第 i 个筹码向左或者右移动 2 个单位，代价为 0。
     * 将第 i 个筹码向左或者右移动 1 个单位，代价为 1。
     * 最开始的时候，同一位置上也可能放着两个或者更多的筹码。
     *
     * 返回将所有筹码移动到同一位置（任意位置）上所需要的最小代价。
     *
     *
     *
     * 示例 1：
     *
     * 输入：chips = [1,2,3,4]
     * 输出：1
     * 解释：第二个筹码移动到位置三的代价是 1，第一个筹码移动到位置三的代价是 0，总代价为 1。
     * 示例 2：
     *
     * 输入：chips = [2,2,2,2,3,4,4,4]
     * 输出：2
     * 解释：第四和第五个筹码移动到位置二的代价都是 1，所以最小总代价为 2。
     */
    public int minCostToMoveChips(int[] position) {
        int e = 0, o = 0;
        for (int i : position) {
            if (i % 2 == 1) {
                e ++;
            } else {
                o ++;
            }
        }
        return Math.min(e, o);
    }

    /**
     * 1790. 仅执行一次字符串交换能否使两个字符串相等
     * 给你长度相等的两个字符串 s1 和 s2 。一次 字符串交换 操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。
     *
     * 如果对 其中一个字符串 执行 最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：s1 = "bank", s2 = "kanb"
     * 输出：true
     * 解释：例如，交换 s2 中的第一个和最后一个字符可以得到 "bank"
     * 示例 2：
     *
     * 输入：s1 = "attack", s2 = "defend"
     * 输出：false
     * 解释：一次字符串交换无法使两个字符串相等
     * 示例 3：
     *
     * 输入：s1 = "kelb", s2 = "kelb"
     * 输出：true
     * 解释：两个字符串已经相等，所以不需要进行字符串交换
     * 示例 4：
     *
     * 输入：s1 = "abcd", s2 = "dcba"
     * 输出：false
     */
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        List<Integer> pos = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                pos.add(i);
            }
            if (pos.size() > 2) {
                return false;
            }
        }
        return s1.charAt(pos.get(0)) == s2.charAt(pos.get(1))
                && s1.charAt(pos.get(1)) == s2.charAt(pos.get(0));
    }

    /**
     * 1133. 最大唯一数
     * 给你一个整数数组 A，请找出并返回在该数组中仅出现一次的最大整数。
     *
     * 如果不存在这个只出现一次的整数，则返回 -1。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[5,7,3,9,4,9,8,3,1]
     * 输出：8
     * 解释：
     * 数组中最大的整数是 9，但它在数组中重复出现了。而第二大的整数是 8，它只出现了一次，所以答案是 8。
     * 示例 2：
     *
     * 输入：[9,9,8,8]
     * 输出：-1
     * 解释：
     * 数组中不存在仅出现一次的整数。
     */
    public int largestUniqueNumber(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer in : A) {
            map.merge(in, 1, Integer::sum);
        }
        int max = Integer.MIN_VALUE;
        for (Map.Entry e : map.entrySet()) {
            if (Integer.parseInt(e.getValue().toString()) == 1) {
                max = Math.max(Integer.parseInt(e.getKey().toString()), max);
            }
        }
        return max == Integer.MIN_VALUE ? -1 : max;
    }

    /**
     * 1118. 一月有多少天
     * 指定年份 Y 和月份 M，请你帮忙计算出该月一共有多少天。
     *
     *
     *
     * 示例 1：
     *
     * 输入：Y = 1992, M = 7
     * 输出：31
     * 示例 2：
     *
     * 输入：Y = 2000, M = 2
     * 输出：29
     * 示例 3：
     *
     * 输入：Y = 1900, M = 2
     * 输出：28
     *
     *
     * 提示：
     *
     * 1583 <= Y <= 2100
     * 1 <= M <= 12
     */
    public int numberOfDays(int Y, int M) {
        int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};
        boolean isRunNian = false;
        if (Y % 100 == 0) {
            isRunNian =  Y % 400 == 0;
        } else {
            isRunNian = Y % 4 == 0;
        }
        if (isRunNian && M == 2) {
            return 29;
        } else {
            return days[M - 1];
        }
    }
}

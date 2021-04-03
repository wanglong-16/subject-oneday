package com.leetcode.april;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-04-01 21:45:01
 * @author: wanglong16@meicai.cn
 */
public class Day1 {

    /**
     * 1800. 最大升序子数组和
     * 给你一个正整数组成的数组 nums ，返回 nums 中一个 升序 子数组的最大可能元素和。
     *
     * 子数组是数组中的一个连续数字序列。
     *
     * 已知子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，若对所有 i（l <= i < r），numsi < numsi+1 都成立，则称这一子数组为 升序 子数组。注意，大小为 1 的子数组也视作 升序 子数组。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [10,20,30,5,10,50]
     * 输出：65
     * 解释：[5,10,50] 是元素和最大的升序子数组，最大元素和为 65 。
     * 示例 2：
     *
     * 输入：nums = [10,20,30,40,50]
     * 输出：150
     * 解释：[10,20,30,40,50] 是元素和最大的升序子数组，最大元素和为 150 。
     * 示例 3：
     *
     * 输入：nums = [12,17,15,13,10,11,12]
     * 输出：33
     * 解释：[10,11,12] 是元素和最大的升序子数组，最大元素和为 33 。
     * 示例 4：
     *
     * 输入：nums = [100,10,1]
     * 输出：100
     */
    public int maxAscendingSum(int[] nums) {
        int temp = nums[0], ans = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                temp += nums[i];
            } else {
                ans = Math.max(ans, temp);
                temp = nums[i];
            }
        }
        return Math.max(ans, temp);
    }

    /**
     * 905. 按奇偶排序数组
     * 给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
     *
     * 你可以返回满足此条件的任何数组作为答案。
     *
     *
     *
     * 示例：
     *
     * 输入：[3,1,2,4]
     * 输出：[2,4,3,1]
     * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
     */
    public int[] sortArrayByParity(int[] A) {
        int[] ans = new int[A.length];
        int left = 0, right = A.length - 1;
        for (Integer in : A) {
            if (in % 2 == 0) {
                ans[left] = in;
                left ++;
            } else {
                ans[right] = in;
                right --;
            }
        }
        return ans;
    }

    /**
     * 1006. 笨阶乘
     * 通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。
     *
     * 相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)和减法(-)。
     *
     * 例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。
     *
     * 另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。
     *
     * 实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。
     *
     *
     *
     * 示例 1：
     *
     * 输入：4
     * 输出：7
     * 解释：7 = 4 * 3 / 2 + 1
     * 示例 2：
     *
     * 输入：10
     * 输出：12
     * 解释：12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
     */
    public int clumsy(int n) {
        int[] special = new int[]{1,2,6,7};
        int[] diff = new int[]{1,2,2,-1};
        if (n <= 4) {
            return special[(n - 1) % 4];
        }
        return n + diff[n % 4];
    }

    /**
     * 1200. 最小绝对差
     * 给你个整数数组 arr，其中每个元素都 不相同。
     *
     * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr = [4,2,1,3]
     * 输出：[[1,2],[2,3],[3,4]]
     * 示例 2：
     *
     * 输入：arr = [1,3,6,10,15]
     * 输出：[[1,3]]
     * 示例 3：
     *
     * 输入：arr = [3,8,-10,23,19,-4,-14,27]
     * 输出：[[-14,-10],[19,23],[23,27]]
     */
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            minDiff = Math.min(arr[i] - arr[i - 1], minDiff);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i -1] == minDiff) {
                ans.add(Arrays.asList(arr[i - 1], arr[i]));
            }
        }
        return ans;
    }

    /**
     * 821. 字符的最短距离
     * 给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
     *
     * 返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
     *
     * 两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。
     *
     *
     *
     * 示例 1：
     *
     * 输入：s = "loveleetcode", c = "e"
     * 输出：[3,2,1,0,1,0,0,1,2,2,1,0]
     * 解释：字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
     * 距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
     * 距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 3 。
     * 对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
     * 距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
     * 示例 2：
     *
     * 输入：s = "aaab", c = "b"
     * 输出：[3,2,1,0]
     */
    public int[] shortestToChar(String s, char c) {
        List<Integer> zeroPos = new ArrayList<>();
        zeroPos.add(Integer.MIN_VALUE);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                zeroPos.add(i);
            }
        }
        zeroPos.add(Integer.MAX_VALUE);
        int[] ans = new int[s.length()];
        int leftP = zeroPos.get(0), rightP = zeroPos.get(1);
        for (int i = 0; i < s.length(); i++) {
            if (i >= rightP) {
                for (int j = 0; j < zeroPos.size(); j++) {
                    if (i < zeroPos.get(j)) {
                        leftP = zeroPos.get(j - 1);
                        rightP = zeroPos.get(j);
                        break;
                    }
                }
            } else {
                ans[i] = Math.min(Math.abs(i - leftP), Math.abs(i - rightP));
            }
        }
        return ans;
    }
}

package com.leetcode.june;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-03 09:31:15
 * @author: wanglong16@meicai.cn
 */
public class Day3 {

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        counter.put(0, -1);
        int current = 0, ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                current --;
            } else {
                current ++;
            }
            if (counter.get(current) != null) {
                ans = Math.max(ans, i - counter.get(current));
            } else {
                counter.put(current, i);
            }
        }
        return ans;
    }

    /**
     * 539. 最小时间差
     * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
     *
     *
     *
     * 示例 1：
     *
     * 输入：timePoints = ["23:59","00:00"]
     * 输出：1
     * 示例 2：
     *
     * 输入：timePoints = ["00:00","23:59","00:00"]
     * 输出：0
     *
     *
     * 提示：
     *
     * 2 <= timePoints <= 2 * 104
     * timePoints[i] 格式为 "HH:MM"
     */
    public int findMinDifference(List<String> timePoints) {
        int minDiff = 0;
        int[] minutes = new int[timePoints.size()];

        for (int i = 0; i < timePoints.size(); i++) {
            minutes[i] = transToMinutes(timePoints.get(i));
        }
        Arrays.sort(minutes);
        for (int i = 0; i < minutes.length - 1; i++) {
            minDiff = Math.min(minDiff, Math.abs(minutes[i + 1] - minutes[i]));
        }
        return minDiff;
    }

    private int transToMinutes(String t1) {
        String[] t1Str = t1.split(":");
        int hour;
        if (t1Str[0].charAt(0) == '0') {
            hour = Integer.parseInt(t1Str[0].substring(1, 2));
        } else {
            hour = Integer.parseInt(t1Str[0]);
        }
        int minute;
        if (t1Str[1].charAt(0) == '0') {
            minute = Integer.parseInt(t1Str[1].substring(1, 2));
        } else {
            minute = Integer.parseInt(t1Str[1]);
        }
        return hour * 60 + minute;
    }

    /**
     * 347. 前 K 个高频元素
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     *
     *
     *
     * 示例 1:
     *
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * 示例 2:
     *
     * 输入: nums = [1], k = 1
     * 输出: [1]
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 105
     * k 的取值范围是 [1, 数组中不相同的元素的个数]
     * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
     *
     *
     * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (Integer in : nums) {
            counter.merge(in, 1 ,Integer::sum);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(counter.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = list.get(i).getKey();
        }
        return ans;
    }

    public boolean isPowerOfThree(int n) {
        if (n < 0) {
            return false;
        }
        while (n > 1) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return n == 1;
    }

    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return strToNum(targetWord) == strToNum(firstWord) + strToNum(secondWord);
    }

    private int strToNum(String str) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (Character ch : str.toCharArray()) {
            if (ch != 'a') {
                isFirst = false;
            }
            if (!isFirst) {
                sb.append((int) ch - 'a');
            }
        }
        return sb.length() == 0 ? 0 : Integer.parseInt(sb.toString());
    }


    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    /**
     * 494. 目标和
     * 给你一个整数数组 nums 和一个整数 target 。
     *
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     *
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     * 示例 2：
     *
     * 输入：nums = [1], target = 1
     * 输出：1
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 20
     * 0 <= nums[i] <= 1000
     * 0 <= sum(nums[i]) <= 1000
     * -1000 <= target <= 100
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int total = 1 << nums.length, ans = 0, sign = 0, calculate = 0;
        for (int i = 0; i < total; i++) {
            sign = i;
            calculate = 0;
            for (int num : nums) {
                if ((sign & 1) == 1) {
                    calculate += num;
                } else {
                    calculate -= num;
                }
                sign >>= 1;
            }
            if (target == calculate) {
                ++ans;
            }
        }
        return ans;
    }


}

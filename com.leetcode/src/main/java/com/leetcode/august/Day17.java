package com.leetcode.august;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-22 22:33:57
 * @author: wanglong16@meicai.cn
 */
public class Day17 {

    public int findGCD(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (Integer in : nums) {
            min = Math.min(in, min);
            max = Math.max(in, max);
        }
        for (int i = min; i >= 1; i--) {
            if (max % i == 0 && min % i == 0) {
                return i;
            }
        }
        return 1;
    }

    public String findDifferentBinaryString(String[] nums) {
        List<String> list = new ArrayList<>();
        for (String s : nums) {
            if (!list.contains(s)) {
                list.add(s);
            }
        }
        Collections.sort(list);
        int len = nums[0].length();
        for (int i = 0; i < list.size(); i++) {
            String str = genIntStr(i, len);
            if (!str.equals(list.get(i))) {
                return str;
            }
        }
        return genIntStr(nums.length, len);
    }

    public String genIntStr(int bit, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append((bit >> i & 1) == 1 ? "1" : 0);
        }
        return sb.reverse().toString();
    }

    public int minimizeTheDifference(int[][] mat, int target) {
        boolean[][] dp = new boolean[mat.length + 1][4900];
        dp[0][0] = true;

        for (int i = 1; i <= mat.length; i++) {
            for (int j = 0; j < 4900; j++) {
                for (int k = 0; k < mat[0].length; k++) {
                    if (j - mat[i - 1][k] >= 0 && dp[i - 1][j - mat[i - 1][k]]) {
                        dp[i][j] = true;
                        break;
                    }
                    dp[i][j] = false;
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        for (int j = 0; j < 4900; j++) {
            if (dp[mat.length][j]) {
                ret = Math.min(Math.abs(target - j), ret);
            }
        }
        return ret;
    }

    public int getMaximumGenerated(int n) {
        if (n <= 1) {
            return n;
        }
        int[] tem = new int[n + 1];
        tem[0] = 0; tem[1] = 1;
        int max = Integer.MIN_VALUE;
        for (int i = 2; i < n + 1; i++) {
            int idx = i / 2;
            if (i % 2 == 0) {
                tem[i] = tem[idx];
            } else {
                tem[i] = tem[idx] + tem[idx + 1];
            }
            max = Math.max(max, tem[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        Day17 day17 = new Day17();
        System.out.println(day17.getMaximumGenerated(7));
    }
}

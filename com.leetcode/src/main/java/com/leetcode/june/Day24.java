package com.leetcode.june;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-25 23:39:38
 * @author: wanglong16@meicai.cn
 */
public class Day24 {

    public int findCircleNum(int[][] isConnected) {
        int cites = isConnected.length; // n 个城市
        boolean[] vis = new boolean[cites];
        int provinces = 0;
        for (int i = 0; i < cites; i++) {
            if (!vis[i]) {
                dfs(isConnected, vis, cites, i);
                provinces ++;
            }
        }
        return provinces;
    }

    private void dfs(int[][] isConn, boolean[] vis, int cities, int curCity) {
        for (int i = 0; i < cities; i++) {
            if (isConn[curCity][i] == 1 && !vis[i]) {
                vis[i] = true;
                dfs(isConn, vis, cities, i);
            }
        }
    }


    public String removeOccurrences(String s, String part) {
        while (s.contains(part)) {
            int st = s.indexOf(part);
            s = s.substring(0, st) + s.substring(st + part.length());
        }
        return s;
    }

    public boolean canBeIncreasing(int[] nums) {
        if (nums.length == 2) {
            return nums[1] > nums[0];
        }
        int firstDecr = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                //第一个递减位
                firstDecr = i;
                break;
            }
        }

        return isIncr(nums, firstDecr) || isIncr(nums, firstDecr - 1);
    }

    public boolean isIncr(int[] nums, int exceptPos) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i != exceptPos) {
                list.add(nums[i]);
            }
        }
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }


    public long maxAlternatingSum(int[] nums) {
        int cur = 0, len = nums.length - 1;
        boolean plus = true;
        long ans = 0;
        while (cur < len) {
            int index = nextPos(nums, cur, plus);
            if (index != cur) {
                cur = index;
            }
            ans += plus ? nums[cur] : - nums[cur];
            plus = !plus;
            if (!plus && isDecr(nums, cur)) {
                return ans;
            }
        }
        return ans;
    }

    public boolean isDecr(int[] nums, int start) {
        for (int i = start; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    //找下一个更小或更大的索引
    public int nextPos(int[] nums, int curIndex, boolean findMax) {
        if (curIndex < nums.length - 1) {
                if (findMax) {
                    for (int i = curIndex + 1; i < nums.length; i++) {
                        if (nums[i] < nums[curIndex]) {
                            return curIndex;
                        } else {
                            curIndex = i;
                        }
                    }
                } else {
                    for (int i = curIndex + 1; i < nums.length; i++) {
                        if (nums[i] > nums[curIndex]) {
                            return curIndex;
                        } else {
                            curIndex = i;
                        }
                    }
                }
        }
        return curIndex;
    }


    public static void main(String[] args) {
        Day24 day24 = new Day24();
        System.out.println(day24.maxAlternatingSum(new int[] {4,2,5,3}));
    }
}

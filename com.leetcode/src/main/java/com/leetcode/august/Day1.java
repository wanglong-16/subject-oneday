package com.leetcode.august;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-03 21:30:30
 * @author: wanglong16@meicai.cn
 */
public class Day1 {

    // abcd
    // 3 2 1

    public int longestRepeatingSubstring(String s) {
        int len = s.length();
        for (int i = len - 1; i >= 1; i--) {
            for (int j = 0; j <= len - i; j++) {
                String str = s.substring(j, j + i);
                String p = s.substring(j + 1);
                if (p.contains(str)) {
                    return str.length();
                }
            }
        }
        return -1;
    }

    public int findUnsortedSubarrayV1(int[] nums) {
        int[] temp = Arrays.copyOf(nums, nums.length);
        Arrays.sort(temp);
        int left = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != temp[i]) {
                left = i;
                break;
            }
        }
        for (int i = nums.length; i >= 0; i--) {
            if (nums[i] != temp[i]) {
                right = i;
                break;
            }
        }
        return right - left;
    }

    public int findUnsortedSubarray(int[] nums) {
        int left = -1, right = -1;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (max > nums[i]) {
                right = i;
            } else {
                max = nums[i];
            }
            if (min < nums[nums.length - i - 1]) {
                left = nums.length - i - 1;
            } else {
                min = nums[nums.length - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }


    int[] parents;
    int[] rank;
    int count;

    public int numSimilarGroups(String[] strs) {
        parents = new int[strs.length];
        rank = new int[strs.length];
        count = strs.length;
        for (int i = 0; i < strs.length; i++) {
            parents[i] = i;
            rank[i] = 1;
            count = strs.length;
        }
        for (int i = 0; i < strs.length; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    if (find(i) != find(j)) {
                        merge(i, j);
                    }
                }
            }
        }
        return count;
    }

    public boolean isSimilar(String str1, String str2) {
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;
            }
            if (count > 2) {
                return false;
            }
        }
        return true;
    }

    public int find(int x) {
        return x == parents[x] ? x : (parents[x] = find(parents[x]));
    }

    public void merge(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx != ry) {
            if (rank[rx] <= ry) {
                parents[rx] = ry;
                if (rank[rx] == rank[ry]) {
                    rank[ry] ++;
                }
            } else {
                parents[ry] = rx;
            }
            count --;
        }
    }

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        System.out.println(day1.findUnsortedSubarray(new int[] {2,6,4,8,10,9,15}));
    }


}

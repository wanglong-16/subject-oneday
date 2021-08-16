package com.leetcode.august;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-09 22:43:59
 * @author: wanglong16@meicai.cn
 */
public class Day7 {


    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.add(1L);
        int count = 0;
        Long cur = 1L;
        Set<Long> used = new HashSet<>();
        used.add(1L);
        while (count < n) {
            cur = queue.poll();
            for (Integer in : primes) {
                if (used.add(cur * in)) {
                    queue.offer(cur * in);
                }
            }
            ++ count;
        }
        return Math.toIntExact(cur);
    }

    public List<List<Integer>> permuteV1(int[] nums) {
        backTracking(nums, new ArrayList<>());
        return ans;
    }

    public void backTracking(int[] nums, List<Integer> temp) {
        if (temp.size() == nums.length) {
            ans.add(new ArrayList<>(temp));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!temp.contains(nums[i])) {
                    backTracking(nums, temp);
                    temp.remove(temp.size() - 1);
                    backTracking(nums, temp);
                }
            }
        }

    }

    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (Integer in : nums) {
            set.add(in);
        }
        dfs(set, new ArrayList<>());
        return ans;
    }

    public void dfs(Set<Integer> nums,  List<Integer> temp) {
        if (temp.size() == nums.size()) {
            ans.add(new ArrayList<>(temp));
        } else {
            for (Integer in : nums) {
                if (!temp.contains(in)) {
                    temp.add(in);
                    dfs(nums, temp);
                    temp.remove(in);
                }
            }
        }
    }

    public List<List<Integer>> subsetsV1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < 1 << nums.length; i++) {
            ans.add(countWithBit(i, nums));
        }
        return ans;
    }

    List<Integer> countWithBit(int bit, int[] nums) {
        List<Integer> tem = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (((bit >> i) & 1) == 1) {
                tem.add(nums[i]);
            }
        }
        return tem;
    }

    List<List<Integer>> subset = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backTrackSubSet(nums,0, new ArrayList<>());
        return subset;
    }

    public void backTrackSubSet(int[] nums, int index, List<Integer> temp) {
        subset.add(new ArrayList<>(temp));
        for (int i = index; i < nums.length; i++) {
            temp.add(nums[i]);
            backTrackSubSet(nums, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }

    public int minOperations(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int ans = 0,pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > pre) {
                pre = nums[i];
            } else {
                ans += pre - nums[i] + 1;
                pre ++;
            }
        }
        return ans;
    }

    public int[] countPoints(int[][] points, int[][] queries) {
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            for (int[] p : points) {
                if (inCircle(p, queries[i])) {
                    ans[i] ++;
                }
            }
        }
        return ans;
    }

    public boolean inCircle(int[] point, int[] circle) {
        return Math.pow(Math.abs(point[0] - circle[0]), 2) + Math.pow(Math.abs(point[1] - circle[1]), 2) <= circle[2] * circle[2];
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        for (int i = 0; i < n; i++) {
            int num = nums2[i], firstMax = -1;
            for (int j = 0; j < m; j++) {
                if (nums1[j] > num) {
                    firstMax = j;
                    break;
                }
            }
            if (firstMax != -1) {
                for (int j = m; j > firstMax; j--) {
                    nums1[j] = nums1[j - 1];
                }
                nums1[firstMax] = num;
            } else {
                nums1[m] = num;
            }
            m ++;
        }
    }


    public static void main(String[] args) {
        Day7 day7 = new Day7();
        day7.merge(new int[] {2, 0}, 1, new int[] {1}, 1);
        System.out.println();
    }
}

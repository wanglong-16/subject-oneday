package com.leetcode.november;

import com.leetcode.util.tree.TreeNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-11-30 09:32:21
 * @author: wanglong16@meicai.cn
 */
public class Day7 {

    public double findMaxAverage(int[] nums, int k) {
        int cnt = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            cnt += nums[i];
            if (i >= k - 1) {
                max = Math.max(max, cnt);
                cnt -= nums[i - k + 1];
            }
        }
        return (double) max / k;
    }

    int total = 0;

    public int findTilt(TreeNode root) {
        countTrNd(root);
        return total;
    }

    public int countTrNd(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int left = countTrNd(node.left);
            int right = countTrNd(node.right);
            total += Math.abs(left - right);
            return left + right + node.val;
        }
    }

    int[] parents;


    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int[] meeting : meetings) {
            union(meeting[0], meeting[1]);
        }

        Arrays.sort(meetings, Comparator.comparingInt(a -> a[2]));
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < parents.length; i++) {
            if (find(parents[i]) == 0 || find(parents[i]) == firstPerson) {
                ans.add(i);
            }
        }
        return ans;
    }

    public int find(int x) {
        return x == parents[x] ? x : (parents[x] = find(parents[x]));
    }

    public void union(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx != ry) {
            parents[y] = rx;
        }
    }

    public int maxPower(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char pre = s.charAt(0);
        int cnt = 1, ans = 1;
        for (int i = 1; i < s.length(); i++) {
            if (pre == s.charAt(i)) {
                cnt ++;
            } else {
                ans = Math.max(ans, cnt);
                cnt = 1;
                pre = s.charAt(i);
            }
        }
        return Math.max(ans, cnt);
    }

    public static void main(String[] args) {
        Day7 day7 = new Day7();
        int[][] arr = new int[][] {
                {1,2,5},{2,3,8},{1,5,10}
        };
        System.out.println(day7.findAllPeople(6, arr, 1));
    }

}

package com.leetcode.august;

import com.leetcode.util.tree.TreeNode;

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

    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

    List<List<Integer>> ans = new ArrayList<>();
    int n;
    int[][] graph;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.graph = graph;
        n = graph.length - 1;
        List<Integer> start = new ArrayList<>();
        start.add(0);
        backTracking(0, start);
        return ans;
    }

    public void backTracking(int cur, List<Integer> temp) {
        if (cur == n) {
            ans.add(new ArrayList<>(temp));
        } else {
            int[] nexts = graph[cur];
            if (nexts.length != 0) {
                for (int next : nexts) {
                    temp.add(next);
                    backTracking(next, temp);
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }

     // This is the interface that allows for creating nested lists.
      // You should not implement it, or speculate about its implementation
      public interface NestedInteger {

          // @return true if this NestedInteger holds a single integer, rather than a nested list.
          public boolean isInteger();

          // @return the single integer that this NestedInteger holds, if it holds a single integer
          // Return null if this NestedInteger holds a nested list
          public Integer getInteger();

          // Set this NestedInteger to hold a single integer.
          public void setInteger(int value);

          // Set this NestedInteger to hold a nested list and adds a nested integer to it.
          public void add(NestedInteger ni);

          // @return the nested list that this NestedInteger holds, if it holds a nested list
          // Return empty list if this NestedInteger holds a single integer
          public List<NestedInteger> getList();

    }
    class Solution {
        public int depthSum(List<NestedInteger> nestedList) {
            Queue<NestedInteger> queue = new ArrayDeque<>(nestedList);
            List<List<Integer>> nl = new ArrayList<>();
            int depth = 1, ans = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                nl.add(depth - 1, new ArrayList<>());
                List<NestedInteger> temp = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    NestedInteger ni = queue.poll();
                    if (ni.isInteger()) {
                        nl.get(depth - 1).add(ni.getInteger());
                    } else {
                        temp.addAll(ni.getList());
                    }
                }
                if (!temp.isEmpty()) {
                    for (NestedInteger n : temp) {
                        queue.offer(n);
                    }
                }
                ++depth;
            }
            for (int i = 0; i < nl.size(); i++) {
                int dep = nl.size() - 1 - i;
                for (Integer in : nl.get(i)) {
                    ans += in * dep;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Day17 day17 = new Day17();
        int[][] graph = new int[][] {
                {1,2},{3},{3},{}
        };
        System.out.println(day17.allPathsSourceTarget(graph));
    }
}

package com.leetcode.april;

import com.leetcode.util.tree.TreeNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-04-30 21:31:14
 * @author: wanglong16@meicai.cn
 */
public class Day30 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode current;
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                current = queue.poll();
                level.add(current.val);
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            ans.add(level);
        }
        List<List<Integer>> reverse = new ArrayList<>();
        for (int i = ans.size() - 1; i >= 0; i--) {
            reverse.add(ans.get(i));
        }
        return reverse;
    }

    public int countNodes(TreeNode root) {
        Queue<TreeNode> nodes = new ArrayDeque<>();
        if (root == null) {
            return 0;
        }
        nodes.offer(root);
        int ans = 0;
        while (!nodes.isEmpty()) {
            TreeNode no = nodes.poll();
            ans ++;
            if (no.left != null) {
                nodes.add(no.left);
            }
            if (no.right != null) {
                nodes.add(no.right);
            }
        }
        return ans;
    }



//    public int getImportance(List<Employee> employees, int id) {
//        Set<Integer> set = new HashSet<>();
//
//    }


    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    public String replaceDigits(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 1) {
                sb.append((char)(s.charAt(i - 1) + s.charAt(i) - 48));
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] > 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        return arr[arr.length - 1];
    }


    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = query(rooms, queries[i][0], queries[i][1]);
        }
        return ans;
    }

    public int query(int[][] rooms, int pref, int minSize) {
        List<Integer> ids = new ArrayList<>();
        for (int[] ints : rooms) {
            if (ints[1] >= minSize) {
                ids.add(ints[0]);
            }
        }
        if (ids.isEmpty()) {
            return -1;
        }
        int minDistance = Integer.MAX_VALUE, ID = Integer.MAX_VALUE;
        for (Integer id : ids) {
            if (Math.abs(id - pref) < minDistance) {
                minDistance = Math.abs(id - pref);
                ID = id;
            } else if (Math.abs(id - pref) == minDistance) {
                if (id < ID) {
                    ID = id;
                }
            }
        }
        return ID;
    }

    public int getMinDistance(int[] nums, int target, int start) {
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                minDis = Math.min(Math.abs(i - start), minDis);
            }
        }
        return minDis;
    }

}

package com.leetcode.october;

import com.leetcode.util.tree.TreeNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-10 10:29:51
 * @author: wanglong16@meicai.cn
 */
public class Day8 {

    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Set<Integer> arr1 = new HashSet<>(), arr2 = new HashSet<>(), arr3 = new HashSet<>();
        for (int value : nums1) {
            arr1.add(value);
        }
        for (int value : nums2) {
            arr2.add(value);
        }
        for (int value : nums3) {
            arr3.add(value);
        }
        Set<Integer> ans = new HashSet<>();
        for (Integer in : arr2) {
            if (arr1.contains(in)) {
                ans.add(in);
            } else {
                arr1.add(in);
            }
        }
        for (Integer in : arr3) {
            if (arr1.contains(in)) {
                ans.add(in);
            } else {
                arr1.add(in);
            }
        }
        return new ArrayList<>(ans);
    }

    public int minOperations(int[][] grid, int x) {
        List<Integer> arr = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                arr.add(grid[i][j]);
                sum += grid[i][j];
            }
        }
        Collections.sort(arr);
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if ((arr.get(j) - arr.get(i)) % x != 0) {
                    return -1;
                }
            }
        }
        int ans = 0;
        int mid = arr.size() / 2;
        for (Integer integer : arr) {
            ans += Math.abs(integer - arr.get(mid)) / x;
        }
        return ans;
    }


    public int cntDiff(int[][] grid, int base, int x) {
        int ans = 0;
        for (int[] ints : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                int diff = Math.abs(base - ints[j]);
                int cnt = diff / x;
                ans += cnt;
            }
        }
        return ans;
    }


    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        Set<Integer> set = new HashSet<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode nd = queue.poll();
                if (set.contains(k - nd.val)) {
                    return true;
                }
                set.add(nd.val);
                if (nd.left != null) {
                    queue.offer(nd.left);
                }
                if (nd.right != null) {
                    queue.offer(nd.right);
                }
            }
        }
        return false;
    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode nd = queue.poll();
                max = Math.max(max, nd.val);
                if (nd.left != null) {
                    queue.offer(nd.left);
                }
                if (nd.right != null) {
                    queue.offer(nd.right);
                }
            }
            ans.add(max);
        }
        return ans;
    }


    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node nd = queue.poll();
                if (nd.left != null) {
                    queue.offer(nd.left);
                }
                if (nd.right != null) {
                    queue.offer(nd.right);
                }
                if (i == 0) {
                    pre = nd;
                } else {
                    pre.next = nd;
                    pre = pre.next;
                }
            }
        }
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }



    public static void main(String[] args) {
        Day8 day8 = new Day8();
        int[][] grid = new int[][] {
                {931,128},{639,712}
        };
        System.out.println(day8.minOperations(
                grid, 73
        ));
    }
}

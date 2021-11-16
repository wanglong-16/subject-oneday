package com.leetcode.november;


import com.leetcode.util.tree.TreeNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-11-03 10:45:43
 * @author: wanglong16@meicai.cn
 */
public class Day2 {

    public int trapRainWater(int[][] heightMap) {
        return 0;
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int cnt = 0;
        int gStart = 0, sStart = 0;
        while (gStart < g.length && sStart < s.length) {
            if (g[gStart] <= s[sStart]) {
                gStart ++;
                cnt ++;
            }
            sStart ++;
        }
        return cnt;
    }

    public int binaryGap(int n) {
        int pre = -1, ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((n >> i & 1) == 1) {
                if (pre != -1) {
                    ans = Math.max(i - pre, ans);
                }
                pre = i;
            }
        }
        return ans;
    }

    public boolean isMajorityElement(int[] nums, int target) {
        int cnt = 0;
        int len = nums.length;
        for (int num : nums) {
            if (num == target) {
                cnt ++;
            }
        }
        return cnt > len / 2;
    }

    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;
        for (int ele : arr) {
            dp.put(ele, dp.getOrDefault(ele - difference, 0) + 1);
            ans = Math.max(dp.get(ele), ans);
        }
        return ans;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // BFS + ArrayDeque
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> dq = new LinkedList<>();
        boolean insertHead = false;
        dq.offer(root);
        while (!dq.isEmpty()) {
            int size = dq.size();
            Deque<Integer> temp = new ArrayDeque<>();
            for (int i = 0; i < size; i++) {
                TreeNode nd = dq.poll();
                if (insertHead) {
                    temp.addFirst(nd.val);
                } else {
                    temp.addLast(nd.val);
                }
                if (nd.left != null) {
                    dq.offer(nd.left);
                }
                if (nd.right != null) {
                    dq.offer(nd.right);
                }
            }
            ans.add(new ArrayList<>(temp));
            insertHead = !insertHead;
        }
        return ans;
    }

    /**
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isNul = false;
        while (!queue.isEmpty()) {
            TreeNode nd = queue.poll();
            if (nd == null) {
                isNul = true;
                continue;
            }
            if (isNul) {
                return false;
            }
            queue.offer(nd.left);
            queue.offer(nd.right);
        }
        return true;
    }

    public static void main(String[] args) {
        Day2 day2 = new Day2();
        //[1,5,7,8,5,3,4,2,1]
        //-2
        System.out.println(day2.longestSubsequence(new int[] {16,-4,-6,-11,-8,-9,4,-11,15,15,-9,11,7,-7,10,-16,4}, 3));
    }



}

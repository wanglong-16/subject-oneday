package com.leetcode.september;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-09-13 19:28:39
 * @author: wanglong16@meicai.cn
 */
public class Day2 {



public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode nextTemp = head.next; // nextT = 2 3 4 5 => 3 4 5
            head.next = prev; //head = 1 => 2 1
            prev = head; // prev = 1 => 2 1
            head = nextTemp; // head = 2 3 4 5 => 3 4 5
        }
        return prev;
    }

    ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root == null) {
            return result;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                level.add(current.val);
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            result.add(level);
        }
        return result;
        // write code here
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }
    public static void main(String[] args) {
        Day2 solution = new Day2();

    }

    public int numberOfBoomerangs(int[][] points) {
        int cnt = 0;
        for (int[] a : points) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int[] b : points) {
                int dis = distance(a, b);
                map.put(dis, map.getOrDefault(dis, 0) + 1);
            }
            for (Integer val : map.values()) {
                cnt += val * (val - 1);
            }
        }

        return cnt;
    }



    public int distance(int[] pa, int[] pb) {
        int dix = Math.abs(pa[0] - pb[0]);
        int diy = Math.abs(pa[1] - pb[1]);
        return dix * dix + diy * diy;
    }


}

package com.leetcode.august;

import com.leetcode.util.tree.TreeNode;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-26 06:59:59
 * @author: wanglong16@meicai.cn
 */
public class Day22 {

    public int numRescueBoatsVerror(int[] people, int limit) {
        Arrays.sort(people);
        int p = 0, times = 0;
        while (p < people.length) {
            int total = 0;
            while (p < people.length && total < limit) {
                if (total + people[p] <= limit) {
                    total+= people[p];
                    p++;
                } else {
                    break;
                }
            }
            times ++;
        }
        return times;
    }


    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int l = 0, r = people.length - 1, times = 0;
        while (l < r) {
            if (people[l] + people[r] <= limit) {
                l ++;
            }
            r --;
            times ++;
        }
        return times;
    }

    public int translateNum(int num) {
        String s = String.valueOf(num);
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            int cnt = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
            if (cnt >= 10 && cnt <= 25) {
                dp[i] = dp[i - 2] + dp[i - 1];
            }
        }
        return dp[dp.length - 1];
    }

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> t = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode nd = queue.poll();
                t.add(nd.val);
                if (nd.left != null) {
                    queue.add(nd.left);
                }
                if (nd.right != null) {
                    queue.add(nd.right);
                }
            }
            tem.add(new ArrayList<>(t));
        }
        return tem.get(tem.size() - 1).get(0);
    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode nd = queue.poll();
                max = Math.max(max, nd.val);
                if (nd.left != null) {
                    queue.add(nd.left);
                }
                if (nd.right != null) {
                    queue.add(nd.right);
                }
            }
            ans.add(max);
        }
        return ans;
    }

    List<List<Integer>> tem = new ArrayList<>();


    public List<List<Integer>> levelOrder(Node root) {
        Queue<Node> queue = new ArrayDeque<>();
        if (root == null) {
            return tem;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> t = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node nd = queue.poll();
                if (nd != null) {
                    t.add(nd.val);
                    if (! nd.children.isEmpty()) {
                        for (Node n : nd.children) {
                            queue.offer(n);
                        }
                    }
                }
            }
            tem.add(new ArrayList<>(t));
        }
        return tem;
    }

    public static void main(String[] args) {
        Day22 day22 = new Day22();
        CountDownLatch cdl = new CountDownLatch(10);
        long st = System.currentTimeMillis();
        System.out.println(st);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    day22.increase();
                }
                cdl.countDown();
                try {
                    cdl.await();
                    long cost = System.currentTimeMillis() - st;
                    System.out.println(Thread.currentThread().getName() + ": cost" + cost + ", sign" +  day22.sign);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private volatile int sign = 0;

    // 非 sync == Thread-9: cost74, sign7869
    // sync == Thread-6: cost58, sign10000

    public synchronized void increase() {
        this.sign ++;
    }



    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}

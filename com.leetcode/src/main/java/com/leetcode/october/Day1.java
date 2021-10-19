package com.leetcode.october;

import com.leetcode.util.tree.TreeNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-01 15:07:42
 * @author: wanglong16@meicai.cn
 */
public class Day1 {


    public String destCity(List<List<String>> paths) {
        Map<String, String> map = new HashMap<>();
        for (List<String> path : paths) {
            map.put(path.get(0), path.get(1));
        }
        Set<String> keySet = map.keySet();
        for (String str : map.values()) {
            if (!keySet.contains(str)) {
                return str;
            }
        }
        return null;
    }

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        while (left < right) {
            int hl = height[left];
            int hr = height[right];
            ans = Math.max(Math.min(hl, hr) * (right - left), ans);
            if (hl < hr) {
                left ++;
            } else {
                right --;
            }
        }
        return ans;
    }

    class CQueue {

        Stack<Integer> sta1 = new Stack<>();

        Stack<Integer> sta2 = new Stack<>();

        public CQueue() {

        }

        public void appendTail(int value) {
            sta1.push(value);
        }

        public int deleteHead() {
            if (sta1.isEmpty()) {
                return -1;
            }
            while (!sta1.isEmpty()) {
                sta2.push(sta1.pop());
            }
            int res = sta2.pop();
            while (!sta2.isEmpty()) {
                sta1.push(sta2.pop());
            }
            return res;
        }
    }

    class MinStack {

        Stack<int[]> elements;
        Queue<Integer> queue = new PriorityQueue<>();
        /** initialize your data structure here. */
        public MinStack() {
            elements = new Stack<>();
        }

        public void push(int x) {
            if (elements.isEmpty()) {
                elements.push(new int[] {x, x});
            } else {
                int min = elements.peek()[1];
                elements.push(new int[] {x, Math.min(min, x)});
            }
        }

        public void pop() {
            elements.pop();
        }

        public int top() {
            return elements.peek()[0];
        }

        public int min() {
            return elements.peek()[1];
        }
    }

    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 7; i >= 0; i --) {
            int val = (num >> (4 * i)) & 0xf;
            if (sb.length() > 0 || val > 0) {
                char digit = val < 10 ? (char) ('0' + val) : (char) ('a' + val - 10);
                sb.append(digit);
            }
        }
        return sb.toString();
    }

    public boolean isValidBSTV1(TreeNode root) {
        return isBst(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isBst(TreeNode node, long left, long right) {
        if (node == null) {
            return true;
        }
        if (node.val >= right || node.val <= left) {
            return false;
        }
        return isBst(node.left, left, node.val) && isBst(node.right, node.val, right);
    }

    List<Integer> list = new ArrayList<>();

    public void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            list.add(node.val);
            inOrder(node.right);
        }
    }

    public boolean isValidBSTV2(TreeNode root) {
        inOrder(root);
        if (list.isEmpty()) {
            return true;
        }
        int pre = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= pre) {
                return false;
            } else {
                pre = list.get(i);
            }
        }
        return true;
    }


    long pre = Long.MIN_VALUE, next = Long.MAX_VALUE;

    public boolean inOrderV2(TreeNode node) {
        if (node != null) {
            boolean l = inOrderV2(node.left);
            if (node.val <= pre) {
                return false;
            }
            pre = node.val;
            boolean r = inOrderV2(node.right);
            return l && r;
        } else {
            return true;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return inOrderV2(root);
    }

    public boolean isBalancedV1(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            int lh = height(root.left);
            int rh = height(root.right);
            if (Math.abs(lh - rh) > 1) {
                return false;
            } else {
                return isBalanced(root.left) && isBalanced(root.right);
            }
        }
    }

    public int height(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return calculate(root) != -1;
    }

    public int calculate(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int l, r;
            if ((l = calculate(node.left)) == -1 || (r = calculate(node.right)) == -1 ||
                    Math.abs(l - r) > 1) {
                return -1;
            }
            return Math.max(l, r) + 1;
        }
    }

    public int countBinarySubstringsV1(String s) {
        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                points.add(new int[] {i, i + 1});
            }
        }
        int cnt = 0;
        for (int[] p : points) {
            int left = p[0], right = p[1];
            char pl = s.charAt(left), pr = s.charAt(right);
            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) == pl && s.charAt(right) == pr) {
                    left --;
                    right ++;
                    cnt++;
                } else {
                    break;
                }
            }
        }
        return cnt;
    }

    public int countBinarySubstrings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int last = 0, cnt = 0, ans = 0;
        char c = s.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != c) {
                ans += Math.min(last, cnt);
                last = cnt;
                cnt = 0;
                c = s.charAt(i);
            }
            cnt++;
        }
        return ans + Math.min(last, cnt);
    }

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        System.out.println(day1.countBinarySubstrings("00110011"));
    }


}

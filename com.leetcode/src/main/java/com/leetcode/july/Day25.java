package com.leetcode.july;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-27 08:21:59
 * @author: wanglong16@meicai.cn
 */
public class Day25 {

    public String interpret(String command) {
        StringBuilder ans = new StringBuilder();
        int tem = 0;
        while (tem < command.length()) {
            if (command.charAt(tem) == 'G') {
                ans.append('G');
                tem++;
            } else if (command.charAt(tem) == '(') {
                if (command.charAt(tem + 1) == ')') {
                    ans.append('o');
                    tem += 2;
                } else {
                    ans.append("al");
                    tem += 4;
                }
            }
        }
        return ans.toString();
    }


    public static void main(String[] args) {
        Day25 day25 = new Day25();
        day25.insert("abc");
        day25.insert("ab");
        day25.insert("ac");
        day25.insert("abcd");
        System.out.println(day25.prefix("ab"));

    }

    class Node {
        boolean isEnd;
        Node[] children;

        public Node(boolean isEnd, Node[] children) {
            this.isEnd = isEnd;
            this.children = children;
        }
    }

    private Node node = new Node(false, new Node[26]);

    public void insert(String str) {
        Node nd = this.node;
        for (Character chr : str.toCharArray()) {
            if (nd.children[chr - 'a'] == null) {
                nd.children[chr - 'a'] = new Node(false, new Node[26]);
            }
            nd = nd.children[chr - 'a'];
        }
        nd.isEnd = true;
    }

    public boolean prefix(String str) {
        Node nd = node;
        for (Character c : str.toCharArray()) {
            if (nd.children[c - 'a'] == null) {
                return false;
            }
            nd = nd.children[c - 'a'];
        }
        return true;
    }

    public boolean search(String str) {
        Node nd = node;
        for (Character c : str.toCharArray()) {
            if (nd.children[c - 'a'] == null) {
                return false;
            }
            nd = nd.children[c - 'a'];
        }
        return nd.isEnd;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public void flatten(TreeNode root) {
        helper(root);
        int size = nodes.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = nodes.get(i - 1), curr = nodes.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    List<TreeNode> nodes = new ArrayList<>();

    public void helper(TreeNode root) {
        if (root != null) {
            nodes.add(root);
            helper(root.left);
            helper(root.right);
        }
    }

}

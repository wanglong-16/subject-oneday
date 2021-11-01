package com.leetcode.october;

import com.leetcode.util.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-25 17:48:11
 * @author: wanglong16@meicai.cn
 */
public class Day15 {

    public List<Stack<TreeNode>> dfs(TreeNode treeNode) {
        List<Stack<TreeNode>> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = treeNode;
        while (root != null || !stack.isEmpty()) {
            if (root == null) {
                System.out.print(" null");
            }
            while (root != null) {
                System.out.print(" " + root.val);
                stack.push(root);
                root = root.left;
                if (root == null) {
                    System.out.print(" null");
                }
            }
            if (!stack.isEmpty()) {
                root = stack.pop().right;
            }
        }

        return ans;
    }

    public void dfsV1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     *        1
     *     5     9
     * 2    3  4    7
     */
    public static void main(String[] args) {
        Day15 day15 = new Day15();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(7);
        day15.dfsV1(root);
        //System.out.println(day15.dfs(root));
    }
}

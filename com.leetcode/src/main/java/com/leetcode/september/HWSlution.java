package com.leetcode.september;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-09-27 19:34:52
 * @author: wanglong16@meicai.cn
 */
public class HWSlution {

    private int target = 0;
    List<List<Integer>> paths = new ArrayList<>();

    public List<List<Integer>> hasPathSum(TreeNode root, int targetSum) {
        this.target = targetSum;
        if (root == null) {
            Collections.emptyList();
        }
        dfs(root, 0, new ArrayList<>());
        return paths;
    }

    private void dfs(TreeNode node, int sum, List<Integer> path) {
        if (node != null) {
            sum += node.val;
            path.add(node.val);
            if (node.left == null && node.right == null) {
                if (sum == target) {
                    paths.add(new ArrayList<>(path));
                }
                return;
            }
            if (node.left != null) {
                dfs(node.left, sum, path);
                path.remove(path.size() - 1);
            }
            if (node.right != null) {
                dfs(node.right, sum, path);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(2);
        HWSlution h = new HWSlution();
        System.out.println(h.hasPathSum(root, 3));
    }


    static class TreeNode {
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
}

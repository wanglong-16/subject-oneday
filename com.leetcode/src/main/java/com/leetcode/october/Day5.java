package com.leetcode.october;

import com.leetcode.util.tree.TreeNode;
import sun.awt.windows.ThemeReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-06 22:36:06
 * @author: wanglong16@meicai.cn
 */
public class Day5 {


    private int target = 0;
    List<Integer> stack = new ArrayList<>();
    List<List<Integer>> list = new ArrayList<>();

    public void hasPathSum(TreeNode root, int targetSum) {
        this.target = targetSum;
        if (root == null) {
            return;
        }
        dfs(root, 0);
    }

    private void dfs(TreeNode node, int sum) {
        if (node != null) {
            sum += node.val;
            stack.add(node.val);
            if (node.left == null && node.right == null) {
                if (sum == target) {
                    list.add(new ArrayList<>(stack));
                }
            }
            if (node.left != null) {
                dfs(node.left, sum);
                stack.remove(stack.size() - 1);
            }
            if (node.right != null) {
                dfs(node.right, sum);
                stack.remove(stack.size() - 1);
            }
        }
    }

    //   1
    // 2    3
    // 1   1

    public static void main(String[] args) {
        Day5 day5 = new Day5();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right = new TreeNode(3);
        day5.hasPathSum(root, 4);
        System.out.println(day5.list);
    }
}

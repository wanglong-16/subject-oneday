package com.leetcode.august;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-19 08:34:58
 * @author: wanglong16@meicai.cn
 */

import com.leetcode.util.tree.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class BSTIterator {

    List<Integer> iter;
    Iterator<Integer> integerIterator;
    public BSTIterator(TreeNode root) {
        iter = new ArrayList<>();
        zx(root);
        integerIterator = iter.iterator();
    }

    public void zx(TreeNode node) {
        if (node != null) {
            iter.add(node.val);
            zx(node.left);
            zx(node.right);
        }
    }

    public int next() {
        return integerIterator.next();
    }

    public boolean hasNext() {
        return integerIterator.hasNext();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
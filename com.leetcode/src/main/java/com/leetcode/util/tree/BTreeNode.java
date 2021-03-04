package com.leetcode.util.tree;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-03 21:58:57
 * @author: wanglong16@meicai.cn
 */
public class BTreeNode {

    public BTreeNode left;

    public BTreeNode right;

    public int val;

    public BTreeNode() {
    }

    public BTreeNode(BTreeNode left, BTreeNode right, int val) {
        this.left = left;
        this.right = right;
        this.val = val;
    }

    public BTreeNode(int val) {
        this.val = val;
    }
}

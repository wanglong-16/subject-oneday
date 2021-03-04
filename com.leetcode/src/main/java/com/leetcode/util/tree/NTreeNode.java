package com.leetcode.util.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: n 叉树节点
 * @version: 1.0
 * @date: 2021-03-02 09:47:18
 * @author: wanglong16@meicai.cn
 */
public class NTreeNode {
    public int val;
    public List<NTreeNode> children;


    public NTreeNode() {
        children = new ArrayList<NTreeNode>();
    }

    public NTreeNode(int _val) {
        val = _val;
        children = new ArrayList<NTreeNode>();
    }

    public NTreeNode(int _val, ArrayList<NTreeNode> _children) {
        val = _val;
        children = _children;
    }
};

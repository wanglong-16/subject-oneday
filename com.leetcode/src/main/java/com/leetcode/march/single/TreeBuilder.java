package com.leetcode.march.single;

import com.leetcode.util.tree.Node;

import java.util.Arrays;
import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-02 09:23:51
 * @author: wanglong16@meicai.cn
 */
public class TreeBuilder {

    Node buildTree(String[] postfix) {
        Stack<Node.BTNode> nodeStack = new Stack<>();
        for (String string : postfix) {
            Node.BTNode node = new Node.BTNode(string);
            if (Arrays.asList("+", "-", "*", "/").contains(string)) {
                node.right = nodeStack.pop();
                node.left = nodeStack.pop();
            }
            nodeStack.push(node);
        }
        return nodeStack.pop();
    }
}
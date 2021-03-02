package com.leetcode.util.tree;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-02 09:22:55
 * @author: wanglong16@meicai.cn
 */
public abstract class Node {

    public abstract int evaluate();
    // define your fields here

    public static class BTreeNode extends Node {

        String value;
        public BTreeNode left;
        public BTreeNode right;

        public BTreeNode(String val) {
            this.value = val;
        }

        //求值
        @Override
        public int evaluate() {
            switch (value) {
                case "+":
                    return left.evaluate() + right.evaluate();
                case "-":
                    return left.evaluate() - right.evaluate();
                case "*":
                    return left.evaluate() * right.evaluate();
                case "/":
                    return left.evaluate() / right.evaluate();
                default:
                    return Integer.parseInt(value);
            }
        }
    }
}

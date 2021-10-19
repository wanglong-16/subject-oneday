package com.leetcode.util.linked;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-02 07:12:51
 * @author: wanglong16@meicai.cn
 */
public interface ImmutableListNode {

    void printValue(); // print the value of this node.

    ImmutableListNode getNext(); // return the next node.
};

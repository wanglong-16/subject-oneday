package com.leetcode.util.linked;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-01 21:50:11
 * @author: wanglong16@meicai.cn
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) { this.val = val; }

    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

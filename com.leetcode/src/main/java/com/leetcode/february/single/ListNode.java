package com.leetcode.february.single;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-11 20:24:27
 * @author: wanglong16@meicai.cn
 */

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

package com.leetcode.march;

import com.leetcode.util.linked.ListNode;

import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-18 07:56:10
 * @author: wanglong16@meicai.cn
 */
public class Day18 {

    /**
     * 92. 反转链表 II
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     *
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     */
    public ListNode reverseBetweenV1(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }
        ListNode prev = head;
        ListNode next = head;
        ListNode ans = new ListNode(0);
        ans.next = prev;
        int pC = 1, nC = 1;
        Stack<Integer> mindVals = new Stack<>();
        while (next != null) {
            if (pC < left) {
                prev = prev.next;
            } else {
                mindVals.push(head.val);
            }
            if (nC < right) {
                next = next.next;
            } else {
                break;
            }
            head = head.next;
        }
        while (!mindVals.isEmpty()) {
            prev.next = new ListNode(mindVals.pop());
            prev = prev.next;
        }
        prev.next = next;
        return ans.next;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        // 建议写在 for 循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 第 3 步：切断出一个子链表（截取链表）
        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;

        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;

        // 第 4 步：同第 206 题，反转链表的子区间
        reverseLinkedList(leftNode);

        // 第 5 步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }

    private void reverseLinkedList(ListNode head) {
        // 也可以使用递归反转一个链表
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

    }


}

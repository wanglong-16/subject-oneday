package com.leetcode.march;

import com.leetcode.util.ListNode;

import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-28 09:24:11
 * @author: wanglong16@meicai.cn
 */
public class Day1 {

    /**
     * 206. 反转链表
     * 反转一个单链表。
     * 示例:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * 进阶:
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 1689. 十-二进制数的最少数目
     * 如果一个十进制数字不含任何前导零，且每一位上的数字不是 0 就是 1 ，那么该数字就是一个 十-二进制数 。例如，101 和 1100 都是 十-二进制数，而 112 和 3001 不是。
     *
     * 给你一个表示十进制整数的字符串 n ，返回和为 n 的 十-二进制数 的最少数目。
     * 示例 1：
     *
     * 输入：n = "32"
     * 输出：3
     * 解释：10 + 11 + 11 = 32
     * 示例 2：
     *
     * 输入：n = "82734"
     * 输出：8
     * 示例 3：
     *
     * 输入：n = "27346209830709182346"
     * 输出：9
     * 提示：
     *
     * 1 <= n.length <= 105
     * n 仅由数字组成
     * n 不含任何前导零并总是表示正整数
     */
    public int minPartitions(String n) {
        int result = 0;
        for (int i = 0; i < n.length(); i++) {
            if (result < (int) n.charAt(i)) {
                result = n.charAt(i);
            }
        }
        return result - 48;
    }

    public static void main(String[] args) {
        System.out.println((int) '9');
    }
}

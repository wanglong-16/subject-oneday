package com.leetcode.march;

import com.leetcode.util.linked.ListNode;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-09 21:53:06
 * @author: wanglong16@meicai.cn
 */
public class Day8 {

    /**
     * 67. 二进制求和
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     * 输入为 非空 字符串且只包含数字 1 和 0。
     * 示例 1:
     * 输入: a = "11", b = "1"
     * 输出: "100"
     * 示例 2:
     *
     * 输入: a = "1010", b = "1011"
     * 输出: "10101"
     * 提示：
     *
     * 每个字符串仅由字符 '0' 或 '1' 组成。
     * 1 <= a.length, b.length <= 10^4
     * 字符串如果不是 "0" ，就都不含前导零。
     */
    public String addBinary(String a, String b) {
        int aL = a.length() - 1, bL = b.length() - 1;
        boolean carry = false;
        StringBuilder sb = new StringBuilder();
        while (aL >= 0 || bL >= 0) {
            int aT = aL >= 0 ? a.charAt(aL) - 48 : 0;
            int bT = bL >= 0 ? b.charAt(bL) - 48 : 0;
            int ret = carry ? (1 + aT + bT) : (aT + bT);
            sb.append(ret % 2);
            carry = ret >= 2;
            aL --;
            bL --;
        }
        if (carry) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    /**
     * 234. 回文链表
     * 请判断一个链表是否为回文链表。
     *
     * 示例 1:
     *
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     *
     * 输入: 1->2->2->1
     * 输出: true
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     */
    public boolean isPalindromeV1(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            head = head.next;
        }
        return sb.toString().equals(sb.reverse().toString());
    }

    public boolean isPalindrome(ListNode head) {
        ListNode prev = null, temp = head;
        while (head != null) {
            ListNode nextTemp = head.next; // nextT = 2 3 4 5 => 3 4 5
            head.next = prev; //head = 1 => 2 1
            prev = head; // prev = 1 => 2 1
            head = nextTemp; // head = 2 3 4 5 => 3 4 5
        }
        while (temp != null) {
            if (temp.val != prev.val) {
                return false;
            }
            prev = prev.next;
            temp = temp.next;
        }
        return true;
    }

}

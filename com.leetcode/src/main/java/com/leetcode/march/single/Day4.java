package com.leetcode.march.single;

import com.leetcode.util.linked.ListNode;

import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-05 09:33:32
 * @author: wanglong16@meicai.cn
 */
public class Day4 {

    /**
     *19. 删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * 进阶：你能尝试使用一趟扫描实现吗？
     * 示例 1：
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     * 示例 2：
     * 输入：head = [1], n = 1
     * 输出：[]
     * 示例 3：
     * 输入：head = [1,2], n = 1
     * 输出：[1]
     * 提示：
     * 链表中结点的数目为 sz
     * 1 <= sz <= 30
     * 0 <= Node.val <= 100
     * 1 <= n <= sz
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //双指针
        int right = 0;
        ListNode R = head, temp = new ListNode(0, head);
        ListNode L = temp;
        while (right < n) {
            R = R.next;
            right ++;
        }
        while (R != null) {
            L = L.next;
            R = R.next;
        }
        L.next = L.next.next;
        return temp.next;
    }

    /**
     * 237. 删除链表中的节点
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
     * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
     * 示例 1：
     * 输入：head = [4,5,1,9], node = 5
     * 输出：[4,1,9]
     * 解释：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * 示例 2：
     * 输入：head = [4,5,1,9], node = 1
     * 输出：[4,5,9]
     * 解释：给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     */
    public void deleteNode(ListNode node) {
        // [4, 5, 1, 9] => [4, 5, 9, 9]
        node.val = node.next.val; // 当前节点的值置为下一个节点的值
        // [4, 5, 9, 9] => [4, 5, 9]
        node.next = node.next.next;
    }

    /**
     * 2. 两数相加
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例 1：
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     * 示例 2：
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     * 示例 3：
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode temp = ans;
        boolean isNext = false; //是否进位
        while (l1 != null || l2 != null) {
            int l1Val = l1 == null ? 0 : l1.val, l2Val = l2 == null ? 0 : l2.val;
            int curVal = isNext ? l1Val + l2Val + 1 : l1Val + l2Val;
            isNext = curVal >= 10;
            temp.next = new ListNode(curVal % 10);
            temp = temp.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (isNext) {
            temp.next = new ListNode(1);
        }
        return ans.next;
    }

    /**
     * 369. 给单链表加一
     * 用一个 非空 单链表来表示一个非负整数，然后将这个整数加一。
     * 你可以假设这个整数除了 0 本身，没有任何前导的 0。
     * 这个整数的各个数位按照 高位在链表头部、低位在链表尾部 的顺序排列。
     * 示例:
     * 输入: [1,2,3]
     * 输出: [1,2,4]
     */
    public ListNode plusOneV1(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        Stack<ListNode> result = new Stack<>();
        //低位在后，用栈调整顺序
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        boolean carry = true; // 开始时加1，就当进位吧
        while (!stack.isEmpty()) {
            int curVal = carry ? stack.pop().val + 1 : stack.pop().val;
            carry = curVal >= 10;
            result.push(new ListNode(curVal % 10));
        }
        if (carry) {
            result.push(new ListNode(1));
        }
        ListNode ans = new ListNode(0);
        ListNode temp = ans;
        while (!result.empty()) {
            temp.next = result.pop();
            temp = temp.next;
        }
        return ans.next;
    }

    // 哨兵头节点 + 找到最后一个 不是 9的数 + 1 其他位置 0
    public ListNode plusOne(ListNode head) {
        // sentinel head
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode notNine = sentinel;
        // find the rightmost not-nine digit
        while (head != null) {
            if (head.val != 9) {
                notNine = head;
            }
            head = head.next;
        }
        // increase this rightmost not-nine digit by 1
        notNine.val++;
        notNine = notNine.next;
        // set all the following nines to zeros
        while (notNine != null) {
            notNine.val = 0;
            notNine = notNine.next;
        }
        return sentinel.val != 0 ? sentinel : sentinel.next;
    }

    /**
     * 82. 删除排序链表中的重复元素 II
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     * 示例 1:
     * 输入: 1->2->3->3->4->4->5
     * 输出: 1->2->5
     * 示例 2:
     * 输入: 1->1->1->2->3
     * 输出: 2->3
     */
    public ListNode deleteDuplicates(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        if (head == null || head.next == null) {
            return head;
        }
        boolean isDup = false; //当前节点的值是否重复
        while (head != null) {
            //这里实现如何将重复的所有值都不添加到栈中
            if (!stack.isEmpty() && head.val == stack.peek()) {
                //当发现和栈中节点数据有重复，就一直标记重复
                isDup = true;
            } else {
                //当发现和栈中节点不重复，把上一个弹出去，再压入新一轮的值
                if (isDup) {
                    stack.pop();
                    isDup = false;
                }
                stack.push(head.val);
            }
            head = head.next;
        }
        //最后一轮如果重复了就再弹出一次。
        if (isDup) {
            stack.pop();
        }
        ListNode ans = new ListNode();
        while (!stack.isEmpty()) {
            //把数据接起来
            ListNode cur = new ListNode(stack.pop());
            cur.next = ans.next;
            ans.next = cur;
        }
        return ans.next;
    }
}

package com.leetcode.march.single;

import com.leetcode.util.linked.ListNode;

import java.util.List;

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

    
}

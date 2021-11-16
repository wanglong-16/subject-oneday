package com.leetcode.november;

import com.leetcode.util.linked.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-11-16 15:10:09
 * @author: wanglong16@meicai.cn
 */
public class Day5 {

    public int timeRequiredToBuy(int[] tickets, int k) {
        int kTk = tickets[k], ans = 0;
        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                ans += Math.min(kTk, tickets[i]);
            } else {
                ans += Math.min(kTk - 1, tickets[i]);
            }
        }
        return ans;
    }

    public ListNode reverseEvenLengthGroups(ListNode head) {
        List<List<ListNode>> templates = new ArrayList<>();
        int cur = 1;
        while (head != null) {
            List<ListNode> t = new ArrayList<>();
            for (int i = 0; i < cur; i++) {
                t.add(head);
                head = head.next;
                if (head == null) {
                    break;
                }
            }
            cur ++;
            templates.add(t);
        }
        ListNode ans = new ListNode();
        ListNode tem = ans;
        for (List<ListNode> t : templates) {
            if (t.size() % 2 == 0) {
                for (int i1 = t.size() - 1; i1 >= 0; i1--) {
                    tem.next = new ListNode(t.get(i1).val);
                    tem = tem.next;
                }
            } else {
                for (ListNode node : t) {
                    tem.next = new ListNode(node.val);
                    tem = tem.next;
                }
            }
        }
        return ans.next;
    }

    public static void main(String[] args) {
        Day5 day5 = new Day5();
        ListNode node = new ListNode(5);
        node.next = new ListNode(2);
        node.next.next = new ListNode(6);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(9);
        node.next.next.next.next.next = new ListNode(1);

        System.out.println(day5.reverseEvenLengthGroups(node));
    }
}

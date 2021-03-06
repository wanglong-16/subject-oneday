package com.leetcode.march;

import com.leetcode.util.linked.ListNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-05 18:53:45
 * @author: wanglong16@meicai.cn
 */
public class Day5 {

    /**
     * 24. 两两交换链表中的节点
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * 示例 1：
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     * 示例 2：
     *
     * 输入：head = []
     * 输出：[]
     * 示例 3：
     * 输入：head = [1]
     * 输出：[1]
     * 提示：
     * 链表中节点的数目在范围 [0, 100] 内
     * 0 <= Node.val <= 100
     * 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）
     */
    public ListNode swapPairsV1(ListNode head) {
        ListNode ans = new ListNode(0);
        ans.next = head;
        ListNode first, second, temp = ans;
        while (temp.next != null && temp.next.next != null) {
            first = temp.next;
            second = temp.next.next;
            temp.next = second;
            first.next = second.next;
            second.next = first;
            temp = first;
        }
        return ans.next;
    }


    /**
     * 86. 分隔链表
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * 你应当 保留 两个分区中每个节点的初始相对位置。
     * 示例 1：
     * 输入：head = [1,4,3,2,5,2], x = 3
     * 输出：[1,2,2,4,3,5]
     * 示例 2：
     * 输入：head = [2,1], x = 2
     * 输出：[1,2]
     * 提示：
     * 链表中节点的数目在范围 [0, 200] 内
     * -100 <= Node.val <= 100
     * -200 <= x <= 200
     */
    public ListNode partitionV1(ListNode head, int x) {
        Queue<Integer> lneX = new ArrayDeque<>();
        Queue<Integer> geX = new ArrayDeque<>();
        while (head != null) {
            if (head.val < x) {
                lneX.add(head.val);
            } else {
                geX.add(head.val);
            }
            head = head.next;
        }
        ListNode ans = new ListNode(0);
        ListNode temp = ans;
        while (!lneX.isEmpty()) {
            temp.next = new ListNode(lneX.poll());
            temp = temp.next;
        }
        while (!geX.isEmpty()) {
            temp.next = new ListNode(geX.poll());
            temp = temp.next;
        }
        return ans.next;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode sHead = new ListNode(0);
        ListNode stmp = sHead;
        ListNode lHead = new ListNode(0);
        ListNode ltmp = lHead;
        while (head != null) {
            if (head.val < x) {
                stmp.next = head;
                stmp = stmp.next;
            } else {
                ltmp.next = head;
                ltmp = ltmp.next;
            }
            head = head.next;
        }
        ltmp.next = null;
        stmp.next = lHead.next;
        return sHead.next;
    }

    /**
     * 148. 排序链表
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * 进阶：
     * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
     * 示例 1：
     * 输入：head = [4,2,1,3]
     * 输出：[1,2,3,4]
     * 示例 2：
     * 输入：head = [-1,5,3,4,0]
     * 输出：[-1,0,3,4,5]
     * 示例 3：
     * 输入：head = []
     * 输出：[]
     */
    public ListNode sortListV1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 冒泡排序
        ListNode first = head, second, ans = new ListNode(0);
        ans.next = head;
        while (first.next != null) {
            second = first;
            while (second.next.next != null) {
                if (second.next.val > second.next.next.val) {
                    //swap second.next and second.next.next
                    // 1 -> 2 -> 3 -> 4
                    ListNode temp3End = second.next.next.next; // 3的末尾 temp = 4
                    ListNode temp2 = second.next; // 2, 3, 4
                    second.next = second.next.next; // 1 指向3 [1, 3, 4]
                    second.next = temp2; // 3 指向 2 [1,3,2,3,4]
                    temp2.next = temp3End;
                }
                second = second.next;
            }
            first = first.next;
        }
        return ans;
    }

    public ListNode sortList(ListNode head) {
        List<ListNode> nodes = new ArrayList<>();
        while (head != null) {
            ListNode t = head.next;
            head.next = null;
            nodes.add(head);
            head.next = t;
            head = head.next;
        }
        ListNode [] arr = nodes.toArray(new ListNode[0]);
        Arrays.sort(arr, Comparator.comparingInt(o -> o.val));
        ListNode ans = new ListNode(0);
        ListNode temp = ans, min;
        for (ListNode listNode : arr) {
            temp.next = listNode;
            temp = temp.next;
        }
        return ans.next;
    }

    /**
     * 1669. 合并两个链表
     * 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
     * 请你将 list1 中第 a 个节点到第 b 个节点删除，并将list2 接在被删除节点的位置。
     * 下图中蓝色边和节点展示了操作后的结果：
     * 请你返回结果链表的头指针。
     * 示例 1：
     * 输入：list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
     * 输出：[0,1,2,1000000,1000001,1000002,5]
     * 解释：我们删除 list1 中第三和第四个节点，并将 list2 接在该位置。上图中蓝色的边和节点为答案链表。
     * 示例 2：
     * 输入：list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
     * 输出：[0,1,1000000,1000001,1000002,1000003,1000004,6]
     * 解释：上图中蓝色的边和节点为答案链表。
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        // 快慢指针 滑动窗口，
        int fast = 0;
        ListNode ans = new ListNode(0);
        ans.next = list1;
        ListNode fastHead = ans, slowHead = ans;
        while (fast <= b + 1) {
            fastHead = fastHead.next;
            if (fast < a) {
                slowHead = slowHead.next;
            }
            fast ++;
        }
        slowHead.next = list2;
        ListNode temp = slowHead;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = fastHead;
        return ans.next;
    }

    /**
     * 25. K 个一组翻转链表
     * 题目描述提示帮助提交记录题目讨论社区题解
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * 进阶：
     * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     * 示例 1：
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[2,1,4,3,5]
     * 示例 2：
     * 输入：head = [1,2,3,4,5], k = 3
     * 输出：[3,2,1,4,5]
     * 示例 3：
     *
     * 输入：head = [1,2,3,4,5], k = 1
     * 输出：[1,2,3,4,5]
     * 示例 4：
     * 输入：head = [1], k = 1
     * 输出：[1]
     * 提示：
     * 列表中节点的数量在范围 sz 内
     * 1 <= sz <= 5000
     * 0 <= Node.val <= 1000
     * 1 <= k <= sz
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }
        Stack<ListNode> reverse = new Stack<>();
        ListNode ans = new ListNode(0);
        ListNode temp = ans, cycFirst = head;
        while (head != null) {
            if (reverse.size() == k) {
                cycFirst = reverse.peek().next;
                while (!reverse.isEmpty()) {
                    temp.next = reverse.pop();
                    temp = temp.next;
                }
            }
            ListNode t = head;
            t.next = null;
            reverse.push(t);
            head = head.next;
        }
        //兼容只有k个情况
        if (reverse.size() == k) {
            while (!reverse.isEmpty()) {
                temp.next = reverse.pop();
                temp = temp.next;
            }
        }
        temp.next = cycFirst;
        return ans.next;
    }

    public ListNode reverseList(ListNode head) {
        // 1 -> 2 -> 3 -> 4
        ListNode prev = null, nextNode;
        while (head != null) {
            nextNode = head.next; //nextNode = 2 3 4
            head.next = prev; //head = 1
            prev.next = nextNode;
            head = nextNode;
        }
        return prev;
    }
}

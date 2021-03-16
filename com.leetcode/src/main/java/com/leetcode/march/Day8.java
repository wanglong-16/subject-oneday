package com.leetcode.march;

import com.leetcode.util.linked.ListNode;

import java.util.ArrayList;
import java.util.List;

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
        ListNode prev = null, node = new ListNode(0);
        ListNode temp = node;
        while (head != null) {
            temp.next = head;
            temp = temp.next;
            ListNode nextTemp = head.next; // nextT = 2 3 4 5 => 3 4 5
            head.next = prev; //head = 1 => 2 1
            prev = head; // prev = 1 => 2 1
            head = nextTemp; // head = 2 3 4 5 => 3 4 5
        }
        node = node.next;
        while (node != null) {
            if (node.val != prev.val) {
                return false;
            }
            prev = prev.next;
            node = node.next;
        }
        return true;
    }

    /**
     * 328. 奇偶链表
     * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
     *
     * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
     *
     * 示例 1:
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 1->3->5->2->4->NULL
     * 示例 2:
     *
     * 输入: 2->1->3->5->6->4->7->NULL
     * 输出: 2->3->6->7->1->5->4->NULL
     * 说明:
     *
     * 应当保持奇数节点和偶数节点的相对顺序。
     * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
     */
    public ListNode oddEvenListV1(ListNode head) {
        ListNode oddHead = new ListNode(0);
        ListNode evenHead = new ListNode(0);
        ListNode oddTemp = oddHead, evenTemp = evenHead;
        int count = 1;
        while (head != null) {
            if (count % 2 == 0) {
                evenTemp.next = new ListNode(head.val);
                evenTemp = evenTemp.next;
            } else {
                oddTemp.next = new ListNode(head.val);
                oddTemp = oddTemp.next;
            }
            head = head.next;
            count ++;
        }
        oddTemp.next = evenHead.next;
        return oddHead.next;
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            //使得节点分离， 奇偶节点分离
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B) {
            A = A == null ? headB : A.next;
            B = B == null ? headA : B.next;
        }
        return A;
    }

    /**
     * 725. 分隔链表
     * 给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
     * 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。
     * 这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。
     *
     * 返回一个符合上述规则的链表的列表。
     *
     * 举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]
     *
     * 示例 1：
     *
     * 输入:
     * root = [1, 2, 3], k = 5
     * 输出: [[1],[2],[3],[],[]]
     * 解释:
     * 输入输出各部分都应该是链表，而不是数组。
     * 例如, 输入的结点 root 的 val= 1, root.next.val = 2, \root.next.next.val = 3, 且 root.next.next.next = null。
     * 第一个输出 output[0] 是 output[0].val = 1, output[0].next = null。
     * 最后一个元素 output[4] 为 null, 它代表了最后一个部分为空链表。
     * 示例 2：
     *
     * 输入:
     * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
     * 输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
     * 解释:
     * 输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。
     * 提示:
     *
     * root 的长度范围： [0, 1000].
     * 输入的每个节点的大小范围：[0, 999].
     * k 的取值范围： [1, 50].
     */
    public ListNode[] splitListToPartsV1(ListNode root, int k) {
        List<ListNode> nodeList = new ArrayList<>();
        while (root != null) {
            nodeList.add(root);
            root = root.next;
        }
        // 共 10个 k = 3 => 4 3 3
        int batch = nodeList.size() / k; // 10 / 3 = 3
        int rem = nodeList.size() % k; // 10 % 3 = 3 余 1
        ListNode [] ans = new ListNode[batch];
        for (int i = 0; i < batch; i++) {
            if (i < rem) {
                ListNode at = new ListNode(0);
                ListNode aTemp = at;
                for (ListNode n : nodeList.subList(i * batch, (i + 1) * batch + 1)) {
                    aTemp.next = new ListNode(n.val);
                    aTemp = aTemp.next;
                }
                ans[i] = at.next;
            } else {
                ListNode bt = new ListNode(0);
                ListNode bTemp = bt;
                for (ListNode n : nodeList.subList(i * batch + rem, (i + 1) * batch + rem)) {
                    bTemp.next = new ListNode(n.val);
                    bTemp = bTemp.next;
                }
                ans[i] = bt.next;
            }
        }
        return ans;
    }

    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode cur = root;
        int N = 0;
        while (cur != null) {
            cur = cur.next;
            N++;
        }
        int width = N / k, rem = N % k;
        ListNode[] ans = new ListNode[k];
        cur = root;
        for (int i = 0; i < k; ++i) {
            ListNode head = new ListNode(0), write = head;
            for (int j = 0; j < width + (i < rem ? 1 : 0); ++j) {
                write = write.next = new ListNode(cur.val);
                if (cur != null) {
                    cur = cur.next;
                }
            }
            ans[i] = head.next;
        }
        return ans;
    }
}

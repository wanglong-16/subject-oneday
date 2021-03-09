package com.leetcode.march;

import com.leetcode.util.linked.ListNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-08 08:33:44
 * @author: wanglong16@meicai.cn
 */
public class Day7 {

    public int kthToLast(ListNode head, int k) {
        ListNode fast = head, slow = head;
        int f = 0;
        while (fast != null) {
            if (f >= k) {
                slow = slow.next;
            }
            fast = fast.next;
            k ++;
        }
        return slow.val;
    }

    /**
     * 剑指 Offer 06. 从尾到头打印链表
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     * 示例 1：
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     * 限制：
     *
     * 0 <= 链表长度 <= 10000
     */
    public int[] reversePrintV1(ListNode head) {
        List<Integer> result = new ArrayList<>();
        while (head != null) {
            result.add(head.val);
            head = head.next;
        }
        int[] ans = new int[result.size()];
        for (int i = result.size() - 1; i >=0; --i) {
            ans[i] = result.get(i);
        }
        return ans;
    }

    // 反转链表 1 2 3
    public int[] reversePrint(ListNode head) {
        int count = 0;
        ListNode prev = null;
        while (head != null) {
            ListNode nextTemp = head.next; // nextT = 2 3 4 5 => 3 4 5
            //每迭代一次，相当于 去掉头节点
            head.next = prev; //head = 1 => 2 1
            // 把当前的头节点和上个头节点链接到一起
            prev = head; // prev = 1 => 2 1
            //prev相当于每次取出头节点
            head = nextTemp; // head = 2 3 4 5 => 3 4 5
            count ++;
        }
        int[] ans = new int[count];
        int temp = count;
        while (prev != null) {
            ans[count - temp] = prev.val;
            prev = prev.next;
            temp --;
        }
        return ans;
    }

    //倒序填充
    public int[] reversePrintV2(ListNode head) {
        //先获取链表长度，创建对应长度数组
        ListNode currNode = head;
        int len = 0;
        while(currNode != null){
            len ++;
            currNode = currNode.next;
        }
        int[] result = new int[len];
        //再次遍历链表，将值倒序填充至结果数组
        currNode = head;
        while(currNode != null){
            result[len - 1] = currNode.val;
            len --;
            currNode = currNode.next;
        }
        return result;
    }

    /**
     * 445. 两数相加 II
     * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     * 进阶：
     * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
     * 示例：
     * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 8 -> 0 -> 7
     */
    public ListNode addTwoNumbersV1(ListNode l1, ListNode l2) {
        //使用栈逆序
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                stack1.push(l1.val);
                l1 = l1.next;
            }
            if (l2 != null) {
                stack2.push(l2.val);
                l2 = l2.next;
            }
        }
        boolean carry = false;
        int current, plus;
        List<Integer> list = new ArrayList<>();
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            plus = (stack1.isEmpty() ? 0 : stack1.pop()) + (stack2.isEmpty() ? 0 : stack2.pop());
            current = carry ? (1 + plus) : plus;
            carry = current >= 10;
            list.add(current % 10);
        }
        ListNode ans = new ListNode(1);
        ListNode temp = ans;
        for (int i = 0; i < list.size(); i++) {
            temp.next = new ListNode(list.get(list.size() - i - 1));
            temp = temp.next;
        }
        return carry ? ans : ans.next;
    }

    // v 官方
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new LinkedList<Integer>();
        Deque<Integer> stack2 = new LinkedList<Integer>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode ans = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int a = stack1.isEmpty() ? 0 : stack1.pop();
            int b = stack2.isEmpty() ? 0 : stack2.pop();
            int cur = a + b + carry;
            carry = cur / 10;
            cur %= 10;
            ListNode curnode = new ListNode(cur);
            curnode.next = ans;
            ans = curnode;
        }
        return ans;
    }

    /**
     * 面试题 02.07. 链表相交
     * 给定两个（单向）链表，判定它们是否相交并返回交点。请注意相交的定义基于节点的引用，而不是基于节点的值。换句话说，如果一个链表的第k个节点与另一个链表的第j个节点是同一节点（引用完全相同），则这两个链表相交。
     * 示例 1：
     * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * 输出：Reference of the node with value = 8
     * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     * 示例 2：
     * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
     * 输出：Reference of the node with value = 2
     * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
     *
     * 示例 3：
     *
     * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
     * 输出：null
     * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
     * 解释：这两个链表不相交，因此返回 null。
     *
     * 注意：
     * 如果两个链表没有交点，返回 null 。
     * 在返回结果后，两个链表仍须保持原有的结构。
     * 可假定整个链表结构中没有循环。
     * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
     */
    public ListNode getIntersectionNodeV1(ListNode headA, ListNode headB) {
        List<ListNode> A = new ArrayList<>();
        while (headA != null) {
            A.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            for (ListNode no : A) {
                if (no == headB) {
                    return no;
                }
            }
            headB = headB.next;
        }
        return null;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B) {
            B = B == null ? headA : B.next;
            A = A == null ? headB : A.next;
        }
        return A;
    }

    /**
     * 1474. 删除链表 M 个节点之后的 N 个节点
     * 给定链表 head 和两个整数 m 和 n. 遍历该链表并按照如下方式删除节点:
     *
     * 开始时以头节点作为当前节点.
     * 保留以当前节点开始的前 m 个节点.
     * 删除接下来的 n 个节点.
     * 重复步骤 2 和 3, 直到到达链表结尾.
     * 在删除了指定结点之后, 返回修改过后的链表的头节点.
     *
     * 进阶问题: 你能通过就地修改链表的方式解决这个问题吗?
     * 示例 1:
     * 输入: head = [1,2,3,4,5,6,7,8,9,10,11,12,13], m = 2, n = 3
     * 输出: [1,2,6,7,11,12]
     * 解析: 保留前(m = 2)个结点,  也就是以黑色节点表示的从链表头结点开始的结点(1 ->2).
     * 删除接下来的(n = 3)个结点(3 -> 4 -> 5), 在图中以红色结点表示.
     * 继续相同的操作, 直到链表的末尾.
     * 返回删除结点之后的链表的头结点.
     * 示例 2:
     * 输入: head = [1,2,3,4,5,6,7,8,9,10,11], m = 1, n = 3
     * 输出: [1,5,9]
     * 解析: 返回删除结点之后的链表的头结点.
     * 示例 3:
     *
     * 输入: head = [1,2,3,4,5,6,7,8,9,10,11], m = 3, n = 1
     * 输出: [1,2,3,5,6,7,9,10,11]
     * 示例 4:
     *
     * 输入: head = [9,3,7,7,9,10,8,2], m = 1, n = 2
     * 输出: [9,7,8]
     */
    public ListNode deleteNodes(ListNode head, int m, int n) {
        int count = 0, nCount = 0;
        ListNode ans = new ListNode(0);
        ans.next = head;
        ListNode temp = ans;
        temp = temp.next;
        while (temp != null) {
            if (count == m) {
                ListNode mStart = temp;
                while (mStart != null && nCount < n) {
                    mStart = mStart.next;
                    nCount ++;
                }
                temp.next = mStart;
                nCount = 0;
                count = 0;
            }
            temp = temp.next;
            count ++;
        }
        return ans.next;
    }

    /**
     * 1047. 删除字符串中的所有相邻重复项
     * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
     *
     * 在 S 上反复执行重复项删除操作，直到无法继续删除。
     *
     * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。

     * 示例：
     *
     * 输入："abbaca"
     * 输出："ca"
     * 解释：
     * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
     *
     *
     * 提示：
     *
     * 1 <= S.length <= 20000
     * S 仅由小写英文字母组成。*
     */
    public String removeDuplicatesV1(String S) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            if (!stack.empty() && stack.peek() == S.charAt(i)) {
                stack.pop();
            }
            stack.push(S.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public String removeDuplicates(String S) {
        char[] s = S.toCharArray();
        int top = -1;
        for (int i = 0; i < S.length(); i++) {
            if (top == -1 || s[top] != s[i]) {
                s[++top] = s[i];
            } else {
                top--;
            }
        }
        return String.valueOf(s, 0, top + 1);
    }

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, temp = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                while (temp != fast) {
                    fast = fast.next;
                    temp = temp.next;
                }
                return fast;
            }
        }
        return null;
    }

    /**
     * 1721. 交换链表中的节点
     * 给你链表的头节点 head 和一个整数 k 。
     * 交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。
     * 示例 1：
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[1,4,3,2,5]
     * 示例 2：
     * 输入：head = [7,9,6,6,7,8,3,0,9,5], k = 5
     * 输出：[7,9,6,6,8,7,3,0,9,5]
     * 示例 3：
     *
     * 输入：head = [1], k = 1
     * 输出：[1]
     * 示例 4：
     *
     * 输入：head = [1,2], k = 1
     * 输出：[2,1]
     * 示例 5：
     * 输入：head = [1,2,3], k = 2
     * 输出：[1,2,3]
     */
    public ListNode swapNodesV1(ListNode head, int k) {
        ListNode count = head, temp = head;
        int c = 0, snap = 0;
        while (count != null) {
            c++;
            snap ++;
            count = count.next;
        }
        ListNode [] nodes = new ListNode[c];
        while (temp != null) {
            nodes[snap - c] = temp;
            temp = temp.next;
            c --;
        }
        if (k >= snap / 2) {
            return head;
        } else {
            ListNode n = new ListNode(nodes[k].val);
            nodes[k] = new ListNode(nodes[snap - k - 1].val);
            nodes[snap - k - 1] = n;
        }
        ListNode ans = new ListNode(0);
        for (ListNode node : nodes) {
            ans.next = node;
        }
        return ans.next;
    }

    public ListNode swapNodes(ListNode head, int k) {
        ListNode current = head, firstK = head, lastK = head;
        int count = 1;
        while (current != null) {
            if (count < k) {
                firstK = firstK.next;
            } else {
                lastK = lastK.next;
            }
            count ++;
            current = current.next;
        }
        int temp = firstK.val;
        lastK.val = temp;
        firstK.val = temp;
        return head;
    }
}

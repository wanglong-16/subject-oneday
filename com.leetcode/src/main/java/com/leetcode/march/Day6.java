package com.leetcode.march;

import com.leetcode.util.linked.ListNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-06 16:00:11
 * @author: wanglong16@meicai.cn
 */
public class Day6 {

    /**
     * 876. 链表的中间结点
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     * 如果有两个中间结点，则返回第二个中间结点。
     * 示例 1：
     * 输入：[1,2,3,4,5]
     * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
     * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
     * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
     * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
     * 示例 2：
     * 输入：[1,2,3,4,5,6]
     * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
     * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
     * 提示：
     * 给定链表的结点数介于 1 和 100 之间。
     */
    public ListNode middleNodeV1(ListNode head) {
        List<ListNode> nodes = new ArrayList<>();
        while (head != null) {
            nodes.add(head);
            head = head.next;
        }
        return nodes.get(nodes.size() / 2);
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 141. 环形链表
     * 给定一个链表，判断链表中是否有环。
     *
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     *
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     * 进阶：
     * 你能用 O(1)（即，常量）内存解决此问题吗？
     * 示例 1：
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     * 示例 2：
     * 输入：head = [1,2], pos = 0
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     * 示例 3：
     * 输入：head = [1], pos = -1
     * 输出：false
     * 解释：链表中没有环。
     * 提示：
     * 链表中节点的数目范围是 [0, 104]
     * -105 <= Node.val <= 105
     * pos 为 -1 或者链表中的一个 有效索引 。
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head.next, slow = head;
        while (fast.next != null && fast.next.next != null) {
            if (fast.val == slow.val) {
                //有环的话快指针会进入环追赶慢指针。
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

    //hashset
    public boolean hasCycleV1(ListNode head) {
        Set<ListNode> seen = new HashSet<ListNode>();
        while (head != null) {
            if (!seen.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 142. 环形链表 II
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     *
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
     *
     * 说明：不允许修改给定的链表。
     *
     * 进阶：
     *
     * 你是否可以使用 O(1) 空间解决此题？
     * 示例 1：
     *
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：返回索引为 1 的链表节点
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     * 示例 2：
     * 输入：head = [1,2], pos = 0
     * 输出：返回索引为 0 的链表节点
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     * 示例 3：
     * 输入：head = [1], pos = -1
     * 输出：返回 null
     * 解释：链表中没有环。
     * 提示：
     * 链表中节点的数目范围在范围 [0, 104] 内
     * -105 <= Node.val <= 105
     * pos 的值为 -1 或者链表中的一个有效索引
     */
    public ListNode detectCycleV1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return head;
            }
            head = head.next;
        }
        return null;
    }

    public ListNode detectCycleV2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            if (set.contains(fast.next)) {
                return fast.next;
            }
            if (set.contains(fast.next.next)) {
                return fast.next.next;
            }
            fast = fast.next.next;
            set.add(slow);
            slow = slow.next;
        }
        return null;
    }

    //三指针 证明从起点到入环点 = 相遇点到入环点 fix bug
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head.next, slow = head;
        while (fast.next != null && fast.next.next != null) {
            if (fast.val == slow.val) {
                //有环的话快指针会进入环追赶慢指针。
                ListNode join = head;
                while (join != fast) {
                    fast = fast.next;
                    join = join.next;
                }
                return join;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return null;
    }

    //三指针 证明从起点到入环点 = 相遇点到入环点
    public ListNode detectCycleV3(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }
}

package com.leetcode.march;

import com.leetcode.util.linked.ImmutableListNode;
import com.leetcode.util.linked.ListNode;
import com.leetcode.util.tree.NTreeNode;

import java.util.ArrayDeque;
import java.util.Queue;
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
    public ListNode reverseListV1(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode nextTemp = head.next; // nextT = 2 3 4 5 => 3 4 5
            head.next = prev; //head = 1 => 2 1
            prev = head; // prev = 1 => 2 1
            head = nextTemp; // head = 2 3 4 5 => 3 4 5
        }
        return prev;
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

    public ListNode reverseListV2(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        Day1 day1 = new Day1();
        System.out.println(day1.reverseList(head));
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

    /**
     * 1265. 逆序打印不可变链表
     * 给您一个不可变的链表，使用下列接口逆序打印每个节点的值：
     *
     * ImmutableListNode: 描述不可变链表的接口，链表的头节点已给出。
     * 您需要使用以下函数来访问此链表（您 不能 直接访问 ImmutableListNode）：
     *
     * ImmutableListNode.printValue()：打印当前节点的值。
     * ImmutableListNode.getNext()：返回下一个节点。
     * 输入只用来内部初始化链表。您不可以通过修改链表解决问题。也就是说，您只能通过上述 API 来操作链表。
     * 进阶：
     *
     * 您是否可以：
     *
     * 使用常数级空间复杂度解决问题？
     * 使用线性级时间复杂度和低于线性级空间复杂度解决问题？
     * 示例 1：
     *
     * 输入：head = [1,2,3,4]
     * 输出：[4,3,2,1]
     * 示例 2：
     *
     * 输入：head = [0,-4,-1,3,-5]
     * 输出：[-5,3,-1,-4,0]
     * 示例 3：
     *
     * 输入：head = [-2,0,6,4,4,-6]
     * 输出：[-6,4,4,6,0,-2]
     */
    public void printLinkedListInReverse(ImmutableListNode head) {
        if (head != null) {
            printLinkedListInReverse(head.getNext());
            head.printValue();
        }
    }

    public void printLinkedListInReverseV1(ImmutableListNode head) {
        Stack<ImmutableListNode> nodes = new Stack<>();
        while (head != null) {
            nodes.push(head);
            head = head.getNext();
        }
        while (!nodes.empty()) {
            nodes.pop().printValue();
        }
    }


    /**
     * 1490. 克隆 N 叉树
     * 给定一棵 N 叉树的根节点 root ，返回该树的深拷贝（克隆）。
     * N 叉树的每个节点都包含一个值（ int ）和子节点的列表（ List[Node] ）。
     * class Node {
     *     public int val;
     *     public List<Node> children;
     * }
     * N 叉树的输入序列用层序遍历表示，每组子节点用 null 分隔（见示例）。
     *
     * 进阶：你的答案可以适用于克隆图问题吗？
     * 示例 1：
     * 输入：root = [1,null,3,2,4,null,5,6]
     * 输出：[1,null,3,2,4,null,5,6]
     * 示例 2：
     * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * 输出：[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * 提示：
     * 给定的 N 叉树的深度小于或等于 1000。
     * 节点的总个数在 [0, 10^4] 之间
     */
    public NTreeNode cloneTreeBfs(NTreeNode root) {
        Queue<NTreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int layerCount = 1;
        NTreeNode result = new NTreeNode(root.val);
        while (!queue.isEmpty()) {

        }
        return result;
    }

    public NTreeNode cloneTree(NTreeNode root) {
        NTreeNode result = new NTreeNode(root.val);
        for (NTreeNode nd : root.children) {
            result.children.add(cloneTree(nd));
        }
        return result;
    }

    /**
     * 1256. 加密数字
     * 给你一个非负整数 num ，返回它的「加密字符串」。
     * 加密的过程是把一个整数用某个未知函数进行转化，你需要从下表推测出该转化函数：
     *  -- n+1的二进制 去除第一位 --
     * 示例 1：
     * 输入：num = 23
     * 输出："1000"
     * 示例 2：
     *
     * 输入：num = 107
     * 输出："101100"
     */
    public String encodeV1(int num) {
        return Integer.toBinaryString(num + 1).substring(1);
    }

    public String encode(int num) {
        int temp = num + 1;
        StringBuilder sb = new StringBuilder();
        while (temp != 0) {
            sb.append(temp & 1);
            temp >>= 1;
        }
        return sb.reverse().substring(1, sb.length());
    }

    /**
     * 1063. 有效子数组的数目
     * 给定一个整数数组 A，返回满足下面条件的 非空、连续 子数组的数目：
     * 子数组中，最左侧的元素不大于其他元素。
     * 示例 1：
     * 输入：[1,4,2,5,3]
     * 输出：11
     * 解释：有 11 个有效子数组，分别是：[1],[4],[2],[5],[3],[1,4],[2,5],[1,4,2],[2,5,3],[1,4,2,5],[1,4,2,5,3] 。
     * 示例 2：
     * 输入：[3,2,1]
     * 输出：3
     * 解释：有 3 个有效子数组，分别是：[3],[2],[1] 。
     * 示例 3：
     * 输入：[2,2,2]
     * 输出：6
     * 解释：有 6 个有效子数组，分别为是：[2],[2],[2],[2,2],[2,2],[2,2,2] 。
     */
    public int validSubarrays(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] >= nums[i]) {
                    count ++;
                } else {
                    break;
                }
            }
            result += count;
        }
        return result;
    }

    /**
     * 42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 示例 1：
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     * 示例 2：
     *
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     * 提示：
     *
     * n == height.length
     * 0 <= n <= 3 * 104
     * 0 <= height[i] <= 105
     * todo check
     */
    public int trap(int[] height) {
        //单调递减栈
        int ans = 0;
        Stack<Integer> decrStack = new Stack<>();
        int left = height[0], start = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] < height[i - 1]) {
                left = height[i - 1];
                start = i - 1;
                break;
            }
        }
        decrStack.push(height[start]);
        for (int i = start + 1; i < height.length; i++) {
            if (height[i] < decrStack.peek()) {
                decrStack.push(height[i]);
            } else {
                int floorLine = Math.min(left, height[i]);
                while (!decrStack.empty()) {
                    ans += floorLine - decrStack.pop();
                }
                left = height[i];
                decrStack.push(left);
            }
        }
        return ans;
    }
}

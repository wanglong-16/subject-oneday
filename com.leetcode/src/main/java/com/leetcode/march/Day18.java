package com.leetcode.march;

import com.leetcode.util.linked.ListNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-18 07:56:10
 * @author: wanglong16@meicai.cn
 */
public class Day18 {

    /**
     * 92. 反转链表 II
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     *
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     */
    public ListNode reverseBetweenV1(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }
        ListNode prev = head;
        ListNode next = head;
        ListNode ans = new ListNode(0);
        ans.next = prev;
        int pC = 1, nC = 1;
        Stack<Integer> mindVals = new Stack<>();
        while (next != null) {
            if (pC < left) {
                prev = prev.next;
            } else {
                mindVals.push(head.val);
            }
            if (nC < right) {
                next = next.next;
            } else {
                break;
            }
            head = head.next;
        }
        while (!mindVals.isEmpty()) {
            prev.next = new ListNode(mindVals.pop());
            prev = prev.next;
        }
        prev.next = next;
        return ans.next;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        // 建议写在 for 循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 第 3 步：切断出一个子链表（截取链表）
        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;

        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;

        // 第 4 步：同第 206 题，反转链表的子区间
        reverseLinkedList(leftNode);

        // 第 5 步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }

    private void reverseLinkedList(ListNode head) {
        // 也可以使用递归反转一个链表
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

    }

    /**
     * 面试题 17.11. 单词距离
     * 有个内含单词的超大文本文件，给定任意两个单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
     *
     * 示例：
     *
     * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
     * 输出：1
     * 提示：
     *
     * words.length <= 100000
     */
    public int findClosestV1(String[] words, String word1, String word2) {
        int word1Start = 0, min = words.length;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                word1Start = i;
                if (i > 0) {
                    for (int j = i - 1; j > 0; j--) {
                        if (words[j].equals(word2)) {
                            min = Math.min(min, i - j);
                        }
                    }
                }
                if (i < words.length - 1) {
                    for (int j = i + 1; j < words.length; j++) {
                        if (words[j].equals(word2)) {
                            min = Math.min(min, j - i);
                        }
                    }
                }
            }
        }
        return min;
    }

    public int findClosest(String[] words, String word1, String word2) {
        int w1 = -1, w2 = -1, min = words.length;
        for (int i = 0; i < words.length; i++) {
            boolean change = false;
            if (words[i].equals(word1)) {
                w1 = i;
                change = true;
            }
            if (words[i].equals(word2)) {
                w2 = i;
                change = true;
            }
            if (w1 == -1 || w2 == -1) {
                continue;
            }
            if (change) {
                min = Math.min(min, Math.abs(w1 - w2));
            }
        }
        return min;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> uniqs = new ArrayList<>();
        for (int num : nums) {
            if (!uniqs.contains(num)) {
                uniqs.add(num);
            }
        }
        Collections.sort(uniqs);
        for (int i = 1; i <= uniqs.size() - 1; i++) {
            for (int j = i - 1; j >= 0; j--) {
                for (int k = i + 1; k < uniqs.size(); k++) {
                    if (uniqs.get(i) + uniqs.get(j) + uniqs.get(k) == 0) {
                        result.add(Arrays.asList(uniqs.get(i), uniqs.get(j), uniqs.get(k)));
                    }
                }
            }
        }
        return result;
    }

    /**
     * 311. 稀疏矩阵的乘法
     * 给你两个 稀疏矩阵 A 和 B，请你返回 AB 的结果。你可以默认 A 的列数等于 B 的行数。
     *
     * 请仔细阅读下面的示例。
     *
     *
     *
     * 示例：
     *
     * 输入：
     *
     * A = [
     *   [ 1, 0, 0],
     *   [-1, 0, 3]
     * ]
     *
     * B = [
     *   [ 7, 0, 0 ],
     *   [ 0, 0, 0 ],
     *   [ 0, 0, 1 ]
     * ]
     *
     * 输出：
     *
     *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
     * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
     *                   | 0 0 1 |
     */
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int[][] ans = new int[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                int sum = 0;
                for (int k = 0; k < mat2.length; k++) {
                    sum += mat1[i][j] * mat2[k][j];
                }
                ans[i][j] = sum;
            }
        }
        return ans;
    }

    /**
     * 419. 甲板上的战舰
     * 给定一个二维的甲板， 请计算其中有多少艘战舰。 战舰用 'X'表示，空位用 '.'表示。 你需要遵守以下规则：
     *
     * 给你一个有效的甲板，仅由战舰或者空位组成。
     * 战舰只能水平或者垂直放置。换句话说,战舰只能由 1xN (1 行, N 列)组成，或者 Nx1 (N 行, 1 列)组成，其中N可以是任意大小。
     * 两艘战舰之间至少有一个水平或垂直的空位分隔 - 即没有相邻的战舰。
     * 示例 :
     *
     * X..X
     * ...X
     * ...X
     * 在上面的甲板中有2艘战舰。
     * 无效样例 :
     * ...X
     * XXXX
     * ...X
     * 你不会收到这样的无效甲板 - 因为战舰之间至少会有一个空位将它们分开。
     *
     * 进阶:
     * 你可以用一次扫描算法，只使用O(1)额外空间，并且不修改甲板的值来解决这个问题吗？
     */
    public int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    count ++;
                    int startR = i;
                    if (startR + 1 < board.length && board[startR + 1][j] == 'X' ) {
                        while (startR < board.length && board[startR][j] == 'X') {
                            board[startR][j] = '.';
                            startR ++;
                        }
                    }
                    int startJ = j;
                    if (startJ + 1 < board[0].length && board[i][startJ + 1] == 'X' ) {
                        while (startJ < board[0].length && board[i][startJ] == 'X') {
                            board[i][startJ] = '.';
                            startJ ++;
                        }
                    }
                }
            }
        }
        return count;
    }

}

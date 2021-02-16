package com.leetcode.february;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-14 20:42:56
 * @author: wanglong16@meicai.cn
 */
public class SolutionSeven {

    /**
     * 243. 最短单词距离
     * 给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。
     *
     * 示例:
     * 假设 words = ["practice", "makes", "perfect", "coding", "makes"]
     *
     * 输入: word1 = “coding”, word2 = “practice”
     * 输出: 3
     * 输入: word1 = "makes", word2 = "coding"
     * 输出: 1
     * 注意:
     * 你可以假设 word1 不等于 word2, 并且 word1 和 word2 都在列表里。
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int p1 = 0, minDistance = words.length;
        while (p1 < words.length) {
            if (words[p1].equals(word1)) {
                int p2Left = p1, p2Right = p1;
                while (p2Left < words.length) {
                    if ((p2Left - p1) >= minDistance) {
                        break;
                    }
                    if (words[p2Left].equals(word2)) {
                        minDistance = Math.min(minDistance, p2Left - p1);
                        break;
                    }
                    p2Left ++;
                }
                while (p2Right >= 0) {
                    if ((p1 - p2Right) >= minDistance) {
                        break;
                    }
                    if (words[p2Right].equals(word2)) {
                        minDistance = Math.min(minDistance, p1 - p2Right);
                        break;
                    }
                    p2Right --;
                }
            }
            p1 ++;
        }
        return minDistance;
    }

    /**
     * 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * 示例 1：
     *
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * 示例 2：
     *
     * 输入：digits = ""
     * 输出：[]
     * 示例 3：
     *
     * 输入：digits = "2"
     * 输出：["a","b","c"]
     *
     *
     * 提示：
     *
     * 0 <= digits.length <= 4
     * digits[i] 是范围 ['2', '9'] 的一个数字。
     */
    public List<String> letterCombinations(String digits) {
        Map<Character, List<Character>> map = new HashMap<Character, List<Character>>() {{
            put('2', Arrays.asList('a', 'b', 'c'));
            put('3', Arrays.asList('d', 'e', 'f'));
            put('4', Arrays.asList('g', 'h', 'i'));
            put('5', Arrays.asList('j', 'k', 'l'));
            put('6', Arrays.asList('m', 'n', 'o'));
            put('7', Arrays.asList('p', 'q', 'r', 's'));
            put('8', Arrays.asList('t', 'u', 'v'));
            put('9', Arrays.asList('w', 'x', 'y', 'z'));
        }};
        char[] characters = digits.toCharArray();
        List<String> result = new ArrayList<>();
        List<List<Character>> patterns = new ArrayList<>();
        StringBuilder stringBuilder = null;
        for (Character character : characters) {
            for (Character character1 : map.get(character)) {

            }
        }

        return result;
    }

    /**
     * 485. 最大连续1的个数
     * 给定一个二进制数组， 计算其中最大连续1的个数。
     *
     * 示例 1:
     *
     * 输入: [1,1,0,1,1,1]
     * 输出: 3
     * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
     * 注意：
     *
     * 输入的数组只包含 0 和1。
     * 输入数组的长度是正整数，且不超过 10,000。
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int pStart = 0, pNext = 0, maxConsecutiveOnes = 0;
        while (pNext < nums.length) {
            if (nums[pNext] == 1) {
                pNext ++;
            } else {
                maxConsecutiveOnes = Math.max(maxConsecutiveOnes, pNext - pStart);
                for (int j = pNext; j < nums.length; j++) {
                    if (nums[j] == 1) {
                        pNext = j;
                        pStart = j;
                        break;
                    }
                    if (j == nums.length - 1) {
                        return maxConsecutiveOnes;
                    }
                }
            }
        }
        return Math.max(maxConsecutiveOnes, pNext - pStart);
    }

    public int findMaxConsecutiveOnesV1(int[] nums) {
        int maxCount = 0, count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        maxCount = Math.max(maxCount, count);
        return maxCount;
    }

    /**
     * 2. 两数相加
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     *
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例 1：
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     * 示例 2：
     *
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     * 示例 3：
     *
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     * 提示：
     *
     * 每个链表中的节点数在范围 [1, 100] 内
     * 0 <= Node.val <= 9
     * 题目数据保证列表表示的数字不含前导零
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int addPos = 0;
        ListNode head = new ListNode();
        while (l1 != null || l2 != null) {

            head.val = (l1.val + l2.val + addPos) % 10;
            addPos = (l1.val + l2.val + addPos) / 10;
            head.next = new ListNode();
            head = head.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 != null) {

        }
        return head;
    }
}

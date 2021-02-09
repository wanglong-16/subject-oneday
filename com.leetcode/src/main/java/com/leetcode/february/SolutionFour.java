package com.leetcode.february;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-06 16:17:18
 * @author: wanglong16@meicai.cn
 */
public class SolutionFour {

    /**
     * 349. 两个数组的交集
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     * 示例 2：
     * <p>
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[9,4]
     * <p>
     * <p>
     * 说明：
     * <p>
     * 输出结果中的每个元素一定是唯一的。
     * 我们可以不考虑输出结果的顺序。
     *
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> unique = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        if (nums1.length < nums2.length) {
            for (Integer i : nums1) {
                unique.add(i);
            }
            for (Integer integer : nums2) {
                if (unique.contains(integer)) {
                    result.add(integer);
                }
            }
        } else {
            for (Integer i : nums2) {
                unique.add(i);
            }
            for (Integer integer : nums1) {
                if (unique.contains(integer)) {
                    result.add(integer);
                }
            }
        }
        int[] ret = new int[result.size()];
        List<Object> objects = Arrays.asList(result.toArray());
        for (int i = 0; i < result.size(); i++) {
            ret[i] = Integer.parseInt(objects.get(i).toString());
        }
        return ret;
    }

    /**
     * 350. 两个数组的交集 II
     * 给定两个数组，编写一个函数来计算它们的交集。
     * 示例 1：
     * <p>
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2,2]
     * 示例 2:
     * <p>
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[4,9]
     * 说明：
     * <p>
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
     * 我们可以不考虑输出结果的顺序。
     * 进阶：
     * <p>
     * 如果给定的数组已经排好序呢？你将如何优化你的算法？
     * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
     * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0, p2 = 0;
        List<Integer> set = new ArrayList<>();
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] == nums2[p2]) {
                set.add(nums1[p1]);
                p1++;
                p2++;
            } else {
                if (nums1[p1] < nums2[p2]) {
                    p1++;
                } else {
                    p2++;
                }
            }
        }
        int[] ret = new int[set.size()];
        List<Object> objects = Arrays.asList(set.toArray());
        for (int i = 0; i < set.size(); i++) {
            ret[i] = Integer.parseInt(objects.get(i).toString());
        }
        return ret;
    }


    /**
     * 83. 删除排序链表中的重复元素
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->1->2
     * 输出: 1->2
     * 示例 2:
     * <p>
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     * 1 2
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode curr = head;
        while (curr.next != null) {
            ListNode next = curr.next;
            if (next.val == curr.val) {
                //删除下一个节点
                curr.next = next.next;
            } else {
                curr = next;
            }
        }
        return head;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 58. 最后一个单词的长度
     * 给你一个字符串 s，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。如果不存在最后一个单词，请返回 0 。
     * <p>
     * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "Hello World"
     * 输出：5
     * 示例 2：
     * <p>
     * 输入：s = " "
     * 输出：0
     */
    public int lengthOfLastWord(String s) {
        String[] strings = s.split(" ");
        if (strings.length == 0) {
            return 0;
        } else {
            return strings[strings.length - 1].length();
        }
    }

    public int lengthOfLastWordV1(String s) {
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        if (end < 0) {
            return 0;
        }
        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }
        return end - start;
    }

    /**
     * 203. 移除链表元素
     * 删除链表中等于给定值 val 的所有节点。
     * <p>
     * 示例:
     * <p>
     * 输入: 1->2->6->3->4->5->6, val = 6
     * 输出: 1->2->3->4->5
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        while (head != null) {
            //找到第一个不是val的节点
            if (head.val != val) {
                break;
            }
            head = head.next;
        }
        //开始遍历各个节点
        ListNode curr = head;
        do {
            if (curr != null && curr.next != null) {
                if (curr.next.val == val) {
                    curr.next = curr.next.next;
                } else {
                    curr = curr.next;
                }
            }
        } while (curr != null && curr.next != null);
        return head;
    }
}

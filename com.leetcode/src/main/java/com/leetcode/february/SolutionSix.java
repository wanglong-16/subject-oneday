package com.leetcode.february;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-11 20:23:48
 * @author: wanglong16@meicai.cn
 */
public class SolutionSix {

    /**
     * 21. 合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        ListNode head = l1;
        while (l1.next != null) {
            if (l2 == null) {
                break;
            }
            if (l1.val >= l2.val) {
                l1.next = l2;
                l1.next.next = l1.next;
                l2 = l2.next;
            } else {
                l1 = l1.next;
            }
        }
        return head;
    }

    //递归
    public ListNode mergeTwoListsV1(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    //迭代
    public ListNode mergeTwoListsV2(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }

    /**
     * 169. 多数元素
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[3,2,3]
     * 输出：3
     * 示例 2：
     *
     * 输入：[2,2,1,1,1,2,2]
     * 输出：2
     *
     *
     * 进阶：
     *
     * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 6. Z 字形变换
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     *
     * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
     *
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     *
     * 请你实现这个将字符串进行指定行数变换的函数：
     *
     * string convert(string s, int numRows);
     *
     *
     * 示例 1：
     *
     * 输入：s = "PAYPALISHIRING", numRows = 3
     * 输出："PAHNAPLSIIGYIR"
     * 示例 2：
     * 输入：s = "PAYPALISHIRING", numRows = 4
     * 输出："PINALSIGYAHRPI"
     * 解释：
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     * 示例 3：
     * == 14 / 6 * 3
     * 输入：s = "A", numRows = 1
     * 输出："A"
     *
     *
     * 提示：
     *
     * 1 <= s.length <= 1000
     * s 由英文字母（小写和大写）、',' 和 '.' 组成
     * 1 <= numRows <= 1000
     */
    public String convert(String s, int numRows) {
        int len = s.length(), col = (len / (2 * numRows - 2)) * (numRows - 1) ;
        char [][] chars = new char[numRows][col];
        char [] template = s.toCharArray();
        for (int i = 0; i < len / (2 * numRows - 2); i++) {
            for (int j = 0; j < numRows; j++) {
                chars[j][i * (numRows - 1)] = template[i];
            }
        }
        return ;
    }

    /**
     * 28. 实现 strStr()
     * 实现 strStr() 函数。
     *
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     *
     * 示例 1:
     *
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * 示例 2:
     *
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     * 说明:
     *
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     *
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     */
    public int strStr(String haystack, String needle) {
        if (needle == null || "".equals(needle) || haystack.equals(needle)) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        int neLen = needle.length();
        for (int i = 0; i <= haystack.length() - neLen; i++) {
            if (haystack.substring(i, i + neLen).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 35. 搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * 你可以假设数组中无重复元素。
     *
     * 示例 1:
     *
     * 输入: [1,3,5,6], 5
     * 输出: 2
     * 示例 2:
     *
     * 输入: [1,3,5,6], 2
     * 输出: 1
     * 示例 3:
     *
     * 输入: [1,3,5,6], 7
     * 输出: 4
     */
    public int searchInsert(int[] nums, int target) {
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                index = i;
                break;
            }
        }
        return index == -1 ? nums.length : index;
    }

    // 排序和二分查找
    public int searchInsertV1(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 1748. 唯一元素的和
     * 给你一个整数数组 nums 。数组中唯一元素是那些只出现 恰好一次 的元素。
     *
     * 请你返回 nums 中唯一元素的 和 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3,2]
     * 输出：4
     * 解释：唯一元素为 [1,3] ，和为 4 。
     * 示例 2：
     *
     * 输入：nums = [1,1,1,1,1]
     * 输出：0
     * 解释：没有唯一元素，和为 0 。
     * 示例 3 ：
     *
     * 输入：nums = [1,2,3,4,5]
     * 输出：15
     * 解释：唯一元素为 [1,2,3,4,5] ，和为 15 。
     */
    public int sumOfUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i : nums) {
            map.merge(i, 1, Integer::sum);
        }
        int ret = 0;
        for (Map.Entry entry : map.entrySet()) {
            if (Integer.parseInt(entry.getValue().toString()) == 1) {
                ret += Integer.parseInt(entry.getKey().toString());
            }
        }
        return ret;
    }

    /**
     * 1512. 好数对的数目
     * 给你一个整数数组 nums 。
     *
     * 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
     *
     * 返回好数对的数目。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3,1,1,3]
     * 输出：4
     * 解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
     * 示例 2：
     *
     * 输入：nums = [1,1,1,1]
     * 输出：6
     * 解释：数组中的每组数字都是好数对
     * 示例 3：
     *
     * 输入：nums = [1,2,3]
     * 输出：0
     */
    public int numIdenticalPairs(int[] nums) {
        int goodNums = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    goodNums ++;
                }
            }
        }
        return goodNums;
    }

    //hash表加统计法
    public int numIdenticalPairsV1(int[] nums) {
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        for (int num : nums) {
            m.put(num, m.getOrDefault(num, 0) + 1);
        }

        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            int v = entry.getValue();
            ans += v * (v - 1) / 2;
        }

        return ans;
    }

}

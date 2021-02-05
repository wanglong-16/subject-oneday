package com.leetcode.february;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-01 21:27:53
 * @author: wanglong16@meicai.cn
 */
public class SolutionTwo {


    /**
     * 283. 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 示例:
     *
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     *
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     */
    public void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                for (int j = i + 1;j < nums.length; j++) {
                    if (nums[j] != 0) {
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                    }
                }
            }
        }
    }

    /**
     * 371. 两整数之和
     * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
     *
     * 示例 1:
     *
     * 输入: a = 1, b = 2
     * 输出: 3
     * 示例 2:
     *
     * 输入: a = -2, b = 3
     * 输出: 1
     */
    public int getSum(int a, int b) {
        //todo
        return 0;
    }


    /**
     * 258. 各位相加
     * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
     *
     * 示例:
     *
     * 输入: 38
     * 输出: 2
     * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
     * 进阶:
     * 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
     */
    public int addDigits(int num) {
        int total = 0;
        while (num != 0) {
            total += num % 10;
            num /= 10;
        }
        if (total < 10) {
            return total;
        } else {
            return addDigits(total);
        }
    }

    /**
     * 206. 反转链表
     * 反转一个单链表。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * 进阶:
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     */
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     * a -> b -> c
     * result -> a
     * result -> b
     */
    public ListNode reverseList(ListNode head) {
        //todo
        ListNode result = head;
        while (head.next != null) {
            ListNode temp = head;
            result.next = temp;
        }
        return result;
    }

    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 246. 中心对称数
     * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
     *
     * 请写一个函数来判断该数字是否是中心对称数，其输入将会以一个字符串的形式来表达数字。
     *
     *
     *
     * 示例 1:
     *
     * 输入: num = "69"
     * 输出: true
     * 示例 2:
     *
     * 输入: num = "88"
     * 输出: true
     * 示例 3:
     *
     * 输入: num = "962"
     * 输出: false
     * 示例 4：
     *
     * 输入：num = "1"
     * 输出：true
     */
    public boolean isStrobogrammatic(String num) {
        List<String> one = Arrays.asList("1", "0", "8");
        if (num.length() == 1 && one.contains(num)) {
            return true;
        }
        Map<Character, Character> map = new HashMap<Character, Character>() {
            {put('1', '1');put('0', '0');put('8', '8');
            put('6', '9');put('9', '6');}
        };
        char [] chars = num.toCharArray();
        int left = 0, right = num.length() - 1;
        while (left <= right ) {
            if (map.get(chars[left]) != null && map.get(chars[left]) == chars[right]) {
                left ++;
                right --;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 1576. 替换所有的问号
     * 给你一个仅包含小写英文字母和 '?' 字符的字符串 s，请你将所有的 '?' 转换为若干小写字母，使最终的字符串不包含任何 连续重复 的字符。
     *
     * 注意：你 不能 修改非 '?' 字符。
     *
     * 题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。
     *
     * 在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。
     */
    public String modifyString(String s) {
        //todo
        if (s.equals("?")) {
            return "a";
        }
        char [] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                if (i == 0) {
                    if (chars[1] == '?' || chars[1] == 'z') {
                        chars[0] = 'a';
                    } else {
                        chars[0] = (char) (chars[1] + 1);
                    }
                } else if (i == chars.length - 1){
                    chars[chars.length - 1] = chars[chars.length - 2] == 'z' ? 'a' : (char) (chars[chars.length - 2] + 1);
                } else {
                    if (chars[i + 1] == '?') {
                        chars[i] = chars[i - 1] == 'z' ? 'a' : (char) (chars[i - 1] + 1);
                    } else {
                        if ((char) (chars[i - 1] + 1) == chars[i + 1]) {
                            chars[i] = chars[i + 1] == 'z' ? 'a' : (char) (chars[i + 1] + 1);
                        } else {
                            chars[i] = chars[i - 1] == 'z' ? 'a' : (char) (chars[i - 1] + 1);
                        }
                    }
                }
            }
        }
        StringBuilder result = new StringBuilder();
        if (!(chars.length == 0)) {
            for (Character c: chars) {
                result.append(c);
            }
        }
        return result.toString();
    }
}

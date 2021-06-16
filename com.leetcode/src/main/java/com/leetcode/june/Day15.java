package com.leetcode.june;

import com.leetcode.util.linked.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-15 21:39:11
 * @author: wanglong16@meicai.cn
 */
public class Day15 {

    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
            if (!list.get(i).equals(list.get(j))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
     *
     * 返回符合要求的 最少分割次数 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：s = "aab"
     * 输出：1
     * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
     * 示例 2：
     *
     * 输入：s = "a"
     * 输出：0
     * 示例 3：
     *
     * 输入：s = "ab"
     * 输出：1
     *
     *
     * 提示：
     *
     * 1 <= s.length <= 2000
     * s 仅由小写英文字母组成
     */
    public int minCut(String s) {
        return Math.min(getMinCut(s), getMinCut(new StringBuilder(s).reverse().toString()));
    }

    public int getMinCut(String s) {
        int l = 0, ans = 0;
        while (l < s.length() - 1) {
            l = find(s, l) + 1;
            if (l < s.length()) {
                ans ++;
            }
        }
        return ans;
    }

    public int find(String s, int left) {
        char lc = s.charAt(left);
        int right = s.length();
        while (true) {
            boolean findFirst = false;
            for (int i = right - 1; i > 0; i--) {
                if (s.charAt(i) == lc) {
                    right = i;
                    findFirst = true;
                    break;
                }
            }
            if (!findFirst || right == left) {
                return left;
            } else {
                if (isHuiWen(s.substring(left, right + 1))) {
                    return right;
                }
            }
        }
    }

    public boolean isHuiWen(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Day15 day15 = new Day15();
        System.out.println(day15.minCut("aba"));
    }
}

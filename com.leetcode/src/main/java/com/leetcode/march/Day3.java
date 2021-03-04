package com.leetcode.march;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-04 10:20:15
 * @author: wanglong16@meicai.cn
 */
public class Day3 {

    /**
     * 22. 括号生成
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * 示例 1：
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     * 示例 2：
     * 输入：n = 1
     * 输出：["()"]
     * 000111, 001011, 001101, 010011, 010101
     * 从 1 - > 1 << n * 2 - 1 计算总的括号
     */
    public List<String> generateParenthesisV1(int n) {
        // 0 代表 "(", 1 代表 ")", n 对的话至少需要 n 个 1,
        // 所以从 1 << (n + 1) - 1 到 i < 1 << (n * 2) - 1 开始遍历统计
        List<String> results = new ArrayList<>();
        Stack<String> stack;
        StringBuilder sb;
        for (int i = (1 << n) - 1; i <= (1 << (n * 2 - 1)) - 1; i++) {
            stack = new Stack<String>() {{push("(");}}; //用栈检查是否是有效括号
            sb = new StringBuilder("("); //单个的最终结果
            for (int j = (n << 1) - 2; j >= 0; j--) { // 需要计算共 2n - 1次
                if (((i >> j) & 1) == 1) { // 当前位是1
                    sb.append(")");
                    if (!stack.isEmpty() && stack.peek().equals("(")) {
                        stack.pop();
                    } else {
                        stack.push(")");
                    }
                } else {
                    sb.append("(");
                    stack.push("(");
                }
            }
            if (stack.isEmpty()) {
                results.add(sb.toString());
            }
        }
        return results;
    }

    //官方递归解法
    ArrayList[] cache = new ArrayList[100];

    public List<String> generate(int n) {
        if (cache[n] != null) {
            return cache[n];
        }
        ArrayList<String> ans = new ArrayList<String>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c) {
                for (String left: generate(c)) {
                    for (String right: generate(n - 1 - c)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        cache[n] = ans;
        return ans;
    }

    public List<String> generateParenthesis(int n) {
        return generate(n);
    }

    // 递归 + 回溯解法2
    public List<String> generateParenthesisV2(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }
    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /**
     * 395. 至少有 K 个重复字符的最长子串
     * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
     * 示例 1：
     * 输入：s = "aaabb", k = 3
     * 输出：3
     * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
     * 示例 2：
     * 输入：s = "ababbc", k = 2
     * 输出：5
     * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
     * 提示：
     * 1 <= s.length <= 104
     * s 仅由小写英文字母组成
     * 1 <= k <= 105
     */
    public int longestSubstring(String s, int k) {
        /// TODO: 2021/3/4  
        return 0;
    }

    /**
     * 面试题 05.03. 翻转数位
     * 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
     * 示例 1：
     * 输入: num = 1775(110111011112)
     * 输出: 8
     * 示例 2：
     * 输入: num = 7(01112)
     * 输出: 4
     */
    public int reverseBits(int num) {
        if (num == -1) {
            return 32;
        }
        int cnt1 = 1;
        int pos = -1;
        int result = 0;
        for (int i = 0; i < 33; i++) {
            if ((num & 1) == 1) {
                cnt1++;
            } else {
                result = Math.max(result, cnt1);
                cnt1 = i - pos;
                pos = i;
            }
            num >>>= 1;
        }
        return result;
    }

    /**
     * 496. 下一个更大元素 I
     * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
     *
     * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
     *
     * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
     * 示例 1:
     *
     * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * 输出: [-1,3,-1]
     * 解释:
     *     对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
     *     对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
     *     对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
     * 示例 2:
     *
     * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
     * 输出: [3,-1]
     * 解释:
     *     对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
     *     对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            boolean flag = false, find = false;
            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == nums1[i]) {
                    flag = true;
                }
                if (flag && nums2[j] > nums1[i]) {
                    ans[i] = nums2[j];
                    find = true;
                    break;
                }
            }
            if (!find) {
                ans[i] = -1;
            }
        }
        return ans;
    }

}

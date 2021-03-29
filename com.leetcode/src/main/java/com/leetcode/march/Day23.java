package com.leetcode.march;

import java.util.Stack;
import java.util.TreeMap;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-23 21:20:07
 * @author: wanglong16@meicai.cn
 */
public class Day23 {

    /**
     * 1450. 在既定时间做作业的学生人数
     * 给你两个整数数组 startTime（开始时间）和 endTime（结束时间），并指定一个整数 queryTime 作为查询时间。
     *
     * 已知，第 i 名学生在 startTime[i] 时开始写作业并于 endTime[i] 时完成作业。
     *
     * 请返回在查询时间 queryTime 时正在做作业的学生人数。形式上，返回能够使 queryTime 处于区间 [startTime[i], endTime[i]]（含）的学生人数。
     * 示例 1：
     *
     * 输入：startTime = [1,2,3], endTime = [3,2,7], queryTime = 4
     * 输出：1
     * 解释：一共有 3 名学生。
     * 第一名学生在时间 1 开始写作业，并于时间 3 完成作业，在时间 4 没有处于做作业的状态。
     * 第二名学生在时间 2 开始写作业，并于时间 2 完成作业，在时间 4 没有处于做作业的状态。
     * 第三名学生在时间 3 开始写作业，预计于时间 7 完成作业，这是是唯一一名在时间 4 时正在做作业的学生。
     * 示例 2：
     *
     * 输入：startTime = [4], endTime = [4], queryTime = 4
     * 输出：1
     * 解释：在查询时间只有一名学生在做作业。
     * 示例 3：
     *
     * 输入：startTime = [4], endTime = [4], queryTime = 5
     * 输出：0
     * 示例 4：
     *
     * 输入：startTime = [1,1,1,1], endTime = [1,3,2,4], queryTime = 7
     * 输出：0
     * 示例 5：
     *
     * 输入：startTime = [9,8,7,6,5,4,3,2,1], endTime = [10,10,10,10,10,10,10,10,10], queryTime = 5
     * 输出：5
     */
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int ans = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (queryTime >= startTime[i] && queryTime <= endTime[i]) {
                ans ++;
            }
        }
        return ans;
    }

    /**
     * 1614. 括号的最大嵌套深度
     * 如果字符串满足以下条件之一，则可以称之为 有效括号字符串（valid parentheses string，可以简写为 VPS）：
     *
     * 字符串是一个空字符串 ""，或者是一个不为 "(" 或 ")" 的单字符。
     * 字符串可以写为 AB（A 与 B 字符串连接），其中 A 和 B 都是 有效括号字符串 。
     * 字符串可以写为 (A)，其中 A 是一个 有效括号字符串 。
     * 类似地，可以定义任何有效括号字符串 S 的 嵌套深度 depth(S)：
     *
     * depth("") = 0
     * depth(C) = 0，其中 C 是单个字符的字符串，且该字符不是 "(" 或者 ")"
     * depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是 有效括号字符串
     * depth("(" + A + ")") = 1 + depth(A)，其中 A 是一个 有效括号字符串
     * 例如：""、"()()"、"()(()())" 都是 有效括号字符串（嵌套深度分别为 0、1、2），而 ")(" 、"(()" 都不是 有效括号字符串 。
     *
     * 给你一个 有效括号字符串 s，返回该字符串的 s 嵌套深度 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：s = "(1+(2*3)+((8)/4))+1"
     * 输出：3
     * 解释：数字 8 在嵌套的 3 层括号中。
     * 示例 2：
     *
     * 输入：s = "(1)+((2))+(((3)))"
     * 输出：3
     * 示例 3：
     *
     * 输入：s = "1+(2*3)/(2-1)"
     * 输出：1
     * 示例 4：
     *
     * 输入：s = "1"
     * 输出：0
     *
     *
     * 提示：
     *
     * 1 <= s.length <= 100
     * s 由数字 0-9 和字符 '+'、'-'、'*'、'/'、'('、')' 组成
     * 题目数据保证括号表达式 s 是 有效的括号表达式
     */
    public int maxDepthV1(String s) {
        Stack<Character> stack = new Stack<>();
        int max = 0;
        for (Character ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            }
            if (ch == ')') {
                if (stack.peek() == '(') {
                    max = Math.max(max, stack.size());
                    stack.pop();
                }
            }
        }
        return max;
    }

    public int maxDepth(String s) {
        int res=0, temp=0;
        for (int i = 0; i<s.length(); i++) {
            if (s.charAt(i) == '(') {
                temp ++;
            } else if (s.charAt(i) == ')') {
                if (temp > res) {
                    res = temp;
                }
                temp --;
            }
        }
        return res;
    }


    /**
     * 415. 字符串相加
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     * 提示：
     *
     * num1 和num2 的长度都小于 5100
     * num1 和num2 都只包含数字 0-9
     * num1 和num2 都不包含任何前导零
     * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
     */
    public String addStrings(String num1, String num2) {
        int n1 = num1.length() - 1, n2 = num2.length() - 1;
        boolean carry = false;
        StringBuilder ans = new StringBuilder();
        while (n1 >= 0 || n2 >= 0) {
            int nu1 = n1 >= 0 ? num1.charAt(n1) - 48 : 0;
            int nu2 = n2 >= 0 ? num2.charAt(n2) - 48 : 0;
            int temp = carry ? (nu1 + nu2 + 1) : (nu1 + nu2);
            carry = temp >= 10;
            ans.append(temp % 10);
            n1 --;
            n2 --;
        }
        return ans.reverse().toString();
    }

    /**
     * 370. 区间加法
     * 假设你有一个长度为 n 的数组，初始情况下所有的数字均为 0，你将会被给出 k​​​​​​​ 个更新的操作。
     *
     * 其中，每个操作会被表示为一个三元组：[startIndex, endIndex, inc]，你需要将子数组 A[startIndex ... endIndex]（包括 startIndex 和 endIndex）增加 inc。
     *
     * 请你返回 k 次操作后的数组。
     *
     * 示例:
     *
     * 输入: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
     * 输出: [-2,0,3,5,3]
     * 解释:
     *
     * 初始状态:
     * [0,0,0,0,0]
     *
     * 进行了操作 [1,3,2] 后的状态:
     * [0,2,2,2,0]
     *
     * 进行了操作 [2,4,3] 后的状态:
     * [0,2,5,5,3]
     *
     * 进行了操作 [0,2,-2] 后的状态:
     * [-2,0,3,5,3]
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            for (int[] update : updates) {
                if (i >= update[0] && i <= update[1]) {
                    ans[i] += update[2];
                }
            }
        }
        return ans;
    }

    /**
     * 456. 132模式
     * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
     *
     * 注意：n 的值小于15000。
     *
     * 示例1:
     *
     * 输入: [1, 2, 3, 4]
     *
     * 输出: False
     *
     * 解释: 序列中不存在132模式的子序列。
     * 示例 2:
     *
     * 输入: [3, 1, 4, 2]
     *
     * 输出: True
     *
     * 解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
     * 示例 3:
     *
     * 输入: [-1, 3, 2, 0]
     *
     * 输出: True
     *
     * 解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
     */
    public boolean find132patternV1(int[] nums) {
        for (int i = 1; i < nums.length - 1; i++) {
            int mid = i;
            int left = mid - 1, right = mid + 1;
            int secMax = Integer.MIN_VALUE;
            while (right < nums.length) {
                if (nums[right] < nums[mid]) {
                    secMax = Math.max(secMax, nums[right]);
                }
                right ++;
            }
            if (secMax != Integer.MIN_VALUE) {
                while (left >= 0) {
                    if (nums[left] < secMax) {
                        return true;
                    }
                    left --;
                }
            }
        }
        return false;
    }

    /**
     *
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        // 左侧最小值
        int leftMin = nums[0];
        // 右侧所有元素
        TreeMap<Integer, Integer> rightAll = new TreeMap<Integer, Integer>();
        for (int k = 2; k < n; ++k) {
            rightAll.put(nums[k], rightAll.getOrDefault(nums[k], 0) + 1);
        }
        for (int j = 1; j < n - 1; ++j) {
            if (leftMin < nums[j]) {
                Integer next = rightAll.ceilingKey(leftMin + 1);
                if (next != null && next < nums[j]) {
                    return true;
                }
            }
            leftMin = Math.min(leftMin, nums[j]);
            rightAll.put(nums[j + 1], rightAll.get(nums[j + 1]) - 1);
            if (rightAll.get(nums[j + 1]) == 0) {
                rightAll.remove(nums[j + 1]);
            }
        }
        return false;
    }

}

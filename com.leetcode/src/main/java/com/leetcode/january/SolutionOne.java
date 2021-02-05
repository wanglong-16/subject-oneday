package com.leetcode.january;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-20 22:37:43
 * @author: wanglong16@meicai.cn
 */
public class SolutionOne {

    /**
     * 数组中有正负数，求出数组中三个数的最大乘积
     * @param nums
     * @return
     */
    public int maximumProduct(int [] nums) {
        //分类讨论：
        //1、找到俩个绝对值最大的负数 + 最大的一个正数 备选
        //2、三个绝对值最大的正数 备选
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);
    }

    /**
     * 线性扫描 得出五个数 最大的三个，最小的俩个
     * @param nums
     * @return
     */
    public int maximumProductV2(int[] nums) {
        // 最小的和第二小的
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        // 最大的、第二大的和第三大的
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }

            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }


    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     * 示例 1：
     *
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     * 示例 2：
     *
     * 输入：n = 1
     * 输出：["()"]
     *
     * 提示:
     * 1 <= n <= 8
     */
    public List<String> generateParenthesis(int n) {
        //先生成2 的 n 次方个模版，再依次检验这些模版是否符合有效括号
        List<String> patterns = new CopyOnWriteArrayList<String>();
        List<String> results = new ArrayList<String>();
        patterns.add("(");
        patterns.add(")");
        for (int i = 0; i < 2 * n - 1; i++) {
            for(String s : patterns) {
                String temp1 = s + ")";
                String temp2 = s + "(";
                patterns.add(temp1);
                patterns.add(temp2);
            }
        }
        //检查每个元素
        for (String str : patterns) {
            if (str.length() == 2 * n && isEffective(str)) {
                results.add(str);
            }
        }
        return results;
    }

    public boolean isEffective(String string) {
        char [] chars = string.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        for (char aChar : chars) {
            if (aChar == '(') {
                stack.push('(');
            } else {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 官方暴力解法
     * @param n
     * @return
     */
    public List<String> generateParenthesis1(int n) {
        List<String> combinations = new ArrayList<String>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

}

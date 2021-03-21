package com.leetcode.march;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-20 17:05:23
 * @author: wanglong16@meicai.cn
 */
public class Day19 {

    /**
     * 150. 逆波兰表达式求值
     * 根据 逆波兰表示法，求表达式的值。
     *
     * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     *
     *
     *
     * 说明：
     *
     * 整数除法只保留整数部分。
     * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     *
     *
     * 示例 1：
     *
     * 输入：tokens = ["2","1","+","3","*"]
     * 输出：9
     * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     * 示例 2：
     *
     * 输入：tokens = ["4","13","5","/","+"]
     * 输出：6
     * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
     * 示例 3：
     *
     * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
     * 输出：22
     * 解释：
     * 该算式转化为常见的中缀算术表达式为：
     *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
     * 提示：
     *
     * 1 <= tokens.length <= 104
     * tokens[i] 要么是一个算符（"+"、"-"、"*" 或 "/"），要么是一个在范围 [-200, 200] 内的整数
     */
    public int evalRPN(String[] tokens) {
        List<String> opts = Arrays.asList("+", "-", "*", "/");
        Stack<Integer> integers = new Stack<>();
        for (String str : tokens) {
            if (!opts.contains(str)) {
                integers.push(Integer.valueOf(str));
            } else {
                if ("+".equals(str)) {
                    integers.push(integers.pop() + integers.pop());
                } else if ("-".equals(str)) {
                    integers.push(integers.pop() - integers.pop());
                } else if ("*".equals(str)) {
                    integers.push(integers.pop() * integers.pop());
                } else {
                    integers.push(integers.pop() / integers.pop());
                }
            }
        }
        return integers.pop();
    }

    /**
     * 
     */

}

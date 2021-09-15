package com.leetcode.september;

import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-09-11 15:00:45
 * @author: wanglong16@meicai.cn
 */
public class Day1 {

    // 1 3
    // 5 4

    // 3 1
    // 6 5
    public int minimumSwitchingTimes(int[][] source, int[][] target) {
        int[] sr = new int[10000];
        int[] ta = new int[10000];
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[0].length; j++) {
                sr[source[i][j]] ++;
                ta[target[i][j]] ++;
            }
        }
        int diff = 0;
        for (int i = 0; i < 10000; i++) {
            diff += Math.abs(sr[i] - ta[i]);
        }
        return diff / 2;
    }

    public boolean checkValidString(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (!stack.isEmpty()) {
                Character top = stack.peek();
                if (c == '(' && top == ')' ||
                        c == ')' && top == '(' ||
                     top == '*') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            } else {
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            if (stack.pop() != '*') {
                return false;
            }
        }
        return true;
    }

    public String reversePrefix(String word, char ch) {
        StringBuilder sb = new StringBuilder();
        boolean find = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ch && ! find) {
                sb.append(word.charAt(i));
                sb.reverse();
                find = true;
            } else {
                sb.append(word.charAt(i));
            }
        }
        return sb.toString();
    }

    public long interchangeableRectangles(int[][] rectangles) {
        long result = 0;
        int[] ret = new int[rectangles.length];
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = i + 1; j < rectangles.length; j++) {
                if (rectangles[i][0] * rectangles[j][1] == rectangles[i][1] * rectangles[j][0]) {
                    result++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        System.out.println(day1.interchangeableRectangles(new int[][]
                {{4,5},{7,8}}
        ));
    }
}

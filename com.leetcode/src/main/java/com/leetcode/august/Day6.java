package com.leetcode.august;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-08 10:18:58
 * @author: wanglong16@meicai.cn
 */
public class Day6 {


    public boolean isPrefixString(String s, String[] words) {
        int start = 0;
        for (int i = 0; i < words.length; i++) {
            String st = words[i];
            if (start + st.length() > s.length()) {
                return false;
            }
            int end = Math.min(start + st.length(), s.length());
            if (s.substring(start, end).equals(st)) {
                start += st.length();
            } else {
                return false;
            }
            if (start == s.length()) {
                break;
            }
        }
        return start == s.length();
    }

    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        int sum = 0;
        for (Integer in : piles) {
            queue.add(in);
            sum += in;
        }
        int t = 0;
        while (t < k) {
            int max = queue.poll();
            sum -= max / 2;
            queue.offer(max - max / 2);
            t ++;
        }
        return sum;
    }

    public int minSwaps(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character ch : s.toCharArray()) {
            if (ch == '[') {
                stack.push('[');
            }
            if (ch == ']') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        return stack.size();
    }


    public static void main(String[] args) {
        int[][] temp = new int[][] {
                {},{},{}
        };
        char[][] chars = new char[][]
                {
                        {'W','B','W','.','.','W','W','.'},
                        {'W','B','.','W','W','W','B','W'},
                        {'.','B','B','.','B','W','B','W'},
                        {'.','.','B','B','B','W','W','.'},
                        {'B','B','.','B','.','.','.','B'},
                        {'.','W','B','.','.','B','.','B'},
                        {'.','W','.','W','.','W','B','W'},
                        {'W','.','B','.','W','W','B','.'}
                };

        Day6 day5 = new Day6();
        System.out.println(day5.minSwaps("][]["));
    }

}

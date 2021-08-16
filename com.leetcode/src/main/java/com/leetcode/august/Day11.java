package com.leetcode.august;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-15 15:31:14
 * @author: wanglong16@meicai.cn
 */
public class Day11 {

    public int numOfStrings(String[] patterns, String word) {
        int ans = 0;
        for (String str : patterns) {
            if (word.contains(str)) {
                ans ++;
            }
        }
        return ans;
    }

    public int[] rearrangeArray(int[] nums) {
        Arrays.sort(nums);
        int[] ans = new int[nums.length];
        int left = 0, right = nums.length - 1, index = -1;
        while (left <= right) {
            if (index ++ % 2 == 1) {
                ans[index] = nums[left ++];
            } else {
                ans[index] = nums[right --];
            }
        }
        return ans;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{};
    }


    public int totalMoney(int n) {
        int weeks = n / 7, day = n % 7;
        int ans = 0;
        for (int i = 0; i < weeks; i++) {
            for (int j = 1; j <= 7; j++) {
                ans += j + i;
            }
        }
        for (int i = 0; i < day; i++) {
            ans += weeks + i;
        }
        return ans;
    }

    public int maximumGain(String s, int x, int y) {
        int ans = 0;
        Character pFirst = x >= y ? 'a' : 'b';
        Character pSecond = x >= y ? 'b' : 'a';
        int max = Math.max(x, y), min = Math.min(x, y);
        Stack<Character> stack1 = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (!stack1.empty() && stack1.peek().equals(pFirst) && c.equals(pSecond)) {
                stack1.pop();
                ans += max;
            } else {
                stack1.push(c);
            }
        }
        Stack<Character> stack2 = new Stack<>();
        while (!stack1.empty()) {
            Character c = stack1.pop();
            if (!stack2.empty() && stack2.peek().equals(pFirst) && c.equals(pSecond)) {
                stack2.pop();
                ans += min;
            } else {
                stack2.push(c);
            }
        }
        return ans;
    }

    public int maxProduct(String[] words) {
        int[] wordSign = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            wordSign[i] = calculateStrIntVal(words[i]);
        }
        int ans = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((wordSign[i] & wordSign[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }

    public int calculateStrIntVal(String str) {
        Set<Character> set = new HashSet<>();
        int ret = 0;
        for (Character ch : str.toCharArray()) {
            if (set.add(ch)) {
                ret += 1 << ch - 'a';
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Day11 day11 = new Day11();
        System.out.println(day11.calculateStrIntVal("abcd"));
    }



}

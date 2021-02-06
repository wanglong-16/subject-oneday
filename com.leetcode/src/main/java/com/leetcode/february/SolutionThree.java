package com.leetcode.february;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-04 10:38:07
 * @author: wanglong16@meicai.cn
 */
public class SolutionThree {

    /**
     * 217. 存在重复元素
     * 给定一个整数数组，判断是否存在重复元素。
     *
     * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (Integer i: nums) {
            if (set.contains(i)) {
                return true;
            } else {
                set.add(i);
            }
        }
        return false;
    }

    /**
     * 202. 快乐数
     * 编写一个算法来判断一个数 n 是不是快乐数。
     *
     * 「快乐数」定义为：
     *
     * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
     * 如果 可以变为  1，那么这个数就是快乐数。
     * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<Integer>();
        while (true) {
            if (n == 1) {
                return true;
            }
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
            n = replacePowerNumber(n);
        }
    }

    public int replacePowerNumber(int n) {
        int ret = 0;
        while (n != 0) {
            int temp = n % 10;
            ret += temp * temp;
            n /= 10;
        }
        return ret;
    }

    /**
     * 66. 加一
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     *
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     *
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     *
     *
     * 示例 1：
     *
     * 输入：digits = [1,2,3]
     * 输出：[1,2,4]
     * 解释：输入数组表示数字 123。
     */
    public int[] plusOne(int[] digits) {
        boolean nextOne = false;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (i == 0 && digits[0] == 9 && nextOne || digits.length == 1 && digits[0] == 9) {
                int [] ret = new int [digits.length + 1];
                ret[0] = 1;
                return ret;
            }
            int curr = digits[i];
            if (nextOne || i == digits.length - 1) {
                curr ++;
                nextOne = curr > 9;
                digits [i] = curr % 10;
            }
        }
        return digits;
    }

    /**
     * 345. 反转字符串中的元音字母
     * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
     *
     *
     *
     * 示例 1：
     *
     * 输入："hello"
     * 输出："holle"
     * 示例 2：
     *
     * 输入："leetcode"
     * 输出："leotcede"
     */
    public String reverseVowels(String s) {
        char [] chars = s.toCharArray();
        List<Character> characters = Arrays.asList('a','e','i','o','u','A','E','I','O','U'
);
        int left = 0, right = chars.length - 1;
        while (left < right) {
            if (characters.contains(chars[left]) && characters.contains(chars[right])) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left ++;
                right --;
            }
            if (!characters.contains(chars[left])) {
                left ++;
            }
            if (!characters.contains(chars[right])) {
                right --;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Character character : chars) {
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    /**
     * 266. 回文排列
     * 给定一个字符串，判断该字符串中是否可以通过重新排列组合，形成一个回文字符串。
     *
     * 示例 1：
     *
     * 输入: "code"
     * 输出: false
     * 示例 2：
     *
     * 输入: "aab"
     * 输出: true
     * 示例 3：
     *
     * 输入: "carerac"
     * 输出: true
     */
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char [] chars = s.toCharArray();
        for (Character c: chars) {
            map.merge(c, 1, Integer::sum);
        }
        int count = 0;
        for (Map.Entry entry : map.entrySet()) {
            if ((int)(entry.getValue()) % 2 != 0) {
                count ++;
            }
        }
        return count <= 1;
    }

}

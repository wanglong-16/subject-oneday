package com.leetcode.february;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-01 21:18:53
 * @author: wanglong16@meicai.cn
 */
public class SolutionOne {

    /**
     * 263. 丑数
     * 编写一个程序判断给定的数是否为丑数。
     * <p>
     * 丑数就是只包含质因数 2, 3, 5 的正整数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 6
     * 输出: true
     * 解释: 6 = 2 × 3
     */
    public boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }
        while (num != 1) {
            if (num % 5 == 0) {
                num = num / 5;
            } else if (num % 3 == 0) {
                num = num / 3;
            } else if (num % 2 == 0) {
                num = num / 2;
            } else {
                break;
            }
        }
        return num == 1;
    }


    /**
     * 344. 反转字符串
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     * <p>
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * <p>
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     */
    public void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char swap = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = swap;
        }
    }

    /**
     * 205. 同构字符串
     * 给定两个字符串 s 和 t，判断它们是否是同构的。
     * <p>
     * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
     * <p>
     * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，
     * 相同字符只能映射到同一个字符上，字符可以映射到自己本身。
     */
    public boolean isIsomorphic(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Map<Character, Character> sMap = new HashMap<Character, Character>(sChars.length) {
        };
        Map<Character, Character> tMap = new HashMap<Character, Character>(sChars.length) {
        };
        for (int i = 0; i < sChars.length; i++) {
            Character sC = sChars[i];
            Character tC = tChars[i];
            if (sMap.get(sC) == null) {
                sMap.put(sC, tC);
            } else {
                if (!sMap.get(sC).equals(tC)) {
                    return false;
                }
            }
            if (tMap.get(tC) == null) {
                tMap.put(tC, sC);
            } else {
                if (!tMap.get(tC).equals(sC)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 242. 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * <p>
     * 示例 1:
     * <p>
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: s = "rat", t = "car"
     * 输出: false
     * 说明:
     * 你可以假设字符串只包含小写字母。
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> sMap = calculateCharacters(s);
        Map<Character, Integer> tMap = calculateCharacters(t);
        if (!sMap.isEmpty() && !tMap.isEmpty()) {
            for (Map.Entry entry : sMap.entrySet()) {
                if (tMap.get(entry.getKey()) == null || !tMap.get(entry.getKey()).equals(entry.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    public Map<Character, Integer> calculateCharacters(String str) {
        char[] characters = str.toCharArray();
        Map<Character, Integer> ret = new HashMap<Character, Integer>();
        for (Character c : characters) {
            if (ret.get(c) != null) {
                ret.put(c, ret.get(c) + 1);
            } else {
                ret.put(c, 1);
            }
        }
        return ret;
    }

    /**
     * 排序比较
     */
    public boolean isAnagramV1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    /**
     * 290. 单词规律
     * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
     *
     * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
     *
     * 示例1:
     *
     * 输入: pattern = "abba", str = "dog cat cat dog"
     * 输出: true
     */
    public boolean wordPattern(String pattern, String s) {
        String [] strings = s.split(" ");
        char [] patterns = pattern.toCharArray();
        Map<Character, String> map = new HashMap<>();
        Map<String, Character> diffMap = new HashMap<>();
        if (patterns.length != strings.length) {
            return false;
        }
        for (int i = 0; i < patterns.length; i++) {
            if (map.get(patterns[i]) != null && !map.get(patterns[i]).equals(strings[i])) {
                return false;
            } else {
                map.put(patterns[i], strings[i]);
            }
        }
        for (int i = 0; i < patterns.length; i++) {
            if (diffMap.get(strings[i]) != null && !diffMap.get(strings[i]).equals(patterns[i])) {
                return false;
            } else {
                diffMap.put(strings[i], patterns[i]);
            }
        }
        return true;
    }
}

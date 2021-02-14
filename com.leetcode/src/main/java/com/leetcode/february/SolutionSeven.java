package com.leetcode.february;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-14 20:42:56
 * @author: wanglong16@meicai.cn
 */
public class SolutionSeven {

    /**
     * 243. 最短单词距离
     * 给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。
     *
     * 示例:
     * 假设 words = ["practice", "makes", "perfect", "coding", "makes"]
     *
     * 输入: word1 = “coding”, word2 = “practice”
     * 输出: 3
     * 输入: word1 = "makes", word2 = "coding"
     * 输出: 1
     * 注意:
     * 你可以假设 word1 不等于 word2, 并且 word1 和 word2 都在列表里。
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int p1 = 0, minDistance = words.length;
        while (p1 < words.length) {
            if (words[p1].equals(word1)) {
                int p2Left = p1, p2Right = p1;
                while (p2Left < words.length) {
                    if (words[p2Left].equals(word2)) {
                        minDistance = Math.min(minDistance, p2Left - p1);
                        break;
                    }
                    p2Left ++;
                }
                while (p2Right >= 0) {
                    if (words[p2Right].equals(word2)) {
                        minDistance = Math.min(minDistance, p1 - p2Right);
                        break;
                    }
                    p2Right --;
                }
            }
            p1 ++;
        }
        return minDistance;
    }

    /**
     * 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * 示例 1：
     *
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * 示例 2：
     *
     * 输入：digits = ""
     * 输出：[]
     * 示例 3：
     *
     * 输入：digits = "2"
     * 输出：["a","b","c"]
     *
     *
     * 提示：
     *
     * 0 <= digits.length <= 4
     * digits[i] 是范围 ['2', '9'] 的一个数字。
     */
    public List<String> letterCombinations(String digits) {
        Map<Character, List<Character>> map = new HashMap<Character, List<Character>>() {{
            put('2', Arrays.asList('a', 'b', 'c'));
            put('3', Arrays.asList('d', 'e', 'f'));
            put('4', Arrays.asList('g', 'h', 'i'));
            put('5', Arrays.asList('j', 'k', 'l'));
            put('6', Arrays.asList('m', 'n', 'o'));
            put('7', Arrays.asList('p', 'q', 'r', 's'));
            put('8', Arrays.asList('t', 'u', 'v'));
            put('9', Arrays.asList('w', 'x', 'y', 'z'));
        }};
        char[] characters = digits.toCharArray();
        List<String> result = new ArrayList<>();
        List<List<Character>> patterns = new ArrayList<>();
        StringBuilder stringBuilder = 
        for (Character character : characters) {
            for (Character character1 : map.get(character)) {

            }
        }
    }
}

package com.leetcode.february;

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
     *
     * 丑数就是只包含质因数 2, 3, 5 的正整数。
     *
     * 示例 1:
     *
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
     *
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     *
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     */
    public void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char swap = s[i];
            s[i] = s[s.length -i -1];
            s[s.length -i -1] = swap;
        }
    }

    /**
     * 205. 同构字符串
     * 给定两个字符串 s 和 t，判断它们是否是同构的。
     *
     * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
     *
     * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，
     * 相同字符只能映射到同一个字符上，字符可以映射到自己本身。
     */
    public boolean isIsomorphic(String s, String t) {
        char [] sChars = s.toCharArray();
        char [] tChars = t.toCharArray();
        Map<Character, Character> smap = new HashMap<Character, Character>(sChars.length) {};
        Map<Character, Character> tmap = new HashMap<Character, Character>(sChars.length) {};
        for (int i = 0; i < sChars.length; i++) {
            Character sC = sChars[i];
            Character tC = tChars[i];
            if (smap.get(sC) == null) {
                smap.put(sC, tC);
            } else {
                if (!smap.get(sC).equals(tC)) {
                    return false;
                }
            }
            if (tmap.get(tC) == null) {
                tmap.put(tC, sC);
            } else {
                if (!tmap.get(tC).equals(sC)) {
                    return false;
                }
            }
        }
        return true;
    }
}

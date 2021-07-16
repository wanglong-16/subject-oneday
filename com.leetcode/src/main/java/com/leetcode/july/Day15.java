package com.leetcode.july;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-15 07:19:31
 * @author: wanglong16@meicai.cn
 */
public class Day15 {

    int[] parent = new int[26];

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int len = s1.length();
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < len; i++) {
            merge(s1.charAt(i) - 97, s2.charAt(i) - 97);
        }
        StringBuilder ans = new StringBuilder();
        for (Character character : baseStr.toCharArray()) {
            int root = find(character - 97);
            ans.append((char) (root + 97));
        }
        return ans.toString();
    }

    public int find(int x) {
        return x == parent[x] ? x : (parent[x] = find(parent[x]));
    }

    public boolean merge(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx == ry) {
            return false;
        }
        if (rx > ry){
            parent[rx] = ry;
        } else {
            parent[ry] = rx;
        }
        return true;
    }

    public static void main(String[] args) {
        Day15 day15 = new Day15();
        System.out.println((int) 'a');
        System.out.println(day15.smallestEquivalentString("parker",
                "morris",
                "parser"));
    }

    class Solution {
        public String smallestEquivalentString(String A, String B, String S) {
            int[] parent = new int[26];
            for (int i = 0; i < 26; i++) {
                parent[i] = i;
            }
            int len = A.length();
            char[] ch1 = A.toCharArray();
            char[] ch2 = B.toCharArray();
            for (int i = 0; i < len; i++) {
                int n1 = ch1[i] - 'a';
                int n2 = ch2[i] - 'a';
                union(parent, n1, n2);
            }
            StringBuilder sb = new StringBuilder();
            for (char ch : S.toCharArray()) {
                int root = find(parent, ch - 'a');
                sb.append((char)(root + 'a'));
            }
            return sb.toString();
        }
        private int find(int[] parent, int index) {
            while (parent[index] != index) {
                index = parent[index];
            }
            return index;
        }
        private void union(int[] parent, int index1, int index2) {
            int xset = find(parent, index1);
            int yset = find(parent, index2);
            if (xset != yset) {
                if (xset < yset) {
                    parent[yset] = xset;
                } else {
                    parent[xset] = yset;
                }
            }
        }
    }


}

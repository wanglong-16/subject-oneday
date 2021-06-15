package com.leetcode.june;

import sun.plugin2.gluegen.runtime.StructAccessor;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-13 10:19:47
 * @author: wanglong16@meicai.cn
 */
public class Day13 {


    public boolean makeEqual(String[] words) {
        int[] chars = new int[26];
        for (String str : words) {
            for (Character ch : str.toCharArray()) {
                chars[ch - 'a'] ++;
            }
        }
        int len = words.length;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] % len != 0) {
                return false;
            }
        }
        return true;
    }


    public int maximumRemovals(String s, String p, int[] removable) {
        int ans = 0;
        for (int i = 0; i < removable.length; i++) {
            String sTemp = genStr(s, ans, removable);
            if (isMatched(sTemp, p)) {
                ans ++;
            } else {
                break;
            }
        }
        return ans - 1;
    }

    public String genStr(String string, int pos, int[] ta) {
        StringBuilder sb = new StringBuilder();
        char[] chars = string.toCharArray();
        Set<Integer> poss = new HashSet<>();
        for (int i = 0; i < pos; i++) {
            poss.add(ta[i]);
        }
        for (int i = 0; i < chars.length; i++) {
            if (!poss.contains(i)) {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    public boolean isMatched(String s, String p) {
        int pT = 0, sT = 0;
        while (pT < p.length()) {
            if (s.charAt(sT) == p.charAt(pT)) {
                pT ++;
            }
            sT ++;
            if (sT == s.length()) {
                break;
            }
        }
        return pT == p.length();
    }

    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean[] find = new boolean[] {false,false,false};
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < triplets.length; i++) {
                    if (triplets[i][j] == target[j]) {
                        find[j] = true;
                        break;
                    }
                }
            }
        return find[0] && find[1] && find[2];
    }


    public static void main(String[] args) {
        Day13 day13 = new Day13();
        //"abcacb"
        //"ab"
        //[3,1,0]
        //"qobftgcueho"
        //"obue"
        //[5,3,0,6,4,9,10,7,2,8]
        //System.out.println(day13.maximumRemovals("abcacb", "ab", new int[] {3,1,0}));

        System.out.println(day13.findShortestSubArray(new int[] {1,2,2,1,2,1,1,1,1,2,2,2}));
        //System.out.println(day13.isMatched("acaaa", "ab"));
    }


    /**
     * 697. 数组的度
     * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
     *
     * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[1, 2, 2, 3, 1]
     * 输出：2
     * 解释：
     * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
     * 连续子数组里面拥有相同度的有如下所示:
     * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
     * 最短连续子数组[2, 2]的长度为2，所以返回2.
     * 示例 2：
     *
     * 输入：[1,2,2,3,1,4,2]
     * 输出：6
     *
     *
     * 提示：
     *
     * nums.length 在1到 50,000 区间范围内。
     * nums[i] 是一个在 0 到 49,999 范围内的整数。
     */
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Element> elements = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (elements.get(nums[i]) != null) {
                Element element = elements.get(nums[i]);
                element.cnt ++;
                element.end = i;
                elements.put(nums[i], element);
            } else {
                elements.put(nums[i], new Element(1, i, i));
            }
        }
        int ans = 0, mxCnt = 0;
        for (Map.Entry<Integer, Element> entry : elements.entrySet()) {
            if (entry.getValue().cnt > mxCnt) {
                mxCnt = entry.getValue().cnt;
                ans = entry.getValue().end - entry.getValue().start + 1;
            } else if (entry.getValue().cnt == mxCnt) {
                ans = Math.min(entry.getValue().end - entry.getValue().start + 1, ans);
            }
        }
        return ans;
    }


    class Element {
        public int cnt;
        public int start;
        public int end;

        public Element(int cnt, int start, int end) {
            this.cnt = cnt;
            this.start = start;
            this.end = end;
        }

    }

    /**
     * 583. 两个字符串的删除操作
     * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
     *
     *
     *
     * 示例：
     *
     * 输入: "sea", "eat"
     * 输出: 2
     * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
     *
     *
     * 提示：
     *
     * 给定单词的长度不超过500。
     * 给定单词中的字符只含有小写字母。
     */
    public int minDistance(String word1, String word2) {
        int diff = 0;
        int w1p = 0, w2p = 0;

        while (w1p < word1.length() || w2p < word2.length()) {
            int w1Idx = word2.indexOf(String.valueOf(word1.charAt(w1p)));
            int w2Idx = word1.indexOf(String.valueOf(word2.charAt(w2p)));

        }

        return -1;
    }


}

package com.leetcode.march;

import java.util.*;
import java.util.stream.Stream;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-21 10:59:10
 * @author: wanglong16@meicai.cn
 */
public class Day20 {

    /**
     * 1165. 单行键盘
     * 我们定制了一款特殊的力扣键盘，所有的键都排列在一行上。
     *
     * 我们可以按从左到右的顺序，用一个长度为 26 的字符串 keyboard （索引从 0 开始，到 25 结束）来表示该键盘的键位布局。
     *
     * 现在需要测试这个键盘是否能够有效工作，那么我们就需要个机械手来测试这个键盘。
     *
     * 最初的时候，机械手位于左边起第一个键（也就是索引为 0 的键）的上方。当机械手移动到某一字符所在的键位时，就会在终端上输出该字符。
     *
     * 机械手从索引 i 移动到索引 j 所需要的时间是 |i - j|。
     *
     * 当前测试需要你使用机械手输出指定的单词 word，请你编写一个函数来计算机械手输出该单词所需的时间。
     * 示例 1：
     *
     * 输入：keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba"
     * 输出：4
     * 解释：
     * 机械手从 0 号键移动到 2 号键来输出 'c'，又移动到 1 号键来输出 'b'，接着移动到 0 号键来输出 'a'。
     * 总用时 = 2 + 1 + 1 = 4.
     * 示例 2：
     *
     * 输入：keyboard = "pqrstuvwxyzabcdefghijklmno", word = "leetcode"
     * 输出：73
     */
    public int calculateTimeV1(String keyboard, String word) {
        int lastIndex = 0, ans = 0, index = 0;
        for (int i = 0; i < word.length(); i++) {
            index = keyboard.indexOf(word.substring(i, i + 1));
            ans += Math.abs(index - lastIndex);
            lastIndex = index;
        }
        return ans;
    }

    public int calculateTime(String keyboard, String word) {
        Map<Character, Integer> map = new HashMap<>(40);
        for (int i = 0; i < keyboard.length(); i++) {
            map.put(keyboard.charAt(i), i);
        }
        int lastIndex = 0, ans = 0, index = 0;
        for (Character c : word.toCharArray()) {
            index = map.get(c);
            ans += Math.abs(index - lastIndex);
            lastIndex = index;
        }
        return ans;
    }

    /**
     * 1684. 统计一致字符串的数目
     * 给你一个由不同字符组成的字符串 allowed 和一个字符串数组 words 。如果一个字符串的每一个字符都在 allowed 中，就称这个字符串是 一致字符串 。
     *
     * 请你返回 words 数组中 一致字符串 的数目。

     * 示例 1：
     *
     * 输入：allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
     * 输出：2
     * 解释：字符串 "aaab" 和 "baa" 都是一致字符串，因为它们只包含字符 'a' 和 'b' 。
     * 示例 2：
     *
     * 输入：allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
     * 输出：7
     * 解释：所有字符串都是一致的。
     * 示例 3：
     *
     * 输入：allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
     * 输出：4
     * 解释：字符串 "cc"，"acd"，"ac" 和 "d" 是一致字符串。     *
     */
    public int countConsistentStringsV1(String allowed, String[] words) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < allowed.length(); i++) {
            set.add(allowed.charAt(i));
        }
        int ans = 0;
        for (String s : words) {
            if (isSame(set, s)) {
                ans ++;
            }
        }
        return ans;
    }

    public boolean isSame(Set<Character> set, String string) {
        for (char c : string.toCharArray()) {
            if (!set.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public int countConsistentStrings(String allowed, String[] words) {
        int al = solve(allowed), ans = 0;
        for (String s : words) {
            int sa = solve(s);
            if (sa == (sa & al)) {
                ans ++;
            }
        }
        return ans;
    }

    public int solve(String string) {
        int n = 0;
        for (char c : string.toCharArray()) {
            n |= 1 << (c - 'a');
        }
        return n;
    }

    /**
     * 1461. 检查一个字符串是否包含所有长度为 K 的二进制子串
     * 给你一个二进制字符串 s 和一个整数 k 。
     *
     * 如果所有长度为 k 的二进制字符串都是 s 的子串，请返回 True ，否则请返回 False 。
     * 示例 1：
     *
     * 输入：s = "00110110", k = 2
     * 输出：true
     * 解释：长度为 2 的二进制串包括 "00"，"01"，"10" 和 "11"。它们分别是 s 中下标为 0，1，3，2 开始的长度为 2 的子串。
     * 示例 2：
     *
     * 输入：s = "00110", k = 2
     * 输出：true
     * 示例 3：
     *
     * 输入：s = "0110", k = 1
     * 输出：true
     * 解释：长度为 1 的二进制串包括 "0" 和 "1"，显然它们都是 s 的子串。
     * 示例 4：
     *
     * 输入：s = "0110", k = 2
     * 输出：false
     * 解释：长度为 2 的二进制串 "00" 没有出现在 s 中。
     * 示例 5：
     *
     * 输入：s = "0000000001011100", k = 4
     * 输出：false
     */
    public boolean hasAllCodes(String s, int k) {
        List<String> templates = getAllTemplates(k);
        for (int i = 0; i < s.length() - k; i++) {
            String st = s.substring(i, i + k);
            templates.remove(st);
        }
        return templates.isEmpty();
    }

    public List<String> getAllTemplates(int k) {
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < (1 << k); i++) {
            StringBuilder sb = new StringBuilder();
            int temp = i;
            for (int j = 0; j < k; j++) {
                sb.append((temp & 1));
                temp >>= 1;
            }
            ret.add(sb.reverse().toString());
        }
        return ret;
    }

    /**
     *
     */
    public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer, Integer> bMap = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            bMap.put(B[i], i);
        }
        int[] ans = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            ans[i] = bMap.get(A[i]);
        }
        return ans;
    }

    /**
     * 1281. 整数的各位积和之差
     * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 234
     * 输出：15
     * 解释：
     * 各位数之积 = 2 * 3 * 4 = 24
     * 各位数之和 = 2 + 3 + 4 = 9
     * 结果 = 24 - 9 = 15
     * 示例 2：
     *
     * 输入：n = 4421
     * 输出：21
     * 解释：
     * 各位数之积 = 4 * 4 * 2 * 1 = 32
     * 各位数之和 = 4 + 4 + 2 + 1 = 11
     * 结果 = 32 - 11 = 21
     */
    public int subtractProductAndSum(int n) {
        int multi = 1, add = 0;
        while (n != 0) {
            int cur = n % 10;
            multi *= cur;
            add += cur;
            n /= 10;
        }
        return multi - add;
    }

    /**
     * 200. 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     *
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     *
     * 此外，你可以假设该网格的四条边均被水包围。
     * 示例 1：
     *
     * 输入：grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * 输出：1
     * 示例 2：
     *
     * 输入：grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * 输出：3
     *
     *
     * 提示：
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * grid[i][j] 的值为 '0' 或 '1'
     */
    public int numIslands(char[][] grid) {
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    total ++;
                    dfs(grid, i, j);
                }
            }
        }
        return total;
    }

    public void dfs(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }

}

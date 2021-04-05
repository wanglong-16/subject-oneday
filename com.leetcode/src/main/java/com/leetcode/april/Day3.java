package com.leetcode.april;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-04-03 10:05:06
 * @author: wanglong16@meicai.cn
 */
public class Day3 {


    /**
     * 908. 最小差值 I
     * 给你一个整数数组 A，请你给数组中的每个元素 A[i] 都加上一个任意数字 x （-K <= x <= K），从而得到一个新数组 B 。
     *
     * 返回数组 B 的最大值和最小值之间可能存在的最小差值。
     *
     *
     *
     * 示例 1：
     *
     * 输入：A = [1], K = 0
     * 输出：0
     * 解释：B = [1]
     * 示例 2：
     *
     * 输入：A = [0,10], K = 2
     * 输出：6
     * 解释：B = [2,8]
     * 示例 3：
     *
     * 输入：A = [1,3,6], K = 3
     * 输出：0
     * 解释：B = [3,3,3] 或 B = [4,4,4]
     */
    public int smallestRangeI(int[] A, int K) {
        Arrays.sort(A);
        int min = A[0], max = A[A.length - 1];
        return (max - min) > 2 * K ? (max - min) - 2 * K : 0;
    }

    /**
     * 1427. 字符串的左右移
     * 给定一个包含小写英文字母的字符串 s 以及一个矩阵 shift，其中 shift[i] = [direction, amount]：
     *
     * direction 可以为 0 （表示左移）或 1 （表示右移）。
     * amount 表示 s 左右移的位数。
     * 左移 1 位表示移除 s 的第一个字符，并将该字符插入到 s 的结尾。
     * 类似地，右移 1 位表示移除 s 的最后一个字符，并将该字符插入到 s 的开头。
     * 对这个字符串进行所有操作后，返回最终结果。
     *
     *
     *
     * 示例 1：
     *
     * 输入：s = "abc", shift = [[0,1],[1,2]]
     * 输出："cab"
     * 解释：
     * [0,1] 表示左移 1 位。 "abc" -> "bca"
     * [1,2] 表示右移 2 位。 "bca" -> "cab"
     * 示例 2：
     *
     * 输入：s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]]
     * 输出："efgabcd"
     * 解释：
     * [1,1] 表示右移 1 位。 "abcdefg" -> "gabcdef"
     * [1,1] 表示右移 1 位。 "gabcdef" -> "fgabcde"
     * [0,2] 表示左移 2 位。 "fgabcde" -> "abcdefg"
     * [1,3] 表示右移 3 位。 "abcdefg" -> "efgabcd"
     */
    public String stringShift(String s, int[][] shift) {
        int move = 0;
        for (int[] sh : shift) {
            if (sh[0] == 0) {
                move -= sh[1];
            } else {
                move += sh[1];
            }
        }
        if (move < 0) {
            move = s.length() + move;
        }
        StringBuilder sb = new StringBuilder();
        return sb.append(s, s.length() - move, s.length()).append(s, 0, s.length() - move).toString();
    }

    /**
     * 剑指 Offer 50. 第一个只出现一次的字符
     * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     *
     * 示例:
     *
     * s = "abaccdeff"
     * 返回 "b"
     *
     * s = ""
     * 返回 " "
     *
     *
     * 限制：
     *
     * 0 <= s 的长度 <= 50000
     */
    public char firstUniqChar(String s) {
        int[] count = new int[26];
        for (Character c : s.toCharArray()) {
            count[c - 97] ++;
        }
        Set<Character> uniq = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            if (count[i] == 1) {
                uniq.add((char)(i + 97));
            }
        }
        char ans = ' ';
        for (int i = 0; i < s.length(); i++) {
            if (uniq.contains(s.charAt(i))) {
                ans = s.charAt(i);
                break;
            }
        }
        return ans;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        int[] ans = new int[k];
        if (k >= 0) {
            System.arraycopy(arr, 0, ans, 0, k);
        }
        return ans;
    }

    /**
     * 剑指 Offer 42. 连续子数组的最大和
     * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     *
     * 要求时间复杂度为O(n)。
     *
     *
     *
     * 示例1:
     *
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     *
     * 提示：
     *
     * 1 <= arr.length <= 10^5
     * -100 <= arr[i] <= 100
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            max = Math.max(nums[i], max);
        }
        return max;
    }

    public boolean squareIsWhite(String coordinates) {
        List<Character> odd = Arrays.asList('b','d','f','h');
        List<Character> even = Arrays.asList('a','c','e','g');
        char c1 = coordinates.charAt(0);
        if (Integer.parseInt(coordinates.substring(1, 1)) % 2 == 0) {
            return odd.contains(c1);
        } else {
            return even.contains(c1);
        }
    }

    /**
     * 剑指 Offer 47. 礼物的最大价值
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     *
     *
     *
     * 示例 1:
     *
     * 输入:
     * [
     *   [1,3,1],
     *   [1,5,1],
     *   [4,2,1]
     * ]
     * 输出: 12
     * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
     */
    public int maxValue(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
            dp[0][i] += dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = grid[i][j] + Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    /**
     * 781. 森林中的兔子
     * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。
     *
     * 返回森林中兔子的最少数量。
     *
     * 示例:
     * 输入: answers = [1, 1, 2]
     * 输出: 5
     * 解释:
     * 两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
     * 之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
     * 设回答了 "2" 的兔子为蓝色。
     * 此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
     * 因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
     *
     * 输入: answers = [10, 10, 10]
     * 输出: 11
     *
     * 输入: answers = []
     */
    public int numRabbits(int[] answers) {
        if (answers.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer in : answers) {
            map.merge(in, 1, Integer::sum);
        }
        int k, v, ans = 0;
        for (Map.Entry e : map.entrySet()) {
            k = Integer.parseInt(e.getKey().toString());
            v = Integer.parseInt(e.getValue().toString());
            if (v % (k + 1) == 0) {
                ans += (v / (k + 1)) * (k + 1);
            } else {
                ans += (v / (k + 1) + 1) * (k + 1);
            }
        }
        return ans;
    }

    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
     *
     *
     *
     * 示例：
     *
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,2,4]
     * 注：[3,1,2,4] 也是正确的答案之一。
     *
     *
     * 提示：
     *
     * 0 <= nums.length <= 50000
     * 1 <= nums[i] <= 10000
     * 通过次数119,571提交次数186,327
     */
    public int[] exchange(int[] nums) {
        int left = 0, right = nums.length - 1;
        int[] ans = new int[nums.length - 1];
        for (Integer in : nums) {
            if (in % 2 == 1) {
                ans[++left] = in;
            } else {
                ans[--right] = in;
            }
        }
        return ans;
    }

}

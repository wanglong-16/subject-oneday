package com.leetcode.march;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-24 21:44:00
 * @author: wanglong16@meicai.cn
 */
public class Day24 {

    /**
     * 1313. 解压缩编码列表
     * 给你一个以行程长度编码压缩的整数列表 nums 。
     * <p>
     * 考虑每对相邻的两个元素 [freq, val] = [nums[2*i], nums[2*i+1]] （其中 i >= 0 ），每一对都表示解压后子列表中有 freq 个值为 val 的元素，你需要从左到右连接所有子列表以生成解压后的列表。
     * <p>
     * 请你返回解压后的列表。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,3,4]
     * 输出：[2,4,4,4]
     * 解释：第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]。
     * 第二对 [3,4] 代表着 4 的出现频次为 3，所以生成数组 [4,4,4]。
     * 最后将它们串联到一起 [2] + [4,4,4] = [2,4,4,4]。
     * 示例 2：
     * <p>
     * 输入：nums = [1,1,2,3]
     * 输出：[1,3,3]
     */
    public int[] decompressRLElistV1(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i += 2) {
            int freq = nums[i];
            int val = nums[i + 1];
            for (int j = 0; j < freq; j++) {
                ans.add(val);
            }
        }
        int[] an = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            an[i] = ans.get(i);
        }
        return an;
    }

    int sumOddLengthSubarrays(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            int left = i + 1, right = arr.length - i,
                    left_even = (left + 1) / 2, right_even = (right + 1) / 2,
                    left_odd = left / 2, right_odd = right / 2;
            res += (left_even * right_even + left_odd * right_odd) * arr[i];
        }
        return res;
    }

    /**
     * LCP 17. 速算机器人
     * 小扣在秋日市集发现了一款速算机器人。店家对机器人说出两个数字（记作 x 和 y），请小扣说出计算指令：
     * <p>
     * "A" 运算：使 x = 2 * x + y；
     * "B" 运算：使 y = 2 * y + x。
     * 在本次游戏中，店家说出的数字为 x = 1 和 y = 0，小扣说出的计算指令记作仅由大写字母 A、B 组成的字符串 s，字符串中字符的顺序表示计算顺序，请返回最终 x 与 y 的和为多少。
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "AB"
     * <p>
     * 输出：4
     * <p>
     * 解释：
     * 经过一次 A 运算后，x = 2, y = 0。
     * 再经过一次 B 运算，x = 2, y = 2。
     * 最终 x 与 y 之和为 4。
     * <p>
     * 提示：
     * <p>
     * 0 <= s.length <= 10
     * s 由 'A' 和 'B' 组成
     */
    public int calculate(String s) {
        int x = 1, y = 0;
        for (Character ch : s.toCharArray()) {
            if (ch == 'A') {
                x = 2 * x + y;
            }
            if (ch == 'B') {
                y = 2 * y + x;
            }
        }
        return x + y;
    }

    /**
     * 1314. 矩阵区域和
     * 给你一个 m * n 的矩阵 mat 和一个整数 K ，请你返回一个矩阵 answer ，其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和：
     * <p>
     * i - K <= r <= i + K, j - K <= c <= j + K
     * (r, c) 在矩阵内。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
     * 输出：[[12,21,16],[27,45,33],[24,39,28]]
     * 示例 2：
     * <p>
     * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
     * 输出：[[45,45,45],[45,45,45],[45,45,45]]
     */
    public int[][] matrixBlockSumV1(int[][] mat, int K) {
        int rows = mat.length, cols = mat[0].length, rTop, rBottom, cLeft, cRight, temp;
        int[][] ans = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rTop = Math.max(0, i - K);
                rBottom = Math.min(rows - 1, i + K);
                cLeft = Math.max(0, j - K);
                cRight = Math.min(cols - 1, j + K);
                temp = 0;
                for (int k = rTop; k <= rBottom; k++) {
                    for (int l = cLeft; l <= cRight; l++) {
                        temp += mat[k][l];
                    }
                }
                ans[i][j] = temp;
            }
        }
        return ans;
    }

    public int[][] matrixBlockSum(int[][] mat, int K) {
        int row = mat.length, col = mat[0].length;
        int[][] prefix = new int[row + 1][col + 1];
        for (int i = 1; i <= row; ++i) {
            for (int j = 1; j <= col; ++j) {
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int[][] res = new int[row][col];
        int x0, y0, x1, y1;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                x0 = Math.max(0, i - K);
                y0 = Math.max(0, j - K);
                x1 = Math.min(row, i + K + 1);
                y1 = Math.min(col, j + K + 1);
                res[i][j] = prefix[x1][y1] - prefix[x1][y0] - prefix[x0][y1] + prefix[x0][y0];
            }
        }
        return res;
    }

    /**
     * 1221. 分割平衡字符串
     * 在一个 平衡字符串 中，'L' 和 'R' 字符的数量是相同的。
     * <p>
     * 给你一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
     * <p>
     * 注意：分割得到的每个字符串都必须是平衡字符串。
     * <p>
     * 返回可以通过分割得到的平衡字符串的 最大数量 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "RLRRLLRLRL"
     * 输出：4
     * 解释：s 可以分割为 "RL"、"RRLL"、"RL"、"RL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
     * 示例 2：
     * <p>
     * 输入：s = "RLLLLRRRLR"
     * 输出：3
     * 解释：s 可以分割为 "RL"、"LLLRRR"、"LR" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
     * 示例 3：
     * <p>
     * 输入：s = "LLLLRRRR"
     * 输出：1
     * 解释：s 只能保持原样 "LLLLRRRR".
     */
    public int balancedStringSplit(String s) {
        Stack<Character> stack = new Stack<>();
        stack.push('0');
        int total = -1;
        for (int i = 0; i < s.length(); i++) {
            if (stack.peek() == '0') {
                total++;
            }
            if (stack.peek() == 'L' && s.charAt(i) == 'R' ||
                    stack.peek() == 'R' && s.charAt(i) == 'L') {
                stack.pop();
                continue;
            }
            stack.push(s.charAt(i));
        }
        return total;
    }

    public int balancedStringSplitV1(String s) {
        int count = 0;
        int sumR = 0;
        int sumL = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                sumR++;
            } else {
                sumL++;
            }
            if (sumR == sumL) {
                count++;
                sumR = 0;
                sumL = 0;
            }
        }
        return count;
    }

    /**
     * 97. 交错字符串
     * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
     *
     * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
     *
     * s = s1 + s2 + ... + sn
     * t = t1 + t2 + ... + tm
     * |n - m| <= 1
     * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
     * 提示：a + b 意味着字符串 a 和 b 连接。
     *
     *
     *
     * 示例 1：
     *
     *
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * 输出：true
     * 示例 2：
     *
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
     * 输出：false
     * 示例 3：
     *
     * 输入：s1 = "", s2 = "", s3 = ""
     * 输出：true
     *
     *
     * 提示：
     *
     * 0 <= s1.length, s2.length <= 100
     * 0 <= s3.length <= 200
     * s1、s2、和 s3 都由小写英文字母组成
     * 通过次数42,293提交次数92,154
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if ("".equals(s1)) {
            return s3.equals(s2);
        }
        if ("".equals(s2)) {
            return s1.equals(s3);
        }
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0, j = 0; i < s1.length() || j < s2.length(); i++, i++) {
            if (i < s1.length()) {
                sb1.append(s1.charAt(i));
            }
            if (j < s2.length()) {
                sb1.append(s2.charAt(j));
                sb2.append(s2.charAt(j));
            }
            if (i < s1.length()) {
                sb2.append(s1.charAt(i));
            }
        }
        return sb1.toString().equals(s3) || sb2.toString().equals(s3);
    }

    /**
     * 1768. 交替合并字符串
     * 给你两个字符串 word1 和 word2 。请你从 word1 开始，通过交替添加字母来合并字符串。如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
     *
     * 返回 合并后的字符串 。
     * 示例 1：
     *
     * 输入：word1 = "abc", word2 = "pqr"
     * 输出："apbqcr"
     * 解释：字符串合并情况如下所示：
     * word1：  a   b   c
     * word2：    p   q   r
     * 合并后：  a p b q c r
     * 示例 2：
     *
     * 输入：word1 = "ab", word2 = "pqrs"
     * 输出："apbqrs"
     * 解释：注意，word2 比 word1 长，"rs" 需要追加到合并后字符串的末尾。
     * word1：  a   b
     * word2：    p   q   r   s
     * 合并后：  a p b q   r   s
     * 示例 3：
     *
     * 输入：word1 = "abcd", word2 = "pq"
     * 输出："apbqcd"
     * 解释：注意，word1 比 word2 长，"cd" 需要追加到合并后字符串的末尾。
     * word1：  a   b   c   d
     * word2：    p   q
     * 合并后：  a p b q c   d
     */
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int p1 = 0, p2 = 0;
        while (p1 < word1.length() || p2 < word2.length()) {
            if (p1 < word1.length()) {
                sb.append(word1.charAt(p1));
                p1 ++;
            }
            if (p2 < word2.length()) {
                sb.append(word2.charAt(p2));
                p2 ++;
            }
        }
        return sb.toString();
    }
}

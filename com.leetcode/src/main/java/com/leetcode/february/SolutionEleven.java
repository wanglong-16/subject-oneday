package com.leetcode.february;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-20 08:36:58
 * @author: wanglong16@meicai.cn
 */
public class SolutionEleven {

    /**
     * 187. 重复的DNA序列
     * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
     *
     * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
     * 示例 1：
     * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
     * 输出：["AAAAACCCCC","CCCCCAAAAA"]
     * 示例 2：
     *
     * 输入：s = "AAAAAAAAAAAAA"
     * 输出：["AAAAAAAAAA"]
     * 提示：
     * 0 <= s.length <= 105
     * s[i] 为 'A'、'C'、'G' 或 'T'
     */
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() <= 10) {
            return Collections.emptyList();
        }
        Set<String> result = new HashSet<>();
        Set<String> stringSet = new HashSet<>();
        for (int i = 10; i <= s.length(); ++i) {
            if (stringSet.contains(s.substring(i - 10, i))) {
                result.add(s.substring(i - 10, i));
            }
            stringSet.add(s.substring(i - 10, i));
        }
        return new ArrayList<>(result);
    }

    /**
     * 201. 数字范围按位与
     * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
     *
     * 示例 1:
     *
     * 输入: [5,7]
     * 输出: 4
     * 示例 2:
     *
     * 输入: [0,1]
     * 输出: 0
     */

    //超时
    public int rangeBitwiseAndV1(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        int result = 0xffffffff;
        for (int i = m; i <= n; ++i) {
            result &= i;
        }
        return result;
    }

    /**
     * 求最长公共前缀
     */
    public int rangeBitwiseAnd(int m, int n) {
        while (n > m) {
            n &= n - 1;
        }
        return n;
    }

    /**
     * 求最长公共前缀
     */
    public int rangeBitwiseAndV2(int m, int n) {
        int count = 0;
        while (n > m) {
            n >>= 1;
            m >>= 1;
            count ++;
        }
        return m << count;
    }



}

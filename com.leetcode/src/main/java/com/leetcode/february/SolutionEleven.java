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

    /**
     * 405. 数字转换为十六进制数
     * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
     * 注意:
     * 十六进制中所有字母(a-f)都必须是小写。
     * 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。
     * 给定的数确保在32位有符号整数范围内。
     * 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
     * 示例 1：
     * 输入:
     * 26
     * 输出:
     * "1a"
     * 示例 2：
     * 输入:
     * -1
     * 输出:
     * "ffffffff"
     */
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        Map<Integer, String> hexMap = new HashMap<Integer, String>() {{
            put(0, "0");put(1, "1"); put(2, "2"); put(3, "3");
            put(4, "4");put(5, "5"); put(6, "6"); put(7, "7");
            put(8, "8");put(9, "9"); put(10, "a"); put(11, "b");
            put(12, "c");put(13, "d"); put(14, "e"); put(15, "f");
        }};
        StringBuilder stringBuilder = new StringBuilder();
        while (num != 0) {
            stringBuilder.append(hexMap.get(num & 0xf));
            num >>>= 4;
        }
        return stringBuilder.reverse().toString();
    }

    /**
     * 477. 汉明距离总和
     * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
     *
     * 计算一个数组中，任意两个数之间汉明距离的总和。
     *
     * 示例:
     *
     * 输入: 4, 14, 2
     *
     * 输出: 6
     *
     * 解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
     * 所以答案为：
     * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
     * 注意:
     *
     * 数组中元素的范围为从 0到 10^9。
     * 数组的长度不超过 10^4。
     */
    public int totalHammingDistanceV1(int[] nums) {
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                total += hammingDistance(nums[i], nums[j]);
            }
        }
        return total;
    }

    public int hammingDistance(int m, int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((m & 1) != (n & 1)) {
                count ++;
            }
            m >>= 1;
            n >>= 1;
        }
        return count;
    }

    public int totalHammingDistance(int[] nums) {
        int total = 0, result = 0;
        for (int i = 0; i < 32; i++) {
            //统计每位的0和1的个数
            int totalZero = 0;
            int totalOne = 0;
            for (int num : nums) {
                if ((num & (1 << i)) == 1 << i) {
                    totalOne++;
                } else {
                    totalZero++;
                }
            }
            // 某位上存在一个1和其他的m个0组合后，就有m次不同，n个1和m个0最后这位上就是 n * m个不同
            result += totalOne * totalZero;
        }
        return result;
    }
}

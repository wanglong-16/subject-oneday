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

    /**
     * 268. 丢失的数字
     * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     *
     *
     *
     * 进阶：
     *
     * 你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
     *
     *
     * 示例 1：
     *
     * 输入：nums = [3,0,1]
     * 输出：2
     * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 2：
     *
     * 输入：nums = [0,1]
     * 输出：2
     * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 3：
     *
     * 输入：nums = [9,6,4,2,3,5,7,0,1]
     * 输出：8
     * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 4：
     *
     * 输入：nums = [0]
     * 输出：1
     * 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
     *
     *
     * 提示：
     *
     * n == nums.length
     * 1 <= n <= 104
     * 0 <= nums[i] <= n
     * nums 中的所有数字都 独一无二
     */
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }
        return nums.length;
    }

    public int missingNumberV1(int[] nums) {
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            result ^= i ^ nums[i];
        }
        return result;
    }

    /**
     * 1486. 数组异或操作
     * 给你两个整数，n 和 start 。
     *
     * 数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。
     *
     * 请返回 nums 中所有元素按位异或（XOR）后得到的结果。
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 5, start = 0
     * 输出：8
     * 解释：数组 nums 为 [0, 2, 4, 6, 8]，其中 (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8 。
     *      "^" 为按位异或 XOR 运算符。
     * 示例 2：
     *
     * 输入：n = 4, start = 3
     * 输出：8
     * 解释：数组 nums 为 [3, 5, 7, 9]，其中 (3 ^ 5 ^ 7 ^ 9) = 8.
     * 示例 3：
     *
     * 输入：n = 1, start = 7
     * 输出：7
     * 示例 4：
     *
     * 输入：n = 10, start = 5
     * 输出：2
     *
     *
     * 提示：
     *
     * 1 <= n <= 1000
     * 0 <= start <= 1000
     * n == nums.length
     */
    public int xorOperation(int n, int start) {
        for (int i = 1; i < n; i++) {
            start ^= start + 2 * i;
        }
        return start;
    }

    /**
     * 342. 4的幂
     * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
     *
     * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x
     * 示例 1：
     *
     * 输入：n = 16
     * 输出：true
     * 示例 2：
     *
     * 输入：n = 5
     * 输出：false
     * 示例 3：
     *
     * 输入：n = 1
     * 输出：true
     */
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        for (int i = 1; i < 32; i+=2) {
            if (n == (1 << i)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPowerOfFourV1(int num) {
        return (num > 0) &&
                ((num & (num - 1)) == 0) //只有一位是1
                && ((num & 0xaaaaaaaa) == 0); //这个位在 1，3，5，7。。位上
    }

    /**
     * 401. 二进制手表
     * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
     *
     * 每个 LED 代表一个 0 或 1，最低位在右侧。
     * 例如，上面的二进制手表读取 “3:25”。
     *
     * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
     * 示例：
     *
     * 输入: n = 1
     * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
     * 提示：
     * 输出的顺序没有要求。
     * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
     * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
     * 超过表示范围（小时 0-11，分钟 0-59）的数据将会被舍弃，也就是说不会出现 "13:00", "0:61" 等时间。
     */
    public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<>();
        // 0 - 11 的二进制表示 1011
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if ((countBitOne(i) + countBitOne(j)) == num) {
                    String minutesFormat = j < 10 ? String.format("0%s", j) : String.valueOf(j);
                    result.add(String.format("%s:%s", i, minutesFormat));
                }
            }
        }
        return result;
    }

    private int countBitOne(int n) {
        int count = 0;
        for (; n != 0; ++count) {
            n &= n - 1;
        }
        return count;
    }

    /**
     * 784. 字母大小写全排列
     * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
     * 示例：
     * 输入：S = "a1b2"
     * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
     *
     * 输入：S = "3z4"
     * 输出：["3z4", "3Z4"]
     *
     * 输入：S = "12345"
     * 输出：["12345"]
     * 提示：
     *
     * S 的长度不超过12。
     * S 仅由数字和字母组成。
     */
    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();
        char [] patterns = S.toCharArray();
        int lettersTotal = 0;
        for (char pattern : patterns) {
            if (!(pattern >= 48 && pattern <= 57)) {
                lettersTotal++;
            }
        }
        if (lettersTotal == 0) {
            result.add(S);
        } else {
            int combineTotal = 1 << lettersTotal; // 总的可能次数
            for (int i = 0; i < combineTotal ; i++) {
                result.add(transBitToString(i, patterns));
            }
        }
        return result;
    }

    // 使用bit位做全排列统计
    private String transBitToString(int cur, char [] patterns) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < patterns.length; i++) {
            char curr;
            if (!(patterns[i] >= 48 && patterns[i] <= 57)) {
                if ((cur & 1) == 1) {
                    if (patterns[i] >= 97) {
                        curr = (char) (patterns[i] - 32);
                    } else {
                        curr = (char) (patterns[i] + 32);
                    }
                } else {
                    curr = patterns[i];
                }
                cur >>= 1;
            } else {
                curr = patterns[i];
            }
            stringBuilder.append(curr);
        }
        return stringBuilder.toString();
    }

}

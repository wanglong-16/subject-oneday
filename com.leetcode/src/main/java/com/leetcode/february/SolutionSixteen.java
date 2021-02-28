package com.leetcode.february;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-27 09:24:17
 * @author: wanglong16@meicai.cn
 */
public class SolutionSixteen {

    /**
     * 395. 至少有K个重复字符的最长子串
     * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
     * 示例 1:
     *
     * 输入:
     * s = "aaabb", k = 3
     *
     * 输出:
     * 3
     *
     * 最长子串为 "aaa" ，其中 'a' 重复了 3 次。
     * 示例 2:
     *
     * 输入:
     * s = "ababbc", k = 2
     *
     * 输出:
     * 5
     * 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
     */
    public int longestSubstring(String s, int k) {
        int max = 0;
        for (int i = 0; i < s.length(); ++i) {
            for (int j = i + 1; j <= s.length(); ++j) {
                String str = s.substring(i, j);
                if (matchKStr(str, k)) {
                    max = Math.max(max, str.length());
                }
            }
        }
        return max;
    }

    public boolean matchKStr(String str, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) != null) {
                map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            } else {
                map.put(str.charAt(i), 1);
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            if (Integer.parseInt(entry.getValue().toString()) < k) {
                return false;
            }
        }
        return true;
    }

    /**
     * 302. 包含全部黑色像素的最小矩形
     * 图片在计算机处理中往往是使用二维矩阵来表示的。
     *
     * 假设，这里我们用的是一张黑白的图片，那么 0 代表白色像素，1 代表黑色像素。
     *
     * 其中黑色的像素他们相互连接，也就是说，图片中只会有一片连在一块儿的黑色像素（像素点是水平或竖直方向连接的）。
     *
     * 那么，给出某一个黑色像素点 (x, y) 的位置，你是否可以找出包含全部黑色像素的最小矩形（与坐标轴对齐）的面积呢？
     * 示例:
     *
     * 输入:
     * [
     *   "0010",
     *   "0110",
     *   "0100"
     * ]
     * 和 x = 0, y = 2
     *
     * 输出: 6
     */
    public int minArea(char[][] image, int x, int y) {
        int minLeft = x, minTop = y, maxRight = x, maxBottom = y;
        for (int i = 0; i < image.length; ++i) {
            for (int j = 0; j < image[0].length; ++j) {
                if (image[i][j] == '1') {
                    minLeft = Math.min(i, minLeft);
                    minTop = Math.min(j, minTop);
                    maxRight = Math.max(i + 1, maxRight);
                    maxBottom = Math.max(j + 1, maxBottom);
                }
            }
        }
        return (maxRight - minLeft) * (maxBottom - minTop);
    }

    /**
     * 面试题 05.06. 整数转换
     * 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
     *
     * 示例1:
     *
     *  输入：A = 29 （或者0b11101）, B = 15（或者0b01111）
     *  输出：2
     * 示例2:
     *
     *  输入：A = 1，B = 2
     *  输出：2
     * 提示:
     *
     * A，B范围在[-2147483648, 2147483647]之间
     */
    public int convertInteger(int A, int B) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans += (A & 1) ^ (B & 1);
            A >>>= 1;
            B >>>= 1;
        }
        return ans;
    }

    /**
     * 面试题 17.04. 消失的数字
     * 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
     *
     * 注意：本题相对书上原题稍作改动
     *
     * 示例 1：
     *
     * 输入：[3,0,1]
     * 输出：2
     * 示例 2：
     *
     * 输入：[9,6,4,2,3,5,7,0,1]
     * 输出：8
     */
    public int missingNumber(int[] nums) {
        int ans = nums.length;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i] ^ i;
        }
        return ans;
    }

    /**
     * 896. 单调数列
     * 如果数组是单调递增或单调递减的，那么它是单调的。
     *
     * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
     *
     * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
     * 示例 1：
     *
     * 输入：[1,2,2,3]
     * 输出：true
     * 示例 2：
     *
     * 输入：[6,5,4,4]
     * 输出：true
     * 示例 3：
     *
     * 输入：[1,3,2]
     * 输出：false
     * 示例 4：
     *
     * 输入：[1,2,4,5]
     * 输出：true
     * 示例 5：
     *
     * 输入：[1,1,1]
     * 输出：true
     */

    public boolean isMonotonic(int[] A) {
        int aStart = A[0], aEnd = A[A.length - 1];
        int incr = 0, decr = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] <= aStart) {
                decr ++;
                aStart = A[i];
            }
            if (A[A.length - i - 1] <= aEnd) {
                incr ++;
                aEnd = A[A.length - i -1];
            }
        }
        return incr == A.length || decr == A.length;
    }

    public boolean isMonotonicV1(int[] A) {
        int a = A[0];
        boolean incr = false, decr = false;
        for (int value : A) {
            if (value <= a) {
                decr = true;
            } else {
                decr = false;
                break;
            }
            a = value;
        }

        for (int value : A) {
            if (value >= a) {
                incr = true;
            } else {
                incr = false;
                break;
            }
            a = value;
        }
        return incr || decr;
    }

    public boolean isMonotonicV2(int[] A) {
        boolean inc = true, dec = true;
        int n = A.length;
        for (int i = 0; i < n - 1; ++i) {
            if (A[i] > A[i + 1]) {
                inc = false;
            }
            if (A[i] < A[i + 1]) {
                dec = false;
            }
        }
        return inc || dec;
    }

    /**
     * 面试题 17.01. 不用加号的加法
     * 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
     *
     * 示例:
     *
     * 输入: a = 1, b = 1
     * 输出: 2
     * 提示：
     *
     * a, b 均可能是负数或 0
     * 结果不会溢出 32 位整数
     * 通过次数8,394提交次数14,162
     */
    public int add(int a, int b) {
        int curBit, nextBit;
        while(b != 0) {
            curBit = a ^ b; //当前位的值
            nextBit = (a & b) << 1; // 同时为1进位
            a = curBit;
            b = nextBit;
        }
        return a;
    }

}

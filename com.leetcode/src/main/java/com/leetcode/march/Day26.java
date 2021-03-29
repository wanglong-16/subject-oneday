package com.leetcode.march;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-25 22:09:54
 * @author: wanglong16@meicai.cn
 */
public class Day26 {

    /**
     * 剑指 Offer 14- I. 剪绳子
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     *
     * 示例 1：
     *
     * 输入: 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1
     * 示例 2:
     *
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
     * 提示：
     *
     * 2 <= n <= 58
     *
     * 11
     * 10
     * 3
     * 4 4 2
     * 3 3 4
     * 3 3 5
     * 4 4 3
     *
     * 4 4 2
     */
    public int cuttingRope(int n) {
        int max = 0, per, temp, an, nT;
        for (int i = 2; i <= n; i++) {
            per = n / i;
            if (per * i - n > n - (per - 1) * i) {
                per = per - 1;
            }
            if ((per + 1) * i - n < n - per * i) {
                per = per + 1;
            }
            temp = i;
            nT = n;
            an = 1;
            while (temp > 1) {
                temp --;
                nT -= per;
                an *= per;
            }
            max = Math.max(max, an * nT);
        }
        return max;
    }

    /**
     * 1704. 判断字符串的两半是否相似
     * 给你一个偶数长度的字符串 s 。将其拆分成长度相同的两半，前一半为 a ，后一半为 b 。
     *
     * 两个字符串 相似 的前提是它们都含有相同数目的元音（'a'，'e'，'i'，'o'，'u'，'A'，'E'，'I'，'O'，'U'）。注意，s 可能同时含有大写和小写字母。
     *
     * 如果 a 和 b 相似，返回 true ；否则，返回 false 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：s = "book"
     * 输出：true
     * 解释：a = "bo" 且 b = "ok" 。a 中有 1 个元音，b 也有 1 个元音。所以，a 和 b 相似。
     * 示例 2：
     *
     * 输入：s = "textbook"
     * 输出：false
     * 解释：a = "text" 且 b = "book" 。a 中有 1 个元音，b 中有 2 个元音。因此，a 和 b 不相似。
     * 注意，元音 o 在 b 中出现两次，记为 2 个。
     * 示例 3：
     *
     * 输入：s = "MerryChristmas"
     * 输出：false
     * 示例 4：
     *
     * 输入：s = "AbCdEfGh"
     * 输出：true
     *
     *
     * 提示：
     *
     * 2 <= s.length <= 1000
     * s.length 是偶数
     * s 由 大写和小写 字母组成
     */
    public boolean halvesAreAlike(String s) {
        List<Character> chars = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        int leftCount = 0, rightCount = 0;
        for (int i = 0, j = s.length() - 1; i < j; i++, j --) {
            if (chars.contains(s.charAt(i))) {
                leftCount ++;
            }
            if (chars.contains(s.charAt(j))) {
                rightCount ++;
            }
        }
        return leftCount == rightCount;
    }

    /**
     * 1299. 将每个元素替换为右侧最大元素
     * 给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。
     *
     * 完成所有替换操作后，请你返回这个数组。
     * 示例 1：
     *
     * 输入：arr = [17,18,5,4,6,1]
     * 输出：[18,6,6,6,1,-1]
     * 解释：
     * - 下标 0 的元素 --> 右侧最大元素是下标 1 的元素 (18)
     * - 下标 1 的元素 --> 右侧最大元素是下标 4 的元素 (6)
     * - 下标 2 的元素 --> 右侧最大元素是下标 4 的元素 (6)
     * - 下标 3 的元素 --> 右侧最大元素是下标 4 的元素 (6)
     * - 下标 4 的元素 --> 右侧最大元素是下标 5 的元素 (1)
     * - 下标 5 的元素 --> 右侧没有其他元素，替换为 -1
     * 示例 2：
     *
     * 输入：arr = [400]
     * 输出：[-1]
     * 解释：下标 0 的元素右侧没有其他元素。
     *
     *
     * 提示：
     *
     * 1 <= arr.length <= 104
     * 1 <= arr[i] <= 105
     */
    public int[] replaceElements(int[] arr) {
        int max = -1;
        int[] ans = new int[arr.length];
        for (int i = arr.length - 1; i > 0; i--) {
            ans[i] = max;
            max = Math.max(max, arr[i]);
        }
        return ans;
    }

    /**
     * 238. 除自身以外数组的乘积
     * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     *
     *
     *
     * 示例:
     *
     * 输入: [1,2,3,4]
     * 输出: [24,12,8,6]
     *
     *
     * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
     *
     * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     *
     * 进阶：
     * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     */
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        ans[0] = 1;
        ans[1] = nums[0];
        for (int i = 2; i < nums.length; i++) {
            ans[i] = ans[i - 1] * ans[i - 2];
        }
        int endMulti = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            endMulti *= nums[i + 1];
            ans[i] = ans[i + 1] * endMulti;
        }
        return ans;
    }

    /**
     * 89. 格雷编码
     * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
     *
     * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
     *
     * 格雷编码序列必须以 0 开头。
     *
     *
     *
     * 示例 1:
     *
     * 输入: 2
     * 输出: [0,1,3,2]
     * 解释:
     * 00 - 0
     * 01 - 1
     * 11 - 3
     * 10 - 2
     *
     * 对于给定的 n，其格雷编码序列并不唯一。
     * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
     *
     * 00 - 0
     * 10 - 2
     * 11 - 3
     * 01 - 1
     * 示例 2:
     *
     * 输入: 0
     * 输出: [0]
     * 解释: 我们定义格雷编码序列必须以 0 开头。
     *      给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
     *      因此，当 n = 0 时，其格雷编码序列为 [0]。
     * 000
     * 001
     * 011 左+1
     * 010 -1
     * 110
     * 111
     * 101
     * 100
     */
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();

        return ans;
    }

    /**
     * 剑指 Offer 17. 打印从1到最大的n位数
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     *
     * 示例 1:
     *
     * 输入: n = 1
     * 输出: [1,2,3,4,5,6,7,8,9]
     *
     *
     * 说明：
     *
     * 用返回一个整数列表来代替打印
     * n 为正整数
     */
    public int[] printNumbers(int n) {
        int max = (int)Math.pow(10, n + 1) - 1;
        int[] ans = new int[max];
        for (int i = 1; i <= max; i++) {
            ans[i - 1] = i;
        }
        return ans;
    }

    /**
     * 537. 复数乘法
     * 给定两个表示复数的字符串。
     *
     * 返回表示它们乘积的字符串。注意，根据定义 i2 = -1 。
     *
     * 示例 1:
     *
     * 输入: "1+1i", "1+1i"
     * 输出: "0+2i"
     * 解释: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
     * 示例 2:
     *
     * 输入: "1+-1i", "1+-1i"
     * 输出: "0+-2i"
     * 解释: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
     * 注意:
     *
     * 输入字符串不包含额外的空格。
     * 输入字符串将以 a+bi 的形式给出，其中整数 a 和 b 的范围均在 [-100, 100] 之间。输出也应当符合这种形式。
     */
    public String complexNumberMultiply(String a, String b) {
        int a0 = Integer.parseInt(a.substring(0, a.indexOf("+")));
        int a1 = Integer.parseInt(a.substring(a.indexOf("+") + 1, a.indexOf("i")));
        int b0 = Integer.parseInt(b.substring(0, b.indexOf("+")));
        int b1 = Integer.parseInt(b.substring(b.indexOf("+") + 1, b.indexOf("i")));
        return (a0 * b0 - a1 * b1) + "+" + (a1 * b0 + a0 * b1) + "i";
    }
}

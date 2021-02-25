package com.leetcode.february;

import java.util.Arrays;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-24 19:22:03
 * @author: wanglong16@meicai.cn
 */
public class SolutionFourteen {

    /**
     * 762. 二进制表示中质数个计算置位
     * 给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。
     * （注意，计算置位代表二进制表示中1的个数。例如 21 的二进制表示 10101 有 3 个计算置位。还有，1 不是质数。）
     *
     * 示例 1:
     *
     * 输入: L = 6, R = 10
     * 输出: 4
     * 解释:
     * 6 -> 110 (2 个计算置位，2 是质数)
     * 7 -> 111 (3 个计算置位，3 是质数)
     * 9 -> 1001 (2 个计算置位，2 是质数)
     * 10-> 1010 (2 个计算置位，2 是质数)
     * 示例 2:
     *
     * 输入: L = 10, R = 15
     * 输出: 5
     * 解释:
     * 10 -> 1010 (2 个计算置位, 2 是质数)
     * 11 -> 1011 (3 个计算置位, 3 是质数)
     * 12 -> 1100 (2 个计算置位, 2 是质数)
     * 13 -> 1101 (3 个计算置位, 3 是质数)
     * 14 -> 1110 (3 个计算置位, 3 是质数)
     * 15 -> 1111 (4 个计算置位, 4 不是质数)
     * 注意:
     *
     * L, R 是 L <= R 且在 [1, 10^6] 中的整数。
     * R - L 的最大值为 10000。
     */
    public int countPrimeSetBits(int L, int R) {
        int result = 0;
        for (int i = L; i <= R; i++) {
            int t = countBitsOne(i);
            // 256 * 256 [60000] * 32  => 8 8 5 = 21
            if (t == 2 || t == 3 || t == 5 || t == 7 || t == 11 || t == 13
            || t == 17 || t == 19) {
                result ++;
            }
        }
        return result;
    }


    /**
     * 1356. 根据数字二进制下 1 的数目排序
     * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
     *
     * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
     *
     * 请你返回排序后的数组。
     * 示例 1：
     *
     * 输入：arr = [0,1,2,3,4,5,6,7,8]
     * 输出：[0,1,2,4,8,3,5,6,7]
     * 解释：[0] 是唯一一个有 0 个 1 的数。
     * [1,2,4,8] 都有 1 个 1 。
     * [3,5,6] 有 2 个 1 。
     * [7] 有 3 个 1 。
     * 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
     * 示例 2：
     *
     * 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
     * 输出：[1,2,4,8,16,32,64,128,256,512,1024]
     * 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
     * 示例 3：
     *
     * 输入：arr = [10000,10000]
     * 输出：[10000,10000]
     * 示例 4：
     *
     * 输入：arr = [2,3,5,7,11,13,17,19]
     * 输出：[2,3,5,17,7,11,13,19]
     * 示例 5：
     *
     * 输入：arr = [10,100,1000,10000]
     * 输出：[10,100,10000,1000]
     *
     *
     * 提示：
     *
     * 1 <= arr.length <= 500
     * 0 <= arr[i] <= 10^4
     */
    public int[] sortByBits(int[] arr) {
        BitNum [] bitNums = new BitNum[arr.length];
        for (int i = 0; i < arr.length; i++) {
            bitNums[i] = new BitNum(arr[i]);
        }
        Arrays.sort(bitNums);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = bitNums[i].getNum();
        }
        return arr;
    }

    private class BitNum implements Comparable<BitNum>{

        private int bits = 0;
        private int num;

        public BitNum(int num) {
            this.num = num;
            while (num > 0) {
                if ((num & 1) == 1) {
                    this.bits ++;
                }
                num >>= 1;
            }
        }

        public int getNum() {
            return num;
        }

        @Override
        public int compareTo(BitNum o) {
            if (o.bits == this.bits) {
                return this.num - o.num;
            }
            return this.bits - o.bits;
        }
    }

    private int countBitsOne(int n) {
        int count = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                count ++;
            }
            n >>= 1;
        }
        return count;
    }


    /**
     * 867. 转置矩阵
     * 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
     *
     * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
     * 示例 1：
     *
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[1,4,7],[2,5,8],[3,6,9]]
     * 示例 2：
     *
     * 输入：matrix = [[1,2,3],[4,5,6]]
     * 输出：[[1,4],[2,5],[3,6]]
     * 提示：
     *
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 1000
     * 1 <= m * n <= 105
     * -109 <= matrix[i][j] <= 109
     * eg. 1, 2, 3
     *     4, 5, 6
     */
    public int[][] transpose(int[][] matrix) {
        int [][] result = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    /**
     * 1528. 重新排列字符串
     * 给你一个字符串 s 和一个 长度相同 的整数数组 indices 。
     *
     * 请你重新排列字符串 s ，其中第 i 个字符需要移动到 indices[i] 指示的位置。
     *
     * 返回重新排列后的字符串。
     * 示例 1：
     * 输入：s = "codeleet", indices = [4,5,6,7,0,2,1,3]
     * 输出："leetcode"
     * 解释：如图所示，"codeleet" 重新排列后变为 "leetcode" 。
     * 示例 2：
     *
     * 输入：s = "abc", indices = [0,1,2]
     * 输出："abc"
     * 解释：重新排列后，每个字符都还留在原来的位置上。
     * 示例 3：
     *
     * 输入：s = "aiohn", indices = [3,1,4,2,0]
     * 输出："nihao"
     * 示例 4：
     *
     * 输入：s = "aaiougrt", indices = [4,0,2,6,7,3,1,5]
     * 输出："arigatou"
     * 示例 5：
     *
     * 输入：s = "art", indices = [1,0,2]
     * 输出："rat"
     * 提示：
     * s.length == indices.length == n
     * 1 <= n <= 100
     * s 仅包含小写英文字母。
     * 0 <= indices[i] < n
     * indices 的所有的值都是唯一的（也就是说，indices 是整数 0 到 n - 1 形成的一组排列）。
     */
    public String restoreString(String s, int[] indices) {
        char [] chars = new char[s.length()];
        for (int i = 0; i < indices.length; i++) {
            chars[indices[i]] = s.charAt(i);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char aChar : chars) {
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }
}

package com.leetcode.february;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-25 23:38:10
 * @author: wanglong16@meicai.cn
 */
public class SolutionFifteen {

    /**
     * 1720. 解码异或后的数组
     * 未知 整数数组 arr 由 n 个非负整数组成。
     * 经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。
     *
     * 给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。
     *
     * 请解码返回原数组 arr 。可以证明答案存在并且是唯一的。
     * 示例 1：
     *
     * 输入：encoded = [1,2,3], first = 1
     * 输出：[1,0,2,1]
     * 解释：若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
     * 示例 2：
     *
     * 输入：encoded = [6,2,7,3], first = 4
     * 输出：[4,2,0,7,4]
     * 提示：
     *
     * 2 <= n <= 104
     * encoded.length == n - 1
     * 0 <= encoded[i] <= 105
     * 0 <= first <= 105
     */
    public int[] decode(int[] encoded, int first) {
        int [] result = new int[encoded.length + 1];
        result[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            result[i + 1] = encoded[i] ^ first;
            first = result[i + 1];
        }
        return result;
    }

    /**
     * 1290. 二进制链表转整数
     * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
     * 请你返回该链表所表示数字的 十进制值 。
     * 示例 1：
     * 输入：head = [1,0,1]
     * 输出：5
     * 解释：二进制数 (101) 转化为十进制数 (5)
     * 示例 2：
     *
     * 输入：head = [0]
     * 输出：0
     * 示例 3：
     *
     * 输入：head = [1]
     * 输出：1
     * 示例 4：
     *
     * 输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
     * 输出：18880
     * 示例 5：
     *
     * 输入：head = [0,0]
     * 输出：0
     *
     *
     * 提示：
     *
     * 链表不为空。
     * 链表的结点总数不超过 30。
     * 每个结点的值不是 0 就是 1。
     */
    public int getDecimalValueV1(ListNode head) {
        List<Integer> bits = new ArrayList<>();
        while (head != null) {
            bits.add(head.val);
            head = head.next;
        }
        int ret = 0;
        if (!bits.isEmpty()) {
            for (int i = bits.size() - 1; i >= 0; i--) {
                ret += bits.get(i) * Math.pow(2, bits.size() - 1 - i);
            }
        }
        return ret;
    }

    public int getDecimalValue(ListNode head) {
        int ret = 0;
        while (head != null) {
            ret = (ret << 1) + head.val;
            head = head.next;
        }
        return ret;
    }

    /**
     * 1310. 子数组异或查询
     * 有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
     * 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
     * 并返回一个包含给定查询 queries 所有结果的数组。
     * 示例 1：
     *
     * 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
     * 输出：[2,7,14,8]
     * 解释：
     * 数组中元素的二进制表示形式是：
     * 1 = 0001
     * 3 = 0011
     * 4 = 0100
     * 8 = 1000
     * 查询的 XOR 值为：
     * [0,1] = 1 xor 3 = 2
     * [1,2] = 3 xor 4 = 7
     * [0,3] = 1 xor 3 xor 4 xor 8 = 14
     * [3,3] = 8
     * 示例 2：
     *
     * 输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
     * 输出：[8,0,4,4]
     * 提示：
     *
     * 1 <= arr.length <= 3 * 10^4
     * 1 <= arr[i] <= 10^9
     * 1 <= queries.length <= 3 * 10^4
     * queries[i].length == 2
     * 0 <= queries[i][0] <= queries[i][1] < arr.length
     */
    public int[] xorQueriesV1(int[] arr, int[][] queries) {
        int [] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int xor = arr[queries[i][0]];
            int l = queries[i][0] + 1, r = queries[i][1];
            for (int j = l; j <= r; j++) {
                xor = xor ^ arr[j];
            }
            result[i] = xor;
        }
        return result;
    }

    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] pre = new int[n + 1];
        //计算每个位置的前缀和 pre[i] 表示前i项的异或和
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] ^ arr[i - 1];
        }
        int[] ans = new int[queries.length];
        for (int j = 0; j < queries.length; j++) {
            ans[j] = pre[queries[j][0]] ^ pre[queries[j][1] + 1];
        }
        return ans;
    }
}

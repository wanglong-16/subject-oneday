package com.leetcode.april;

import com.leetcode.util.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-04-04 16:58:56
 * @author: wanglong16@meicai.cn
 */
public class Day4 {

    /**
     * 面试题 17.21. 直方图的水量
     * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
     *
     *
     *
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。
     *
     * 示例:
     *
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     */
    public int trap(int[] height) {
        return 0;
    }


    /**
     * 1394. 找出数组中的幸运数
     * 在整数数组中，如果一个整数的出现频次和它的数值大小相等，我们就称这个整数为「幸运数」。
     *
     * 给你一个整数数组 arr，请你从中找出并返回一个幸运数。
     *
     * 如果数组中存在多个幸运数，只需返回 最大 的那个。
     * 如果数组中不含幸运数，则返回 -1 。
     *
     *
     * 示例 1：
     *
     * 输入：arr = [2,2,3,4]
     * 输出：2
     * 解释：数组中唯一的幸运数是 2 ，因为数值 2 的出现频次也是 2 。
     * 示例 2：
     *
     * 输入：arr = [1,2,2,3,3,3]
     * 输出：3
     * 解释：1、2 以及 3 都是幸运数，只需要返回其中最大的 3 。
     * 示例 3：
     *
     * 输入：arr = [2,2,2,3,3]
     * 输出：-1
     * 解释：数组中不存在幸运数。
     * 示例 4：
     *
     * 输入：arr = [5]
     * 输出：-1
     * 示例 5：
     *
     * 输入：arr = [7,7,7,7,7,7,7]
     * 输出：7
     *
     *
     * 提示：
     *
     * 1 <= arr.length <= 500
     * 1 <= arr[i] <= 500
     */
    public int findLucky(int[] arr) {
        int[] ys = new int[501];
        for (Integer in : arr) {
            ys[in] ++;
        }
        for (int i = ys.length - 1; i > 0; i--) {
            if (ys[i] == i) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 剑指 Offer 66. 构建乘积数组
     * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
     *
     *
     *
     * 示例:
     *
     * 输入: [1,2,3,4,5]
     * 输出: [120,60,40,30,24]
     * 1 2 3 4 5  1 2 3 4
     *         24 120 60
     * 提示：
     *
     * 所有元素乘积之和不会溢出 32 位整数
     * a.length <= 100000
     */
    public int[] constructArrV1(int[] a) {
        int[] ans = new int[a.length];
        int temp;
        for (int i = 0; i < a.length; i++) {
            temp = 1;
            for (int j = i + 1; j < i + a.length; j++) {
                temp *= a[j % a.length];
            }
            ans[i] = temp;
        }
        return ans;
    }

    public int[] constructArr(int[] a) {
        if (a.length == 0) {
            return new int[0];
        }
        int[] ans = new int[a.length];
        int[] dpl = new int[a.length], dpr = new int[a.length];
        dpl[0] = 1; dpr[0] = 1;
        for (int i = 1; i < a.length; i++) {
            dpl[i] = dpl[i - 1] * a[i - 1];
        }
        for (int i = 1; i < a.length; i++) {
            dpr[i] = dpr[i - 1] * a[a.length - i];
        }
        for (int i = 0; i < a.length; i++) {
            ans[i] = dpl[i] * dpr[a.length - i - 1];
        }
        return ans;
    }

    /**
     * 1816. 截断句子
     * 句子 是一个单词列表，列表中的单词之间用单个空格隔开，且不存在前导或尾随空格。每个单词仅由大小写英文字母组成（不含标点符号）。
     *
     * 例如，"Hello World"、"HELLO" 和 "hello world hello world" 都是句子。
     * 给你一个句子 s​​​​​​ 和一个整数 k​​​​​​ ，请你将 s​​ 截断 ​，​​​使截断后的句子仅含 前 k​​​​​​ 个单词。返回 截断 s​​​​​​ 后得到的句子。
     *
     *
     *
     * 示例 1：
     *
     * 输入：s = "Hello how are you Contestant", k = 4
     * 输出："Hello how are you"
     * 解释：
     * s 中的单词为 ["Hello", "how" "are", "you", "Contestant"]
     * 前 4 个单词为 ["Hello", "how", "are", "you"]
     * 因此，应当返回 "Hello how are you"
     * 示例 2：
     *
     * 输入：s = "What is the solution to this problem", k = 4
     * 输出："What is the solution"
     * 解释：
     * s 中的单词为 ["What", "is" "the", "solution", "to", "this", "problem"]
     * 前 4 个单词为 ["What", "is", "the", "solution"]
     * 因此，应当返回 "What is the solution"
     * 示例 3：
     *
     * 输入：s = "chopper is not a tanuki", k = 5
     * 输出："chopper is not a tanuki"
     */
    public String truncateSentence(String s, int k) {
        String[] strings = s.split(" ");
        if (k >= strings.length) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(strings[i]).append(" ");
        }
        return sb.delete(sb.length() - 1, sb.length()).toString();
    }
    /**
     * 108. 将有序数组转换为二叉搜索树
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     *
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     *
     *
     *
     * 示例 1：
     *
     *
     * 输入：nums = [-10,-3,0,5,9]
     * 输出：[0,-3,9,-10,null,5]
     * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
     *
     * 示例 2：
     *
     *
     * 输入：nums = [1,3]
     * 输出：[3,1]
     * 解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     * nums 按 严格递增 顺序排列
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return help(nums, 0, nums.length - 1);
    }

    private TreeNode help(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = help(nums, left, mid);
        root.right = help(nums, mid, right);
        return root;
    }
}

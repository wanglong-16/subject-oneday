package com.leetcode.march;

import com.leetcode.util.linked.ListNode;
import com.leetcode.util.tree.TreeNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-11 08:11:46
 * @author: wanglong16@meicai.cn
 */
public class Day9 {

    /**
     * 1305. 两棵二叉搜索树中的所有元素
     * 给你 root1 和 root2 这两棵二叉搜索树。
     *
     * 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
     * 示例 1：
     * 输入：root1 = [2,1,4], root2 = [1,0,3]
     * 输出：[0,1,1,2,3,4]
     * 示例 2：
     *
     * 输入：root1 = [0,-10,10], root2 = [5,1,7,0,2]
     * 输出：[-10,0,0,1,2,5,7,10]
     * 示例 3：
     *
     * 输入：root1 = [], root2 = [5,1,7,0,2]
     * 输出：[0,1,2,5,7]
     * 示例 4：
     *
     * 输入：root1 = [0,-10,10], root2 = []
     * 输出：[-10,0,10]
     * 示例 5：
     * 输入：root1 = [1,null,8], root2 = [8,1]
     * 输出：[1,1,8,8]
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> arr = new ArrayList<>();
        visitTree(arr, root1);
        visitTree(arr,root2);
        Collections.sort(arr);
        return arr;
    }

    private void visitTree(List<Integer> vals, TreeNode treeNode) {
        if (treeNode != null) {
            vals.add(treeNode.val);
            visitTree(vals, treeNode.left);
            visitTree(vals, treeNode.right);
        }
    }

    /**
     * 1502. 判断能否形成等差数列
     * 给你一个数字数组 arr 。
     *
     * 如果一个数列中，任意相邻两项的差总等于同一个常数，那么这个数列就称为 等差数列 。
     *
     * 如果可以重新排列数组形成等差数列，请返回 true ；否则，返回 false 。
     * 示例 1：
     * 输入：arr = [3,5,1]
     * 输出：true
     * 解释：对数组重新排序得到 [1,3,5] 或者 [5,3,1] ，任意相邻两项的差分别为 2 或 -2 ，可以形成等差数列。
     * 示例 2：
     *
     * 输入：arr = [1,2,4]
     * 输出：false
     * 解释：无法通过重新排序得到等差数列。
     */
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        if (arr.length <= 2) {
            return true;
        }
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] != arr[1] - arr[0]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1636. 按照频率将数组升序排序
     * 给你一个整数数组 nums ，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，请你按照数值本身将它们 降序 排序。
     * 请你返回排序后的数组。
     * 示例 1：
     *
     * 输入：nums = [1,1,2,2,2,3]
     * 输出：[3,1,1,2,2,2]
     * 解释：'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
     * 示例 2：
     *
     * 输入：nums = [2,3,1,3,2]
     * 输出：[1,3,3,2,2]
     * 解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
     * 示例 3：
     *
     * 输入：nums = [-1,1,-6,4,5,-6,1,4,1]
     * 输出：[5,-1,4,4,-6,-6,1,1,1]
     * 提示：
     * 1 <= nums.length <= 100
     * -100 <= nums[i] <= 100
     */
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> buckets = new HashMap<>(); // 0 = -100
        for (int num : nums) {
            buckets.merge(num, 1, Integer::sum);
        }
        List<BucketItem> items = new ArrayList<>();
        for (Map.Entry entry : buckets.entrySet()) {
            items.add(new BucketItem(Integer.parseInt(entry.getKey().toString()), Integer.parseInt(entry.getValue().toString())));
        }
        Collections.sort(items);
        int [] ans = new int[nums.length];
        int count = 0;
        for (BucketItem i : items) {
            for (int j = 0; j < i.freq; j++) {
                ans[count + j] = i.num;
            }
            count += i.freq;
        }
        return ans;
    }

    public class BucketItem implements Comparable<BucketItem> {

        public int num;
        public int freq;

        public BucketItem(int num, int freq) {
            this.num = num;
            this.freq = freq;
        }

        @Override
        public int compareTo(BucketItem o) {
            return this.freq == o.freq ? o.num - this.num : o.freq - this.freq;
        }
    }

}

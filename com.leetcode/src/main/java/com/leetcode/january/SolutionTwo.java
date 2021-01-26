package com.leetcode.january;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-20 22:37:43
 * @author: wanglong16@meicai.cn
 */
public class SolutionTwo {

    /**
     * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
     * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，
     * 都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]]
     * 就是连续递增子序列。
     * 示例 1：
     * 输入：nums = [1,3,5,4,7]
     * 输出：3
     * 解释：最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }
        int start = 0, stop = 1, length = nums.length, maxL = 0;
        while (stop < length) {
            if (nums[stop] <= nums[stop - 1]) {
                maxL = Math.max(maxL, stop - start);
                start = stop;
            }
            stop ++;
        }
        return Math.max(maxL, stop - start);
    }


    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 示例 1:
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */

    public int lengthOfLongestSubstring(String s) {
        final char [] chars = s.toCharArray();
        if (chars.length <= 1) {
            return chars.length;
        }
        int start = 0, stop = 0, maxL = 0;
        Set<Character> set = new HashSet<Character>();
        while (stop < chars.length){
            if (set.contains(chars[stop])) {
                for (int i = start; i < stop; i++) {
                    set.remove(chars[i]);
                    if (chars[i] == chars[stop]) {
                        start = i + 1;
                        break;
                    }
                }
            }
            set.add(chars[stop]);
            stop ++;
            maxL = Math.max(maxL, set.size());
        }
        return maxL;
    }


    /**
     * todo 
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     *
     * 示例:
     *
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {

            for (Integer integer : nums) {
                arr.add(integer);
            }
        }
        return result;
    }



}

package com.leetcode.february;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-07 17:53:21
 * @author: wanglong16@meicai.cn
 */
public class SolutionFive {

    /**
     * 73. 矩阵置零
     * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
     *
     * 示例 1:
     *
     * 输入:
     * [
     *   [1,1,1],
     *   [1,0,1],
     *   [1,1,1]
     * ]
     * 输出:
     * [
     *   [1,0,1],
     *   [0,0,0],
     *   [1,0,1]
     * ]
     * 示例 2:
     *
     * 输入:
     * [
     *   [0,1,2,0],
     *   [3,4,5,2],
     *   [1,3,1,5]
     * ]
     * 输出:
     * [
     *   [0,0,0,0],
     *   [0,4,5,0],
     *   [0,3,1,0]
     * ]
     * 进阶:
     *
     * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
     * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
     * 你能想出一个常数空间的解决方案吗？
     */
    public void setZeroes(int[][] matrix) {
        //遍历数组找到 0点的坐标， x放到一个集合，y放到一个集合
        Set<Integer> posX = new HashSet<>();
        Set<Integer> posY = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    posX.add(i);
                    posY.add(j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (posX.contains(i) || posY.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 118. 杨辉三角
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     *
     *
     *
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * 示例:
     *
     * 输入: 5
     * 输出:
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
     */
    public List<List<Integer>> generate(int numRows) {
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> ret = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            temp = genList(temp);
            ret.add(temp);
        }
        return ret;
    }

    public List<Integer> genList(List<Integer> last) {
        List<Integer> ret = new ArrayList<>();
        ret.add(1);
        if (!last.isEmpty()) {
            for (int i = 0; i < last.size(); i++) {
                if (i == (last.size() -1)) {
                    ret.add(1);
                } else {
                    ret.add(last.get(i) + last.get(i + 1));
                }
            }
        }
        return ret;
    }

    /**
     * 14. 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     *
     *
     * 示例 1：
     *
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * 示例 2：
     *
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     */
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length, index = 0;
        String max = "";
        for (int i = 0; i < len; i++) {
            max = max.length() >= strs[i].length() ? max : strs[i];
        }
        if (strs.length == 0 || max.equals("")) {
            return "";
        }
        for (int i = 0; i < max.length(); i++) {
            if (countIndex(strs, max, i) != len) {
                break;
            }
            index ++;
        }
        return strs[0].substring(0, index);
    }

    public int countIndex(String[] strs, String max, int index) {
        int count = 0;
        Character character = max.charAt(index);
        for (String str : strs) {
            if (str.equals("")) {
                return 0;
            }
            if (str.length() <= index) {
                break;
            }
            if (str.charAt(index) == character) {
                count ++;
            }
        }
        return count;
    }

    /**
     * 27. 移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     *
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     *
     *
     * 说明:
     *
     * 为什么返回数值是整数，但输出的答案是数组呢?
     *
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     *
     * 你可以想象内部操作如下:
     *
     * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
     * int len = removeElement(nums, val);
     *
     * // 在函数里修改输入数组对于调用者是可见的。
     * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
     * for (int i = 0; i < len; i++) {
     *     print(nums[i]);
     * }
     */
    public int removeElement(int[] nums, int val) {
        //遍历求解
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == val) {
                for (int j = i; j < len - 1; j++) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
                len --;
            }
            if (nums[i] == val) {
                i --;
            }
        }
        return len;
    }

    //快慢双指针解法
    public int removeElementV1(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

}

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

}

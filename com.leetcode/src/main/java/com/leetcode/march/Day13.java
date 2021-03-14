package com.leetcode.march;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-14 16:15:42
 * @author: wanglong16@meicai.cn
 */
public class Day13 {

    /**
     * 64. 最小路径和
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     *
     * 说明：每次只能向下或者向右移动一步。
     * 示例 1：
     * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
     * 输出：7
     * 解释：因为路径 1→3→1→1→1 的总和最小。
     * 示例 2：
     *
     * 输入：grid = [[1,2,3],[4,5,6]]
     * 输出：12
     */
    public int minPathSum(int[][] grid) {
        for (int i = 1; i < grid.length; i++) {
            grid[i][0] = grid[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
            grid[0][i] = grid[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                grid[i][j] = Math.min(grid[i][j -1], grid[i - 1][j]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
}

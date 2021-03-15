package com.leetcode.march;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    /**
     * 74. 搜索二维矩阵
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     *
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     *
     *
     * 示例 1：
     *
     *
     * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
     * 输出：true
     * 示例 2：
     * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
     * 输出：false
     * 提示：
     *
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 100
     * -104 <= matrix[i][j], target <= 104
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;

        // 二分查找
        int left = 0, right = m * n - 1;
        int pivotIdx, pivotElement;
        while (left <= right) {
            pivotIdx = (left + right) / 2;
            pivotElement = matrix[pivotIdx / n][pivotIdx % n];
            if (target == pivotElement) {
                return true;
            } else {
                if (target < pivotElement) {
                    right = pivotIdx - 1;
                } else {
                    left = pivotIdx + 1;
                }
            }
        }
        return false;
    }

    /**
     * 1288. 删除被覆盖区间
     * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
     *
     * 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
     *
     * 在完成所有删除操作后，请你返回列表中剩余区间的数目。
     * 示例：
     *
     * 输入：intervals = [[1,4],[3,6],[2,8]]
     * 输出：2
     * 解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
     *
     *
     * 提示：​​​​​​
     *
     * 1 <= intervals.length <= 1000
     * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
     * 对于所有的 i != j：intervals[i] != intervals[j]
     */
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        int ans = 0, curr, prev = 0;
        for (int[] interval : intervals) {
            curr = interval[1];
            if (prev < curr) {
                ans++;
                prev = curr;
            }
        }
        return ans;
    }

    /**
     * 54. 螺旋矩阵
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     * 示例 1：
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     * 示例 2：
     * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>(matrix.length * matrix[0].length);
        int startR = 0, startC = 0, stopR = matrix.length - 1, stopC = matrix[0].length - 1;
        while (startR != stopR && startC != stopC) {
            // 行
            for (int i = startC; i <= stopC; i++) {
                ans.add(matrix[startR][i]);
            }
            for (int i = startR + 1; i <= stopR; i++) {
                ans.add(matrix[i][stopC]);
            }
            for (int i = stopC - 1; i >= startC; i--) {
                ans.add(matrix[stopR][i]);
            }
            for (int i = stopR - 1; i >= startR + 1; i--) {
                ans.add(matrix[i][startC]);
            }
            startR ++; startC ++; stopR --; stopC --;
        }
        if (startC == stopC && startR <= stopR) {
            for (int i = startR; i <= stopR; i++) {
                ans.add(matrix[i][startC]);
            }
        }
        if (startC <= stopC && startR == stopR){
            for (int i = startC; i <= stopC; i++) {
                ans.add(matrix[startR][i]);
            }
        }
        return ans;
    }

    /**
     * 剑指 Offer 64. 求1+2+…+n
     * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     * 示例 1：
     *
     * 输入: n = 3
     * 输出: 6
     * 示例 2：
     *
     * 输入: n = 9
     * 输出: 45
     * 限制：
     *
     * 1 <= n <= 10000
     */

    public int sumNums(int n) {
        int ans = 0, A = n, B = n + 1;
        boolean flag;

        flag = ((A & 1) > 0) && (ans += B) > 0;
        B <<= 1;
        A >>= 1;

        flag = ((A & 1) > 0) && (ans += B) > 0;
        B <<= 1;
        A >>= 1;

        flag = ((A & 1) > 0) && (ans += B) > 0;
        B <<= 1;
        A >>= 1;

        flag = ((A & 1) > 0) && (ans += B) > 0;
        B <<= 1;
        A >>= 1;

        flag = ((A & 1) > 0) && (ans += B) > 0;
        B <<= 1;
        A >>= 1;

        flag = ((A & 1) > 0) && (ans += B) > 0;
        B <<= 1;
        A >>= 1;

        flag = ((A & 1) > 0) && (ans += B) > 0;
        B <<= 1;
        A >>= 1;

        flag = ((A & 1) > 0) && (ans += B) > 0;
        B <<= 1;
        A >>= 1;

        flag = ((A & 1) > 0) && (ans += B) > 0;
        B <<= 1;
        A >>= 1;

        flag = ((A & 1) > 0) && (ans += B) > 0;
        B <<= 1;
        A >>= 1;

        flag = ((A & 1) > 0) && (ans += B) > 0;
        B <<= 1;
        A >>= 1;

        flag = ((A & 1) > 0) && (ans += B) > 0;
        B <<= 1;
        A >>= 1;

        flag = ((A & 1) > 0) && (ans += B) > 0;
        B <<= 1;
        A >>= 1;

        flag = ((A & 1) > 0) && (ans += B) > 0;
        B <<= 1;
        A >>= 1;
        return ans >> 1;
    }

    /**
     *
     */

}

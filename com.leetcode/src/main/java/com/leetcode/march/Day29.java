package com.leetcode.march;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-30 08:49:44
 * @author: wanglong16@meicai.cn
 */
public class Day29 {

    /**
     * 1198. 找出所有行中最小公共元素
     * 给你一个矩阵 mat，其中每一行的元素都已经按 严格递增 顺序排好了。请你帮忙找出在所有这些行中 最小的公共元素。
     *
     * 如果矩阵中没有这样的公共元素，就请返回 -1。
     *
     *
     *
     * 示例：
     *
     * 输入：mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
     * 输出：5
     *
     *
     * 提示：
     *
     * 1 <= mat.length, mat[i].length <= 500
     * 1 <= mat[i][j] <= 10^4
     * mat[i] 已按严格递增顺序排列。
     */
    public int smallestCommonElement(int[][] mat) {
        int rowOne = 0;
        boolean contains = true;
        int[] lastRM = new int[mat.length - 1];
        for (int i = 0; i < mat[0].length; i++) {
            // 第一行每个值
            rowOne = mat[0][i];
            contains = true;
            for (int j = 1; j < mat.length; j++) {
                //从记忆集中拿上次的最小值
                int start = lastRM[j];
                for (int k = start; k < mat[0].length; k++) {
                    if (rowOne == mat[j][k]) {
                        start = k;
                    }
                    if (start == mat[0].length - 1) {
                        contains = false;
                    }
                }
                lastRM[j] = start;
            }
            if (contains) {
                return rowOne;
            }
        }
        return -1;
    }


    /**
     * 728. 自除数
     * 自除数 是指可以被它包含的每一位数除尽的数。
     *
     * 例如，128 是一个自除数，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
     *
     * 还有，自除数不允许包含 0 。
     *
     * 给定上边界和下边界数字，输出一个列表，列表的元素是边界（含边界）内所有的自除数。
     *
     * 示例 1：
     *
     * 输入：
     * 上边界left = 1, 下边界right = 22
     * 输出： [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
     * 注意：
     *
     * 每个输入参数的边界满足 1 <= left <= right <= 10000。
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isDividNum(i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public boolean isDividNum(int n) {
        if (n < 10) {
            return true;
        }
        String str = String.valueOf(n);
        for (Character chr : str.toCharArray()) {
            if (chr == 48 || n % (chr - 48) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 463. 岛屿的周长
     * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
     *
     * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
     *
     * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
     * 输出：16
     * 解释：它的周长是上面图片中的 16 个黄色的边
     * 示例 2：
     *
     * 输入：grid = [[1]]
     * 输出：4
     * 示例 3：
     *
     * 输入：grid = [[1,0]]
     * 输出：4
     *
     *
     * 提示：
     *
     * row == grid.length
     * col == grid[i].length
     * 1 <= row, col <= 100
     * grid[i][j] 为 0 或 1
     */
    public int islandPerimeter(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                ans += countPerimeter(grid, i, j);
            }
        }
        return ans;
    }

    public int countPerimeter(int[][] grid, int r, int c) {
        int ans = 4;
        if (c > 0 && grid[r][c - 1] == 1) {
            ans --;
        }
        if (c < grid[0].length && grid[r][c + 1] == 1) {
            ans --;
        }
        if (r > 0 && grid[r - 1][c] == 1) {
            ans --;
        }
        if (r < grid.length && grid[r - 1][c] == 1) {
            ans --;
        }
        return ans;
    }



    /**
     * 1752. 检查数组是否经排序和轮转得到
     * 给你一个数组 nums 。nums 的源数组中，所有元素与 nums 相同，但按非递减顺序排列。
     *
     * 如果 nums 能够由源数组轮转若干位置（包括 0 个位置）得到，则返回 true ；否则，返回 false 。
     *
     * 源数组中可能存在 重复项 。
     *
     * 注意：我们称数组 A 在轮转 x 个位置后得到长度相同的数组 B ，当它们满足 A[i] == B[(i+x) % A.length] ，其中 % 为取余运算。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [3,4,5,1,2]
     * 输出：true
     * 解释：[1,2,3,4,5] 为有序的源数组。
     * 可以轮转 x = 3 个位置，使新数组从值为 3 的元素开始：[3,4,5,1,2] 。
     * 示例 2：
     *
     * 输入：nums = [2,1,3,4]
     * 输出：false
     * 解释：源数组无法经轮转得到 nums 。
     * 示例 3：
     *
     * 输入：nums = [1,2,3]
     * 输出：true
     * 解释：[1,2,3] 为有序的源数组。
     * 可以轮转 x = 0 个位置（即不轮转）得到 nums 。
     * 示例 4：
     *
     * 输入：nums = [1,1,1]
     * 输出：true
     * 解释：[1,1,1] 为有序的源数组。
     * 轮转任意个位置都可以得到 nums 。
     * 示例 5：
     *
     * 输入：nums = [2,1]
     * 输出：true
     * 解释：[1,2] 为有序的源数组。
     * 可以轮转 x = 5 个位置，使新数组从值为 2 的元素开始：[2,1] 。
     */
    public boolean check(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                count ++;
            }
        }
        if (nums[nums.length - 1] > nums[0]) {
            count ++;
        }
        return count <= 1;
    }


}

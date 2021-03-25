package com.leetcode.march;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-25 07:58:24
 * @author: wanglong16@meicai.cn
 */
public class Day25 {

    /**
     * 1534. 统计好三元组
     * 给你一个整数数组 arr ，以及 a、b 、c 三个整数。请你统计其中好三元组的数量。
     * <p>
     * 如果三元组 (arr[i], arr[j], arr[k]) 满足下列全部条件，则认为它是一个 好三元组 。
     * <p>
     * 0 <= i < j < k < arr.length
     * |arr[i] - arr[j]| <= a
     * |arr[j] - arr[k]| <= b
     * |arr[i] - arr[k]| <= c
     * 其中 |x| 表示 x 的绝对值。
     * <p>
     * 返回 好三元组的数量 。
     * 示例 1：
     * <p>
     * 输入：arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3
     * 输出：4
     * 解释：一共有 4 个好三元组：[(3,0,1), (3,0,1), (3,1,1), (0,1,1)] 。
     * 示例 2：
     * <p>
     * 输入：arr = [1,1,2,2,3], a = 0, b = 0, c = 1
     * 输出：0
     * 解释：不存在满足所有条件的三元组。
     */
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int ans = 0;
        for (int j = 1; j < arr.length - 1; j++) {
            for (int i = j - 1; i >= 0; i--) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (Math.abs(arr[i] - arr[j]) <= a &&
                            Math.abs(arr[j] - arr[k]) <= b &&
                            Math.abs(arr[i] - arr[k]) <= c) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 1725. 可以形成最大正方形的矩形数目
     * 给你一个数组 rectangles ，其中 rectangles[i] = [li, wi] 表示第 i 个矩形的长度为 li 、宽度为 wi 。
     * <p>
     * 如果存在 k 同时满足 k <= li 和 k <= wi ，就可以将第 i 个矩形切成边长为 k 的正方形。例如，矩形 [4,6] 可以切成边长最大为 4 的正方形。
     * <p>
     * 设 maxLen 为可以从矩形数组 rectangles 切分得到的 最大正方形 的边长。
     * <p>
     * 返回可以切出边长为 maxLen 的正方形的矩形 数目 。
     * 示例 1：
     * <p>
     * 输入：rectangles = [[5,8],[3,9],[5,12],[16,5]]
     * 输出：3
     * 解释：能从每个矩形中切出的最大正方形边长分别是 [5,3,5,5] 。
     * 最大正方形的边长为 5 ，可以由 3 个矩形切分得到。
     * 示例 2：
     * <p>
     * 输入：rectangles = [[2,3],[3,7],[4,3],[3,7]]
     * 输出：3
     */
    public int countGoodRectangles(int[][] rectangles) {
        Arrays.sort(rectangles, Comparator.comparingInt(a -> Math.min(a[0], a[1])));
        int max = Math.min(rectangles[rectangles.length - 1][0], rectangles[rectangles.length - 1][1]);
        int ans = 0;
        for (int i = rectangles.length - 1; i >= 0; i--) {
            if (Math.min(rectangles[i][0], rectangles[i][1]) != max) {
                break;
            }
            ans++;
        }
        return ans;
    }

    /**
     * 1277. 统计全为 1 的正方形子矩阵
     * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：matrix =
     * [
     * [0,1,1,1],
     * [1,1,1,1],
     * [0,1,1,1]
     * ]
     * 输出：15
     * 解释：
     * 边长为 1 的正方形有 10 个。
     * 边长为 2 的正方形有 4 个。
     * 边长为 3 的正方形有 1 个。
     * 正方形的总数 = 10 + 4 + 1 = 15.
     * 示例 2：
     * <p>
     * 输入：matrix =
     * [
     * [1,0,1],
     * [1,1,0],
     * [1,1,0]
     * ]
     * 输出：7
     * 解释：
     * 边长为 1 的正方形有 6 个。
     * 边长为 2 的正方形有 1 个。
     * 正方形的总数 = 6 + 1 = 7.
     */
    public int countSquaresV1(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length, maxCount = Math.min(rows, cols);
        int ans = 0, area;
        for (int i = 1; i <= maxCount; i++) {
            // 统计边长为i的正方形个数 面积 = i * i
            for (int r = 0; r <= rows - i; r++) {
                for (int c = 0; c <= cols - i; c++) {
                    // 左上角可选值
                    area = 0;
                    for (int j = r; j < r + i; j++) {
                        for (int k = c; k < c + i; k++) {
                            area += matrix[j][k];
                        }
                    }
                    if (area == i * i) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public int countSquares(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(Math.max(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                }
                ans += dp[i][j];
            }
        }
        return ans;
    }
}

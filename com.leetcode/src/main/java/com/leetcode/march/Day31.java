package com.leetcode.march;

import java.util.Arrays;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-04-01 07:59:25
 * @author: wanglong16@meicai.cn
 */
public class Day31 {

    /**
     * 1742. 盒子中小球的最大数量
     * 你在一家生产小球的玩具厂工作，有 n 个小球，编号从 lowLimit 开始，到 highLimit 结束（包括 lowLimit 和 highLimit ，即 n == highLimit - lowLimit + 1）。另有无限数量的盒子，编号从 1 到 infinity 。
     *
     * 你的工作是将每个小球放入盒子中，其中盒子的编号应当等于小球编号上每位数字的和。例如，编号 321 的小球应当放入编号 3 + 2 + 1 = 6 的盒子，而编号 10 的小球应当放入编号 1 + 0 = 1 的盒子。
     *
     * 给你两个整数 lowLimit 和 highLimit ，返回放有最多小球的盒子中的小球数量。如果有多个盒子都满足放有最多小球，只需返回其中任一盒子的小球数量。
     *
     *
     *
     * 示例 1：
     *
     * 输入：lowLimit = 1, highLimit = 10
     * 输出：2
     * 解释：
     * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 ...
     * 小球数量：2 1 1 1 1 1 1 1 1 0  0  ...
     * 编号 1 的盒子放有最多小球，小球数量为 2 。
     * 示例 2：
     *
     * 输入：lowLimit = 5, highLimit = 15
     * 输出：2
     * 解释：
     * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 ...
     * 小球数量：1 1 1 1 2 2 1 1 1 0  0  ...
     * 编号 5 和 6 的盒子放有最多小球，每个盒子中的小球数量都是 2 。
     * 示例 3：
     *
     * 输入：lowLimit = 19, highLimit = 28
     * 输出：2
     * 解释：
     * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 12 ...
     * 小球数量：0 1 1 1 1 1 1 1 1 2  0  0  ...
     * 编号 10 的盒子放有最多小球，小球数量为 2 。
     */
    public int countBalls(int lowLimit, int highLimit) {
        int[] count = new int[46]; // 99999
        for (int i = lowLimit; i <= highLimit; i++) {
            count[calculate(i)] ++;
        }
        Arrays.sort(count);
        return count[45];
    }

    private int calculateV1(int n) {
        int ans = 0;
        while (n > 0) {
            ans += n % 10;
            n /= 10;
        }
        return ans;
    }

    private int calculate(int n) {
        int ans = 0;
        for (char c: String.valueOf(n).toCharArray()) {
            ans += c - 48;
        }
        return ans;
    }


    /**
     * 766. 托普利茨矩阵
     * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
     *
     * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
     *
     *
     *
     * 示例 1：
     *
     *
     * 输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
     * 输出：true
     * 解释：
     * 在上述矩阵中, 其对角线为:
     * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
     * 各条对角线上的所有元素均相同, 因此答案是 True 。
     * 示例 2：
     *
     *
     * 输入：matrix = [[1,2],[2,2]]
     * 输出：false
     * 解释：
     * 对角线 "[1, 2]" 上的元素不同。
     *
     *
     * 提示：
     *
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 20
     * 0 <= matrix[i][j] <= 99
     * 进阶：
     * 如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？
     * 如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        int head;
        for (int i = 0; i < matrix[0].length; i++) {
            head = matrix[0][i];
            for (int k = 0; k < matrix.length && k + i < matrix[0].length; k++) {
                if (head != matrix[k][i + k]) {
                    return false;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            head = matrix[i][0];
            for (int k = 0; k < matrix[0].length && k + i < matrix.length; k++) {
                if (head != matrix[k + i][k]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 1385. 两个数组间的距离值
     * 给你两个整数数组 arr1 ， arr2 和一个整数 d ，请你返回两个数组之间的 距离值 。
     *
     * 「距离值」 定义为符合此距离要求的元素数目：对于元素 arr1[i] ，不存在任何元素 arr2[j] 满足 |arr1[i]-arr2[j]| <= d 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
     * 输出：2
     * 解释：
     * 对于 arr1[0]=4 我们有：
     * |4-10|=6 > d=2
     * |4-9|=5 > d=2
     * |4-1|=3 > d=2
     * |4-8|=4 > d=2
     * 所以 arr1[0]=4 符合距离要求
     *
     * 对于 arr1[1]=5 我们有：
     * |5-10|=5 > d=2
     * |5-9|=4 > d=2
     * |5-1|=4 > d=2
     * |5-8|=3 > d=2
     * 所以 arr1[1]=5 也符合距离要求
     *
     * 对于 arr1[2]=8 我们有：
     * |8-10|=2 <= d=2
     * |8-9|=1 <= d=2
     * |8-1|=7 > d=2
     * |8-8|=0 <= d=2
     * 存在距离小于等于 2 的情况，不符合距离要求
     *
     * 故而只有 arr1[0]=4 和 arr1[1]=5 两个符合距离要求，距离值为 2
     * 示例 2：
     *
     * 输入：arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
     * 输出：2
     * 示例 3：
     *
     * 输入：arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
     * 输出：1
     */
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int ans = 0;
        for (Integer in : arr1) {
            if (isAllMatch(in - d, in + d, arr2)) {
                ans ++;
            }
        }
        return ans;
    }

    private boolean isAllMatch(int min, int max, int[] arr) {
        if (max < arr[0] || min > arr[arr.length - 1]) {
            return true;
        } else {
            for (Integer in : arr) {
                if (in <= max && in >= min) {
                    return false;
                }
            }
        }
        return true;
    }
}

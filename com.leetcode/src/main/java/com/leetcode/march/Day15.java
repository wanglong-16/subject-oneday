package com.leetcode.march;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-15 23:44:50
 * @author: wanglong16@meicai.cn
 */
public class Day15 {

    /**
     *1409. 查询带键的排列
     * 给你一个待查数组 queries ，数组中的元素为 1 到 m 之间的正整数。 请你根据以下规则处理所有待查项 queries[i]（从 i=0 到 i=queries.length-1）：
     *
     * 一开始，排列 P=[1,2,3,...,m]。
     * 对于当前的 i ，请你找出待查项 queries[i] 在排列 P 中的位置（下标从 0 开始），然后将其从原位置移动到排列 P 的起始位置（即下标为 0 处）。注意， queries[i] 在 P 中的位置就是 queries[i] 的查询结果。
     * 请你以数组形式返回待查数组  queries 的查询结果。
     * 示例 1：
     *
     * 输入：queries = [3,1,2,1], m = 5
     * 输出：[2,1,2,1]
     * 解释：待查数组 queries 处理如下：
     * 对于 i=0: queries[i]=3, P=[1,2,3,4,5], 3 在 P 中的位置是 2，接着我们把 3 移动到 P 的起始位置，得到 P=[3,1,2,4,5] 。
     * 对于 i=1: queries[i]=1, P=[3,1,2,4,5], 1 在 P 中的位置是 1，接着我们把 1 移动到 P 的起始位置，得到 P=[1,3,2,4,5] 。
     * 对于 i=2: queries[i]=2, P=[1,3,2,4,5], 2 在 P 中的位置是 2，接着我们把 2 移动到 P 的起始位置，得到 P=[2,1,3,4,5] 。
     * 对于 i=3: queries[i]=1, P=[2,1,3,4,5], 1 在 P 中的位置是 1，接着我们把 1 移动到 P 的起始位置，得到 P=[1,2,3,4,5] 。
     * 因此，返回的结果数组为 [2,1,2,1] 。
     * 示例 2：
     *
     * 输入：queries = [4,1,2,2], m = 4
     * 输出：[3,1,2,0]
     * 示例 3：
     *
     * 输入：queries = [7,5,5,8,3], m = 8
     * 输出：[6,5,0,7,5]
     */
    public int[] processQueries(int[] queries, int m) {
        int[] ans = new int[m];
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= m; i++) {
            list.add(m);
        }

        for (int i = 0; i < m; i++) {
            int index = list.indexOf(queries[i]);
            ans[i] = index;
            list.remove(index);
            list.add(0, queries[i]);
        }
        return ans;
    }

    /**
     * 59. 螺旋矩阵 II
     * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     * 示例 1：
     *
     *
     * 输入：n = 3
     * 输出：[[1,2,3],[8,9,4],[7,6,5]]
     * 示例 2：
     *
     * 输入：n = 1
     * 输出：[[1]]
     *
     *
     * 提示：
     *
     * 1 <= n <= 20
     * 通过次数67,093提交次数85,275
     */
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int rs = 0, cs = 0, re= n - 1, ce = n - 1, current = 1;
        while (rs <= re && cs <= ce) {
            for (int i = cs; i <= ce; i++) {
                ans[rs][i] = current;
                current ++;
            }
            for (int i = rs + 1; i <= re; i++) {
                ans[i][ce] = current;
                current ++;
            }
            for (int i = ce - 1; i >= cs; i--) {
                ans[re][i] = current;
                current ++;
            }
            for (int i = re - 1; i >= rs + 1; i--) {
                ans[i][cs] = current;
                current ++;
            }
            rs ++;
            cs ++;
            re --;
            ce --;
        }
        return ans;
    }

    /**
     * 807. 保持城市天际线
     * 在二维数组grid中，grid[i][j]代表位于某处的建筑物的高度。 我们被允许增加任何数量（不同建筑物的数量可能不同）的建筑物的高度。 高度 0 也被认为是建筑物。
     *
     * 最后，从新数组的所有四个方向（即顶部，底部，左侧和右侧）观看的“天际线”必须与原始数组的天际线相同。 城市的天际线是从远处观看时，由所有建筑物形成的矩形的外部轮廓。 请看下面的例子。
     *
     * 建筑物高度可以增加的最大总和是多少？
     *
     * 例子：
     * 输入： grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]
     * 输出： 35
     * 解释：
     * The grid is:
     * [ [3, 0, 8, 4],
     *   [2, 4, 5, 7],
     *   [9, 2, 6, 3],
     *   [0, 3, 1, 0] ]
     *
     * 从数组竖直方向（即顶部，底部）看“天际线”是：[9, 4, 8, 7]
     * 从水平水平方向（即左侧，右侧）看“天际线”是：[8, 7, 9, 3]
     *
     * 在不影响天际线的情况下对建筑物进行增高后，新数组如下：
     *
     * gridNew = [ [8, 4, 8, 7],
     *             [7, 4, 7, 7],
     *             [9, 4, 8, 7],
     *             [3, 3, 3, 3] ]
     * 说明:
     *
     * 1 < grid.length = grid[0].length <= 50。
     *  grid[i][j] 的高度范围是： [0, 100]。
     * 一座建筑物占据一个grid[i][j]：换言之，它们是 1 x 1 x grid[i][j] 的长方体。
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] maxR = new int[grid.length];
        int[] maxC = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            int max = 0;
            for (int j = 0; j < grid[0].length; j++) {
                max = Math.max(max, grid[i][j]);
            }
            maxR[i] = max;
        }
        for (int i = 0; i < grid[0].length; i++) {
            int max = 0;
            for (int j = 0; j < grid.length; j++) {
                max = Math.max(max, grid[j][i]);
            }
            maxC[i] = max;
        }
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                total += Math.min(maxR[i], maxC[j]) - grid[i][j];
            }
        }
        return total;
    }

    /**
     * 1395. 统计作战单位数
     *  n 名士兵站成一排。每个士兵都有一个 独一无二 的评分 rating 。
     *
     * 每 3 个士兵可以组成一个作战单位，分组规则如下：
     *
     * 从队伍中选出下标分别为 i、j、k 的 3 名士兵，他们的评分分别为 rating[i]、rating[j]、rating[k]
     * 作战单位需满足： rating[i] < rating[j] < rating[k] 或者 rating[i] > rating[j] > rating[k] ，其中  0 <= i < j < k < n
     * 请你返回按上述条件可以组建的作战单位数量。每个士兵都可以是多个作战单位的一部分。
     * 示例 1：
     *
     * 输入：rating = [2,5,3,4,1]
     * 输出：3
     * 解释：我们可以组建三个作战单位 (2,3,4)、(5,4,1)、(5,3,1) 。
     * 示例 2：
     *
     * 输入：rating = [2,1,3]
     * 输出：0
     * 解释：根据题目条件，我们无法组建作战单位。
     * 示例 3：
     *
     * 输入：rating = [1,2,3,4]
     * 输出：4
     */
    public int numTeams(int[] rating) {
        int n = rating.length;
        int ans = 0;
        // 枚举三元组中的 j
        for (int j = 1; j < n - 1; ++j) {
            int iless = 0, imore = 0;
            int kless = 0, kmore = 0;
            for (int i = 0; i < j; ++i) {
                if (rating[i] < rating[j]) {
                    ++iless;
                } else if (rating[i] > rating[j]) {
                    ++imore;
                }
            }
            for (int k = j + 1; k < n; ++k) {
                if (rating[k] < rating[j]) {
                    ++kless;
                } else if (rating[k] > rating[j]) {
                    ++kmore;
                }
            }
            ans += iless * kmore + imore * kless;
        }
        return ans;
    }
}

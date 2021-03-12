package com.leetcode.march;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-12 07:04:02
 * @author: wanglong16@meicai.cn
 */
public class Day11 {


    /**
     * 1491. 去掉最低工资和最高工资后的工资平均值
     * 给你一个整数数组 salary ，数组里每个数都是 唯一 的，其中 salary[i] 是第 i 个员工的工资。
     *
     * 请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。
     *
     *
     *
     * 示例 1：
     *
     * 输入：salary = [4000,3000,1000,2000]
     * 输出：2500.00000
     * 解释：最低工资和最高工资分别是 1000 和 4000 。
     * 去掉最低工资和最高工资以后的平均工资是 (2000+3000)/2= 2500
     * 示例 2：
     *
     * 输入：salary = [1000,2000,3000]
     * 输出：2000.00000
     * 解释：最低工资和最高工资分别是 1000 和 3000 。
     * 去掉最低工资和最高工资以后的平均工资是 (2000)/1= 2000
     * 示例 3：
     *
     * 输入：salary = [6000,5000,4000,3000,2000,1000]
     * 输出：3500.00000
     * 示例 4：
     *
     * 输入：salary = [8000,9000,2000,3000,6000,1000]
     * 输出：4750.00000
     *
     *
     * 提示：
     *
     * 3 <= salary.length <= 100
     * 10^3 <= salary[i] <= 10^6
     * salary[i] 是唯一的。
     * 与真实值误差在 10^-5 以内的结果都将视为正确答案。
     */
    public double averageV1(int[] salary) {
        int min = salary[0], max = salary[0], total = 0;
        for (Integer in : salary) {
            total += in;
            max = Math.max(in, max);
            min = Math.min(in, min);
        }
        return ((double) (total - max - min)) / (salary.length - 2);
    }

    public double average(int[] salary) {
        Arrays.sort(salary);
        int total = 0;
        for (int i = 1; i < salary.length - 1; i++) {
            total += salary[i];
        }
        return ((double) total) / (salary.length - 2);
    }


    /**
     * 973. 最接近原点的 K 个点
     * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
     *
     * （这里，平面上两点之间的距离是欧几里德距离。）
     *
     * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
     *
     *
     *
     * 示例 1：
     *
     * 输入：points = [[1,3],[-2,2]], K = 1
     * 输出：[[-2,2]]
     * 解释：
     * (1, 3) 和原点之间的距离为 sqrt(10)，
     * (-2, 2) 和原点之间的距离为 sqrt(8)，
     * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
     * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
     * 示例 2：
     *
     * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
     * 输出：[[3,3],[-2,4]]
     * （答案 [[-2,4],[3,3]] 也会被接受。）
     */
    public int[][] kClosest(int[][] points, int k) {
        int[][] ans = new int[k][2];
        Arrays.sort(points, (p1, p2) -> (int) ((Math.pow(p1[0], 2) + Math.pow(p1[1], 2)) - (Math.pow(p2[0], 2) + Math.pow(p2[1], 2))));
        if (k >= 0) {
            System.arraycopy(points, 0, ans, 0, k);
        }
        return ans;
    }

    /**
     * 1183. 矩阵中 1 的最大数量
     * 现在有一个尺寸为 width * height 的矩阵 M，矩阵中的每个单元格的值不是 0 就是 1。
     *
     * 而且矩阵 M 中每个大小为 sideLength * sideLength 的 正方形 子阵中，1 的数量不得超过 maxOnes。
     *
     * 请你设计一个算法，计算矩阵中最多可以有多少个 1。
     *
     *
     *
     * 示例 1：
     *
     * 输入：width = 3, height = 3, sideLength = 2, maxOnes = 1
     * 输出：4
     * 解释：
     * 题目要求：在一个 3*3 的矩阵中，每一个 2*2 的子阵中的 1 的数目不超过 1 个。
     * 最好的解决方案中，矩阵 M 里最多可以有 4 个 1，如下所示：
     * [1,0,1]
     * [0,0,0]
     * [1,0,1]
     * 示例 2：
     *
     * 输入：width = 3, height = 3, sideLength = 2, maxOnes = 2
     * 输出：6
     * 解释：
     * [1,0,1]
     * [1,0,1]
     * [1,0,1]
     */
    public int[][] highFive(int[][] items) {
        Arrays.sort(items, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        Map<Integer, int []> scores = new HashMap<>();
        for (int[] item : items) {
            int id = item[0], score = item[1];
            if (scores.get(id) != null) {
                int oldTotal = scores.get(id)[0];
                if (oldTotal <= 5) {
                    int oldScores = scores.get(id)[1];
                    scores.put(id, new int[]{(scores.get(id)[0] + 1), oldScores + score});
                }
            } else {
                scores.put(id, new int[]{1, score});
            }
        }
        int[][] ans = new int[scores.size()][2];
        int count = 0;
        for (Map.Entry e : scores.entrySet()) {
            int avgScore = ((int[])e.getValue())[1] / 5;
            ans[count] = new int[] {Integer.parseInt(e.getKey().toString()), avgScore};
            count ++;
        }
        return ans;
    }



}

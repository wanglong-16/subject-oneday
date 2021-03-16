package com.leetcode.march;

import java.util.*;

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


    /**
     * 1122. 数组的相对排序
     * 给你两个数组，arr1 和 arr2，
     *
     * arr2 中的元素各不相同
     * arr2 中的每个元素都出现在 arr1 中
     * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
     * 示例：
     *
     * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
     * 输出：[2,2,2,1,4,3,3,9,6,7,19]
     * 提示：
     *
     * 1 <= arr1.length, arr2.length <= 1000
     * 0 <= arr1[i], arr2[i] <= 1000
     * arr2 中的元素 arr2[i] 各不相同
     * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> existBucket = new HashMap<>();
        Map<Integer, Integer> notExistBucket = new HashMap<>();
        for (Integer in : arr2) {
            existBucket.put(in, 0);
        }
        for (Integer in : arr1) {
            if (existBucket.get(in) != null) {
                existBucket.put(in, existBucket.get(in) + 1);
            } else {
                notExistBucket.merge(in, 1, Integer::sum);
            }
        }
        int [][] notExist = new int[notExistBucket.size()][2];
        if (notExistBucket.size() != 0) {
            int cnt = 0;
            for (Map.Entry e : notExistBucket.entrySet()) {
                notExist[cnt] = new int[] {Integer.parseInt(e.getKey().toString()), Integer.parseInt(e.getValue().toString())};
                cnt ++;
            }
            Arrays.sort(notExist, Comparator.comparingInt(a -> a[0]));
        }
        int [] ans = new int[arr1.length];
        int count = 0;
        for (int i = 0; i < arr2.length; i++) {
            int val = arr2[i], times = existBucket.get(val);
            for (int j = 1; j <= times; j++) {
                ans[count] = val;
                count ++;
            }
        }
        for (int i = 0; i < notExist.length; i++) {
            int val = notExist[i][0], times = notExist[i][1];
            for (int j = 1; j <= times; j++) {
                ans[count] = val;
                count ++;
            }
        }
        return ans;
    }

    /**
     * 1640. 能否连接形成数组
     * 给你一个整数数组 arr ，数组中的每个整数 互不相同 。另有一个由整数数组构成的数组 pieces，其中的整数也 互不相同 。请你以 任意顺序 连接 pieces 中的数组以形成 arr 。但是，不允许 对每个数组 pieces[i] 中的整数重新排序。
     *
     * 如果可以连接 pieces 中的数组形成 arr ，返回 true ；否则，返回 false 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr = [85], pieces = [[85]]
     * 输出：true
     * 示例 2：
     *
     * 输入：arr = [15,88], pieces = [[88],[15]]
     * 输出：true
     * 解释：依次连接 [15] 和 [88]
     * 示例 3：
     *
     * 输入：arr = [49,18,16], pieces = [[16,18,49]]
     * 输出：false
     * 解释：即便数字相符，也不能重新排列 pieces[0]
     * 示例 4：
     *
     * 输入：arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
     * 输出：true
     * 解释：依次连接 [91]、[4,64] 和 [78]
     * 示例 5：
     *
     * 输入：arr = [1,3,5,7], pieces = [[2,4,6,8]]
     * 输出：false
     *
     *
     * 提示：
     *
     * 1 <= pieces.length <= arr.length <= 100
     * sum(pieces[i].length) == arr.length
     * 1 <= pieces[i].length <= arr.length
     * 1 <= arr[i], pieces[i][j] <= 100
     * arr 中的整数 互不相同
     * pieces 中的整数 互不相同（也就是说，如果将 pieces 扁平化成一维数组，数组中的所有整数互不相同）
     */
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int [] ar : pieces) {
            map.put(ar[0], ar);
        }
        int ptr = 0;
        while (ptr < arr.length) {
            int head = arr[ptr];
            if (map.get(head) == null) {
                return false;
            } else {
                int [] curArr = map.get(head);
                for (int value : curArr) {
                    if (value != arr[ptr]) {
                        return false;
                    } else {
                        ptr++;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 1387. 将整数按权重排序
     * 我们将整数 x 的 权重 定义为按照下述规则将 x 变成 1 所需要的步数：
     *
     * 如果 x 是偶数，那么 x = x / 2
     * 如果 x 是奇数，那么 x = 3 * x + 1
     * 比方说，x=3 的权重为 7 。因为 3 需要 7 步变成 1 （3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1）。
     *
     * 给你三个整数 lo， hi 和 k 。你的任务是将区间 [lo, hi] 之间的整数按照它们的权重 升序排序 ，如果大于等于 2 个整数有 相同 的权重，那么按照数字自身的数值 升序排序 。
     *
     * 请你返回区间 [lo, hi] 之间的整数按权重排序后的第 k 个数。
     *
     * 注意，题目保证对于任意整数 x （lo <= x <= hi） ，它变成 1 所需要的步数是一个 32 位有符号整数。
     *
     *
     *
     * 示例 1：
     *
     * 输入：lo = 12, hi = 15, k = 2
     * 输出：13
     * 解释：12 的权重为 9（12 --> 6 --> 3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1）
     * 13 的权重为 9
     * 14 的权重为 17
     * 15 的权重为 17
     * 区间内的数按权重排序以后的结果为 [12,13,14,15] 。对于 k = 2 ，答案是第二个整数也就是 13 。
     * 注意，12 和 13 有相同的权重，所以我们按照它们本身升序排序。14 和 15 同理。
     * 示例 2：
     *
     * 输入：lo = 1, hi = 1, k = 1
     * 输出：1
     * 示例 3：
     *
     * 输入：lo = 7, hi = 11, k = 4
     * 输出：7
     * 解释：区间内整数 [7, 8, 9, 10, 11] 对应的权重为 [16, 3, 19, 6, 14] 。
     * 按权重排序后得到的结果为 [8, 10, 11, 7, 9] 。
     * 排序后数组中第 4 个数字为 7 。
     * 示例 4：
     *
     * 输入：lo = 10, hi = 20, k = 5
     * 输出：13
     * 示例 5：
     *
     * 输入：lo = 1, hi = 1000, k = 777
     * 输出：570
     */
    public int getKth(int lo, int hi, int k) {
        int [][] ints = new int[hi - lo + 1][2];
        for (int i = lo; i <= hi; i++) {
            ints[i - lo] = new int[] {i, calculateWeight(i)};
        }
        Arrays.sort(ints, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
        return ints[k][0];
    }

    public int calculateWeight(int n) {
        int result = 0;
        while (n != 1) {
            if ((n & 1) == 0) {
                n >>= 1;
            } else {
                n = n * 3 + 1;
            }
            result ++;
        }
        return result;
    }

}

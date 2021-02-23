package com.leetcode.february;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-23 09:49:31
 * @author: wanglong16@meicai.cn
 */
public class SolutionThirteen {

    /**
     * 922. 按奇偶排序数组 II
     * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
     *
     * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
     *
     * 你可以返回任何满足上述条件的数组作为答案。
     * 示例：
     *
     * 输入：[4,2,5,7]
     * 输出：[4,5,2,7]
     * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     *
     *
     * 提示：
     *
     * 2 <= A.length <= 20000
     * A.length % 2 == 0
     * 0 <= A[i] <= 1000
     */
    public int[] sortArrayByParityII(int[] A) {
        List<Integer> evens = new ArrayList<>();
        List<Integer> odds = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            if ((A[i] & 1) == 0) {
                odds.add(A[i]);
            } else {
                evens.add(A[i]);
            }
        }
        Integer [] evenArr = evens.toArray(new Integer[] {});
        Integer [] oddArr = odds.toArray(new Integer[] {});
        Arrays.sort(evenArr);
        Arrays.sort(oddArr);
        int [] result = new int[A.length];
        for (int i = 0; i < evenArr.length; i++) {
            result[i * 2 + 1] = evenArr[i];
            result[i * 2] = oddArr[i];
        }
        return result;
    }

    /**
     * 1030. 距离顺序排列矩阵单元格
     * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
     * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
     * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
     * 示例 1：
     * 输入：R = 1, C = 2, r0 = 0, c0 = 0
     * 输出：[[0,0],[0,1]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
     * 示例 2：
     *
     * 输入：R = 2, C = 2, r0 = 0, c0 = 1
     * 输出：[[0,1],[0,0],[1,1],[1,0]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
     * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
     * 示例 3：
     *
     * 输入：R = 2, C = 3, r0 = 1, c0 = 2
     * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
     * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                Cell cell = new Cell(new int[] {i, j}, Math.abs(i - r0) + Math.abs(j - c0));
                cells.add(cell);
            }
        }
        Cell [] cells1 = cells.toArray(new Cell[0]);
        Arrays.sort(cells1);
        int [][] result = new int[cells.size()][2];
        for (int i = 0; i < cells1.length; i++) {
            result[i] = cells1[i].getCoordinate();
        }
        return result;
    }

    private class Cell implements Comparable<Cell>{

        private int [] coordinate = new int [2];

        private int distance = 0;

        public Cell() {
        }

        public Cell(int[] coordinate, int distance) {
            this.coordinate = coordinate;
            this.distance = distance;
        }

        public int[] getCoordinate() {
            return coordinate;
        }

        public void setCoordinate(int[] coordinate) {
            this.coordinate = coordinate;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public int compareTo(Cell c1) {
            return this.distance - c1.distance;
        }
    }

    public int[][] allCellsDistOrderV1(int R, int C, int r0, int c0) {
        int[][] ret = new int[R * C][];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ret[i * C + j] = new int[]{i, j};
            }
        }
        Arrays.sort(ret, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return (Math.abs(a[0] - r0) + Math.abs(a[1] - c0)) - (Math.abs(b[0] - r0) + Math.abs(b[1] - c0));
            }
        });
        return ret;
    }


    /**
     * 976. 三角形的最大周长
     * 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
     * 如果不能形成任何面积不为零的三角形，返回 0。
     * 示例 1：
     *
     * 输入：[2,1,2]
     * 输出：5
     * 示例 2：
     *
     * 输入：[1,2,1]
     * 输出：0
     * 示例 3：
     *
     * 输入：[3,2,3,4]
     * 输出：10
     * 示例 4：
     *
     * 输入：[3,6,2,3]
     * 输出：8
     *
     *
     * 提示：
     *
     * 3 <= A.length <= 10000
     * 1 <= A[i] <= 10^6
     */
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            if (A[i] + A[i - 1] > A[i - 2] && A[i - 1] + A[i - 2] > A[i] && A[i] + A[i - 2] > A[i - 1]) {
                return A[i] + A[i - 1] + A[i + 2];
            }
        }
        return 0;
    }

    /**
     * 164. 最大间距
     * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
     *
     * 如果数组元素个数小于 2，则返回 0。
     * 示例 1:
     *
     * 输入: [3,6,9,1]
     * 输出: 3
     * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
     * 示例 2:
     *
     * 输入: [10]
     * 输出: 0
     * 解释: 数组元素个数小于 2，因此返回 0。
     * 说明:
     *
     * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
     * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
     */
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] - nums[i] > max) {
                max = nums[i + 1] - nums[i];
            }
        }
        return max;
    }

    /**
     * 693. 交替位二进制数
     * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
     * 示例 1：
     * 输入：n = 5
     * 输出：true
     * 解释：5 的二进制表示是：101
     * 示例 2：
     *
     * 输入：n = 7
     * 输出：false
     * 解释：7 的二进制表示是：111.
     * 示例 3：
     *
     * 输入：n = 11
     * 输出：false
     * 解释：11 的二进制表示是：1011.
     * 示例 4：
     *
     * 输入：n = 10
     * 输出：true
     * 解释：10 的二进制表示是：1010.
     * 示例 5：
     *
     * 输入：n = 3
     * 输出：false
     *
     *
     * 提示：
     *
     * 1 <= n <= 231 - 1
     */
    public boolean hasAlternatingBits(int n) {
        while (n > 0) {
            if ((n & 1) == 0 && ((n >> 1) & 1) == 1
                    || (n & 1) == 1 && ((n >> 1) & 1) == 0) {
                n >>= 2;
            } else {
                return false;
            }
        }
        return true;
    }
}

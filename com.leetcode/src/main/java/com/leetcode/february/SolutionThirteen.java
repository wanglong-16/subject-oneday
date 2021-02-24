package com.leetcode.february;

import javafx.geometry.Pos;

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

    /**
     * 832. 翻转图像
     * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
     *
     * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
     *
     * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
     *
     * 示例 1:
     *
     * 输入: [[1,1,0],[1,0,1],[0,0,0]]
     * 输出: [[1,0,0],[0,1,0],[1,1,1]]
     * 解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     *      然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
     * 示例 2:
     *
     * 输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
     * 输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     * 解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
     *      然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     * 说明:
     *
     * 1 <= A.length = A[0].length <= 20
     * 0 <= A[i][j] <= 1
     */
    public int[][] flipAndInvertImage(int[][] A) {
        int [][] ret = new int[A.length][];
        for (int i = 0; i < A.length; i++) {
            ret[i] = reverseAndNorArr(A[i]);
        }
        return ret;
    }

    private int [] reverseAndNorArr(int [] arr) {
        int [] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result [arr.length - i -1] = arr[i] == 1 ? 0 : 1;
        }
        return result;
    }

    /**
     * 296. 最佳的碰头地点
     * 有一队人（两人或以上）想要在一个地方碰面，他们希望能够最小化他们的总行走距离。
     *
     * 给你一个 2D 网格，其中各个格子内的值要么是 0，要么是 1。
     *
     * 1 表示某个人的家所处的位置。这里，我们将使用 曼哈顿距离 来计算，其中 distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|。
     *
     * 示例：
     *
     * 输入:
     *
     * 1 - 0 - 0 - 0 - 1
     * |   |   |   |   |
     * 0 - 0 - 0 - 0 - 0
     * |   |   |   |   |
     * 0 - 0 - 1 - 0 - 0
     *
     * 输出: 6
     *
     * 解析: 给定的三个人分别住在(0,0)，(0,4) 和 (2,2):
     *      (0,2) 是一个最佳的碰面点，其总行走距离为 2 + 2 + 2 = 6，最小，因此返回 6。
     */
    public int minTotalDistance(int[][] grid) {
        List<Position> homePos = new ArrayList<>();
        List<Position> togetherPos = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    homePos.add(new Position(i, j));
                }
                togetherPos.add(new Position(i, j));
            }
        }
        int minDistance = Integer.MAX_VALUE;
        for (Position pToge : togetherPos) {
            int distance = 0;
            for (Position pHome : homePos) {
                distance += pHome.manHaDunDistance(pToge);
            }
            if (distance < minDistance) {
                minDistance = distance;
            }
        }
        return minDistance;
    }

    private class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int manHaDunDistance (Position another) {
            return Math.abs(another.x - x) + Math.abs(another.y - y);
        }
    }

    /**
     * 4. 寻找两个正序数组的中位数
     * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
     *
     * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
     * 示例 1：
     *
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     * 示例 2：
     *
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     * 示例 3：
     *
     * 输入：nums1 = [0,0], nums2 = [0,0]
     * 输出：0.00000
     * 示例 4：
     *
     * 输入：nums1 = [], nums2 = [1]
     * 输出：1.00000
     * 示例 5：
     *
     * 输入：nums1 = [2], nums2 = []
     * 输出：2.00000
     * 提示：
     *
     * nums1.length == m
     * nums2.length == n
     * 0 <= m <= 1000
     * 0 <= n <= 1000
     * 1 <= m + n <= 2000
     * -106 <= nums1[i], nums2[i] <= 106
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> newArr = new ArrayList<>(nums1.length + nums2.length);
        int nums1Ptr = 0, nums2Ptr = 0;

        while (nums1Ptr < nums1.length || nums2Ptr < nums2.length) {
            if (nums1Ptr < nums1.length && nums2Ptr < nums2.length) {
                if (nums1[nums1Ptr] <= nums2[nums2Ptr]) {
                    newArr.add(nums1[nums1Ptr]);
                    nums1Ptr ++;
                } else {
                    newArr.add(nums2[nums2Ptr]);
                    nums2Ptr ++;
                }
            } else if (nums1Ptr >= nums1.length) {
                newArr.add(nums2[nums2Ptr]);
                nums2Ptr ++;
            } else {
                newArr.add(nums1[nums1Ptr]);
                nums1Ptr ++;
            }
        }
        newArr.sort(Comparator.comparingInt(o -> o));
        if (newArr.size() % 2 == 0) {
            return (double) (newArr.get(newArr.size() / 2) + newArr.get((newArr.size() / 2) - 1)) / 2;
        } else {
            return (double) (newArr.get(newArr.size() / 2));
        }
    }

    //todo check https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
    public double findMedianSortedArraysV1(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArraysV1(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        // median1：前一部分的最大值
        // median2：后一部分的最小值
        int median1 = 0, median2 = 0;

        while (left <= right) {
            // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;

            // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
            int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);

            if (nums_im1 <= nums_j) {
                median1 = Math.max(nums_im1, nums_jm1);
                median2 = Math.min(nums_i, nums_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }

    /**
     * 315. 计算右侧小于当前元素的个数
     * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
     * 示例：
     *
     * 输入：nums = [5,2,6,1]
     * 输出：[2,1,1,0]
     * 解释：
     * 5 的右侧有 2 个更小的元素 (2 和 1)
     * 2 的右侧仅有 1 个更小的元素 (1)
     * 6 的右侧有 1 个更小的元素 (1)
     * 1 的右侧有 0 个更小的元素
     * 提示：
     *
     * 0 <= nums.length <= 10^5
     * -10^4 <= nums[i] <= 10^4
     */
    // todo 60/65 ac 带有回溯的解法 错误
    public List<Integer> countSmallerV1(int[] nums) {
        int [] ret = new int[nums.length];
        List<Integer> result = new ArrayList<>(nums.length);
        int lastCount = 0, lastIndex = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] >= nums[lastIndex]) {
                lastCount = countOneIndexSmaller(nums, i, lastIndex) + lastCount;
                ret[i] = lastCount;
                lastIndex = i;
            } else {
                ret[i] = countOneIndexSmaller(nums, i, nums.length - 1);
            }
        }
        for (int i = 0; i < ret.length; i++) {
            result.add(ret[i]);
        }
        return result;
    }

    //暴力求解，62/65 超时
    public List<Integer> countSmallerV2(int[] nums) {
        int [] ret = new int[nums.length];
        List<Integer> result = new ArrayList<>(nums.length);
        for (int i = nums.length - 1; i >= 0; i--) {
            ret[i] = countOneIndexSmaller(nums, i, nums.length - 1);
        }
        for (int value : ret) {
            result.add(value);
        }
        return result;
    }

    private int countOneIndexSmaller(int[] nums, int pos, int start) {
        int count = 0;
        for (int i = start; i >= pos; i--) {
            if (nums[i] < nums[pos]) {
                count ++;
            }
        }
        return count;
    }
}

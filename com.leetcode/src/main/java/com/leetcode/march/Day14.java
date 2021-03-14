package com.leetcode.march;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-14 09:36:26
 * @author: wanglong16@meicai.cn
 */
public class Day14 {

    /**
     * 1476. 子矩形查询
     * 请你实现一个类 SubrectangleQueries ，它的构造函数的参数是一个 rows x cols 的矩形（这里用整数矩阵表示），并支持以下两种操作：
     *
     * 1. updateSubrectangle(int row1, int col1, int row2, int col2, int newValue)
     *
     * 用 newValue 更新以 (row1,col1) 为左上角且以 (row2,col2) 为右下角的子矩形。
     * 2. getValue(int row, int col)
     *
     * 返回矩形中坐标 (row,col) 的当前值。
     *
     *
     * 示例 1：
     *
     * 输入：
     * ["SubrectangleQueries","getValue","updateSubrectangle","getValue","getValue","updateSubrectangle","getValue","getValue"]
     * [[[[1,2,1],[4,3,4],[3,2,1],[1,1,1]]],[0,2],[0,0,3,2,5],[0,2],[3,1],[3,0,3,2,10],[3,1],[0,2]]
     * 输出：
     * [null,1,null,5,5,null,10,5]
     * 解释：
     * SubrectangleQueries subrectangleQueries = new SubrectangleQueries([[1,2,1],[4,3,4],[3,2,1],[1,1,1]]);
     * // 初始的 (4x3) 矩形如下：
     * // 1 2 1
     * // 4 3 4
     * // 3 2 1
     * // 1 1 1
     * subrectangleQueries.getValue(0, 2); // 返回 1
     * subrectangleQueries.updateSubrectangle(0, 0, 3, 2, 5);
     * // 此次更新后矩形变为：
     * // 5 5 5
     * // 5 5 5
     * // 5 5 5
     * // 5 5 5
     * subrectangleQueries.getValue(0, 2); // 返回 5
     * subrectangleQueries.getValue(3, 1); // 返回 5
     * subrectangleQueries.updateSubrectangle(3, 0, 3, 2, 10);
     * // 此次更新后矩形变为：
     * // 5   5   5
     * // 5   5   5
     * // 5   5   5
     * // 10  10  10
     * subrectangleQueries.getValue(3, 1); // 返回 10
     * subrectangleQueries.getValue(0, 2); // 返回 5
     * 示例 2：
     *
     * 输入：
     * ["SubrectangleQueries","getValue","updateSubrectangle","getValue","getValue","updateSubrectangle","getValue"]
     * [[[[1,1,1],[2,2,2],[3,3,3]]],[0,0],[0,0,2,2,100],[0,0],[2,2],[1,1,2,2,20],[2,2]]
     * 输出：
     * [null,1,null,100,100,null,20]
     * 解释：
     * SubrectangleQueries subrectangleQueries = new SubrectangleQueries([[1,1,1],[2,2,2],[3,3,3]]);
     * subrectangleQueries.getValue(0, 0); // 返回 1
     * subrectangleQueries.updateSubrectangle(0, 0, 2, 2, 100);
     * subrectangleQueries.getValue(0, 0); // 返回 100
     * subrectangleQueries.getValue(2, 2); // 返回 100
     * subrectangleQueries.updateSubrectangle(1, 1, 2, 2, 20);
     * subrectangleQueries.getValue(2, 2); // 返回 20
     */
    class SubrectangleQueriesV1 {

        int [][] rectangle;

        public SubrectangleQueriesV1(int[][] rectangle) {
            this.rectangle = rectangle;
        }

        public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
            for (int i = row1; i <= row2; i++) {
                for (int j = col1; j <= col2; j++) {
                    rectangle[i][j] = newValue;
                }
            }
        }

        public int getValue(int row, int col) {
            return rectangle[row][col];
        }
    }

    class SubrectangleQueries {

        List<int []> rem = new ArrayList<>();

        int [][] rectangle;

        public SubrectangleQueries(int[][] rectangle) {
            this.rectangle = rectangle;
        }

        public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
            this.rem.add(new int[] {row1, row2, col1, col2, newValue});
        }

        public int getValue(int row, int col) {
            for (int i = rem.size() - 1; i >= 0; i--) {
                int [] arr = rem.get(i);
                if (row >= arr[0] && row <= arr[1]
                        && col >= arr[2] && col <= arr[3]) {
                    return arr[4];
                }
            }
            return rectangle[row][col];
        }
    }

    class Range {
        public int left;
        public int right;

        public int top;
        public int bottom;

        public int val;

        public Range(int left, int right, int top, int bottom) {
            this.left = left;
            this.right = right;
            this.top = top;
            this.bottom = bottom;
        }
    }

    /**
     * Your SubrectangleQueries object will be instantiated and called as such:
     * SubrectangleQueries obj = new SubrectangleQueries(rectangle);
     * obj.updateSubrectangle(row1,col1,row2,col2,newValue);
     * int param_2 = obj.getValue(row,col);
     */

    /**
     * 1769. 移动所有球到每个盒子所需的最小操作数
     * 有 n 个盒子。给你一个长度为 n 的二进制字符串 boxes ，其中 boxes[i] 的值为 '0' 表示第 i 个盒子是 空 的，而 boxes[i] 的值为 '1' 表示盒子里有 一个 小球。
     *
     * 在一步操作中，你可以将 一个 小球从某个盒子移动到一个与之相邻的盒子中。第 i 个盒子和第 j 个盒子相邻需满足 abs(i - j) == 1 。注意，操作执行后，某些盒子中可能会存在不止一个小球。
     *
     * 返回一个长度为 n 的数组 answer ，其中 answer[i] 是将所有小球移动到第 i 个盒子所需的 最小 操作数。
     *
     * 每个 answer[i] 都需要根据盒子的 初始状态 进行计算。
     * 示例 1：
     *
     * 输入：boxes = "110"
     * 输出：[1,1,3]
     * 解释：每个盒子对应的最小操作数如下：
     * 1) 第 1 个盒子：将一个小球从第 2 个盒子移动到第 1 个盒子，需要 1 步操作。
     * 2) 第 2 个盒子：将一个小球从第 1 个盒子移动到第 2 个盒子，需要 1 步操作。
     * 3) 第 3 个盒子：将一个小球从第 1 个盒子移动到第 3 个盒子，需要 2 步操作。将一个小球从第 2 个盒子移动到第 3 个盒子，需要 1 步操作。共计 3 步操作。
     * 示例 2：
     *
     * 输入：boxes = "001011"
     * 输出：[11,8,5,4,3,4]
     * 提示：
     *
     * n == boxes.length
     * 1 <= n <= 2000
     * boxes[i] 为 '0' 或 '1'
     */
    public int[] minOperationsV1(String boxes) {
        int [] ans = new int[boxes.length()];
        for (int i = 0; i < boxes.length(); i++) {
            int left = i - 1, right = i + 1, count = 0;
            while (left >= 0 || right < boxes.length()) {
                if (left >= 0) {
                    if (boxes.charAt(left) == '1') {
                        count += Math.abs(left - i);
                    }
                    left --;
                }
                if (right < boxes.length()) {
                    if (boxes.charAt(right) == '1') {
                        count += Math.abs(right - i);
                    }
                    right ++;
                }
            }
            ans[i] = count;
        }
        return ans;
    }

    public int[] minOperations(String boxes) {
        int[] answer = new int[boxes.length()];
        int left = 0, right = 0, total = 0;//左边盒子的个数，右边盒子的个数，操作数
        //计算第一个盒子的操作数
        if (boxes.charAt(0) == '1') {
            left ++;
        }
        for (int i = 1 ; i < boxes.length(); i++) {
            if (boxes.charAt(i) == '1') {
                right++;
                total += i;
            }
        }
        answer[0] = total;
        //根据前一个盒子的操作数，计算下一个盒子的操作数
        for (int i = 1; i < boxes.length(); i++) {
            total = total + left - right;
            if (boxes.charAt(i) == '1') {
                left ++;
                right --;
            }
            answer[i] = total;
        }
        return answer;
    }

    /**
     * 1480. 一维数组的动态和
     * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
     *
     * 请返回 nums 的动态和。
     * 示例 1：
     *
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,6,10]
     * 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
     * 示例 2：
     *
     * 输入：nums = [1,1,1,1,1]
     * 输出：[1,2,3,4,5]
     * 解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。
     * 示例 3：
     *
     * 输入：nums = [3,1,2,10,1]
     * 输出：[3,4,6,16,17]
     */
    public int[] runningSum(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        int[] ans = new int[nums.length];
        ans[0] = nums[0];
        int lastTotal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans[i] = lastTotal;
            lastTotal += nums[i];
        }
        return ans;
    }

    /**
     * 1442. 形成两个异或相等数组的三元组数目
     * 给你一个整数数组 arr 。
     *
     * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
     *
     * a 和 b 定义如下：
     *
     * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
     * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
     * 注意：^ 表示 按位异或 操作。
     *
     * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
     * 示例 1：
     *
     * 输入：arr = [2,3,1,6,7]
     * 输出：4
     * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
     * 示例 2：
     *
     * 输入：arr = [1,1,1,1,1]
     * 输出：10
     * 示例 3：
     *
     * 输入：arr = [2,3]
     * 输出：0
     * 示例 4：
     *
     * 输入：arr = [1,3,5,7,9]
     * 输出：3
     * 示例 5：
     *
     * 输入：arr = [7,11,12,9,5,2,7,17,22]
     * 输出：8
     * 提示：
     *
     * 1 <= arr.length <= 300
     * 1 <= arr[i] <= 10^8
     */
    public int countTriplets(int[] arr) {
        // xor i -> k
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            int xor = arr[i];
            for (int k = i + 1; k < arr.length; k++) {
                xor ^= arr[k];
                if (xor == 0) {
                    total += k - i;
                }
            }
        }
        return total;
    }
}

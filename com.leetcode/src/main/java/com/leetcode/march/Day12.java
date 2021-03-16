package com.leetcode.march;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-13 08:29:53
 * @author: wanglong16@meicai.cn
 */
public class Day12 {

    /**
     * 1099. 小于 K 的两数之和
     * 给你一个整数数组 nums 和整数 k ，返回最大和 sum ，满足存在 i < j 使得 nums[i] + nums[j] = sum 且 sum < k 。如果没有满足此等式的 i,j 存在，则返回 -1 。
     * 示例 1：
     *
     * 输入：nums = [34,23,1,24,75,33,54,8], k = 60
     * 输出：58
     * 解释：
     * 34 和 24 相加得到 58，58 小于 60，满足题意。
     * 示例 2：
     *
     * 输入：nums = [10,20,30], k = 15
     * 输出：-1
     * 解释：
     * 我们无法找到和小于 15 的两个元素。
     */
    public int twoSumLessThanKV1(int[] nums, int k) {
        Arrays.sort(nums);
        int max = -1, sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                sum = nums[i] + nums[j];
                if (sum < k) {
                    max = Math.max(max, sum);
                } else {
                    break;
                }
            }
        }
        return max;
    }

    public int twoSumLessThanK(int[] A, int K) {
        Arrays.sort(A);
        int l = 0, r = A.length - 1;
        int result = -1;
        while (l < r) {
            if (A[l] + A[r] >= K) {
                r--;
            } else {
                result = Math.max(result, A[l] + A[r]);
                l++;
            }
        }
        return result;
    }

    /**
     * 1403. 非递增顺序的最小子序列
     * 给你一个数组 nums，请你从中抽取一个子序列，满足该子序列的元素之和 严格 大于未包含在该子序列中的各元素之和。
     *
     * 如果存在多个解决方案，只需返回 长度最小 的子序列。如果仍然有多个解决方案，则返回 元素之和最大 的子序列。
     *
     * 与子数组不同的地方在于，「数组的子序列」不强调元素在原数组中的连续性，也就是说，它可以通过从数组中分离一些（也可能不分离）元素得到。
     *
     * 注意，题目数据保证满足所有约束条件的解决方案是 唯一 的。同时，返回的答案应当按 非递增顺序 排列。
     * 示例 1：
     *
     * 输入：nums = [4,3,10,9,8]
     * 输出：[10,9]
     * 解释：子序列 [10,9] 和 [10,8] 是最小的、满足元素之和大于其他各元素之和的子序列。但是 [10,9] 的元素之和最大。
     * 示例 2：
     *
     * 输入：nums = [4,4,7,6,7]
     * 输出：[7,7,6]
     * 解释：子序列 [7,7] 的和为 14 ，不严格大于剩下的其他元素之和（14 = 4 + 4 + 6）。因此，[7,6,7] 是满足题意的最小子序列。注意，元素按非递增顺序返回。
     * 示例 3：
     *
     * 输入：nums = [6]
     * 输出：[6]
     */
    public List<Integer> minSubsequenceV1(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(nums);
        int leftPart = 0, rightPart = 0;
        for (Integer in : nums) {
            leftPart += in;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            leftPart -= nums[i];
            rightPart += nums[i];
            ans.add(nums[i]);
            if (rightPart > leftPart) {
                break;
            }
        }
        return ans;
    }

    public List<Integer> minSubsequence(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(nums);
        int total = 0;
        for (Integer in : nums) {
            total += in;
        }
        int half = total / 2, rightPart = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            rightPart += nums[i];
            ans.add(nums[i]);
            if (rightPart > half) {
                break;
            }
        }
        return ans;
    }

    /**
     * 1727. 重新排列后的最大子矩阵
     * 给你一个二进制矩阵 matrix ，它的大小为 m x n ，你可以将 matrix 中的 列 按任意顺序重新排列。
     *
     * 请你返回最优方案下将 matrix 重新排列后，全是 1 的子矩阵面积。
     *
     *
     *
     * 示例 1：
     * 输入：matrix = [[0,0,1],[1,1,1],[1,0,1]]
     * 输出：4
     * 解释：你可以按照上图方式重新排列矩阵的每一列。
     * 最大的全 1 子矩阵是上图中加粗的部分，面积为 4 。
     * 示例 2：
     * 输入：matrix = [[1,0,1,0,1]]
     * 输出：3
     * 解释：你可以按照上图方式重新排列矩阵的每一列。
     * 最大的全 1 子矩阵是上图中加粗的部分，面积为 3 。
     * 示例 3：
     *
     * 输入：matrix = [[1,1,0],[1,0,1]]
     * 输出：2
     * 解释：由于你只能整列整列重新排布，所以没有比面积为 2 更大的全 1 子矩形。
     * 示例 4：
     *
     * 输入：matrix = [[0,0],[0,0]]
     * 输出：0
     * 解释：由于矩阵中没有 1 ，没有任何全 1 的子矩阵，所以面积为 0 。
     *
     *  0 0 1
     *  1 1 1
     *  1 0 1
     *
     *  110 010 111 排序前
     *  6   2   7
     *  7   6   2
     *  111 110 010 排序后
     * 只适用于32行以下的矩阵
     */
    public int largestSubmatrix(int[][] matrix) {
        int [] colBits = new int[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            int bit = 0;
            for (int j = 0; j < matrix.length; j++) {
                bit += matrix[j][i] * (1 << j);
            }
            colBits[i] = bit;
        }
        Arrays.sort(colBits);
        int maxArea = 0;
        for (int i = 0; i < colBits.length; i++) {
            int sameBits = colBits[i];
            for (int j = i + 1; j < colBits.length; j++) {
                sameBits &= colBits[j];
            }
            maxArea = Math.max(calculateBitOne(sameBits) * (colBits.length - i), maxArea);
        }
        return maxArea;
    }

    private int calculateBitOne(int num) {
        int count = 0;
        while (num != 0) {
            if ((num & 1) == 1) {
                count ++;
            }
            num >>= 1;
        }
        return count;
    }

    /**
     * 剑指 Offer 45. 把数组排成最小的数
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * 示例 1:
     *
     * 输入: [10,2]
     * 输出: "102"
     * 示例 2:
     *
     * 输入: [3,30,34,5,9]
     * 输出: "3033459"
     * 提示:
     * 0 < nums.length <= 100
     * 说明:
     *
     * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
     * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
     */
    public String minNumber(int[] nums) {
        String [] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strings, (a, b) -> {
            long ab = Long.parseLong(a + b), ba = Long.parseLong(b + a);
            return Long.compare(ab, ba);
        });

        StringBuilder ans = new StringBuilder();
        for (String str : strings) {
            ans.append(str);
        }
        return ans.toString();
    }

    /**
     * 面试题 16.21. 交换和
     * 给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。
     *
     * 返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。
     *
     * 示例:
     *
     * 输入: array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]
     * 输出: [1, 3]
     * 示例:
     *
     * 输入: array1 = [1, 2, 3], array2 = [4, 5, 6]
     * 输出: []
     */
    public int[] findSwapValues(int[] array1, int[] array2) {
        Arrays.sort(array1);
        Arrays.sort(array2);
        int sum1 = 0, sum2 = 0;
        for (Integer in: array1) {
            sum1 += in;
        }
        for (Integer in: array2) {
            sum2 += in;
        }
        int diff = sum1 - sum2;
        if (diff == 0 || (diff > 0 && (array1[array1.length - 1] - array2[0]) < diff)
                || (diff < 0 && (array1[0] - array2[array2.length - 1]) > diff)) {
            return new int[] {};
        }
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {

            }
        }
        return null;
    }

    /**
     * 287. 寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
     *
     * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,3,4,2,2]
     * 输出：2
     * 示例 2：
     *
     * 输入：nums = [3,1,3,4,2]
     * 输出：3
     * 示例 3：
     *
     * 输入：nums = [1,1]
     * 输出：1
     * 示例 4：
     *
     * 输入：nums = [1,1,2]
     * 输出：1
     *
     *
     * 提示：
     *
     * 2 <= n <= 3 * 104
     * nums.length == n + 1
     * 1 <= nums[i] <= n
     * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
     */
    public int findDuplicateV1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /**
     * 252. 会议室
     * 给定一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，请你判断一个人是否能够参加这里面的全部会议。
     * 示例 1：
     *
     * 输入：intervals = [[0,30],[5,10],[15,20]]
     * 输出：false
     * 示例 2：
     *
     * 输入：intervals = [[7,10],[2,4]]
     * 输出：true
     */
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1471. 数组中的 k 个最强值
     * 给你一个整数数组 arr 和一个整数 k 。
     *
     * 设 m 为数组的中位数，只要满足下述两个前提之一，就可以判定 arr[i] 的值比 arr[j] 的值更强：
     *
     *  |arr[i] - m| > |arr[j] - m|
     *  |arr[i] - m| == |arr[j] - m|，且 arr[i] > arr[j]
     * 请返回由数组中最强的 k 个值组成的列表。答案可以以 任意顺序 返回。
     *
     * 中位数 是一个有序整数列表中处于中间位置的值。形式上，如果列表的长度为 n ，那么中位数就是该有序列表（下标从 0 开始）中位于 ((n - 1) / 2) 的元素。
     *
     * 例如 arr = [6, -3, 7, 2, 11]，n = 5：数组排序后得到 arr = [-3, 2, 6, 7, 11] ，数组的中间位置为 m = ((5 - 1) / 2) = 2 ，中位数 arr[m] 的值为 6 。
     * 例如 arr = [-7, 22, 17, 3]，n = 4：数组排序后得到 arr = [-7, 3, 17, 22] ，数组的中间位置为 m = ((4 - 1) / 2) = 1 ，中位数 arr[m] 的值为 3 。
     *
     *
     * 示例 1：
     *
     * 输入：arr = [1,2,3,4,5], k = 2
     * 输出：[5,1]
     * 解释：中位数为 3，按从强到弱顺序排序后，数组变为 [5,1,4,2,3]。最强的两个元素是 [5, 1]。[1, 5] 也是正确答案。
     * 注意，尽管 |5 - 3| == |1 - 3| ，但是 5 比 1 更强，因为 5 > 1 。
     * 示例 2：
     *
     * 输入：arr = [1,1,3,5,5], k = 2
     * 输出：[5,5]
     * 解释：中位数为 3, 按从强到弱顺序排序后，数组变为 [5,5,1,1,3]。最强的两个元素是 [5, 5]。
     * 示例 3：
     *
     * 输入：arr = [6,7,11,7,6,8], k = 5
     * 输出：[11,8,6,6,7]
     * 解释：中位数为 7, 按从强到弱顺序排序后，数组变为 [11,8,6,6,7,7]。
     * [11,8,6,6,7] 的任何排列都是正确答案。
     * 示例 4：
     *
     * 输入：arr = [6,-3,7,2,11], k = 3
     * 输出：[-3,11,2]
     * 示例 5：
     *
     * 输入：arr = [-7,22,17,3], k = 2
     * 输出：[22,17]
     */
    public int[] getStrongestV1(int[] arr, int k) {
        Arrays.sort(arr);
        int mid = arr[(arr.length - 1) >> 1];
        int [][] arrWeight = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int weight = Math.abs(arr[i] - mid);
            arrWeight[i] = new int[] {arr[i], weight};
        }
        Arrays.sort(arrWeight, (a, b) -> (a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]));
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arrWeight[i][0];
        }
        return ans;
    }

    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int mid = arr[(arr.length - 1) / 2],index = 0;
        int[] ans = new int[k];
        int left = 0,right = arr.length - 1;
        while (index < k) {
            int a = Math.abs(arr[left] - mid),b = Math.abs(arr[right] - mid);
            if (a > b) {
                ans[index] = arr[left];
                left++;
            }
            else {
                ans[index] = arr[right];
                right--;
            }
            index++;
        }
        return ans;
    }
}

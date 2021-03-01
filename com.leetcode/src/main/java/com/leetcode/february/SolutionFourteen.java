package com.leetcode.february;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-24 19:22:03
 * @author: wanglong16@meicai.cn
 */
public class SolutionFourteen {

    /**
     * 762. 二进制表示中质数个计算置位
     * 给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。
     * （注意，计算置位代表二进制表示中1的个数。例如 21 的二进制表示 10101 有 3 个计算置位。还有，1 不是质数。）
     *
     * 示例 1:
     *
     * 输入: L = 6, R = 10
     * 输出: 4
     * 解释:
     * 6 -> 110 (2 个计算置位，2 是质数)
     * 7 -> 111 (3 个计算置位，3 是质数)
     * 9 -> 1001 (2 个计算置位，2 是质数)
     * 10-> 1010 (2 个计算置位，2 是质数)
     * 示例 2:
     *
     * 输入: L = 10, R = 15
     * 输出: 5
     * 解释:
     * 10 -> 1010 (2 个计算置位, 2 是质数)
     * 11 -> 1011 (3 个计算置位, 3 是质数)
     * 12 -> 1100 (2 个计算置位, 2 是质数)
     * 13 -> 1101 (3 个计算置位, 3 是质数)
     * 14 -> 1110 (3 个计算置位, 3 是质数)
     * 15 -> 1111 (4 个计算置位, 4 不是质数)
     * 注意:
     *
     * L, R 是 L <= R 且在 [1, 10^6] 中的整数。
     * R - L 的最大值为 10000。
     */
    public int countPrimeSetBits(int L, int R) {
        int result = 0;
        for (int i = L; i <= R; i++) {
            int t = countBitsOne(i);
            // 256 * 256 [60000] * 32  => 8 8 5 = 21
            if (t == 2 || t == 3 || t == 5 || t == 7 || t == 11 || t == 13
            || t == 17 || t == 19) {
                result ++;
            }
        }
        return result;
    }


    /**
     * 1356. 根据数字二进制下 1 的数目排序
     * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
     *
     * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
     *
     * 请你返回排序后的数组。
     * 示例 1：
     *
     * 输入：arr = [0,1,2,3,4,5,6,7,8]
     * 输出：[0,1,2,4,8,3,5,6,7]
     * 解释：[0] 是唯一一个有 0 个 1 的数。
     * [1,2,4,8] 都有 1 个 1 。
     * [3,5,6] 有 2 个 1 。
     * [7] 有 3 个 1 。
     * 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
     * 示例 2：
     *
     * 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
     * 输出：[1,2,4,8,16,32,64,128,256,512,1024]
     * 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
     * 示例 3：
     *
     * 输入：arr = [10000,10000]
     * 输出：[10000,10000]
     * 示例 4：
     *
     * 输入：arr = [2,3,5,7,11,13,17,19]
     * 输出：[2,3,5,17,7,11,13,19]
     * 示例 5：
     *
     * 输入：arr = [10,100,1000,10000]
     * 输出：[10,100,10000,1000]
     *
     *
     * 提示：
     *
     * 1 <= arr.length <= 500
     * 0 <= arr[i] <= 10^4
     */
    public int[] sortByBits(int[] arr) {
        BitNum [] bitNums = new BitNum[arr.length];
        for (int i = 0; i < arr.length; i++) {
            bitNums[i] = new BitNum(arr[i]);
        }
        Arrays.sort(bitNums);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = bitNums[i].getNum();
        }
        return arr;
    }

    private class BitNum implements Comparable<BitNum>{

        private int bits = 0;
        private int num;

        public BitNum(int num) {
            this.num = num;
            while (num > 0) {
                if ((num & 1) == 1) {
                    this.bits ++;
                }
                num >>= 1;
            }
        }

        public int getNum() {
            return num;
        }

        @Override
        public int compareTo(BitNum o) {
            if (o.bits == this.bits) {
                return this.num - o.num;
            }
            return this.bits - o.bits;
        }
    }

    private int countBitsOne(int n) {
        int count = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                count ++;
            }
            n >>= 1;
        }
        return count;
    }


    /**
     * 867. 转置矩阵
     * 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
     *
     * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
     * 示例 1：
     *
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[1,4,7],[2,5,8],[3,6,9]]
     * 示例 2：
     *
     * 输入：matrix = [[1,2,3],[4,5,6]]
     * 输出：[[1,4],[2,5],[3,6]]
     * 提示：
     *
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 1000
     * 1 <= m * n <= 105
     * -109 <= matrix[i][j] <= 109
     * eg. 1, 2, 3
     *     4, 5, 6
     */
    public int[][] transpose(int[][] matrix) {
        int [][] result = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    /**
     * 1528. 重新排列字符串
     * 给你一个字符串 s 和一个 长度相同 的整数数组 indices 。
     *
     * 请你重新排列字符串 s ，其中第 i 个字符需要移动到 indices[i] 指示的位置。
     *
     * 返回重新排列后的字符串。
     * 示例 1：
     * 输入：s = "codeleet", indices = [4,5,6,7,0,2,1,3]
     * 输出："leetcode"
     * 解释：如图所示，"codeleet" 重新排列后变为 "leetcode" 。
     * 示例 2：
     *
     * 输入：s = "abc", indices = [0,1,2]
     * 输出："abc"
     * 解释：重新排列后，每个字符都还留在原来的位置上。
     * 示例 3：
     *
     * 输入：s = "aiohn", indices = [3,1,4,2,0]
     * 输出："nihao"
     * 示例 4：
     *
     * 输入：s = "aaiougrt", indices = [4,0,2,6,7,3,1,5]
     * 输出："arigatou"
     * 示例 5：
     *
     * 输入：s = "art", indices = [1,0,2]
     * 输出："rat"
     * 提示：
     * s.length == indices.length == n
     * 1 <= n <= 100
     * s 仅包含小写英文字母。
     * 0 <= indices[i] < n
     * indices 的所有的值都是唯一的（也就是说，indices 是整数 0 到 n - 1 形成的一组排列）。
     */
    public String restoreString(String s, int[] indices) {
        char [] chars = new char[s.length()];
        for (int i = 0; i < indices.length; i++) {
            chars[indices[i]] = s.charAt(i);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char aChar : chars) {
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }

    /**
     * 56. 合并区间
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     * 示例 1：
     *
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2：
     *
     * 输入：intervals = [[1,4],[4,5]]
     * 输出：[[1,5]]
     * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     */
    public int[][] merge(int[][] intervals) {
        Interval [] intervalArr = new Interval[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            intervalArr[i] = new Interval(intervals[i][0], intervals[i][1]);
        }
        Arrays.sort(intervalArr);
        int [][] result = new int[intervals.length][2];
        int left = intervals.length / 2, right = intervals.length / 2;
        while (left >=0 || right < intervals.length) {
            Interval l = intervalArr[left];
            Interval r = intervalArr[right];

            left --;
            right ++;
        }
        return result;
    }


    private class Interval implements Comparable<Interval> {
        int start;
        int end;
        double mid;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
            this.mid = (double) (start + end) / 2;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public int compareTo(Interval o) {
            return (int) (this.mid - o.mid);
        }
    }

    /**
     * 280. 摆动排序
     * 给你一个无序的数组 nums, 将该数字 原地 重排后使得 nums[0] <= nums[1] >= nums[2] <= nums[3]...。
     *
     * 示例:
     *
     * 输入: nums = [3,5,2,1,6,4]
     * 输出: 一个可能的解答是 [3,5,1,6,2,4]
     */
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i + 1 < nums.length; i += 2) {
            int temp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = temp;
        }
    }

    public void wiggleSortV1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if ((i % 2 == 0) == (nums[i] > nums[i + 1])) {
                int temp = nums[i];
                nums[i] = nums[i + 1];
                nums[i + 1] = temp;
            }
        }
    }

    /**
     * 1086. 前五科的均分
     * 给你一个不同学生的分数列表 items，其中 items[i] = [IDi, scorei] 表示 IDi 的学生的一科分数，你需要计算每个学生 最高的五科 成绩的 平均分。
     *
     * 返回答案 result 以数对数组形式给出，其中 result[j] = [IDj, topFiveAveragej] 表示 IDj 的学生和他 最高的五科 成绩的 平均分。result 需要按 IDj  递增的 顺序排列 。
     *
     * 学生 最高的五科 成绩的 平均分 的计算方法是将最高的五科分数相加，然后用 整数除法 除以 5 。
     * 示例 1：
     *
     * 输入：items = [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
     * 输出：[[1,87],[2,88]]
     * 解释：
     * ID = 1 的学生分数为 91、92、60、65、87 和 100 。前五科的平均分 (100 + 92 + 91 + 87 + 65) / 5 = 87
     * ID = 2 的学生分数为 93、97、77、100 和 76 。前五科的平均分 (100 + 97 + 93 + 77 + 76) / 5 = 88.6，但是由于使用整数除法，结果转换为 88
     * 示例 2：
     *
     * 输入：items = [[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100]]
     * 输出：[[1,100],[7,100]]
     *
     *
     * 提示：
     *
     * 1 <= items.length <= 1000
     * items[i].length == 2
     * 1 <= IDi <= 1000
     * 0 <= scorei <= 100
     * 对于每个 IDi，至少 存在五个分数
     * 100
     * 99 98 97
     */
    public int[][] highFiveV1(int[][] items) {
        Arrays.sort(items, Comparator.comparingInt(o -> o[0]));
        Set<Integer> stu = new HashSet<>();
        for (int [] item : items) {
            stu.add(item[0]);
        }
        int [][] ret = new int[stu.size()][];
        int currStuId = items[0][0];
        List<Integer> scores = new ArrayList<>();
        int index = 0;
        for (int[] item : items) {
            if (currStuId == item[0]) {
                scores.add(item[1]);
            } else {
                Integer[] arr = scores.toArray(new Integer[0]);
                Arrays.sort(arr);
                int total = 0;
                for (int j = arr.length - 1; j >= arr.length - 5; j--) {
                    total += arr[j];
                }
                ret[index] = new int[]{currStuId, total / 5};
                currStuId = item[0];
                index++;
            }
        }
        return ret;
    }

    public int[][] highFive(int[][] items) {
        Arrays.sort(items, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        int maxId = items[items.length-1][0];
        int[][] result = new int[maxId][2];
        int id;
        int score;
        for (int i = 0; i < items.length; i++) {
            if (i == 0 || items[i][0] != items[i - 1][0]) {
                id = items[i][0];
                result[id - 1][0] = id;
                for (int j = i; j < i + 5; j++) {
                    score = items[j][1];
                    result[id - 1][1] += score;
                }
                result[id - 1][1] /= 5;
                i += 4;
            }
        }
        return result;
    }

    /**
     * 1342. 将数字变成 0 的操作次数
     * 给你一个非负整数 num ，请你返回将它变成 0 所需要的步数。 如果当前数字是偶数，你需要把它除以 2 ；否则，减去 1 。
     *
     * 示例 1：
     *
     * 输入：num = 14
     * 输出：6
     * 解释：
     * 步骤 1) 14 是偶数，除以 2 得到 7 。
     * 步骤 2） 7 是奇数，减 1 得到 6 。
     * 步骤 3） 6 是偶数，除以 2 得到 3 。
     * 步骤 4） 3 是奇数，减 1 得到 2 。
     * 步骤 5） 2 是偶数，除以 2 得到 1 。
     * 步骤 6） 1 是奇数，减 1 得到 0 。
     * 示例 2：
     *
     * 输入：num = 8
     * 输出：4
     * 解释：
     * 步骤 1） 8 是偶数，除以 2 得到 4 。
     * 步骤 2） 4 是偶数，除以 2 得到 2 。
     * 步骤 3） 2 是偶数，除以 2 得到 1 。
     * 步骤 4） 1 是奇数，减 1 得到 0 。
     * 示例 3：
     *
     * 输入：num = 123
     * 输出：12
     *
     *
     * 提示：
     *
     * 0 <= num <= 10^6
     */
    public int numberOfSteps (int num) {
        int count = 0;
        while (num != 1) {
            if (num % 2 == 0) {
                num >>= 1;
            } else {
                num --;
            }
            count ++;
        }
        return count;
    }

}

package com.leetcode.february;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-17 08:27:23
 * @author: wanglong16@meicai.cn
 */
public class SolutionNine {

    /**
     * 412. Fizz Buzz
     * 写一个程序，输出从 1 到 n 数字的字符串表示。
     *
     * 1. 如果 n 是3的倍数，输出“Fizz”；
     *
     * 2. 如果 n 是5的倍数，输出“Buzz”；
     *
     * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
     *
     * 示例：
     *
     * n = 15,
     *
     * 返回:
     * [
     *     "1",
     *     "2",
     *     "Fizz",
     *     "4",
     *     "Buzz",
     *     "Fizz",
     *     "7",
     *     "8",
     *     "Fizz",
     *     "Buzz",
     *     "11",
     *     "Fizz",
     *     "13",
     *     "14",
     *     "FizzBuzz"
     * ]
     * @param n
     * @return
     */
    public List<String> fizzBuzz(int n) {
        List<String> fizzBuzz = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                fizzBuzz.add("FizzBuzz");
            } else if (i % 5 == 0) {
                fizzBuzz.add("Buzz");
            } else if (i % 3 == 0) {
                fizzBuzz.add("Fizz");
            } else {
                fizzBuzz.add(String.valueOf(i));
            }
        }
        return fizzBuzz;
    }

    /**
     * 566. 重塑矩阵
     * 在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
     *
     * 给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
     *
     * 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
     *
     * 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
     *
     * 示例 1:
     *
     * 输入:
     * nums =
     * [[1,2],
     *  [3,4]]
     * r = 1, c = 4
     * 输出:
     * [[1,2,3,4]]
     * 解释:
     * 行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵, 用之前的元素值一行一行填充新矩阵。
     * 示例 2:
     *
     * 输入:
     * nums =
     * [[1,2],
     *  [3,4]]
     * r = 2, c = 4
     * 输出:
     * [[1,2],
     *  [3,4]]
     * 解释:
     * 没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。 所以输出原矩阵。
     * 注意：
     *
     * 给定矩阵的宽和高范围在 [1, 100]。
     * 给定的 r 和 c 都是正数。
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums[0].length * nums.length != r * c) {
            return nums;
        }
        int [][] newNums = new int[r][c];
        int cols = nums[0].length;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                newNums[(i * cols + j) / c][(i * cols + j) % c] = nums[i][j];
            }
        }
        return newNums;
    }

    /**
     * 219. 存在重复元素 II
     * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
     *
     *
     *
     * 示例 1:
     *
     * 输入: nums = [1,2,3,1], k = 3
     * 输出: true
     * 示例 2:
     *
     * 输入: nums = [1,0,1,1], k = 1
     * 输出: true
     * 示例 3:
     *
     * 输入: nums = [1,2,3,1,2,3], k = 2
     * 输出: false
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k >= nums.length) {
            return isContains(nums, 0, nums.length);
        }
        int slideLeft = 0;
        while (slideLeft + k < nums.length) {
            if (isContains(nums, slideLeft, slideLeft + k + 1)) {
                return true;
            }
            slideLeft ++;
        }
        return false;
    }

    private boolean isContains(int[] nums, int start, int end) {
        Set<Integer> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }

    //哈希去重
    public boolean containsNearbyDuplicateV1(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 87. 字符串中的第一个唯一字符
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     * 示例：
     * s = "leetcode"
     * 返回 0
     *
     * s = "loveleetcode"
     * 返回 2
     * 提示：你可以假定该字符串只包含小写字母。
     */
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char [] chars = s.toCharArray();
        for (Character c : chars) {
            map.merge(c, 1, Integer::sum);
        }
        List<Character> uniqs = map.entrySet().stream().filter(e-> e.getValue() == 1).map(Map.Entry::getKey).collect(Collectors.toList());
        for (int i = 0; i < chars.length; i++) {
            if (uniqs.contains(chars[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 189. 旋转数组
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * 进阶：
     * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
     * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
     * 示例 1:
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     * 输入：nums = [-1,-100,3,99], k = 2
     * 输出：[3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     * 提示：
     * 1 <= nums.length <= 2 * 104
     * -231 <= nums[i] <= 231 - 1
     * 0 <= k <= 105
     */
    public void rotateV1(int[] nums, int k) {
        //计算移动后的坐标插入结果数组
        int [] result = nums.clone();
        for (int i = 0; i < nums.length; i++) {
            nums[(k + i) % result.length] = result[i];
        }
    }

    //迭代，每次向右走一步
    public void rotate(int[] nums, int k) {
        //计算移动后的坐标插入结果数组
        for (int i = 0; i < k; i++) {
            moveStepOne(nums);
        }
    }

    public void moveStepOne(int [] nums) {
        int last = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            nums[i + 1] = nums[i];
        }
        nums[0] = last;
    }

    /**
     * 660. 移除 9
     * 从 1 开始，移除所有包含数字 9 的所有整数，例如 9，19，29，……
     *
     * 这样就获得了一个新的整数数列：1，2，3，4，5，6，7，8，10，11，……
     * 给定正整数 n，请你返回新数列中第 n 个数字是多少。1 是新数列中的第一个数字。     *
     * 样例 1:
     * 输入: 9
     * 输出: 10
     * 注释 ：n 不会超过 9 x 10^8。
     */
    public int newInteger(int n) {
        for (int i = 1; i <= n; i++) {
            if (containsNine(i)) {
                n ++;
            }
        }
        return n;
    }

    private boolean containsNine(int n) {
        while (n != 0) {
            if (n % 10 == 9) {
                return true;
            }
            n /= 10;
        }
        return false;
    }

    public int newIntegerV1(int n) {
        return Integer.parseInt(Integer.toString(n, 9));
    }

    // n 不会超过 9 x 10^8。
//    private boolean containsNine(int n) {
//        return (n + 1) % 10 == 0 || (n + 10) % 100 == 0 || (n + 100) % 1000 == 0
//                || (n + 1000) % 10000 == 0 || (n + 10000) % 100000 == 0 || (n + 100000) % 1000000 == 0 ||
//                (n + 1000000) % 100000000 == 0 || (n + 10000000) % 100000000 == 0 || (n + 100000000) % 1000000000 == 0;
//    }
}

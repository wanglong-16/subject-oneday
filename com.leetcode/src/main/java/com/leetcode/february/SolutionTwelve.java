package com.leetcode.february;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-22 21:31:12
 * @author: wanglong16@meicai.cn
 */
public class SolutionTwelve {

    /**
     * 面试题 16.01. 交换数字
     * 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
     * 示例：
     * <p>
     * 输入: numbers = [1,2]
     * 输出: [2,1]
     * 提示：
     * numbers.length == 2
     */
    public int[] swapNumbers(int[] numbers) {
        numbers[0] ^= numbers[1]; // num0 = num1 ^ num0
        numbers[1] ^= numbers[0]; //num1 = num1 ^ num0 ^ num1
        numbers[0] ^= numbers[1]; //num0 = num1 ^ num0 ^ num1 ^ num0 ^ num1
        return numbers;
    }

    /**
     * 421. 数组中两个数的最大异或值
     * 给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。
     * <p>
     * 找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。
     * <p>
     * 你能在O(n)的时间解决这个问题吗？
     * <p>
     * 示例:
     * <p>
     * 输入: [3, 10, 5, 25, 2, 8]
     * <p>
     * 输出: 28
     * <p>
     * 解释: 最大的结果是 5 ^ 25 = 28.
     */
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(nums[i] ^ nums[j], max);
            }
        }
        return max;
    }

    private int bitDistance(int m, int n) {
        int distance = 0;
        while (m > 0 || n > 0) {
            if ((m & 1) != (n & 1)) {
                distance++;
            }
            m >>= 1;
            n >>= 1;
        }
        return distance;
    }

    // 先确定高位，再确定低位（有点贪心算法的意思），才能保证这道题的最大性质
    // 一位接着一位去确定这个数位的大小
    // 利用性质： a ^ b = c ，则 a ^ c = b，且 b ^ c = a

    public int findMaximumXORV1(int[] nums) {
        int res = 0;
        int mask = 0;
        for (int i = 30; i >= 0; i--) {
            // 注意点1：注意保留前缀的方法，mask 是这样得来的
            // 用异或也是可以的 mask = mask ^ (1 << i);
            mask = mask | (1 << i);

            // System.out.println(Integer.toBinaryString(mask));
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                // 注意点2：这里使用 & ，保留前缀的意思（从高位到低位）
                set.add(num & mask);
            }

            // 这里先假定第 n 位为 1 ，前 n-1 位 res 为之前迭代求得
            int temp = res | (1 << i);
            for (Integer prefix : set) {
                if (set.contains(prefix ^ temp)) {
                    res = temp;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 面试题 16.07. 最大数值
     * 编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。
     * <p>
     * 示例：
     * <p>
     * 输入： a = 1, b = 2
     * 输出： 2
     */
    public int maximum(int a, int b) {
        for (int i = 0; i < 32; i++) {
            //todo
        }
        return a;
    }

    /**
     * 11. 盛最多水的容器
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 说明：你不能倾斜容器。
     * 示例 1：
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * 示例 2：
     * <p>
     * 输入：height = [1,1]
     * 输出：1
     * 示例 3：
     * <p>
     * 输入：height = [4,3,2,1,4]
     * 输出：16
     * 示例 4：
     * <p>
     * 输入：height = [1,2,1]
     * 输出：2
     * <p>
     * <p>
     * 提示：
     * <p>
     * n = height.length
     * 2 <= n <= 3 * 104
     * 0 <= height[i] <= 3 * 104
     */
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max((j - i) * Math.min(height[i], height[j]), max);
            }
        }
        return max;
    }

    public int maxAreaV1(int[] height) {
        int left = 0, right = height.length - 1;
        int result = 0;
        while (left < right) {
            result = Math.max(result, Math.min(height[left], height[right]) * (right - left));
            if (height[left] <= height[right]) {
                left++;
            } else {
                right++;
            }
        }
        return result;
    }

    /**
     * 397. 整数替换
     * 给定一个正整数 n ，你可以做如下操作：
     * <p>
     * 如果 n 是偶数，则用 n / 2替换 n 。
     * 如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
     * n 变为 1 所需的最小替换次数是多少？
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 8
     * 输出：3
     * 解释：8 -> 4 -> 2 -> 1
     * 示例 2：
     * <p>
     * 输入：n = 7
     * 输出：4
     * 解释：7 -> 8 -> 4 -> 2 -> 1
     * 或 7 -> 6 -> 3 -> 2 -> 1
     * 示例 3：
     * <p>
     * 输入：n = 4
     * 输出：2
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= n <= 231 - 1
     */
    public int integerReplacement(int n) {
        int count = 0;
        while (n > 1) {
            if ((n & 1) == 0) {
                n /= 2;
            } else {
                if (((n + 1) & n) == 0 && ((n - 1) & (n - 2)) != 0) {
                    n++;
                } else {
                    n--;
                }
            }
            count++;
        }
        return count;
    }


    public int integerReplacementV1(int n) {
        int count = 0;
        while (n != 1) {
            //与运算判断最后一位来区分奇偶
            if ((n & 1) == 0) {
                //偶数直接无符号右移，
                //2147483647 会被奇数处理算法加一溢出为负数，
                //若选用带符号右移将无法回到1.
                n >>>= 1;
            } else {
                //识别奇数的上一位是否为1，即 以 10 结尾(xxxx01)还是以11结尾(xxxx11)
                if ((n & 2) == 0) {
                    //01结尾最优则应当 用 n -1 取代 n
                    n -= 1;
                } else {
                    //11结尾除3这个特殊情况外，其余选用 n + 1取代 n，原因如上
                    if (n == 3) {
                        //3的特殊性处理，原因如上
                        count += 2;
                        break;
                    } else {
                        n += 1;
                    }
                }
            }
            count++;
        }
        return count;
    }

    /**
     * 15. 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * 示例 1：
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 示例 2：
     * 输入：nums = []
     * 输出：[]
     * 示例 3：
     * 输入：nums = [0]
     * 输出：[]
     * 提示：
     * 0 <= nums.length <= 3000
     * -105 <= nums[i] <= 105
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int firstLessEqualsZero = 0; //第一个小于0的索引
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] <= 0) {
                firstLessEqualsZero = i;
                break;
            }
        }
        for (int i = 0; i < firstLessEqualsZero; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j <= firstLessEqualsZero; j++) {
                if (nums[j] == nums[j - 1]) {
                    continue;
                }
                for (int k = nums.length - 1; k >= firstLessEqualsZero; k--) {
                    if (nums[i] + nums[k] + nums[j] == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return result;
    }
}

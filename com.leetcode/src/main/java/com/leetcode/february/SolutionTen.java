package com.leetcode.february;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-18 09:28:57
 * @author: wanglong16@meicai.cn
 */
public class SolutionTen {

    /**
     * 137. 只出现一次的数字 II
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
     *
     * 说明：
     *
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     *
     * 输入: [2,2,3,2]
     * 输出: 3
     * 示例 2:
     *
     * 输入: [0,1,0,1,0,1,99]
     * 输出: 99
     */
    //排序 + 比较
    public int singleNumberV1(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int result = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 && nums[0] != nums[1]) {
                result = nums[0];
                break;
            } else if (i == nums.length - 1 && nums[nums.length - 2] != nums[nums.length - 1]) {
                result = nums[nums.length - 1];
                break;
            } else {
                if (nums[i] != nums[i + 1] && nums[i] != nums[i - 1]) {
                    result = nums[i];
                    break;
                }
            }
        }
        return result;
    }

    // map 计数
    public int singleNumberV3(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(nums.length / 2);
        for (Integer integer : nums) {
            if (map.containsKey(integer)) {
                map.put(integer, map.get(integer) + 1);
            } else {
                map.put(integer, 1);
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue().equals(1)) {
                return (int) entry.getKey();
            }
        }
        return 0;
    }

    //位运算
    public int singleNumberV2(int[] nums) {
        int seenOnce = 0, seenTwice = 0;
        for (int num : nums) {
            seenOnce = ~ seenTwice & (seenOnce ^ num);
            seenTwice = ~ seenOnce & (seenTwice ^ num);
        }
        return seenOnce;
    }

    /**
     * 231. 2的幂
     * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
     * 示例 1:
     * 输入: 1
     * 输出: true
     * 解释: 20 = 1
     * 示例 2:
     * 输入: 16
     * 输出: true
     * 解释: 24 = 16
     * 示例 3:
     * 输入: 218
     * 输出: false
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        int countOne = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                countOne ++;
            }
            if (countOne > 1) {
                return false;
            }
            n >>= 1;
        }
        return true;
    }

    /**
     * 260. 只出现一次的数字 III
     * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
     * 进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
     * 示例 1：
     * 输入：nums = [1,2,1,3,2,5]
     * 输出：[3,5]
     * 解释：[5, 3] 也是有效的答案。
     * 示例 2：
     *
     * 输入：nums = [-1,0]
     * 输出：[-1,0]
     * 示例 3：
     *
     * 输入：nums = [0,1]
     * 输出：[1,0]
     * 提示：
     *
     * 2 <= nums.length <= 3 * 104
     * -231 <= nums[i] <= 231 - 1
     * 除两个只出现一次的整数外，nums 中的其他数字都出现两次
     */
    public int[] singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (Integer integer : nums) {
            if (map.containsKey(integer)) {
                map.put(integer, map.get(integer) + 1);
            } else {
                map.put(integer, 1);
            }
        }
        int [] ret = new int[2];
        List<Integer> list = new ArrayList<>();
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue().equals(1)) {
                list.add(Integer.parseInt(entry.getKey().toString()));
            }
        }
        ret[0] = list.get(0);
        ret[1] = list.get(1);
        return ret;
    }

    public int[] singleNumberIIV1(int[] nums) {
        int bitmask = 0;
        //把数组中的所有元素全部都异或一遍
        for (int num : nums) {
            bitmask ^= num;
        }
        //因为异或运算的结果不一定都是2的n次幂，
        //在二进制中可能会有多个1，为了方便计算
        //我们只需保留其中的任何一个1，其他的都
        //让他变为0，这里保留的是最右边的1
        bitmask &= -bitmask;
        int[] rets = {0, 0};
        for (int num : nums) {
            //然后再把数组分为两部分，每部分在
            //分别异或
            if ((num & bitmask) == 0) {
                rets[0] ^= num;
            } else {
                rets[1] ^= num;
            }
        }
        return rets;
    }

    /**
     * 338. 比特位计数
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     *
     * 示例 1:
     *
     * 输入: 2
     * 输出: [0,1,1]
     * 示例 2:
     *
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     * 进阶:
     *
     * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
     * 要求算法的空间复杂度为O(n)。
     * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
     */
    public int[] countBits(int num) {
        int [] result = new int[num + 1];
        for (int i = 0; i <= num ; i++) {
            result[i] = bitCount(i);
        }
        return result;
    }

    private int bitCount(int n) {
        int bitOneTotal = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                bitOneTotal ++;
            }
            n >>= 1;
        }
        return bitOneTotal;
    }

    /**
     * 476. 数字的补数
     * 给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。
     * 示例 1:
     *
     * 输入: 5
     * 输出: 2
     * 解释: 5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
     * 示例 2:
     *
     * 输入: 1
     * 输出: 0
     * 解释: 1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
     */
    public int findComplementV1(int num) {
        int [] bits = new int[32];
        for (int i = 0; i < 32; i++) {
            bits[i] = num & 1;
            num >>= 1;
        }
        int result = 0, firstOne = 0;
        for (int i = 32 - 1; i >= 0; i--) {
            if (bits[i] != 0) {
                firstOne = i;
                break;
            }
        }
        for (int i = 0; i <= firstOne; i++) {
            int reverse = bits[i] == 1 ? 0 : 1;
            result += reverse * Math.pow(2, i);
        }
        return result;
    }

    public int findComplement(int num) {
        int maxBitPos = 0, temp = num;
        for (int i = 0; i < 32; i++) {
            if (temp == 0) {
                maxBitPos = i;
                break;
            }
            temp >>= 1;
        }
        return num ^ (1 >> maxBitPos - 1);
    }

}

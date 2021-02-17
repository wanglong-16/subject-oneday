package com.leetcode.january;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-20 22:37:43
 * @author: wanglong16@meicai.cn
 */
public class SolutionFour {


    /**
     * 204. 计数质数
     * 统计所有小于非负整数 n 的质数的数量
     * 示例 1：
     * 输入：n = 10
     * 输出：4
     * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     */
    public int countPrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        if (n == 0 || n == 1) {
            return 0;
        } else if (n == 2){
            return 1;
        } else {
            primes.add(2);
            for (int i = 2; i < n; i++) {
                if (isPrime(i, primes)) {
                    primes.add(i);
                }
            }
            return primes.size();
        }
    }

    public boolean isPrime(int n, List<Integer> primes) {
        for (Integer i : primes) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int countPrimesV1(int n) {
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }

    public boolean isPrime(int x) {
        for (int i = 2; i * i <= x; ++i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 191.编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
     *
     *  
     *
     * 提示：
     *
     * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
     * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
     *  
     *
     * 进阶：
     *
     * 如果多次调用这个函数，你将如何优化你的算法？
     */
    public int hammingWeight(int n) {
        int mask = 1, bits = 0;
        for (int i = 0; i < 32; i++) {
            if ((mask & n) != 0) {
                // 0010 & 1101 = 0000
                bits ++;
            }
            mask <<= 1;
        }
        return bits;
    }

    public int hammingWeightV1(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
            // 1011 & 1010 -> 1010
            // 1010 & 1001 -> 1000
            // 1000 & 0111 -> 0000
            // 每次 n和 n-1的二进制技巧
        }
        return sum;
    }

    /**
     * 190. 颠倒二进制位
     * 颠倒给定的 32 位无符号整数的二进制位。
     *
     *
     *
     * 示例 1：
     *
     * 输入: 00000010100101000001111010011100
     * 输出: 00111001011110000010100101000000
     * 解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
     *      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
     * 示例 2：
     *
     * 输入：11111111111111111111111111111101
     * 输出：10111111111111111111111111111111
     * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
     *      因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
     */
    public int reverseBits(int n) {
        int mask = 1, posVal = 0, ret = 0;
        for (int i = 0; i < 32; i++) {
            posVal = (n & mask) != 0 ? 1 : 0; // 倒数第i位是1还是0
            mask <<= 1;//掩码左移
            if (posVal == 1) {
                ret |= (1 << (32 - i -1));
            }
        }
        return ret;
    }
}

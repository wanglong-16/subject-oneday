package com.leetcode.february;

import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-04 10:38:07
 * @author: wanglong16@meicai.cn
 */
public class SolutionThree {

    /**
     * 217. 存在重复元素
     * 给定一个整数数组，判断是否存在重复元素。
     *
     * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (Integer i: nums) {
            if (set.contains(i)) {
                return true;
            } else {
                set.add(i);
            }
        }
        return false;
    }

    /**
     * 202. 快乐数
     * 编写一个算法来判断一个数 n 是不是快乐数。
     *
     * 「快乐数」定义为：
     *
     * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
     * 如果 可以变为  1，那么这个数就是快乐数。
     * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<Integer>();
        while (true) {
            if (n == 1) {
                return true;
            }
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
            n = replacePowerNumber(n);
        }
    }

    public int replacePowerNumber(int n) {
        int ret = 0;
        while (n != 0) {
            int temp = n % 10;
            ret += temp * temp;
            n /= 10;
        }
        return ret;
    }
}

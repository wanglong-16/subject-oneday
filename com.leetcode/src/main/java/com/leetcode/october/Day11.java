package com.leetcode.october;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-18 15:33:57
 * @author: wanglong16@meicai.cn
 */
public class Day11 {

    public int findComplement(int num) {
        int maxPos = 0, ans = 0;
        for (int i = 0; i < 32; i++) {
            int cur = (num >> i) & 1;
            if (cur == 1) {
                maxPos = i;
            }
        }
        for (int i = 0; i <= maxPos; i++) {
            if (((num >> i) & 1) == 0) {
                ans += 1 << i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Day11 day11 = new Day11();
        System.out.println(day11.findComplement(5));
    }
}

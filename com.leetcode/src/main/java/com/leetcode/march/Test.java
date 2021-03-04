package com.leetcode.march;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-28 09:23:52
 * @author: wanglong16@meicai.cn
 */
public class Test {

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        Day2 day2 = new Day2();
        int [][] arr = new int[][] {
                {46,89},{50,53},{52,68},{72,45},{77,81}
        };
        System.out.println(day2.maxEnvelopes(arr));
    }
}

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
        Day3 day3 = new Day3();
        Day9 day9 = new Day9();
        int [][] arr = new int[][] {
                {46,89},{50,53},{52,68},{72,45},{77,81}
        };
        String[] st1 = new String[] {"cooler","lock","touch"};
        String[] st2 = new String[] {"i like cooler cooler","lock touch cool","locker like touch"};
        System.out.println(day9.sortFeatures(st1, st2));
    }
}

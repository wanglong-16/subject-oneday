package com.leetcode.may;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-05-04 08:48:25
 * @author: wanglong16@meicai.cn
 */
public class Test {


    public static void main(String[] args) {
        Day12 day12 = new Day12();
        int[][] logs = new int[][] {{1950,1961},{1960,1971},{1970,1981}};
        System.out.println(day12.maximumPopulation(logs));
    }
}

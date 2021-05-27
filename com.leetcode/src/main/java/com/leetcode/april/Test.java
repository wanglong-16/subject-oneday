package com.leetcode.april;

import java.util.Arrays;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-04-04 15:31:58
 * @author: wanglong16@meicai.cn
 */
public class Test {

    public static void main(String[] args) {
       Day30 day30 = new Day30();
        int[][] val = new int[][] {{2,2},{1,2},{3,2}};
        int[][] quary = new int[][] {{3,1},{3,3},{5,2}};

        System.out.println(day30.closestRoom(val, quary));
    }
}

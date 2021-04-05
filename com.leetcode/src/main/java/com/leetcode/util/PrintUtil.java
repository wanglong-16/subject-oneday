package com.leetcode.util;

import java.util.Arrays;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-04-05 20:14:27
 * @author: wanglong16@meicai.cn
 */
public class PrintUtil {

    public static void printTwoDimArr(int[][] arr) {
        for (int[] ar : arr) {
            System.out.println(Arrays.toString(ar));
        }
    }
}

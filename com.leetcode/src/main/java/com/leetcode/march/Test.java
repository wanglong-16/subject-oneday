package com.leetcode.march;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-28 09:23:52
 * @author: wanglong16@meicai.cn
 */
public class Test {

    public static void main(String[] args) {
        Day11 day11 = new Day11();
        int [][] arr = new int[][] {
                {46,89},{50,53},{52,68},{72,45},{77,81}
        };
        String[] st1 = new String[] {"cooler","lock","touch"};
        String[] st2 = new String[] {"i like cooler cooler","lock touch cool","locker like touch"};

        int [] a1 = new int[] {2,3,1,3,2,4,6,7,9,2,19};
        int [] a2 = new int[] {2,1,4,3,9,6};
        System.out.println(day11.calculateWeight(13));
    }

    private static void testSortMap() {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>() {{
            put(1, 2);
            put(2, 3);
        }};

    }
}

package com.leetcode.july;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-31 20:23:39
 * @author: wanglong16@meicai.cn
 */
public class Day27 {


    public String[] findRelativeRanks(int[] score) {
        int[][] scoreSort = new int[score.length][2];
        for (int i = 0; i < score.length; i++) {
            scoreSort[i] = new int[] {i, score[i]};
        }
        Map<Integer, String> topThree = new HashMap<Integer, String>() {
            {
                put(0, "Gold Medal");
                put(1, "Silver Medal");
                put(2, "Bronze Medal");
            }
        };
        Arrays.sort(scoreSort, (a, b) -> b[1] - a[1]);
        String[] ans = new String[score.length];
        for (int i = 0; i < scoreSort.length ; i++) {
            int index = scoreSort[i][0];
            if (i <= 2) {
                ans[index] = topThree.get(i);
            } else {
                ans[index] = String.valueOf(i);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Day27 day27 = new Day27();
        System.out.println(Arrays.toString(day27.findRelativeRanks(new int[] {3, 5, 6, 2, 1})));
    }
}

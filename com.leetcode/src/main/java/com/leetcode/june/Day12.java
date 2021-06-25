package com.leetcode.june;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-12 22:27:52
 * @author: wanglong16@meicai.cn
 */
public class Day12 {

    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] dis = new int[51];
        for (int [] arr : ranges) {
            for (int i = arr[0]; i <= arr[1]; i++) {
                dis[i] = 1;
            }
        }
        for (int i = 1; i < 51; i++) {
            if (dis[i] == 0 && i >= left && i <= right) {
                return false;
            }
        }
        return true;
    }

    public int chalkReplacer(int[] chalk, int k) {
        int stus = chalk.length;
        int start = 0;
        long total = 0;
        for (int in : chalk) {
            total += in;
        }
        if (k > total) {
            k = (int) (k % total);
        }
        while (true) {
            start = start % stus;
            if (k < chalk[start]) {
                return start;
            }
            k = k - chalk[start];
            start ++;
        }
    }

    public int largestMagicSquare(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int min = Math.min(row - i, col - j);
                int maxCnt = 1, rCnt = 0, cCnt = 0;
                for (int k = i, l = j; k < min; k++, l++) {
                    rCnt += grid[k][j];
                    cCnt += grid[i][l];
                    if (rCnt == cCnt) {
                        maxCnt = Math.max(maxCnt, k - i + 1);
                    }
                }
                dp[i][j] = maxCnt;
            }
        }
        int ans = 1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (dp[i][j] > 1) {
                    if (checkArr(grid, dp[i][j], i, j)) {
                        ans = Math.max(ans, dp[i][j]);
                    }
                }
            }
        }
        return ans;
    }

    private boolean checkArr(int[][] arr, int cnt, int rSt, int cSt) {
        int xCnt = 0, yCnt = 0;
        for (int i = rSt, j = cSt; i < rSt + cnt; i++, j ++) {
            xCnt += arr[i][j];
        }
        for (int i = rSt, j = cSt + cnt; i < rSt + cnt; i++, j --) {
            yCnt += arr[i][j];
        }
        if (xCnt != yCnt) {
            return false;
        }
        for (int i = rSt; i < rSt + cnt; i++) {
            int cn = 0;
            for (int j = cSt; j < cSt + cnt; j++) {
                cn += arr[i][j];
            }
            if (cn != xCnt) {
                return false;
            }
        }
        for (int j = cSt; j < cSt + cnt; j++) {
            int cn = 0;
            for (int i = rSt; i < rSt + cnt; i++) {
                cn += arr[i][j];
            }
            if (cn != xCnt) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Day12 day12 = new Day12();
        int[][] arr = new int[][] {{7,1,4,5,6},{2,5,1,6,4},{1,5,4,3,2},{1,2,7,3,4}};

        System.out.println(day12.largestMagicSquare(arr));
    }

}

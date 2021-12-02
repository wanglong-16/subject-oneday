package com.leetcode.november;

import java.util.Random;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-11-27 16:45:54
 * @author: wanglong16@meicai.cn
 */
class Solution {
    int[][] mat;
    int rows, cols;
    Random r = new Random();

    public Solution(int m, int n) {
        mat = new int[m][n];
        rows = m;
        cols = n;
    }

    public int[] flip() {
        int rd = r.nextInt(rows * cols);
        int r = rd / cols;
        int c = rd % cols;
        mat[r][c] = 1;
        return new int[] {r, c};
    }

    public void reset() {
        mat = new int[rows][cols];
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(m, n);
 * int[] param_1 = obj.flip();
 * obj.reset();
 */

package com.leetcode.august;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-09-04 22:23:18
 * @author: wanglong16@meicai.cn
 */
public class Day26 {

    public int findMiddleIndex(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum - curSum - nums[i] == curSum) {
                return i;
            } else {
                curSum += nums[i];
            }
        }
        return -1;
    }

    int rows;
    int cols;
    boolean[][] visited;
    int[][] land;

    public int[][] findFarmland(int[][] land) {
        rows = land.length;
        cols = land[0].length;
        visited = new boolean[rows][cols];
        this.land = land;
        List<int[]> temp = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && land[i][j] == 1) {
                    temp.add(find(i, j));
                }
            }
        }
        if (!temp.isEmpty()) {
            int[][] ans = new int[temp.size()][4];
            for (int i = 0; i < temp.size(); i++) {
                ans[i] = temp.get(i);
            }
            return ans;
        } else {
            return new int[][] {};
        }
    }

    public int[] find(int row, int col) {
        int temR = row, temC = col;
        boolean fR = false, fC = false;
        for (int i = row; i < this.rows; i++) {
            if (this.land[i][col] != 1) {
                temR = i - 1;
                fR = true;
                break;
            }
        }
        if (!fR) {
            temR = this.rows - 1;
        }
        for (int i = col; i < this.cols; i++) {
            if (this.land[row][i] != 1) {
                temC = i - 1;
                fC = true;
                break;
            }
        }
        if (!fC) {
            temC = this.cols - 1;
        }
        for (int i = row; i <= temR; i++) {
            for (int j = col; j <= temC; j++) {
                this.visited[i][j] = true;
            }
        }
        return new int[] {row, col, temR, temC};
    }




    public static void main(String[] args) {

        Day26 day26 = new Day26();
        System.out.println(day26.findFarmland(new int[][] {{1,0,0},{0,1,1},{0,1,1}}));
    }
}

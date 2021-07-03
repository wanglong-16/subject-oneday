package com.leetcode.june;

import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-30 12:26:57
 * @author: wanglong16@meicai.cn
 */
public class Day29 {

    // 用数组表示 岛屿某个点出发遍历的4个方向
    private int[][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // -1、旋转折叠用这个
    //private int[][] cir = new int[][] {{-1, 1}, {1, 1}, {-1, -1}, {1, -1}};
    // 用岛屿相同大小的boolean数组表示 某些位置已经被访问过
    boolean[][] visited;
    // copy 一份岛屿原始数据，方便多个方法调用
    int[][] grid;
    // 行数
    int rows;
    // 列数
    int cols;
    //行列数是数组的俩个关键指标，单独列出来调用更方便。

    public int maxAreaOfIsland(int[][] grid) {
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.visited = new boolean[rows][cols];
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    ans = Math.max(ans,  dfs(i, j));
                }
            }
        }
        return ans;
    }

    public int dfs(int r, int c) {
        int size = 1;
        visited[r][c] = true;
        for (int[] d : dir) {
            int nr = r + d[0], nc = c + d[1];
            // 遍历下一个点位 都是先判断是否越界 再判断是否访问过 以及是否是目标值
            if (inGrid(nr, nc) && !visited[nr][nc] && grid[nr][nc] == 1) {
                size += dfs(nr, nc);
            }
        }
        return size;
    }

    // 是否越界单独抽取方法，方便调用
    public boolean inGrid(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    public static void main(String[] args) {
        Day29 day29 = new Day29();
        int[][] grid = new int[][] {
                {1, 1, 0, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
        };
        System.out.println(day29.maxAreaOfIsland(grid));
    }

}

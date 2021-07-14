package com.leetcode.july;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-10 22:26:43
 * @author: wanglong16@meicai.cn
 */
public class Day7 {

    public int countTriples(int n) {
        int ans = 0;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                double k = Math.sqrt(i * i - j * j);
                int _k = (int) k;
                if (_k * _k + j * j == i * i) {
                    ans ++;
                }
                if ((_k + 1) * (_k + 1) + j * j == i * i) {
                    ans ++;
                }
            }
        }
        return ans;
    }

    // . 空格子， + 墙

    int [][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    boolean [][] visited;
    char[][] maze;
    int rows;
    int cols;
    boolean flag = true;

    public int nearestExit(char[][] maze, int[] entrance) {
        rows = maze.length;
        cols = maze[0].length;
        visited = new boolean[rows][cols];
        this.maze = maze;
        List arr = new ArrayList();
        dfs(entrance[0], entrance[1], arr);
        return arr.size() == 0 || !flag ? -1 : arr.size();
    }

    void dfs(int r, int c, List<List<int[]>> arr) {
        visited[r][c] = true;
        List<int[]> nextp = new ArrayList<>();
        for (int [] d : dir) {
            int nr = r + d[0], nc = c + d[1];
            if (inArea(nr, nc) && !visited[nr][nc] && maze[nr][nc] == '.') {
                nextp.add(new int[] {nr, nc});
                if (isOver(nr, nc)) {
                    break;
                } else {
                    dfs(nr, nc, arr);
                }
            }
        }
        if (!nextp.isEmpty()) {
            arr.add(nextp);
        } else {
            flag = false;
        }
    }

    public boolean inArea(int r, int c) {
        return r >= 0 && c >= 0 && r < rows && c < cols;
    }

    public boolean isOver(int r, int c) {
        return r == 0 || c == 0 || r == rows - 1 || c == cols - 1;
    }

    public int[] getConcatenation(int[] nums) {
        int[] ans = new int[nums.length * 2];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i];
            ans[i + nums.length] = nums[i];
        }
        return ans;
    }


    public static void main(String[] args) {
        Day7 day7 = new Day7();
        char [][] grid = new char[][] {{'+','.','+','+','+','+','+'}, {'+','.','+','.','.','.','+'}, {'+','.','+','.','+','.','+'}, {'+','+','+','+','.','+','.'}};
        System.out.println(day7.nearestExit(grid, new int[] {0, 1}));
    }
}

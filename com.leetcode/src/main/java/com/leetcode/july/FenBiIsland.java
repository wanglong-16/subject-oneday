package com.leetcode.july;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-01 17:21:30
 * @author: wanglong16@meicai.cn
 */
public class FenBiIsland {

    int[][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] grid;
    boolean[][] visited;
    int rows;
    int cols;


    public int closedIsland(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        visited = new boolean[rows][cols];
        int ans = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && grid[i][j] == 0) {
                    List<int[]> island = new ArrayList<>();
                    dfs(island, i, j);
                    boolean isOver = false;
                    for (int k = 0; k < island.size(); k++) {
                        if (isOverLands(island.get(k)[0], island.get(k)[1])) {
                            isOver = true;
                            break;
                        }
                    }
                    if (!isOver) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }

    private void dfs(List<int[]> island, int r, int c) {
        island.add(new int[] {r, c});
        visited[r][c] = true;
        for (int[] d : dir) {
            int nr = r + d[0], nc = c + d[1];
            if (inGrid(nr, nc) && grid[nr][nc] == 1 && !visited[nr][nc]) {
                dfs(island, nr, nc);
            }
        }
    }

    public boolean isOverLands(int r, int c) {
        return r == 0 || c == 0 || r == (rows - 1) || c == (cols - 1);
    }

    public boolean inGrid(int r, int c) {
        return r >= 0 && c >= 0 && r < rows && c < cols;
    }

//    public boolean dfs(int r, int c) {
//        if (isOverLands(r, c)) {
//            return false;
//        } else {
//            for (int[] d : dir) {
//                int nr = r + d[0], nc = c + d[1];
//                if (inGrid(nr, nc) && !visited[nr][nc] && grid[nr][nc] == 0) {
//                    boolean temp = dfs(nr, nc);
//                    if (!temp) {
//                        return false;
//                    }
//                }
//            }
//        }
//        return true;
//    }

}

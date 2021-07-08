package com.leetcode.july;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-05 22:18:14
 * @author: wanglong16@meicai.cn
 */
public class Day4 {

    int[][] direction = new int[][] {{1,0}, {-1,0}, {0,-1}, {0,1}};

    boolean[][] visited;
    int rows;
    int cols;
    int[][] grid;

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        int ans = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (this.grid[i][j] == 0) {
                    visited = new boolean[rows][cols];
                    ans = Math.max(ans, dfs(i, j));
                }
            }
        }
        return ans;
    }

    public int dfs(int r, int c) {
        visited[r][c] = true;
        int ans = 1;
        for (int[] d : direction) {
            int nr = r + d[0], nc = c + d[1];
            if (inLand(nr, nc) && grid[nr][nc] == 1) {
                ans += dfs(nr,nc);
            }
        }
        return ans;
    }


    public boolean inLand(int r, int c) {
        return r >= 0 && c >= 0 && r < rows && c < cols;
    }







    public int countPairs(int[] deliciousness) {
        long cnt = 0;
        for (int i = 0; i < deliciousness.length; i++) {
            for (int j = i + 1; j < deliciousness.length; j++) {
                if (deliciousness[i] % 2 == 1 && deliciousness[j] % 2 == 0
                || deliciousness[i] % 2 == 0 && deliciousness[j] % 2 == 1) {
                    continue;
                }
                int temp = deliciousness[i] + deliciousness[j];
                if (temp!= 0 && (temp & (temp - 1)) == 0) {
                    cnt ++;
                }
            }
        }
        return (int) (cnt % 1000000007);
    }








    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind(m * n);
        visited = new boolean[m][n];
        rows = m;
        cols = n;
        List<Integer> ans = new ArrayList<>();
        for (int[] pos : positions) {
            int r = pos[0], c = pos[1];
            if (!visited[r][c]) {
                visited[r][c] = true;
                uf.incrCount();
                for (int [] dir : direction) {
                    int nr = r + dir[0], nc = c + dir[1];
                    if (inLand(nr, nc) && visited[nr][nc]) {
                        uf.merge(r * cols + c, nr * cols + nc);
                    }
                }
            }
            ans.add(uf.getCount());
        }
        return ans;
    }


    class UnionFind {

        int [] f;
        int count;
        int[] rank;

        public UnionFind(int n) {
            f = new int[n];
            rank = new int[n];
            count = 0;
            for (int i = 0; i < n; i++) {
                f[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            return f[x] == x ? x : (f[x] = find(f[x]));
        }

        public void merge(int x, int y) {
            int fx = find(x), fy = find(y);
            if (fx == fy) {
                return;
            }
            if (rank[fx] > rank[fy]) {
                f[fy] = fx;
            } else if (rank[fy] > rank[fx]) {
                f[fx] = fy;
            } else {
                f[fx] = fy;
                rank[fy]++;
            }
            count --;
        }

        public void incrCount() {
            count ++;
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        Day4 day4 = new Day4();
        System.out.println(day4.numIslands2(1, 2, new int[][] {{0,1},{0,0}}));
    }
}

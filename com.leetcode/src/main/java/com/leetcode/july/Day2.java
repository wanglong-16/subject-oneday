package com.leetcode.july;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-02 21:40:59
 * @author: wanglong16@meicai.cn
 */
public class Day2 {

    public int makeConnected(int n, int[][] connections) {
        if (n - 1 > connections.length) {
            return -1;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] con : connections) {
            uf.union(con[0], con[1]);
        }
        return uf.getCount() - 1;
    }

    class UnionFind {

        int count;
        int[] p;

        public UnionFind(int n) {
            p = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
        }

        public int find(int x) {
            return x == p[x] ? x : (p[x] = find(p[x]));
        }

        public void union(int x, int y) {
            int xp = find(x), yp = find(y);
            if (xp != yp) {
                p[xp] = yp;
                count --;
            }
        }

        public int getCount() {
            return count;
        }

    }





    int[][] dir = new int[][] {
            {-1,0},{1,0},{0,-1},{0,1},{1,1},{1,-1},{-1,1},{-1,-1}
    };

    boolean[][] visited;
    int[][] land;
    int rows;
    int cols;

    public int[] pondSizes(int[][] land) {
        this.rows = land.length;
        this.cols = land[0].length;
        this.land = land;
        visited = new boolean[rows][cols];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && land[i][j] == 0) {
                    int la = dfs(i, j);
                    list.add(la);
                }
            }
        }
        list.sort(Comparator.comparingInt(a -> a));
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public int dfs(int r, int c) {
        int la = 1;
        visited[r][c] = true;
        for (int [] d : dir) {
            int nr = r + d[0], nc = c + d[1];
            if (inland(nr, nc) && !visited[nr][nc] && land[nr][nc] == 0) {
               la += dfs(nr, nc);
            }
        }
        return la;
    }

    public boolean inland(int r, int c) {
        return r >=0 && c >= 0 && r < rows && c < cols;
    }

}

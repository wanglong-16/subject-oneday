package com.leetcode.july;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Queue;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-12 21:16:24
 * @author: wanglong16@meicai.cn
 */
public class Day12 {

    // todo find the bug
    public int minimumCost(int n, int[][] connections) {
        Arrays.sort(connections, Comparator.comparingInt(a -> a[2]));
        UFSet ufSet = new UFSet(n);
        int cost = 0, cnt = 0;
        for (int[] conn : connections) {
            if (ufSet.merge(conn[0] - 1, conn[1] - 1)) {
                cost += conn[2];
                cnt ++;
            }
            if (cnt == n - 1) {
                return cost;
            }
        }
        return -1;
    }

    class UFSet {

        int[] p;
        public UFSet(int n) {
            p = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
        }

        public int find(int x) {
            if (x == p[x]) {
                return x;
            }
            return find(p[x]);
        }

        public boolean merge(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) {
                return false;
            }
            p[x] = ry;
            return true;
        }
    }

    class Solution {

        public int minimumCost(int n, int[][] connections) {
            Arrays.sort(connections, Comparator.comparingInt(a -> a[2]));
            int[] parent = new int[n+1];
            for (int i = 1; i <= n; ++i) {
                parent[i] = i;
            }
            int cost = 0, cnt = 0;
            for (int[] edge : connections) {
                if (union(edge[0], edge[1], parent)) {
                    cost += edge[2];
                    cnt ++;
                }
                if (cnt == n - 1) {
                    return cost;
                }
            }
            return -1;
        }

        public int findRoot(int x, int[] parent) {
            return x == parent[x] ? x : (parent[x] = findRoot(parent[x], parent));
        }

        public boolean union(int a, int b, int[] parent) {
            a = findRoot(a, parent);
            b = findRoot(b, parent);
            if (a == b) {
                return false;
            }
            parent[a] = b;
            return true;
        }
    }



    public int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length, cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(entrance);
        visited[entrance[0]][entrance[1]] = true;
        int [][] dirs = new int[][] {{-1,0}, {1, 0}, {0, -1}, {0, 1}};
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans ++;
            for (int i = 0; i < size; i++) {
                int[] next = queue.poll();
                for (int [] dir : dirs) {
                    int nr = next[0] + dir[0], nc = next[1] + dir[1];
                    if ((nr >= 0 && nr < rows && nc >= 0 && nc < cols) && !visited[nr][nc]
                             && maze[nr][nc] == '.') {
                        if (nr == 0 || nc == 0 || nr == rows - 1 || nc == cols - 1) {
                            return ans;
                        }
                        queue.offer(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Day12 day12 = new Day12();
        System.out.println(day12.nearestExit(new char[][] {{'+','+','+'}, {'.','.','.'}, {'+','+','+'}, }, new int[] {1, 0}));
    }

}

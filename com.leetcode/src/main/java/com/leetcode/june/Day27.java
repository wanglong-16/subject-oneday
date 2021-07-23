package com.leetcode.june;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-29 22:21:52
 * @author: wanglong16@meicai.cn
 */
public class Day27 {

    private int parents[];
    public int countComponents(int n, int[][] edges) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int[] edge : edges) {
            merge(edge[0], edge[1]);
        }
        int ans = 0;
        for (int i = 0; i < parents.length; i++) {
            if (i == parents[i]) {
                ans ++;
            }
        }
        return ans;
    }

    public int find(int x) {
        return x == find(x) ? x : (parents[x] = find(parents[x]));
    }

    public void merge(int x, int y) {
        if (x != y) {
            parents[find(x)] = find(y);
        }
    }

    
    
    private boolean visited[][];
    
    private int[][] dir = new int[][] {{1,0}, {-1,0}, {0,1}, {0, -1}};

    private List<List<int[]>> isLands = new ArrayList<>();

    public int numDistinctIslands(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    List<int[]> island = new ArrayList<>();
                    dfs(island, grid, i, j);
                    isLands.add(island);
                }
            }
        }
        Set<String> islandStrings = new HashSet<>();
        for (List<int[]> island : isLands) {
            islandStrings.add(convertIslandToStr(island));
        }
        return islandStrings.size();
    }

    public int numDistinctIslands2(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    List<int[]> island = new ArrayList<>();
                    dfs(island, grid, i, j);
                    isLands.add(island);
                }
            }
        }
        Set<String> islandStrings = new HashSet<>();
        for (List<int[]> island : isLands) {
            islandStrings.add(convertIslandToStr2(island));
        }
        return islandStrings.size();
    }


    
    private List<int[]> bfs(int[][] grid, int r, int c) {
        List<int[]> island = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {r,c});
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            island.add(point);
            for (int[] d : dir) {
                int nr = point[0] + d[0], nc = point[1] + d[1];
                if (inGrid(nr, nc, grid) && !visited[nr][nc] && grid[nr][nc] == 1) {
                    queue.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        return island;
    }

    private void dfs(List<int[]> island, int[][] grid, int r, int c) {
        island.add(new int[] {r, c});
        visited[r][c] = true;
        for (int[] d : dir) {
            int nr = r + d[0], nc = c + d[1];
            if (inGrid(nr, nc, grid) && grid[nr][nc] == 1 && !visited[nr][nc]) {
                dfs(island, grid, nr, nc);
            }
        }
    }
    
    public boolean inGrid(int r, int c, int[][] grid) {
        return r >= 0 && c >= 0 && r < grid.length && c < grid[0].length;
    }
    
    public String convertIslandToStr(List<int[]> island) {
        island.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int headR = island.get(0)[0], headC = island.get(0)[1];
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < island.size(); i++) {
            ans.append(island.get(i)[0] - headR).append(island.get(i)[1] - headC);
        }
        return ans.toString();
    }

    public String convertIslandToStr2(List<int[]> island) {
        // 找到最小坐标点
        int headR = Integer.MAX_VALUE, headC = Integer.MAX_VALUE;
        for (int[] i : island) {
            headR = Math.min(headR, i[0]);
            headC = Math.min(headC, i[1]);
        }
        List<List<int[]>> islandList = new ArrayList<>();
        int[][] dire = new int[][] {{-1, 1}, {1, -1}, {1, 1}, {-1, -1}};
        for (int[] d : dire) {
            List<int[]> positive = new ArrayList<>();
            List<int[]> negative = new ArrayList<>();
            for (int[] point : island) {
                int r = (point[0] - headR) * d[0], c = (point[1] - headC) * d[1];
                positive.add(new int[] {r, c});
                negative.add(new int[] {c, r});
            }
            positive.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            negative.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            islandList.add(positive);
            islandList.add(negative);
        }
        islandList.sort(Comparator.comparing(List::toString));
        return islandList.get(0).toString();
    }

    public static void main(String[] args) {
        Day27 day27 = new Day27();
        int[][] grid = new int[][] {{1,1,0,1,1},{1,0,0,0,0},{0,0,0,0,1},{1,1,0,1,1}};
        System.out.println(day27.numDistinctIslands2(grid));
    }


}

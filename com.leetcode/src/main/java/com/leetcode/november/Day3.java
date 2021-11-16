package com.leetcode.november;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-11-06 18:28:16
 * @author: wanglong16@meicai.cn
 */
public class Day3 {

    /**
     * 101 011 010
     */
    public int missingNumber(int[] nums) {
        int ans = nums.length;
        for (int i = 0; i < nums.length; i++) {
            ans ^= i ^ nums[i];
        }
        return ans;
    }

    /**
     *  2062
     * @param word
     * @return
     */
    public int countVowelSubstrings(String word) {
        if (word.length() < 5) {
            return 0;
        }
        int cnt = 0;
        List<Character> list = Arrays.asList('a', 'e', 'i', 'o', 'u');
        for (int i = 0; i < word.length(); i++) {
            boolean flag = false;
            for (int j = i; j < Math.min(i + 5, word.length()); j++) {
                if (!list.contains(word.charAt(j))) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }
            for (int j = i + 5; j <= word.length(); j++) {
                String sustr = word.substring(i, j);
                if (sustr.contains("a") &&
                        sustr.contains("e") &&
                        sustr.contains("i") &&
                        sustr.contains("o") &&
                        sustr.contains("u")) {
                    ++cnt;
                }
                if (j != word.length() && !list.contains(word.charAt(j))) {
                    break;
                }
            }
        }
        return cnt;
    }

    // 2063
    public long countVowels(String word) {
        long cnt = 0;
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        for (int i = 0; i < word.length(); i++) {
            if (set.contains(word.charAt(i))) {
                cnt += (long)(i + 1) * (word.length() - i);
            }
        }
        return cnt;
    }

    public int maxCount(int m, int n, int[][] ops) {
        for (int[] op : ops) {
            m = Math.min(op[0], m);
            n = Math.min(op[1], n);
        }
        return m * n;
    }

    private int rows, cols;

    List<int[]> Dirs = Arrays.asList(
            new int[] {1, 0}, new int[] {-1, 0}, new int[] {0, 1}, new int[] {0, -1}
    );

    public int[][] updateMatrix(int[][] mat) {
        rows = mat.length;
        cols = mat[0].length;
        int[][] ans = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1) {
                    List<int[]> dirsCopy = new ArrayList<>(Dirs);
                    if (j == 0) {
                        dirsCopy.remove(3);
                    }
                    if (j == cols - 1) {
                        dirsCopy.remove(2);
                    }
                    if (i == 0) {
                        dirsCopy.remove(1);
                    }
                    if (i == rows - 1) {
                        dirsCopy.remove(0);
                    }
                    ans[i][j] = bfs(mat, i, j, dirsCopy);
                }
            }
        }
        return ans;
    }


    public int bfs(int[][] grid, int row, int col, List<int[]> dirs) {
        boolean[][] vis = new boolean[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {row, col});
        vis[row][col] = true;
        int minDistance = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                for (int[] dir : dirs) {
                    int cr = dir[0] + current[0];
                    int cc = dir[1] + current[1];
                    if ((cr >= 0 && cr < rows && cc >= 0 && cc < cols)) {
                        if (grid[cr][cc] == 0) {
                            return minDistance;
                        }
                        if (!vis[cr][cc]) {
                            queue.offer(new int[] {cr, cc});
                            vis[cr][cc] = true;
                        }
                    }
                }
            }
            ++ minDistance;
        }
        return minDistance;
    }

    static class Solution {
        static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int[][] updateMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int[][] dist = new int[m][n];
            boolean[][] seen = new boolean[m][n];
            Queue<int[]> queue = new LinkedList<int[]>();
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (matrix[i][j] == 0) {
                        queue.offer(new int[]{i, j});
                        seen[i][j] = true;
                    }
                }
            }

            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int i = cell[0], j = cell[1];
                for (int d = 0; d < 4; ++d) {
                    int ni = i + dirs[d][0];
                    int nj = j + dirs[d][1];
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n && !seen[ni][nj]) {
                        dist[ni][nj] = dist[i][j] + 1;
                        queue.offer(new int[]{ni, nj});
                        seen[ni][nj] = true;
                    }
                }
            }

            return dist;
        }
    }


    public static void main(String[] args) {
        Day3 day3 = new Day3();
        System.out.println(Arrays.deepToString(day3.updateMatrix(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        })));

        //[[0,0,0],[0,1,0],[1,1,1]]
    }









}

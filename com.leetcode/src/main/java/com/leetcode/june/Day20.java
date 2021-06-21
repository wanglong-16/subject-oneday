package com.leetcode.june;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-20 10:03:10
 * @author: wanglong16@meicai.cn
 */
public class Day20 {

    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            int n = (int) (num.charAt(i) - '0');
            if (n % 2 == 1) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }


    public int numberOfRounds(String startTime, String finishTime) {
        int stMins = convertToMinutes(startTime);
        int finMins = convertToMinutes(finishTime);
        int ans = 0;
        if (stMins <= finMins) {
            return countTimes(stMins, finMins);
        } else {
            return countTimes(stMins, 60 * 24) + countTimes(0, finMins);
        }
    }

    private int convertToMinutes(String time) {
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int minutes = Integer.parseInt(times[1]);
        return hour * 60 + minutes;
    }

    public int countTimes(int start, int end) {
        int ans = 0;
        for (int i = 0; i <= 60 * 24 - 15; i += 15) {
            if (start <= i && end >= i + 15) {
                ans++;
            }
        }
        return ans;
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int ans = 0;
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[0].length; j++) {
                if (grid2[i][j] == 1) {
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.add(new int[]{i, j});
                    List<int[]> dy = new ArrayList<>();
                    dy.add(new int[]{i, j});
                    boolean find = true;
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        grid2[cur[0]][cur[1]] = 0;
                        if (grid1[cur[0]][cur[1]] != 1) {
                            find = false;
                        }
                        if (cur[0] < grid2.length - 1 && grid2[cur[0] + 1][cur[1]] == 1) {
                            queue.add(new int[]{cur[0] + 1, cur[1]});
                            dy.add(new int[]{cur[0] + 1, cur[1]});
                        }
                        if (cur[1] < grid2[0].length - 1 && grid2[cur[0]][cur[1] + 1] == 1) {
                            queue.add(new int[]{cur[0], cur[1] + 1});
                            dy.add(new int[]{cur[0], cur[1] + 1});
                        }
                        if (cur[0] >= 1 && grid2[cur[0] - 1][cur[1]] == 1) {
                            queue.add(new int[]{cur[0] - 1, cur[1]});
                            dy.add(new int[]{cur[0] - 1, cur[1]});
                        }
                        if (cur[1] >= 1 && grid2[cur[0]][cur[1] - 1] == 1) {
                            queue.add(new int[]{cur[0], cur[1] - 1});
                            dy.add(new int[]{cur[0], cur[1] - 1});
                        }
                    }
                    if (find) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }


    class Ponit {
        int r;
        int c;

        public Ponit(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    class Solution {
        ArrayList<ArrayList<Node>> island = new ArrayList<>();

        public int countSubIslands(int[][] grid1, int[][] grid2) {
            int n = grid2.length;
            int m = grid2[0].length;
            boolean[][] visited = new boolean[n][m];

            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    if (!visited[x][y] && grid2[x][y] == 1) {
                        ArrayList<Node> src = new ArrayList<>();
                        dfs(x, y, visited, grid2, src);
                        island.add(src);
                    }
                }
            }

            //求解ans,“每一行”存储着岛屿信息
            int ans = 0;
            for (ArrayList<Node> tlist : island) {
                int i = 0;
                for (; i < tlist.size(); ++i) {
                    Node t = tlist.get(i);
                    //在grid1中对用0,说明不是子岛屿
                    if (grid1[t.x][t.y] == 0) {
                        break;
                    }
                }
                if (i == tlist.size()) {
                    ans++;
                }
            }

            return ans;
        }

        public void dfs(int x, int y, boolean[][] visited, int[][] grid, ArrayList<Node> src) {
            int n = grid.length;
            int m = grid[0].length;
            if ((x >= 0 && x < n) && (y >= 0 && y < m) && !visited[x][y] && grid[x][y] == 1) {
                visited[x][y] = true;
                Node t = new Node(x, y);
                src.add(t);
                dfs(x + 1, y, visited, grid, src);
                dfs(x - 1, y, visited, grid, src);
                dfs(x, y + 1, visited, grid, src);
                dfs(x, y - 1, visited, grid, src);
            }
        }
    }

    class Node {
        public int x;
        public int y;

        public Node(int i, int j) {
            x = i;
            y = j;
        }
    }

    //[[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
//输出：3
    public static void main(String[] args) {
        Day20 day20 = new Day20();
        int[][] grid1 = new int[][]{
                {1, 1, 1, 0, 0}, {0, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 1, 1}
        };
        int[][] grid2 = new int[][]{
                {1, 1, 1, 0, 0}, {0, 0, 1, 1, 1}, {0, 1, 0, 0, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}
        };
        System.out.println(day20.countSubIslands(grid1, grid2));
    }
}

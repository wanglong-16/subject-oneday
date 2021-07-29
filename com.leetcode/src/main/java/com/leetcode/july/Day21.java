package com.leetcode.july;

import com.sun.tools.corba.se.idl.StringGen;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-23 22:23:06
 * @author: wanglong16@meicai.cn
 */
public class Day21 {

    class MapSum {

        Map<String, Integer> map = new HashMap<>();

        /** Initialize your data structure here. */
        public MapSum() {
        }

        public void insert(String key, int val) {
            map.put(key, val);
        }

        public int sum(String prefix) {
            int ans = 0;
            for (String str : map.keySet()) {
                if (str.indexOf(prefix) == 0) {
                    ans += map.get(str);
                }
            }
            return ans;
        }
    }

    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int[][] ans = new int[rowSum.length][colSum.length];
        for (int i = 0; i < rowSum.length; i++) {
            for (int j = 0; j < colSum.length; j++) {
                int min = Math.min(rowSum[i], colSum[j]);
                ans[i][j] = min;
                rowSum[i] -= min;
                colSum[j] -= min;
            }
        }
        return ans;
    }


    Set<List<Integer>> arr = new HashSet<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        backTrack(nums, 0, new ArrayList<>());
        return new ArrayList<>(arr);
    }

    public void backTrack(int[] nums, int index, List<Integer> temp) {
        if (index == nums.length) {
            arr.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[index]);
        backTrack(nums, index + 1, temp);
        temp.remove(temp.size() - 1);
        backTrack(nums, index + 1, temp);
    }

    public String maximumTime(String time) {
        StringBuilder sb = new StringBuilder();
        sb.append(time.charAt(0) != '?' ? time.charAt(0) : (
                (time.charAt(1) != '?' && time.charAt(1) >= 4) ? '1' : '2'
        )).append(time.charAt(1) != '?' ? time.charAt(1) : (sb.charAt(0) == '2' ? '3' : '9'))
        .append(':').append(time.charAt(3) == '?' ? '5' : time.charAt(3)).
        append(time.charAt(4) == '?' ? '9' : time.charAt(4));
        return sb.toString();
    }


    public int numRookCaptures(char[][] board) {
        int rows = board.length, cols = board[0].length;
        int Rr = 0, Rc = 0;
        for (int i = 0; i < rows; i++) {
            boolean find = false;
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'R') {
                    Rr = i;
                    Rc = j;
                    find = true;
                    break;
                }
            }
            if (find) {
                break;
            }
        }
        int ans = 0;
        for (int i = Rr + 1; i < rows; i++) {
            if (board[i][Rc] != '.') {
                if (board[i][Rc] != 'p') {
                    ans ++;
                }
                break;
            }
        }
        for (int i = Rr - 1; i >= 0; i--) {
            if (board[i][Rc] != '.') {
                if (board[i][Rc] != 'p') {
                    ans ++;
                }
                break;
            }
        }
        for (int i = Rc + 1; i < cols; i++) {
            if (board[Rr][i] != '.') {
                if (board[i][Rc] != 'p') {
                    ans ++;
                }
                break;
            }
        }
        for (int i = Rc - 1; i >= 0; i--) {
            if (board[Rr][i] != '.') {
                if (board[i][Rc] != 'p') {
                    ans ++;
                }
                break;
            }
        }
        return ans;
    }

    int[][] dir = new int[][] {{-1,0}, {1,0}, {0, -1}, {0,1}};
    int rows;
    int cols;
    public int surfaceArea(int[][] grid) {
        int ans = 0;
        this.rows = grid.length;
        this.cols = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                ans += grid[i][j] > 0 ? 4 * grid[i][j] + 2 : 0;
                ans -= calculateDecr(i, j, grid);
            }
        }
        return ans;
    }

    public int calculateDecr(int r, int c, int[][] grid) {
        int height = grid[r][c], ans = 0;
        for (int[] d : dir) {
            int nr = r + d[0], nc = c + d[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                ans += Math.min(grid[nr][nc], height) * 2;
            }
        }
        return ans;
    }

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int a = 0, b = 0, ans = 1;
        for (int i = 2; i <= n; i++) {
            a = b;
            b = ans;
            ans = a + b;
        }
        return ans;
    }

    public int climbStairs(int n) {
        int[] ans = new int[n + 1];
        ans[1] = 1;
        ans[2] = 2;
        for (int i = 3; i <= n; i++) {
            ans[i] = ans[i - 1] + ans[i - 2];
        }
        return ans[n];
    }

    public int tribonacci(int n) {
        if (n < 2) {
            return n;
        }
        if (n == 2) {
            return 1;
        }
        return tribonacci(n - 3) + tribonacci(n - 2) + tribonacci( n - 1);
    }

    public int tribonacciV1(int n) {
        if (n < 2) {
            return n;
        }
        if (n == 2) {
            return 1;
        }
        int p = 0, q = 1, r = 1;
        for (int i = 3; i <= n; i++) {
            int temp = p + q + r;
            p = q;
            q = r;
            r = temp;
        }
        return r;
    }


    public static void main(String[] args) {
        Day21 day21 = new Day21();
        System.out.println(day21.surfaceArea(new int[][] {{1,0}, {0, 2}}));
    }

}

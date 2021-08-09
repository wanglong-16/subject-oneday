package com.leetcode.august;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-07 21:31:16
 * @author: wanglong16@meicai.cn
 */
public class Day5 {

    int[][] grid;

    public String makeFancyString(String s) {
        StringBuilder ans = new StringBuilder();
        Character pre = s.charAt(0);
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == pre) {
                count ++;
            } else {
                count = 1;
                pre = s.charAt(i);
            }
            if (count <= 2) {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }
    int[][] dirs = new int[][] {
            {-1,0}, {1,0}, {0,-1}, {0,1}
            ,{-1,1},{-1,-1},{1,-1},{1,1}
    };
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        char diffColor = color == 'W' ? 'B' : 'W';
        for (int[] dir : dirs) {
            int tR = rMove, tC = cMove;
            if (!inGrid(tR + dir[0], tC + dir[1]) || board[tR + dir[0]][tC + dir[1]] == '.'  || board[tR + dir[0]][tC + dir[1]] == color) {
                continue;
            }
            while (inGrid(tR, tC)) {
                tR += dir[0];
                tC += dir[1];
                if (!inGrid(tR, tC) || board[tR][tC] == '.') {
                    break;
                }
                if (board[tR][tC] == diffColor) {
                    continue;
                }
                if (board[tR][tC] == color) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean inGrid(int r, int c) {
        return r >= 0 && c >= 0 && r < 8 && c < 8;
    }



    public int minSpaceWastedKResizing(int[] nums, int k) {
        boolean[] must = new boolean[nums.length];
        int mustCnt = 0;
        int max = nums[0];
        //必须调整的点位
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                must[i] = true;
                mustCnt ++;
            }
        }
        if (k <= mustCnt) {

        }
        return 0;
    }



    public static void main(String[] args) {
        int[][] temp = new int[][] {
                {},{},{}
        };
        char[][] chars = new char[][]
                {{'W','B','W','.','.','W','W','.'},
                        {'W','B','.','W','W','W','B','W'},
                        {'.','B','B','.','B','W','B','W'},
                        {'.','.','B','B','B','W','W','.'},
                        {'B','B','.','B','.','.','.','B'},
                        {'.','W','B','.','.','B','.','B'},
                        {'.','W','.','W','.','W','B','W'},
                        {'W','.','B','.','W','W','B','.'}};

        Day5 day5 = new Day5();
        System.out.println(day5.checkMove(chars, 4, 6, 'W'));
    }
}

package com.leetcode.june;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-23 22:05:20
 * @author: wanglong16@meicai.cn
 */
public class Day23 {

    public int findKthPositive(int[] arr, int k) {
        int lose = 0, expect = 1, index = 0;
        while (index < arr.length) {
            if (arr[index] == expect) {
                expect ++;
                index ++;
            } else {
                lose ++;
                expect ++;
            }
            if (lose == k) {
                return expect;
            }
        }
        return expect + k - lose;
    }

    /**
     * 130. 被围绕的区域
     * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     *
     *
     * 示例 1：
     *
     *
     * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
     * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
     * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     * 示例 2：
     *
     * 输入：board = [["X"]]
     * 输出：[["X"]]
     *
     *
     * 提示：
     *
     * m == board.length
     * n == board[i].length
     * 1 <= m, n <= 200
     * board[i][j] 为 'X' 或 'O'
     * @param board
     */
    int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void solve(char[][] board) {
        //从边界出发找到O，并以此出发广度优先搜索标记为边界 B
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if (board[i][board[0].length - 1] == 'O') {
                dfs(board, i, board[0].length - 1);
            }
        }
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'O') {
                dfs(board, 0, i);
            }
            if (board[board.length - 1][i] == 'O') {
                dfs(board, board.length - 1, i);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int row, int col) {
        board[row][col] = 'B';
        for (int[] d : dir) {
            if (row + d[0] >= 0 && row + d[0] < board.length &&
                    col + d[1] >= 0 && col + d[1] < board[0].length &&
                    board[row + d[0]][col + d[1]] == 'O') {
                dfs(board, row + d[0], col + d[1]);
            }
        }
    }

}

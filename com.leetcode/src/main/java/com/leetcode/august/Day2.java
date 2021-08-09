package com.leetcode.august;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-04 08:37:46
 * @author: wanglong16@meicai.cn
 */
public class Day2 {

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int x = nums[i], y = nums[j], z = nums[k];
                    if (x + y > z) {
                        ans ++;
                    }
                }
            }
        }
        return ans;
    }

    public boolean isTriangle(int x, int y, int z) {
        return x + y > z && x + z > y && y + z > x;
    }



    public int findCircleNum(int[][] isConnected) {
        parents = new int[isConnected.length];
        rank = new int[isConnected.length];
        count = isConnected.length;
        for (int i = 0; i < isConnected.length; i++) {
            parents[i] = i;
            rank[i] = 1;
        }
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected.length; j++) {
                if (i != j && isConnected[i][j] == 1) {
                    merge(i, j);
                }
            }
        }
        return count;
    }

    int[] parents;
    int[] rank;
    int count;


    public int find(int x) {
        return x == parents[x] ? x : (parents[x] = find(parents[x]));
    }

    public void merge(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx != ry) {
            if (rank[rx] <= rank[ry]) {
                parents[rx] = ry;
                if (rank[rx] == rank[ry]) {
                    rank[ry] ++;
                }
            } else {
                parents[ry] = rx;
            }
            count --;
        }
    }




    class Trie {

        /** Initialize your data structure here. */
        public Trie() {
            head = new Node();
        }

        private Node head;

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node node = head;
            for (Character c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new Node();
                }
                node = node.children[c - 'a'];
            }
            node.isWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node node = head;
            for (Character c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    return false;
                }
                node = node.children[c - 'a'];
            }
            return node.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Node node = head;
            for (Character c : prefix.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    return false;
                }
                node = node.children[c - 'a'];
            }
            return true;
        }



        class Node {
            Node[] children;
            boolean isWord = false;

            public Node() {
                this.children = new Node[26];
            }
        }

    }

    //   1 4 5 7
    //1    3 4 6
    //4  3   1 3
    //5  4 1   2
    //7  6 3 2

    List<int[]> island = new ArrayList<>();
    int rows;
    int cols;
    boolean[][] visited;
    int[][] grid;
    int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int cnt = 0;

    public int maxAreaOfIsland(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j);
                    ans = Math.max(ans, cnt);
                    cnt = 0;
                }
            }
        }
        return ans;
    }

    public void dfs(int row, int col) {
        this.cnt ++;
        visited[row][col] = true;
        for (int[] dir : dirs) {
            int nr = row + dir[0], nc = col + dir[1];
            if (inGrid(nr, nc) && !visited[nr][nc] && grid[nr][nc] == 1) {
                dfs(nr, nc);
            }
        }
    }

    public boolean inGrid(int row, int col) {
        return row >= 0 && col >= 0 && row < rows && col < cols;
    }

}

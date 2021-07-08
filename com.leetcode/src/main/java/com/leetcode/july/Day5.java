package com.leetcode.july;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-07 08:54:59
 * @author: wanglong16@meicai.cn
 */
public class Day5 {

    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};
    int[][] grid;
    int N;

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        N = grid.length;

        int index = 2;
        int[] area = new int[N*N + 2];
        for (int r = 0; r < N; ++r) {
            for (int c = 0; c < N; ++c) {
                if (grid[r][c] == 1) {
                    area[index] = dfs(r, c, index++);
                }
            }
        }

        int ans = 0;
        for (int x: area) {
            ans = Math.max(ans, x);
        }
        for (int r = 0; r < N; ++r) {
            for (int c = 0; c < N; ++c) {
                if (grid[r][c] == 0) {
                    Set<Integer> seen = new HashSet();
                    for (Integer move: neighbors(r, c)) {
                        if (grid[move / N][move % N] > 1) {
                            seen.add(grid[move / N][move % N]);
                        }
                    }

                    int bns = 1;
                    for (int i: seen) {
                        bns += area[i];
                    }
                    ans = Math.max(ans, bns);
                }
            }
        }

        return ans;
    }

    public int dfs(int r, int c, int index) {
        int ans = 1;
        grid[r][c] = index;
        for (Integer move: neighbors(r, c)) {
            if (grid[move / N][move % N] == 1) {
                grid[move / N][move % N] = index;
                ans += dfs(move / N, move % N, index);
            }
        }

        return ans;
    }

    public List<Integer> neighbors(int r, int c) {
        List<Integer> ans = new ArrayList();
        for (int k = 0; k < 4; ++k) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (0 <= nr && nr < N && 0 <= nc && nc < N) {
                ans.add(nr * N + nc);
            }
        }

        return ans;
    }

    public int numPairsDivisibleBy60(int[] time) {
        int[] cnt = new int[60];
        for (Integer in : time) {
            cnt[in % 60] ++;
        }
        int ans = 0;
        for (int i = 1; i < 30; i++) {
            if (cnt[i] > 0 && cnt[60 - i] > 0) {
                ans += cnt[i] * cnt[60 - i];
            }
        }
        if (cnt[0] > 1) {
            ans += cnt[0] * (cnt[0] - 1) / 2;
        }
        if (cnt[30] > 1) {
            ans += cnt[30] * (cnt[30] - 1) / 2;
        }
        return ans;
    }

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int[] cntA = new int[26];
        int[] cntB = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            cntA[word1.charAt(i) - 'a'] ++;
            cntB[word2.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < 26; i++) {
            if (cntA[i] > 0 && cntB[i] == 0 || cntA[i] == 0 && cntB[i] > 0) {
                return false;
            }
        }
        Arrays.sort(cntA);
        Arrays.sort(cntB);
        for (int i = 0; i < 26; i++) {
            if (cntA[i] != cntB[i]) {
                return false;
            }
        }
        return true;
    }

}

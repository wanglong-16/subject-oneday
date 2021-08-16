package com.leetcode.august;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-14 21:50:59
 * @author: wanglong16@meicai.cn
 */
public class Day10 {


    public String strWithout3a3b(int a, int b) {
        StringBuilder ans = new StringBuilder();
        while (a > 0 || b > 0) {
            if (a >= b) {
                if (ans.length() >= 2 &&
                        ans.charAt(ans.length() - 2) == 'a'
                        && ans.charAt(ans.length() - 1) == 'a') {
                    ans.append("b");
                    b--;
                } else {
                    ans.append("a");
                    a--;
                }
            } else {
                if (ans.length() >= 2 && ans.charAt(ans.length() - 2) == 'b'
                        && ans.charAt(ans.length() - 1) == 'b') {
                    ans.append("a");
                    a--;
                } else {
                    ans.append("b");
                    b--;
                }
            }
        }
        return ans.toString();
    }

    List<List<Integer>> ans = new ArrayList<>();
    Set<List<Integer>> set = new HashSet<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backTracking(candidates, target, new ArrayList<>());
        return ans;
    }

    public void backTracking(int[] candidates, int cost, List<Integer> temp) {
        if (cost >= 0) {
            if (cost == 0) {
                List<Integer> ele = new ArrayList<>(temp);
                Collections.sort(ele);
                if (set.add(ele)) {
                    ans.add(ele);
                }
            }
            for (int i = 0; i < candidates.length; i++) {
                temp.add(candidates[i]);
                backTracking(candidates, cost - candidates[i], temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    Map<Integer, Integer> cntMap = new HashMap<>();
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int findPathsV1(int m, int n, int maxMove, int startRow, int startColumn) {
        if (m == 1 && n == 1) {
            return 4;
        }
        if (m == 1 || n == 1) {
            for (int i = 0; i < Math.max(m, n); i++) {
                int t = i > 0 && i < Math.max(m, n) - 1 ? 2 : 3;
                cntMap.put(i, t);
            }
        } else {
            for (int i = 0; i < m; i++) {
                int t = i > 0 && i < m - 1 ? 1 : 2;
                cntMap.put(i * n, t);
                cntMap.put((i + 1) * n - 1, t);
            }
            for (int i = 1; i < n - 1; i++) {
                cntMap.put(i, 1);
                cntMap.put((m - 1) * n + i, 1);
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startRow, startColumn});
        long cnt = 0;
        while (maxMove > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cp = queue.poll();
                int val = cp[0] * n + cp[1];
                if (cntMap.containsKey(val)) {
                    cnt += cntMap.get(val);
                }
                for (int[] dir : dirs) {
                    int[] np = new int[]{cp[0] + dir[0], cp[1] + dir[1]};
                    if (np[0] >= 0 && np[0] < m && np[1] >= 0 && np[1] < n) {
                        queue.offer(np);
                    }
                }
            }
            maxMove--;
        }

        return (int) (cnt % 1000000007);
    }

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        final int MOD = 1000000007;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int outCounts = 0;
        int[][][] dp = new int[maxMove + 1][m][n];
        dp[0][startRow][startColumn] = 1;
        for (int i = 0; i < maxMove; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    int count = dp[i][j][k];
                    if (count > 0) {
                        for (int[] direction : directions) {
                            int j1 = j + direction[0], k1 = k + direction[1];
                            if (j1 >= 0 && j1 < m && k1 >= 0 && k1 < n) {
                                dp[i + 1][j1][k1] = (dp[i + 1][j1][k1] + count) % MOD;
                            } else {
                                outCounts = (outCounts + count) % MOD;
                            }
                        }
                    }
                }
            }
        }
        return outCounts;
    }


    public static void main(String[] args) {
        Day10 day10 = new Day10();
        System.out.println(day10.findPaths(1, 1, 5, 0, 0));
    }
}

package com.leetcode.july;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-13 22:12:23
 * @author: wanglong16@meicai.cn
 */
public class Day13 {

    int[] wellCosts;
    int[] p;
    public int minCostToSupplyWaterV1(int n, int[] wells, int[][] pipes) {
        int directlyWellCost = 0;
        this.wellCosts = new int[n + 1];
        this.p = new int[n + 1];
        for (int i = 0; i < n; i++) {
            directlyWellCost += wells[i];
            this.wellCosts[i + 1] = wells[i];
            p[i] = i + 1;
        }

        Arrays.sort(pipes, Comparator.comparingInt(a -> a[2]));
        for (int[] pipe : pipes) {
            int pipeCost = pipe[2];
            int rx = find(pipe[0]), ry = find(pipe[1]);
            if (rx == ry) {
                continue;
            }
            if (wellCosts[rx] > wellCosts[ry] && wellCosts[rx] > pipeCost) {
                p[rx] = ry;
                directlyWellCost += pipeCost - wellCosts[rx];
            } else if (wellCosts[ry] > pipeCost) {
                directlyWellCost += pipeCost - wellCosts[ry];
            }
        }

        return directlyWellCost;
    }


    int[] parent;
    int[] cost;
    public int find(int x) {
        return x == parent[x] ? x : (parent[x] = find(parent[x]));
    }
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        int ans = 0;

        parent = new int[n + 1];
        cost = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            parent[i] = i;
            cost[i] = wells[i - 1];
            ans += wells[i - 1];
        }

        Arrays.sort(pipes, Comparator.comparingInt(o -> o[2]));

        for (int[] edge: pipes) {
            int h1 = edge[0];
            int h2 = edge[1];
            int weight = edge[2];
            int root1 = find(h1);
            int root2 = find(h2);
            if (root1 == root2) {
                continue;
            }
            if (cost[root1] > cost[root2] && cost[root1] > weight) {
                parent[root1] = root2;
                ans += weight - cost[root1];
            } else if (cost[root2] > weight) {
                parent[root2] = root1;
                ans += weight - cost[root2];
            }
        }
        return ans;
    }

    class UFSet {
        int[] p;
        int[] wellCosts;
        public UFSet(int n, int[] wellCosts) {
            p = new int[n + 1];
            this.wellCosts = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                p[i] = i;
                this.wellCosts[i] = wellCosts[i - 1];
            }
        }

        public int find(int x) {
            return x == p[x] ? x : (p[x] = find(p[x]));
        }


        public boolean merge(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) {
                return false;
            } else {
                if (wellCosts[rx] > wellCosts[ry]) {
                    p[rx] = ry;
                } else {
                    p[ry] = rx;
                }
                return true;
            }
        }
    }

    public static void main(String[] args) {
        Day13 day13 = new Day13();
        //5
        //[46012,72474,64965,751,33304]
        //[[2,1,6719],[3,2,75312],[5,3,44918]]
        System.out.println(day13.minCostToSupplyWater(5, new int[] {46012,72474,64965,751,33304}, new int[][] {{2,1,6719},{3,2,75312},{5,3,44918}}));
    }


}

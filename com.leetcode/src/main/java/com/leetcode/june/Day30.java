package com.leetcode.june;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-30 17:49:13
 * @author: wanglong16@meicai.cn
 */
public class Day30 {



    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n);
        int [] ans = new int[] {};
        for (int[] edg : edges) {
            if (uf.find(edg[0]) == uf.find(edg[1])) {
                ans = edg;
                break;
            } else {
                uf.union(edg[0], edg[1]);
            }
        }
        return ans;
    }

    class UnionFind {
        private int[] p;

        public UnionFind(int n) {
            p = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                p[i] = i;
            }
        }

        public int find(int x) {
            if (x == p[x]) {
                return x;
            } else {
                return find(p[x]);
            }
        }

        public void union(int x, int y) {
            p[find(x)] = find(y);
        }
    }

}

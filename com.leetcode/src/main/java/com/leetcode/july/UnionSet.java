package com.leetcode.july;

import java.util.Arrays;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-05 22:00:42
 * @author: wanglong16@meicai.cn
 */
public class UnionSet {

    public int removeStones(int[][] stones) {
        int n = stones.length;
        DisjointSetUnion dsu = new DisjointSetUnion();
        for (int i = 0; i < n; i++) {
            dsu.union(stones[i][0], stones[i][1] + 10001);
        }

        return n - dsu.numberOfConnectedComponent();
    }


    class DisjointSetUnion {
        int[] f;
        int[] rank;

        public DisjointSetUnion() {
            f = new int[20001];
            rank = new int[20001];
            Arrays.fill(f, -1);
            Arrays.fill(rank, -1);
        }

        public int find(int x) {
            if (f[x] < 0) {
                f[x] = x;
                rank[x] = 1;
            }
            return f[x] == x ? x : (f[x] = find(f[x]));
        }

        public void union(int x, int y) {
            int fx = find(x), fy = find(y);
            if (fx == fy) {
                return;
            }
            if (rank[fx] < rank[fy]) {
                int temp = fx;
                fx = fy;
                fy = temp;
            }
            rank[fx] += rank[fy];
            f[fy] = fx;
        }

        public int numberOfConnectedComponent() {
            int num = 0;
            for (int i = 0; i < 20000; i++) {
                if (f[i] == i) {
                    num++;
                }
            }
            return num;
        }
    }
}

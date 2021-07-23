package com.leetcode.july;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-01 02:51:01
 * @author: wanglong16@meicai.cn
 */
public class Day1 {

    int[][] relation;
    public int numWays(int n, int[][] relation, int k) {
        this.relation = relation;
        List<Integer> temp = Collections.singletonList(0);
        for (int i = 0; i < k; i++) {
            temp = findNext(temp);
        }
        int ans = 0;
        for (Integer in : temp) {
            if (in == n - 1) {
                ans++;
            }
        }
        return ans;
    }

    public List<Integer> findNext(List<Integer> current) {
        List<Integer> ans = new ArrayList<>();
        for (int[] re : this.relation) {
            if (current.contains(re[0])) {
                ans.add(re[1]);
            }
        }
        return ans;
    }

    
    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        int connect = 0;
        for (int[] edg : edges) {
            if (uf.find(edg[0]) == uf.find(edg[1])) {
                return false;
            } else {
                uf.merge(edg[0], edg[1]);
            }
        }
        return uf.getCount() == 1;
    }

    public int minSwapsCouples(int[] row) {
        int len = row.length;
        UnionFind uf = new UnionFind(len);
        for (int i = 0; i < len / 2; i++) {
            uf.union(row[i * 2], row[i * 2 + 1]);
        }
        return len / 2 - uf.getCnt();
    }
    
    class UnionFind {
        private int[] p;

        private int count;

        public UnionFind(int n) {
            p = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
        }

        public int find(int x) {
            return p[x] == x ? x : (p[x] = find(p[x]));
        }

        public void merge(int x, int y) {
            p[find(x)] = find(y);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            p[rootX] = rootY;
            count--;
        }

        public int getCnt() {
            return count;
        }

        public int getCount() {
            int ans = 0;
            for (int i = 0; i < p.length; i++) {
                if (p[i] == i) {
                    ans ++;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        System.out.println(day1.minSwapsCouples(new int[]{0, 2, 1, 3}));
    }
}

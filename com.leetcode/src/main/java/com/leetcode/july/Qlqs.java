package com.leetcode.july;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-01 17:02:33
 * @author: wanglong16@meicai.cn
 */
public class Qlqs {

    public int minSwapsCouples(int[] row) {
        int len = row.length;
        int N = len / 2;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < len; i += 2) {
            unionFind.merge(row[i] / 2, row[i + 1] / 2);
        }
        return len / 2 - unionFind.getCount();
    }

    private class UnionFindV1 {

        private int[] parent;

        private int count;

        public int getCount() {
            return count;
        }

        public UnionFindV1(int n) {
            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parent[rootX] = rootY;
            count--;
        }
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
        Qlqs qlqs = new Qlqs();
        System.out.println(qlqs.minSwapsCouples(new int[] {0,2,1,3}));
    }

}

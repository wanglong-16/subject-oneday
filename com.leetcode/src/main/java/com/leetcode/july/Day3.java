package com.leetcode.july;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-03 21:54:19
 * @author: wanglong16@meicai.cn
 */
public class Day3 {

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        List<CharCnt> charCnts = new ArrayList<>();
        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            charCnts.add(new CharCnt(e.getKey(), e.getValue()));
        }
        charCnts.sort((a, b) -> (b.getCnt() - a.getCnt()));
        StringBuilder ans = new StringBuilder();
        for (CharCnt cnt : charCnts) {
            for (int i = 0; i < cnt.getCnt(); i++) {
                ans.append(cnt.getC());
            }
        }
        return ans.toString();
    }

    class CharCnt {
        private Character c;
        private int cnt;

        public CharCnt(Character c, int cnt) {
            this.c = c;
            this.cnt = cnt;
        }

        public Character getC() {
            return c;
        }

        public void setC(Character c) {
            this.c = c;
        }

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }
    }

    public int removeStones(int[][] stones) {
        int n = stones.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    uf.merge(i, j);
                }
            }
        }
        return n - uf.getCount();
    }

    class UnionFind {
        int count;

        int[] p;

        public UnionFind(int n) {
            p = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
            count = n;
        }

        public int find(int x) {
            return p[x] == x ? x : (p[x] = find(p[x]));
        }

        public void merge(int x, int y) {
            int xp = find(x), yp = find(y);
            if (xp != yp) {
                p[xp] = yp;
                count --;
            }
        }

        public int getCount() {
            return count;
        }
    }


    public int eliminateMaximum(int[] dist, int[] speed) {
        int[] times = new int[dist.length];
        for (int i = 0; i < dist.length; i++) {
            times[i] = (int)Math.ceil((double) dist[i] / (double) speed[i]);
        }
        Arrays.sort(times);
        int ans = 0;
        boolean find = true;
        for (int i = 0; i < times.length - 1; i++) {
            if (times[i + 1] == times[i]) {
                find = false;
                break;
            }
            ans++;
        }
        ans = find ? times.length : ans;
        return ans;
    }

    public static void main(String[] args) {
        Day3 day3 = new Day3();
        int[][] stones = new int[][] {
                {0,0},{0,1},{1,0},{1,2},{2,1},{2,2}
        };
        System.out.println(day3.removeStones(stones));
    }


}

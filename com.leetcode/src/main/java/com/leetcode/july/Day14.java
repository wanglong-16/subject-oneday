package com.leetcode.july;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-13 23:44:07
 * @author: wanglong16@meicai.cn
 */
public class Day14 {


    public int minCostConnectPoints(int[][] points) {
        List<Edge> edges = new ArrayList<>();
        int n = points.length;
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                edges.add(new Edge(i, j, calculateDistance(points[i], points[j])));
            }
        }
        edges.sort(Comparator.comparingInt(Edge::getDistance));
        int ans = 0;
        for (Edge edge : edges) {
            if (merge(edge.getX(), edge.getY())) {
                ans += edge.getDistance();
                n--;
            }
            if (n == 1) {
                return ans;
            }
        }
        return -1;
    }


    public int calculateDistance(int[] x, int[] y) {
        return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
    }

    class Edge {
        int x;
        int y;
        int distance;

        public Edge(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }


    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        parents = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            parents[i] = i;
        }
        for (int[] swap : allowedSwaps) {
            merge(swap[0], swap[1]);
        }
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < parents.length; i++) {
            Integer root = find(i);
            //同一个联通分量里，source的数就视为+1，target的数就视为-1
            if (map.containsKey(root)) {
                //source，视为+1
                map.get(root).put(source[i], map.get(root).getOrDefault(source[i], 0) + 1);
                //target，视为-1
                map.get(root).put(target[i], map.get(root).getOrDefault(target[i], 0) - 1);
            } else {
                Map<Integer, Integer> tmp = new HashMap<>();
                //source，视为+1
                tmp.put(source[i], 1);
                //target，视为-1
                tmp.put(target[i], tmp.getOrDefault(target[i], 0) - 1);
                map.put(root, tmp);
            }
        }
        int ans = 0;
        for (int key : map.keySet()) {
            Map<Integer, Integer> tmp = map.get(key);
            for (int kk : tmp.keySet()) {
                int tt = tmp.get(kk);
                //不妨只累加正数的数量
                if (tt > 0) {
                    ans += tt;
                }
            }
        }
        return ans;
    }


    int[] parents;

    public int find(int x) {
        return x == parents[x] ? x : (parents[x] = find(parents[x]));
    }

    public boolean merge(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx == ry) {
            return false;
        } else {
            parents[rx] = ry;
            return true;
        }
    }



    public static void main(String[] args) {
        Day14 day14 = new Day14();
        System.out.println(day14.minimumHammingDistance(new int[]{1, 2, 3, 4}, new int[]{2, 1, 4, 5}, new int[][]{{0, 1}, {2, 3}}));
    }


}

package com.leetcode.august;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-19 22:33:46
 * @author: wanglong16@meicai.cn
 */
public class Day13 {

    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        int timeA = convertTime(timePoints.get(0));
        int timeB = convertTime(timePoints.get(timePoints.size() - 1));
        int minDiff = Math.min(Math.abs(Math.min(timeA, timeB) + 1440 - Math.max(timeA, timeB)),
                Math.abs(timeA - timeB));
        for (int i = 0; i < timePoints.size() - 1; i++) {
            minDiff = Math.min(minDiff, getDiff(convertTime(timePoints.get(i)), convertTime(timePoints.get(i + 1))));
        }
        return minDiff;
    }

    public int convertTime(String time) {
        String[] tim = time.split(":");
        return Integer.parseInt(tim[0]) * 60 + Integer.parseInt(tim[1]);
    }

    public int getDiff(int a, int b) {
        int diff = Math.abs(a - b);
        if (diff > 720) {
            return Math.abs(Math.min(a, b) + 1440 - Math.max(a, b));
        } else {
            return diff;
        }
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        Stack<Integer> decrStack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!decrStack.empty() && temperatures[i] > temperatures[decrStack.peek()]) {
                ans[decrStack.peek()] = i - decrStack.pop();
            }
            decrStack.push(i);
        }
        return ans;
    }

    public int compress(char[] chars) {
        int start = 0, next = 0, compressStart = 0;
        while (next < chars.length) {
            if (chars[next] != chars[start]) {
                compressStart = getCompressStart(chars, start, next, compressStart);
                start = next;
            }
            next ++;
        }
        compressStart = getCompressStart(chars, start, next, compressStart);
        return compressStart;
    }

    private int getCompressStart(char[] chars, int start, int next, int compressStart) {
        int len = next - start;
        String lenStr = String.valueOf(len);
        chars[compressStart] = chars[start];
        compressStart ++;
        if (len != 1) {
            for (int i = 0; i < lenStr.length(); i++) {
                chars[compressStart++] = lenStr.charAt(i);
            }
        }
        return compressStart;
    }

    public int minTimeToType(String word) {
        char cur = 'a';
        int ans = 0;
        for (char c : word.toCharArray()) {
            int l = Math.min(c, cur), r = Math.max(c, cur);
            int len = Math.min(r - l, l + 26 - r);
            ans += len + 1;
            cur = c;
        }
        return ans;
    }

    public long maxMatrixSum(int[][] matrix) {
        long ans = 0;
        int rows = matrix.length, cols = matrix[0].length;
        int nagCnt = 0, minAbsVal = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] < 0) {
                    nagCnt ++;
                }
                int absV = Math.abs(matrix[i][j]);
                minAbsVal = Math.min(minAbsVal, absV);
                ans += absV;
            }
        }
        if (nagCnt % 2 == 1) {
            return ans - minAbsVal * 2;
        }
        return ans;
    }

    int[] parents;
    int cnt;
    public int find(int x) {
        return x == parents[x] ? x : (parents[x] = find(parents[x]));
    }

    public void merge(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx != ry) {
            parents[x] = parents[y];
            cnt --;
        }
    }

    Map<Integer, List<int[]>> nodes = new HashMap<>();


    public int countPaths(int n, int[][] roads) {
        long _ans = 0;
        for (int[] road : roads) {
            List<int[]> roadM = nodes.getOrDefault(road[0], new ArrayList<>());
            List<int[]> roadN = nodes.getOrDefault(road[1], new ArrayList<>());
            roadM.add(road);
            roadN.add(new int[] {road[1], road[0], road[2]});
            nodes.put(road[0], roadM);
            nodes.put(road[1], roadN);
        }
        int minCost = Integer.MAX_VALUE;
        Queue<int[]> wayNodes = new ArrayDeque<>();
        // 当前节点号，cost
        wayNodes.add(new int[] {0, 0});
        while (!wayNodes.isEmpty()) {
            int size = wayNodes.size();
            for (int i = 0; i < size; i++) {
                int[] curNode = wayNodes.poll();
                int nd = curNode[0];
                int cost = curNode[1];
                if (nd == n - 1) {
                    if (cost < minCost) {
                        minCost = cost;
                        _ans = 0;
                    }
                }
                if (cost <= minCost) {
                    if (cost == minCost) {
                        _ans ++;
                    } else {
                        List<int[]> nextNodes = nodes.get(nd);
                        if (nextNodes != null && !nextNodes.isEmpty()) {
                            for (int[] nxtNode : nextNodes) {
                                if (nxtNode[1] > nd) {
                                    wayNodes.offer(new int[] {nxtNode[1], cost + nxtNode[2]});
                                }
                            }
                        }
                    }
                }
            }
        }
        return (int)_ans % 1000000007;
    }

    class Solution {
        int mod = (int)1e9 + 7;
        public int countPaths(int n, int[][] roads) {
            long inf = (long)1e18;
            Node[] nodes = new Node[n];
            for(int i = 0; i < n; i++){
                nodes[i] = new Node();
                nodes[i].id = i;
                nodes[i].dist = inf;
            }
            for(int[] e : roads){
                Node a = nodes[e[0]];
                Node b = nodes[e[1]];
                Edge edge = new Edge();
                edge.a = a;
                edge.b = b;
                edge.w = e[2];
                edge.a.adj.add(edge);
                edge.b.adj.add(edge);
            }
            nodes[0].dist = 0;
            nodes[0].way = 1;
            TreeSet<Node> pq = new TreeSet<>(Comparator.<Node>comparingLong(x -> x.dist).thenComparingInt(x -> x.id));
            pq.add(nodes[0]);
            while(!pq.isEmpty()){
                Node head = pq.pollFirst();
                for(Edge e : head.adj){
                    Node node = e.other(head);
                    if(node.dist > head.dist + e.w){
                        pq.remove(node);
                        node.dist = head.dist + e.w;
                        pq.add(node);
                        node.way = 0;
                    }

                    if(node.dist == head.dist + e.w){
                        node.way += head.way;
                        node.way %= mod;
                    }
                }
            }

            return nodes[n - 1].way;
        }


    }

    class Edge{
        Node a;
        Node b;
        int w;
        Node other(Node x){
            return a == x ? b : a;
        }
    }

    class Node{
        int id;
        List<Edge> adj = new ArrayList();
        long dist;
        int way;
    }


    public static void main(String[] args) {
        Day13 day13 = new Day13();
        int[][] ma = new int[][] {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};
        System.out.println(day13.compress(new char[] {'a','a','a','b','b','a','a'}));
    }

}

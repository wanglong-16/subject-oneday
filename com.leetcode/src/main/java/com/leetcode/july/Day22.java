package com.leetcode.july;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-24 22:13:28
 * @author: wanglong16@meicai.cn
 */
public class Day22 {


    public boolean areOccurrencesEqual(String s) {
        if (s.length() == 0) {
            return true;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int c = map.get(s.charAt(0));
        for (Map.Entry<Character, Integer> entry : map.entrySet()
             ) {
            if (entry.getValue() != c) {
                return false;
            }
        }
        return true;
    }

    //所有到达时间 互不相同 。
    public int smallestChair(int[][] times, int targetFriend) {
        int[] timeTarget = times[targetFriend];
        int start = timeTarget[0];
        Arrays.sort(times, (a, b) -> (a[0] - b[0]));
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int cur = 1, ans = 0;
        for (int i = 0; i < times.length; i++) {
            if (!priorityQueue.isEmpty() && priorityQueue.peek()[1] <= cur) {
                int[] seat = priorityQueue.poll();
                priorityQueue.offer(new int[] {seat[0], times[i][1], times[i][0]});
            } else {
                priorityQueue.offer(new int[] {i, times[i][1], times[i][0]});
            }
            if (!priorityQueue.isEmpty() && priorityQueue.peek()[2] == start) {
                ans = priorityQueue.peek()[1];
                break;
            }
        }
        //[seatNum, leave]
        return ans;
    }

    public List<List<Long>> splitPainting(int[][] segments) {
        List<List<Long>> ans = new ArrayList<>();
        Arrays.sort(segments, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
        long[] chafenArr = new long[100001];
        boolean[] isBreak = new boolean[100001];
        int start = segments[0][0], end = segments[0][1];

        for (int i = 0; i < segments.length; i ++) {
            int [] arr = segments[i];
            if (arr[0] < end) {
                end = Math.max(arr[1], end);
            } else {
                isBreak[end] = true;
                start = arr[0];
                end = arr[1];
            }
            chafenArr[arr[0]] += arr[2];
            chafenArr[arr[1]] -= arr[2];
        }
        for (int i = 1; i < chafenArr.length; i++) {
            chafenArr[i] += chafenArr[i - 1];
        }
        int cur = 0;
        for (int i = 1; i < chafenArr.length; i++) {
            if (chafenArr[cur] == 0L) {
                cur ++;
            } else {
                if (isBreak[i]) {
                    ans.add(Arrays.asList((long)cur, (long) (i), chafenArr[cur]));
                    cur = i;
                }
                if (chafenArr[i] != chafenArr[cur]) {
                    ans.add(Arrays.asList((long)cur, (long) (i), chafenArr[cur]));
                    cur = i;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    //[[1,4,5],[1,4,7],[4,7,1],[4,7,11]]

        //[[1,7,12]]
        //预期：
        //[[1,4,12],[4,7,12]]
        int[][] grid = new int[][] {
                {1,4,5},
                {1,4,7},
                {4,7,1},
                {4,7,11}
        };

        Day22 day22 = new Day22();
        System.out.println(day22.splitPainting(grid));
    }
}

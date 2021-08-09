package com.leetcode.august;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-06 22:30:19
 * @author: wanglong16@meicai.cn
 */
public class Day4 {

    /**
     * 你被给定一个 m × n 的二维网格 rooms ，网格中有以下三种可能的初始化值：
     *
     * -1 表示墙或是障碍物
     * 0 表示一扇门
     * INF 无限表示一个空的房间。然后，我们用 231 - 1 = 2147483647 代表 INF。你可以认为通往门的距离总是小于 2147483647 的。
     * 你要给每个空房间位上填上该房间到 最近门的距离 ，如果无法到达门，则填 INF 即可。
     *
     */

    final int DOOR = 0;
    final int EMPTY = Integer.MAX_VALUE;
    final int WALL = -1;

    int[][] directions = new int[][] {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    int rows;
    int cols;
    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> doors = new ArrayDeque<>();
        rows = rooms.length;
        cols = rooms[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == DOOR) {
                    doors.add(new int[] {i, j});
                }
            }
        }
        while (!doors.isEmpty()) {
            int[] point = doors.poll();
            for (int[] dir : directions) {
                int r = point[0] + dir[0], c = point[1] + dir[1];
                if (inRooms(r, c) && rooms[r][c] == EMPTY) {
                    rooms[r][c] = rooms[point[0]][point[1]] + 1;
                    doors.add(new int[] {r, c});
                }
            }
        }
    }

    public boolean inRooms(int r, int c) {
        return r >= 0 && c >= 0 && r < rows && c < cols;
    }


    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        int first = Integer.MIN_VALUE, count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == first + 1) {
                count ++;
            } else if (nums[i] == first){
                continue;
            } else {
                max = Math.max(count, max);
                count = 1;
            }
            first = nums[i];
        }
        max = Math.max(count, max);
        return max;
    }

    private int[] parents;
    private int[] distinctNums;
    private int[] rank;

    public int longestConsecutiveV1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (Integer in : nums) {
            set.add(in);
        }
        int ans = 0;
        if (!set.isEmpty()) {
            distinctNums = new int[set.size()];
            parents = new int[set.size()];
            rank = new int[set.size()];
            for (int i = 0; i < set.size(); i++) {
                parents[i] = i;
                rank[i] = 1;
            }

            Iterator<Integer> it = set.iterator();
            int cursor = 0;
            while (it.hasNext()) {
                distinctNums[cursor++] = it.next();
            }
            for (int i = 0; i < set.size(); i++) {
                for (int j = i + 1; j < set.size(); j++) {
                    if (distinctNums[i] - distinctNums[j] == 1 || distinctNums[j] - distinctNums[i] == 1) {
                        merge(i, j);
                    }
                }
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < parents.length; i++) {
                int root = find(i);
                map.put(root, map.getOrDefault(root, 0) + 1);
            }
            for (Integer in : map.values()) {
                ans = Math.max(in, ans);
            }
        }
        return ans;
    }

    public int find(int x) {
        return x == parents[x] ? x : (parents[x] = find(parents[x]));
    }

    public void merge(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx != ry) {
            if (rank[rx] <= rank[ry]) {
                parents[rx] = ry;
                if (rank[rx] == rank[ry]) {
                    rank[ry] ++;
                }
            } else {
                parents[ry] = rx;
            }
        }
    }

    public static void main(String[] args) {
        Day4 day4 = new Day4();
        System.out.println(day4.longestConsecutive(new int[] {9,1,4,7,3,-1,0,5,8,-1,6}));
    }

}

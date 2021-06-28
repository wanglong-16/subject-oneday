package com.leetcode.june;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-27 10:09:17
 * @author: wanglong16@meicai.cn
 */
public class Day25 {


    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length - 1] * nums[nums.length - 2] - nums[0] * nums[1];
    }

    public int[][] rotateGrid(int[][] grid, int k) {
        int rows = grid.length, cols = grid[0].length;
        int circle = Math.min(rows, cols) / 2;//最多转次数
        // 00 11 (cir,cir)
        for (int i = 0; i < circle; i++) {
            rotate(grid, i, k);
        }
        return grid;
    }

    public void rotate(int[][] grid, int cir, int k) {
        int rows = grid.length, cols = grid[0].length;
        List<Integer> queue = new ArrayList<>();
        for (int i = cir; i < rows - cir - 1; i++) {
            queue.add(grid[i][cir]);
        }
        for (int i = cir; i < cols - cir - 1; i++) {
            queue.add(grid[rows - cir - 1][i]);
        }
        for (int i = rows - cir - 1; i > cir; i--) {
            queue.add(grid[i][cols - cir - 1]);
        }
        for (int i = cols - cir - 1; i > cir; i--) {
            queue.add(grid[cir][i]);
        }
        k = queue.size() - (k % queue.size());
        int idx = 0;
        for (int i = cir; i < rows - cir - 1; i++) {
            int tem = queue.get((idx + k) % queue.size());
            grid[i][cir] = tem;
            idx++;
        }
        for (int i = cir; i < cols - cir - 1; i++) {
            int tem = queue.get((idx + k) % queue.size());
            grid[rows - cir - 1][i] = tem;
            idx++;
        }
        for (int i = rows - cir - 1; i > cir; i--) {
            int tem = queue.get((idx + k) % queue.size());
            grid[i][cols - cir - 1] = tem;
            idx++;
        }
        for (int i = cols - cir - 1; i > cir; i--) {
            int tem = queue.get((idx + k) % queue.size());
            grid[cir][i] =  tem;
            idx++;
        }
    }

    public long wonderfulSubstrings(String word) {
        int[] charCnt;
        long ans = 0;
        Set<Character> set;
        boolean wonder = true;
        for (int i = 0; i < word.length(); i++) {
            charCnt = new int[10];
            set = new HashSet<>();
            for (int j = i; j < word.length(); j++) {
                charCnt[word.charAt(j) - 'a'] ++;
                if (charCnt[word.charAt(j) - 'a'] % 2 == 1) {
                    set.add(word.charAt(j));
                } else {
                    set.remove(word.charAt(j));
                }
                wonder = set.size() <= 1;
                if (wonder) {
                    ans ++;
                }
            }

        }
        return ans;
    }

    public boolean checkWonderful(int[] charCnt) {
        int evenCnt = 0;
        for (int i = 0; i < 10; i++) {
            if (charCnt[i] % 2 == 1) {
                evenCnt ++;
            }
            if (evenCnt > 1) {
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {
        Day25 day25 = new Day25();
        int[][] grid = new int[][] {{40,10},{30,2}};
        System.out.println(day25.wonderfulSubstrings("aba"));
    }
}

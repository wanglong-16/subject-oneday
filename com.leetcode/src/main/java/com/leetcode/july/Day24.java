package com.leetcode.july;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-26 22:32:12
 * @author: wanglong16@meicai.cn
 */
public class Day24 {

    int[] parent;
    int[] rank;

    public boolean equationsPossible(String[] equations) {
        List<String> equationsQueue = new ArrayList<>();
        for (String equa : equations) {
            if (equa.charAt(1) == '!') {
                equationsQueue.add(equa);
            } else {
                equationsQueue.add(0, equa);
            }
        }
        parent = new int[26];
        rank = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
        //a==b
        for (String str : equationsQueue) {
            int a = str.charAt(0) - 'a';
            int b = str.charAt(3) - 'a';
            boolean eq = str.charAt(1) == '=';
            // a == b => a 和 b 联通
            if (!eq) {
                if (find(a) == find(b)) {
                    return false;
                }
            } else {
                merge(a, b);
            }
        }
        return true;
    }

    public int find(int x) {
        return x == parent[x] ? x : (parent[x] = find(parent[x]));
    }

    public void merge(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx != ry) {
            if (rank[rx] <= rank[ry]) {
                parent[rx] = ry;
                if (rank[rx] == rank[ry]) {
                    rank[ry] ++;
                }
            } else {
                parent[ry] = rx;
            }
        }
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length <= 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        Day24 day24 = new Day24();
        System.out.println(day24.equationsPossible(new String[] {"a==b","b!=c","c==a"}));
    }

}

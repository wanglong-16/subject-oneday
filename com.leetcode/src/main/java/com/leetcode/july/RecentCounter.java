package com.leetcode.july;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-24 22:17:37
 * @author: wanglong16@meicai.cn
 */
class RecentCounter {

    List<Integer> count = new ArrayList<>();

    int pointer = 0;

    public RecentCounter() {
    }

    public int ping(int t) {
        count.add(t);
        while (count.get(pointer) < t - 3000) {
            pointer ++;
        }
        return count.size() - pointer + 1;
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */

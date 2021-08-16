package com.leetcode.august;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-14 08:47:51
 * @author: wanglong16@meicai.cn
 */
class MovingAverage {

    Queue<Integer> queue = new ArrayDeque<>();
    int total = 0;
    int size;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        if (!queue.isEmpty() && queue.size() == size) {
            total -= queue.poll();
        }
        total += val;
        queue.offer(val);
        return (double)total / queue.size();
    }

}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
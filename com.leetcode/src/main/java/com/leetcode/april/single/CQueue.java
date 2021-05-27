package com.leetcode.april.single;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-04-05 19:56:09
 * @author: wanglong16@meicai.cn
 */
class CQueue {

    private Queue<Integer> queue;
    public CQueue() {
        queue = new ArrayDeque<>();
    }

    public void appendTail(int value) {
        queue.add(value);
    }

    public int deleteHead() {
        if (queue.isEmpty()) {
            return -1;
        }
        return queue.poll();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
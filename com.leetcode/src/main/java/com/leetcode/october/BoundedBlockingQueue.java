package com.leetcode.october;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-06 21:52:19
 * @author: wanglong16@meicai.cn
 */
class BoundedBlockingQueue {

    private final ReentrantLock lock = new ReentrantLock();

    private final Object prod = new Object();
    private final Object consu = new Object();
    Queue<Integer> queue;
    final int full;


    public BoundedBlockingQueue(int capacity) {
        queue = new ArrayDeque<>(capacity);
        full = capacity;
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized (prod) {
            while (queue.size() == full) {
                prod.wait();
            }
            queue.offer(element);
        }
        synchronized (consu) {
            consu.notify();
        }
    }

    public int dequeue() throws InterruptedException {
        synchronized (consu) {
            while (queue.size() == full) {
                consu.wait();
            }
        }
        synchronized (prod) {
            prod.notify();
        }
        return queue.poll();
    }

    public int size() {
        synchronized (prod) {
            return queue.size();
        }
    }
}

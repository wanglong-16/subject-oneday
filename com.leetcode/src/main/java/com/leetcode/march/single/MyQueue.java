package com.leetcode.march.single;

import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-05 09:22:14
 * @author: wanglong16@meicai.cn
 */
class MyQueue {

    Stack<Integer> left;

    Stack<Integer> right;

    /** Initialize your data structure here. */
    public MyQueue() {
        left = new Stack<>();
        right = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        left.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        while (!left.empty()) {
            right.push(left.pop());
        }
        int result = right.pop();
        while (!right.empty()) {
            left.push(right.pop());
        }
        return result;
    }

    /** Get the front element. */
    public int peek() {
        if (left.isEmpty()) {
            return -1;
        }
        return left.get(0);
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return left.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

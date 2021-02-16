package com.leetcode.february;

import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-16 13:28:30
 * @author: wanglong16@meicai.cn
 */
public class MainStackV1 {

    private Stack<Integer> stack;
    private Stack<Integer> minRecord;

    /** initialize your data structure here. */
    public MainStackV1() {
        this.stack = new Stack<>();
        this.minRecord = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if(minRecord.isEmpty() ||minRecord.peek()>=x) {
            minRecord.push(x);
        }
    }

    public void pop() {
        int res = stack.pop();
        if(minRecord.peek()>=res) {
            minRecord.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minRecord.peek();
    }
}

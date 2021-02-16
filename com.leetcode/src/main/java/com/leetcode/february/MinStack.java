package com.leetcode.february;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-16 12:34:34
 * @author: wanglong16@meicai.cn
 */
class MinStack {

    List<Integer> stack;
    int pos;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new ArrayList<>();
        pos = -1;
    }

    public void push(int x) {
        stack.add(x);
        pos ++;
    }

    public void pop() {
        if (pos != -1) {
            stack.remove(pos);
            pos --;
        }
    }

    public int top() {
        if (pos == -1) {
            return 0;
        } else {
            return stack.get(pos);
        }
    }

    public int getMin() {
        if (pos != -1) {
            int min = stack.get(0);
            for (Integer integer : stack) {
                min = Math.min(integer, min);
            }
            return min;
        } else {
            return 0;
        }
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

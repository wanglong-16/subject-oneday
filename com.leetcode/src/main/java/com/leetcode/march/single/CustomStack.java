package com.leetcode.march.single;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * 1381. 设计一个支持增量操作的栈
 * 请你设计一个支持下述操作的栈。
 *
 * 实现自定义栈类 CustomStack ：
 * CustomStack(int maxSize)：用 maxSize 初始化对象，maxSize 是栈中最多能容纳的元素数量，栈在增长到 maxSize 之后则不支持 push 操作。
 * void push(int x)：如果栈还未增长到 maxSize ，就将 x 添加到栈顶。
 * int pop()：弹出栈顶元素，并返回栈顶的值，或栈为空时返回 -1 。
 * void inc(int k, int val)：栈底的 k 个元素的值都增加 val 。如果栈中元素总数小于 k ，则栈中的所有元素都增加 val 。
 * @version: 1.0
 * @date: 2021-03-02 22:16:08
 * @author: wanglong16@meicai.cn
 */
class CustomStack {

    List<Integer> stack;

    int maxSize;

    int headP;

    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        this.headP = -1;
        stack = new ArrayList<>(maxSize);
    }

    public void push(int x) {
        if (headP < maxSize - 1) {
            stack.add(x);
            headP ++;
        }
    }

    public int pop() {
        if (headP > -1) {
            int result = stack.get(headP);
            stack.remove(headP);
            headP --;
            return result;
        } else {
            return -1;
        }
    }

    public void increment(int k, int val) {
        int end = Math.min(headP + 1, k);
        for (int i = 0; i < end; i++) {
            stack.set(i, stack.get(i) + val);
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */

package com.leetcode.june;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-16 22:37:59
 * @author: wanglong16@meicai.cn
 */
class FirstUnique {

    Map<Integer, Integer> map = new HashMap<>();
    Queue<Integer> queue = new LinkedList<>();

    public FirstUnique(int[] nums) {
        for (Integer in : nums) {
            map.put(in, map.getOrDefault(in, 0) + 1);
            queue.add(in);
        }
    }

    public int showFirstUnique() {
        while (!queue.isEmpty()) {
            Integer in = queue.peek();
            if (map.get(in) > 1) {
                queue.poll();
            } else {
                return in;
            }
        }
        return -1;
    }

    public void add(int value) {
        queue.add(value);
        map.put(value, map.getOrDefault(value, 0) + 1);
    }

    //

}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */

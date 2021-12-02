package com.leetcode.november;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-11-25 19:55:16
 * @author: wanglong16@meicai.cn
 */
class S1 {

    int[] source;
    int[] shuffle;
    public S1(int[] nums) {
        source = nums;
        shuffle = new int[nums.length];
    }

    public int[] reset() {
        return source;
    }

    public int[] shuffle() {
        List<Integer> list = new ArrayList<>();
        for (int i : source) {
            list.add(i);
        }

        int i = 0;
        Random r = new Random();
        while (i < source.length) {
            int index = r.nextInt(list.size());
            shuffle[i++] = list.get(index);
            list.remove(index);
        }
        return shuffle;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

package com.leetcode.april.single;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-04-04 17:27:32
 * @author: wanglong16@meicai.cn
 */
class MedianFinder {

    List<Integer> arr;
    /** initialize your data structure here. */
    public MedianFinder() {
        arr = new ArrayList<>();
    }

    public void addNum(int num) {
        arr.add(num);
    }

    public double findMedian() {
        if (!arr.isEmpty()) {
            Collections.sort(arr);
        }
        int size = arr.size();
        if (size % 2 == 0) {
            return (double) arr.get(size / 2);
        } else {
            return (double) (arr.get(size / 2) + arr.get(size / 2 + 1)) / 2;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

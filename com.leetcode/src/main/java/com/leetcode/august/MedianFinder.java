package com.leetcode.august;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-27 08:06:59
 * @author: wanglong16@meicai.cn
 */
class MedianFinder {

    int[] data = new int[100];
    int cursor = -1;

    List<Integer> coll = new ArrayList<>();
    /** initialize your data structure here. */
    public MedianFinder() {

    }

    public void addNum(int num) {
        //data[num] ++;
        coll.add(num);
    }

    public double findMedian() {
        Collections.sort(coll);
        int mid = coll.size() / 2;
        return (coll.size() & 1) == 1 ? coll.get(mid) : coll.get(mid) + coll.get(mid - 1);
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

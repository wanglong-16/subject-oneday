package com.leetcode.june;

import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-04 09:21:09
 * @author: wanglong16@meicai.cn
 */
public class ZigzagIterator {

    int[][] v;
    boolean isV1 = true;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        int row = 2, col = Math.max(v1.size(), v2.size());
        v = new int[row][col];
        for (int i = 0; i < v1.size(); i++) {
            v[0][i] = v1.get(i);
        }
    }
//
//    public int next() {
//        int ans = -1;
//        if (!_v1.isEmpty() || !_v2.isEmpty()) {
//            if (isV1 && !_v1.isEmpty()) {
//                ans = _v1.poll();
//                if (!_v2.isEmpty()) {
//                    isV1 = false;
//                }
//            }
//            if (!isV1 && !_v2.isEmpty()) {
//                ans = _v2.poll();
//                if (!_v1.isEmpty()) {
//                    isV1 = true;
//                }
//            }
//        }
//        return ans;
//    }
//
//    public boolean hasNext() {
//        return !_v1.isEmpty() || !_v2.isEmpty();
//    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */

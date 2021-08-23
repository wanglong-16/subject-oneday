package com.leetcode.august;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-20 23:07:41
 * @author: wanglong16@meicai.cn
 */
public class ZigzagIterator {

    List<Integer> list1, list2;
    boolean one = true;
    int p1 = 0, p2 = 0;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        list1 = new ArrayList<>(v1);
        list2 = new ArrayList<>(v2);
    }

    public int next() {
        int ans = -1;
        if (p2 < list2.size() && p1 < list1.size()) {
            if (one) {
                ans = list1.get(p1);
                p1 ++;
                one = false;
            } else {
                ans = list2.get(p2);
                p2 ++;
                one = true;
            }
        } else {
            if (p1 < list1.size()) {
                ans = list1.get(p1);
                p1 ++;
            }
            if (p2 < list2.size()) {
                ans = list2.get(p2);
                p2 ++;
            }
        }
        return ans;
    }

    public boolean hasNext() {
        return p1 < list1.size() || p2 < list2.size();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */

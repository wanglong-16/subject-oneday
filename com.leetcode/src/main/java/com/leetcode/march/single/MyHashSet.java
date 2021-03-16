package com.leetcode.march.single;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-13 11:37:37
 * @author: wanglong16@meicai.cn
 */
class MyHashSet {

    List<Integer> elements;

    /** Initialize your data structure here. */
    public MyHashSet() {
        elements = new ArrayList<>();
    }

    public void add(int key) {
        if (!elements.contains(key)) {
            elements.add(key);
        }
    }

    public void remove(int key) {
        if (elements.contains(key)) {
            elements.remove((Integer) key);
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return elements.contains(key);
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */

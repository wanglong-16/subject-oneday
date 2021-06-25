package com.leetcode.june;

import java.util.HashMap;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-22 22:36:15
 * @author: wanglong16@meicai.cn
 */
class MyHashMap {

    HashMap<Integer, Integer> map = new HashMap<>();

    /** Initialize your data structure here. */
    public MyHashMap() {

    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        map.put(key, value);
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        return map.getOrDefault(key, -1);
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        map.remove(key);
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */

package com.leetcode.june;


import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-21 22:48:27
 * @author: wanglong16@meicai.cn
 */
class RandomizedSet {

    Map<Integer,Integer> map;
    List<Integer> integers;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        integers = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.get(val) != null) {
            return false;
        } else {
            integers.add(val);
            map.put(val, integers.size() - 1);
            return true;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (map.get(val) == null) {
            return false;
        } else {
            int lastElement = integers.get(integers.size() - 1);
            int idx = map.get(val);
            integers.set(idx, lastElement);
            map.put(lastElement, idx);
            // delete the last element
            integers.remove(integers.size() - 1);
            map.remove(val);
            return true;
        }
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return integers.get(new Random().nextInt(integers.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

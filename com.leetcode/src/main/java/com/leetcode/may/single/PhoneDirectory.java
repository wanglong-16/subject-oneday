package com.leetcode.may.single;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-05-07 00:07:47
 * @author: wanglong16@meicai.cn
 */
public class PhoneDirectory {
    boolean[] p;

    int max;
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        p = new boolean[maxNumbers];
        max = maxNumbers;
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        for (int i = 0; i < max; i++) {
            if (!p[i]) {
                p[i] = true;
                return i;
            }
        }
        return -1;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return number < max && !p[number];
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (number < max) {
            p[number] = false;
        }
    }
}

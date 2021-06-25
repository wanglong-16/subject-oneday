package com.leetcode.june;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-15 21:44:21
 * @author: wanglong16@meicai.cn
 */
class PhoneDirectory {

    int[] phones;
    int cap;

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        phones = new int[maxNumbers];
        cap = maxNumbers;
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        for (int i = 0; i < cap; i++) {
            if (phones[i] != 1) {
                phones[i] = 1;
                return i;
            }
        }
        return -1;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return number < cap && number >= 0 && phones[number] == 0;
    }

    /** Recycle or release a number. */
    public void release(int number) {
        phones[number] = 0;
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
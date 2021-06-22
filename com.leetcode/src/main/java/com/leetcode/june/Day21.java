package com.leetcode.june;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-21 18:34:07
 * @author: wanglong16@meicai.cn
 */
public class Day21 {

    public int minDeletionSizeI(String[] strs) {
        int ans = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) < strs[j - 1].charAt(i)) {
                    ans ++;
                    break;
                }
            }
        }
        return ans;
    }

    public int minDeletionSize(String[] strs) {
        for (int i = 0; i < strs[0].length(); i++) {
            if (isDictSort(strs, i)) {
                return i;
            }
        }
        return strs.length;
    }

    public boolean isDictSort(String[] strs, int index) {
        for (int j = 1; j < strs.length; j++) {
            if (strs[j].charAt(index) < strs[j - 1].charAt(index)) {
                return false;
            }
        }
        return true;
    }

}

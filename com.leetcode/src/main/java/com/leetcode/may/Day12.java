package com.leetcode.may;

import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-05-13 08:25:26
 * @author: wanglong16@meicai.cn
 */
public class Day12 {

    public int minimumTotal(List<List<Integer>> triangle) {
        int  min = Integer.MAX_VALUE, ans = 0;
        for (List<Integer> arr : triangle) {
            for (Integer in : arr) {
                min = Math.min(min, in);
            }
            ans += min;
            min = Integer.MAX_VALUE;
        }
        return ans;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        char[] mags = new char[26];
        for (char c : magazine.toCharArray()) {
            mags[c - 97] ++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (mags[c - 97] == 0) {
                return false;
            } else {
                mags[c - 97] --;
            }
        }
        return true;
    }


}

package com.leetcode.may;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int X = Math.max(0, Math.min(ax2, bx2) - Math.max(ax1, bx1));  //相交长
        int Y = Math.max(0, Math.min(ay2, by2) - Math.max(ax2, bx2));  //相交宽
        return getArea(ax1, ay1, ax2, ay2) - X * Y + getArea(bx1, by1, bx2, by2);
    }

    public int getArea(int a, int b, int c, int d) {
        return Math.abs((c - a) * (d - b));
    }

    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer in : nums) {
            map.merge(in, 1, Integer::sum);
        }
        double divThree = (double)nums.length / 3;
        List<Integer> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > divThree) {
                ans.add(entry.getKey());
            }
        }
        return ans;
    }

    public int subsetXORSum(int[] nums) {
        int maxPosVal = (1 << nums.length), ans = 0;
        for (int i = 0; i < maxPosVal; i++) {
            ans += generateXor(i, nums.length, nums);
        }
        return ans;
    }

    private int generateXor(int posVal, int len, int[] nums) {
        int ans = 0;
        for (int i = len - 1; i >= 0; i--) {
            if ((posVal & 1) == 1) {
                ans = ans ^ nums[i];
            }
            posVal >>= 1;
        }
        return ans;
    }


    public int minSwaps(String s) {
        int zeroCnt = 0, oneCnt = 0;
        if (s.length() == 0 || s.length() == 1) {
            return 0;
        }
        boolean oddZero = true; // 1， 3， 5 。。位置必须是0
        if (s.charAt(0) == '0') {
            oddZero = false; // 1， 3， 5 。。位置必须是1
        }
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                zeroCnt ++;
            } else {
                oneCnt ++;
            }
            if (oddZero && i % 2 == 1 && s.charAt(i) == '1') {
                ans ++;
            }
            if (!oddZero && i % 2 == 1 && s.charAt(i) == '0') {
                ans ++;
            }
        }
        if (zeroCnt == oneCnt) {
            return ans;
        } else if (Math.abs(zeroCnt - oneCnt) == 1) {
            // 100,
            if (zeroCnt > oneCnt && oddZero || zeroCnt < oneCnt && !oddZero) {
                return ans + 1;
            } else {
                return ans;
            }
        } else {
            return -1;
        }
    }

    public int maximumPopulation(int[][] logs) {
        int[] d = new int[110];
        for(int[] log : logs) {     //遍历每个人的出生和死亡年份
            d[log[0] - 1950] += 1;  //出生年份人数+1
            d[log[1] - 1950] -= 1;  //死亡年份人数-1
        }
        int s = 0, res = 0, cnt = 0;
        for(int i = 0; i <= 100; i++){
            s += d[i];      //s是记录每一年的存活人数
            if(s > cnt){
                cnt = s;
                res = i;
            }
        }
        return res + 1950;
    }

}

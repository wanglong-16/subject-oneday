package com.leetcode.may;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-05-23 10:30:16
 * @author: wanglong16@meicai.cn
 */
public class Day22 {


    public boolean checkZeroOnes(String s) {
        int zero = calculate('0', s);
        int one = calculate('1', s);
        return one > zero;
    }

    public int calculate(char c, String s) {
        int max = 0, temp = 0;;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                temp ++;
            } else {
                max = Math.max(temp, max);
                temp = 0;
            }
        }
        max = Math.max(temp, max);
        return max;
    }


    public int minSpeedOnTime(int[] dist, double hour) {
        if (dist.length > hour) {
            return -1;
        }
        boolean arrive = true;
        int speed = dist.length;
        while (arrive) {
            speed --;
            if (speed == 0) {
                return 1;
            }
            arrive = canArrive(dist, speed, hour);
        }
        return speed + 1;
    }

    public boolean canArrive(int[] dist, int speed, double hour) {
        double total = 0;
        for (int i = 0; i < dist.length - 1; i++) {
            if (dist[i] % speed == 0) {
                total += (int) (dist[i] / speed);
            } else {
                total += (int)(dist[i] / speed) + 1;
            }
        }
        return total + (double) (dist[dist.length - 1] / speed) <= hour;
    }


    public int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid % 2 == 1) {
                mid--;
            }
            if (nums[mid] == nums[mid + 1]) {
                lo = mid + 2;
            } else {
                hi = mid;
            }
        }
        return nums[lo];
    }


}

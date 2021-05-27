package com.leetcode.april.single;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-05-01 22:41:17
 * @author: wanglong16@meicai.cn
 */
public class SeatManager {

    boolean[] seats;

    public SeatManager(int n) {
        seats = new boolean[n + 1];
    }

    public int reserve() {
        for (int i = 1; i <= seats.length; i++) {
            if (!seats[i]) {
                seats[i] = true;
                return i;
            }
        }
        return 0;
    }

    public void unreserve(int seatNumber) {
        seats[seatNumber] = false;
    }
}

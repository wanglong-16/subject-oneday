package com.leetcode.october;

import java.util.concurrent.Semaphore;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-06 17:52:32
 * @author: wanglong16@meicai.cn
 */
class Solution {

    private int n;

    private Semaphore odd = new Semaphore(0);
    private Semaphore even = new Semaphore(1);


    public Solution(int n) {
        this.n = n;
    }

    public void printOdd() {
        for (int i = 1; i <= this.n;) {
            try {
                if (i % 2 == 0) {
                    odd.acquire();
                    System.out.println(i);
                    even.release();
                }
                i ++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printEven() {
        for (int i = 0; i <= this.n;) {
            try {
                if (i % 2 == 1) {
                    even.acquire();
                    System.out.println(i);
                    odd.release();
                }
                i ++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Solution s1 = new Solution(5);
        new Thread(() -> {
            s1.printEven();
        }).start();

        new Thread(() -> {
            s1.printOdd();
        }).start();
    }
}

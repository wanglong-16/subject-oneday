package com.leetcode.october;

import java.util.concurrent.Semaphore;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-06 17:01:19
 * @author: wanglong16@meicai.cn
 */
public class Day4 {

    private volatile int n;
    private Semaphore zero = new Semaphore(1);
    private Semaphore even = new Semaphore(0);
    private Semaphore odd = new Semaphore(0);

    public Day4(int n) {
        this.n = n;
    }

    public void printEven() {
        for (int i = 0; i < this.n;) {
            try {
                even.acquire();
                System.out.println(i * 2 + 1);
                i ++;
                odd.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void printOdd() {
        for (int i = 0; i < this.n;) {
            try {
                odd.acquire();
                System.out.println(i * 2 + 2);
                i ++;
                zero.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void printZero() {
        for (int i = 0; i < this.n;) {
            try {
                zero.acquire();
                System.out.println("0");
                i ++;
                even.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Day4 day4 = new Day4(5);
        new Thread(day4::printZero).start();
        new Thread(day4::printEven).start();
        new Thread(day4::printOdd).start();

    }


}

package com.leetcode.october;

import java.util.concurrent.CyclicBarrier;
import java.util.function.IntConsumer;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-06 21:42:28
 * @author: wanglong16@meicai.cn
 */
class FizzBuzz {
    private int n;

    CyclicBarrier cb = new CyclicBarrier(4);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                printFizz.run();
            }
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            if (i % 5 == 0 && i % 3 != 0) {
                printBuzz.run();
            }
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                printFizzBuzz.run();
            }
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i);
            }
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

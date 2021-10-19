package com.leetcode.october;

import java.util.concurrent.Semaphore;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-06 21:24:35
 * @author: wanglong16@meicai.cn
 */
class H2O {

    Semaphore h = new Semaphore(2);
    Semaphore o = new Semaphore(0);


    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        if (h.availablePermits() <= 0) {
            o.release();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        if (o.availablePermits() <= 0) {
            h.release(2);
        }
    }
}

package com.leetcode.january;

import java.util.concurrent.Semaphore;

/**
 * @description: 按序打印
 * @version: 1.0
 * @date: 2021-01-23 20:55:08
 * @author: wanglong16@meicai.cn
 */
class Foo {

    public Foo() {

    }

    /**
     * todo 信号量
     */
    private Semaphore two = new Semaphore(0);

    private Semaphore three = new Semaphore(0);

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        two.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // printSecond.run() outputs "second". Do not change or remove this line.
        two.acquire();
        printSecond.run();
        three.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        three.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

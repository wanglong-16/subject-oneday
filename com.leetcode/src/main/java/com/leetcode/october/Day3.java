package com.leetcode.october;

import java.util.concurrent.Semaphore;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-05 21:59:32
 * @author: wanglong16@meicai.cn
 */
public class Day3 {

    class FooBar {

        private int n;

        private Semaphore foo = new Semaphore(1);

        private Semaphore bar = new Semaphore(0);

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                foo.acquire();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                bar.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                bar.acquire();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                foo.release();
            }
        }
        /**
         *                 this.wait();
         *                 // printFoo.run() outputs "foo". Do not change or remove this line.
         *                 printFoo.run();
         *                 this.notify();
         *
         *                 this.notify();
         *                 this.wait();
         *                 // printBar.run() outputs "bar". Do not change or remove this line.
         *                 printBar.run();
         */
    }


}

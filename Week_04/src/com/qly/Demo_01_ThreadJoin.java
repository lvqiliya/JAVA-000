package com.qly;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo_01_ThreadJoin {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        // 异步执行
        AtomicInteger result = new AtomicInteger();
        Thread t = new Thread(() -> {
            result.set(sum());
        });
        t.start();
        t.join();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}
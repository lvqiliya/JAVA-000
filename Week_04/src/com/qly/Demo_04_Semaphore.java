package com.qly;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo_04_Semaphore {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        long start = System.currentTimeMillis();
        // 异步执行
        AtomicInteger result = new AtomicInteger();
        new Thread(() -> {
            result.set(sum());
            semaphore.release();
        }).start();
        semaphore.acquire();
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

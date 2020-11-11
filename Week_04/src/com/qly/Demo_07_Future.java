package com.qly;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo_07_Future {

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
            1, 1,
            60, TimeUnit.SECONDS,
            new LinkedBlockingQueue(1 << 1),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        /*AtomicInteger result = new AtomicInteger();
        Future future = EXECUTOR.submit(() -> {
            result.set(sum());
        });
        future.get();*/
        Future<Integer> future = EXECUTOR.submit(() -> sum());
        int result = future.get();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        EXECUTOR.shutdown();
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

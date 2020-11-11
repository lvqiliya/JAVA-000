package com.qly;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Demo_09_CompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> sum());
        int result = cf.get();
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

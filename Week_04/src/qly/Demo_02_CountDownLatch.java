package qly;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo_02_CountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(1);
        long start = System.currentTimeMillis();
        // 异步执行
        AtomicInteger result = new AtomicInteger();
        new Thread(() -> {
            result.set(sum());
            cdl.countDown();
        }).start();
        cdl.await();
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

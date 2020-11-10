package qly;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo_03_CyclicBarrier {

    private static int value;
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        CyclicBarrier barrier = new CyclicBarrier(1, () -> {
            System.out.println("异步计算结果为：" + value);
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        });
        // 异步执行 下面方法
        new Thread(() -> {
            value = sum();
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
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

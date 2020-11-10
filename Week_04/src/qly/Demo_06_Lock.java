package qly;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo_06_Lock {

    private static int result;

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Demo_06_Lock demo = new Demo_06_Lock();
        long start = System.currentTimeMillis();
        // 异步执行
        Thread t = new Thread(() -> {
            demo.set();
        });
        t.start();
        demo.get();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private void set() {
        lock.lock();
        try {
            result = sum();
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private int get() throws InterruptedException {
        lock.lock();
        try {
            while (result == 0) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
        return result;
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

package qly;

public class Demo_05_Synchronized {

    private static int result;

    public static void main(String[] args) throws InterruptedException {
        Demo_05_Synchronized demo = new Demo_05_Synchronized();
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

    private synchronized void set() {
        result = sum();
        notifyAll();
    }

    private synchronized int get() throws InterruptedException {
        while (result == 0) {
            wait();
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

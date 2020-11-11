# 说明
题目2：思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程？

学生一共写出了9个demo来实现本题，思路分别如下：
1. 用 Thread.join() 使主线程等待计算完成；
2. 用 CountDownLatch 的 countDown() 和 await() 方法使主线程等待计算完成；
3. 用 CyclicBarrier 的 await() 方法，当计算完成后，使用其回调函数打印计算结果；
4. 用 Semaphore 的 acquire() 阻塞主线程，当计算线程 release() 时，主线程继续运行并打印计算结果；
5. 用 Synchronized 实现设值和取值的互斥，通过 wait() 阻塞取值，计算完成后通过 notifyAll() 唤醒阻塞线程，以满足题目要求；
6. 用 Lock 和 Condition 实现设置和取值的互斥，通过 condition.await() 阻塞取值，计算完成后通过 condition.signalAll() 唤醒阻塞线程，以满足题目要求，实现原理同 Demo_05_Synchronized;
7. 用线程池提交 Callable，用 Future 接收返回值，从而满足题目要求；
8. 用线程池提交 FutureTask，用 FutureTask 接收返回值，从而满足题目要求；
9. 用 CompletableFuture.supplyAsync() 提交计算线程，并接收返回值，从而满足题目要求。
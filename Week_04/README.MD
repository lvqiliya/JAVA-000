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

# 并发总结（未完待续）
## 并发理论基础
	Java 内存模型（JMM）
		volatile
			禁用 CPU 缓存以保证可见性，禁用编译优化以保证有序性
		synchronized
			互斥锁以保证原子性
		final
			变量生而不变
		Happen-Before
			程序的顺序性规则，单线程内前面的操作 H-B 后面的操作
			volatile 变量规则，volatile 写 H-B volatile 读
			传递性规则，因为 A H-B B 且 B H-B C，所以 A H-B C
			synchronized 规则，解锁 H-B 加锁
			线程 start() 规则，若 t1 调用了 t2.start()，则 start() H-B t2 的所有操作（有点废话）
			线程 join() 规则，若 t1 调用了 t2.join()，则线程 t2 所有操作 H-B t2.join() 操作（有点废话）

## 并发工具类
	AQS（volatile + CAS）线程的启动和阻塞利用了 LockSupport 的 park() 和 unpark()
		Lock 和 Condition，通过 Lock 解决互斥问题，Condition 解决同步问题。
			能够相应中断，避免死锁，破坏了不可抢占的死锁条件
			支持超时，避免长时间阻塞，破坏了不可抢占的死锁条件
			非阻塞的获取锁，破坏了不可抢占的死锁条件
		Semaphore
		ReadWriteLock
		StampedLock
		CountDownLatch
		CyclicBarrier
	Future
	FutureTask
	CompletableFuture
	线程池（核心线程数，最大线程数，存活时间，时间单位，阻塞队列，线程工厂，拒绝策略）
	原子类，volatile + CAS
	
JUC内容太多，目前仅整理了主要脉络，后续会每个点进行补充，完善整个思维导图。
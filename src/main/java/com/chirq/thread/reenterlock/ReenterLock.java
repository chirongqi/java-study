package com.chirq.thread.reenterlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 是一个可重入的互斥（/独占）锁，又称为“独占锁”。
 * 
 * ReentrantLock通过自定义队列同步器（AQS-AbstractQueuedSychronized，是实现锁的关键）来实现锁的获取与释放。
 * 
 * 其可以完全替代 synchronized 关键字。JDK 5.0 早期版本，其性能远好于 synchronized，但 JDK 6.0 开始，JDK 对
 * synchronized 做了大量的优化，使得两者差距并不大。
 * 
 * “独占”，就是在同一时刻只能有一个线程获取到锁，而其它获取锁的线程只能处于同步队列中等待，只有获取锁的线程释放了锁，后继的线程才能够获取锁。
 * 
 * “可重入”，就是支持重进入的锁，它表示该锁能够支持一个线程对资源的重复加锁。
 * 
 * 该锁还支持获取锁时的公平和非公平性选择。“公平”是指“不同的线程获取锁的机制是公平的”，而“不公平”是指“不同的线程获取锁的机制是非公平的”。 <br>
 * 与synchronized 相比，重入锁有着显示的操作过程，何时加锁，何时释放，都在程序员的控制中。
 * 
 * <br>
 * 小结<br>
 * lock()：获得锁，如果锁被占用，进入等待。 lockInterruptibly()：获得锁，但优先响应中断。
 * tryLock()：尝试获得锁，如果成功，立即放回 true，反之失败返回 false。该方法不会进行等待，立即返回。 tryLock(long
 * time, TimeUnit unit)：在给定的时间内尝试获得锁。 unLock()：释放锁。 对于其实现原理，下篇博文将详细分析，其主要包含三个要素：
 * 
 * 原子状态：原子状态有 CAS（compareAndSetState） 操作来存储当前锁的状态，判断锁是否有其他线程持有。
 * 等待队列：所有没有请求到锁的线程，会进入等待队列进行等待。待有线程释放锁后，系统才能够从等待队列中唤醒一个线程，继续工作。详见：队列同步器——AQS（待更新）
 * 阻塞原语 park() 和
 * unpark()，用来挂起和恢复线程。没有得到锁的线程将会被挂起。关于阻塞原语，详见：线程阻塞工具类——LockSupport（待更新）。
 */
public class ReenterLock implements Runnable {
	public static Lock lock = new ReentrantLock();
	public static int i = 0;

	public void run() {
		for (int j = 0; j < 100000; j++) {
			lock.lock();
			// lock.lock();
			try {
				i++;
			} finally {
				lock.unlock();
				// lock.unlock();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ReenterLock reenterLock = new ReenterLock();
		Thread t1 = new Thread(reenterLock);
		Thread t2 = new Thread(reenterLock);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}
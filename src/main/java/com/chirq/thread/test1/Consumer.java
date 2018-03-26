package com.chirq.thread.test1;

import java.util.List;

/**
 * 消费者
 * 
 */
public class Consumer implements Runnable {
	private List<Task> buffer;

	public Consumer(List<Task> buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println(222);
			synchronized (buffer) {
				while (buffer.isEmpty()) {
					try {
						buffer.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Task task = buffer.remove(0);
				buffer.notifyAll();
				System.out.println("消费者[" + Thread.currentThread().getName() + "] 消费任务 " + task + "  还剩" + buffer.size());
			}
		}
	}
}
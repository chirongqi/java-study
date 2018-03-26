package com.chirq.thread.test2;

import java.util.concurrent.BlockingQueue;

import com.chirq.thread.test1.Task;

/**
 * 生产者
 * 
 */
class Producer implements Runnable {
	private BlockingQueue<Task> buffer;

	public Producer(BlockingQueue<Task> buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println(1111);
			try {
				Task task = new Task();
				// put(anObject):把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断
				buffer.put(task);
				// offer(anObject):表示如果可能的话,将anObject加到BlockingQueue里,即如果BlockingQueue可以容纳, 则返回true,否则返回false.（本方法不阻塞当前执行方法的线程）
				// buffer.offer(task);
				System.out.println("生产者[" + Thread.currentThread().getName() + "] 添加任务 " + task + "  剩余" + buffer.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

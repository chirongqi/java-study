package com.chirq.thread.test2;

import java.util.concurrent.BlockingQueue;

import com.chirq.thread.test1.Task;

/**
 * 消费者
 * 
 * 
 */
class Consumer implements Runnable {
	private BlockingQueue<Task> buffer;

	public Consumer(BlockingQueue<Task> buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (true) {
			try {
				// take():取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到 sBlockingQueue有新的数据被加入
//				Task task = buffer.take();
				// poll(time):取走BlockingQueue里排在首位的对象,若不能立即取出,则可以等time参数规定的时间,取不到时返回null;
				 Task task = buffer.poll();
				System.out.println("消费者[" + Thread.currentThread().getName() + "] 消费 " + task + "  剩余" + buffer.size());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
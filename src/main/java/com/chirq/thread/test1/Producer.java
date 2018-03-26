package com.chirq.thread.test1;

import java.util.List;

/**
 * 生产者
 * 
 * 
 */
public class Producer implements Runnable {
	private List<Task> buffer;

	public Producer(List<Task> buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println(111);
			synchronized (buffer) {
				while (buffer.size() >= Constants.MAX_BUFFER_SIZE) {
					try {
						buffer.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				Task task = new Task();
				buffer.add(task);
				buffer.notifyAll();
				System.out.println("生产者[" + Thread.currentThread().getName() + "] 生产了 " + task);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

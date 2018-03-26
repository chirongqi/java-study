package com.chirq.thread.test2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.chirq.thread.test1.Task;

class Test7 {
	public static void main(String[] args) {
		BlockingQueue<Task> buffer = new LinkedBlockingQueue<>(10);
		ExecutorService es = Executors.newFixedThreadPool(2);

		es.execute(new Producer(buffer));
		es.execute(new Consumer(buffer));

		for (int i = 1; i <= 5; ++i) {
			es.execute(new Producer(buffer));
		}
		// for (int i = 1; i <= 5; ++i) {
		// es.execute(new Consumer(buffer));
		// }
	}
}

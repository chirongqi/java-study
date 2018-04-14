package com.chirq.thread.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test06 {
	public static void main(String[] args) {
		List<Task> buffer = new ArrayList<>(Constants.MAX_BUFFER_SIZE);
		ExecutorService es = Executors.newFixedThreadPool(15);
		es.execute(new Producer(buffer));
		es.execute(new Consumer(buffer));
//
		for (int i = 1; i <= 5; ++i) {
			es.execute(new Producer(buffer));
		}
		for (int i = 1; i <= 5; ++i) {
			es.execute(new Consumer(buffer));
		}
	}
}

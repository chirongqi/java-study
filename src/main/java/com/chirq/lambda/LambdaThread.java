package com.chirq.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LambdaThread {

	public static void main(String[] args) {
		testThread();
		// testForEach();
		callableFun();
	}

	/**
	 * 用lambda表达式实现Runnable
	 */
	public static void testThread() {
		// Java 8之前：
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Before Java8, too much code for too little to do");
				System.out.println(this.getClass());
			}
		}).start();

		// Java 8方式：
		new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
		Runnable runnable2 = () -> {
			System.out.println("Java 8 RunningfromLambda");
		};
		new Thread(runnable2).start();
		System.out.println(runnable2.getClass());

		new Thread(() -> {
			int a = 100;
			System.out.println(a);
		}).start();

	}

	/**
	 * 使用lambda表达式对列表进行迭代
	 */
	public static void testForEach() {
		// Java 8之前：
		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		for (String feature : features) {
			System.out.println(feature);
		}

		// Java 8之后：
		features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		features.forEach(n -> System.out.println(n));

		// 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
		// 看起来像C++的作用域解析运算符
		features.forEach(System.out::println);
	}

	public static void callableFun() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		List<Callable<String>> list = new ArrayList<>();
		Callable<String> c1 = () -> {
			System.out.println("c1执行了");
			return "自定义";
		};
		list.add(c1);
		Callable<String> c2 = () -> "Hello";
		list.add(c2);
		try {
			List<Future<String>> futures = executor.invokeAll(list);
			for (Future future : futures) {
				System.out.println((future).get());
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}
}

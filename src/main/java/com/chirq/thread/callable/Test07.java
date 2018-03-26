package com.chirq.thread.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 *  Callable接口也是一个单方法接口，显然这是一个回调方法，类似于函数式编程中的回调函数，在Java 8
 * 以前，Java中还不能使用Lambda表达式来简化这种函数式编程。和Runnable接口不同的是Callable接口的回调方法call方法会返回一个对象，这个对象可以用将来时的方式在线程执行结束的时候获得信息
 * 。上面代码中的call方法就是将计算出的10000个0到1之间的随机小数的平均值返回，我们通过一个Future接口的对象得到了这个返回值
 * 。目前最新的Java版本中，Callable接口和Runnable接口都被打上了@FunctionalInterface的注解，也就是说它可以用函数式编程的方式（Lambda表达式）创建接口对象。    <br>
 * 下面是Future接口的主要方法： get()：获取结果。如果结果还没有准备好，get方法会阻塞直到取得结果；当然也可以通过参数设置阻塞超时时间。 
 * cancel()：在运算结束前取消。 isDone()：可以用来判断运算是否结束。
 * 
 * isCancelled方法表示任务是否被取消成功，如果在任务正常完成前被取消成功，则返回 true。 isDone方法表示任务是否已经完成，若任务完成，则返回true； 
 * get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回； get(long timeout,
 * TimeUnit unit)用来获取执行结果，如果在指定时间内，还没获取到结果，就直接返回null。 Future Executor就是Runnable和Callable的调度容器，
 * Future就是对于具体的Runnable或者Callable任务的执行结果进行
 * 取消、查询是否完成、获取结果、设置结果操作。get方法会阻塞，直到任务返回结果(Future简介)。Future声明如下
 */
public class Test07 {

	private static final int POOL_SIZE = 10;

	static class CalcThread implements Callable<Double> {
		private List<Double> dataList = new ArrayList<>();

		public CalcThread() {
			for (int i = 0; i < 10000; ++i) {
				dataList.add(Math.random());
			}
		}

		@Override
		public Double call() throws Exception {
			double total = 0;
			for (Double d : dataList) {
				total += d;
			}
			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName() + "执行完成");
			return total / dataList.size();
		}

	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		List<Future<Double>> fList = new ArrayList<>();
		ExecutorService es = Executors.newFixedThreadPool(5);
		for (int i = 0; i < POOL_SIZE; ++i) {
			fList.add(es.submit(new CalcThread()));
		}
		System.out.println(fList.get(4).isCancelled());
		for (Future<Double> f : fList) {
			try {
				f.get(200, TimeUnit.MILLISECONDS);
				System.out.println(f.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();

		System.out.println((end - start) / 1000);
		es.shutdown();

		// 第二种方式
		start = System.currentTimeMillis();
		ExecutorService executor = Executors.newCachedThreadPool();
		List<Future<?>> ftList = new ArrayList<>();
		for (int i = 0; i < POOL_SIZE; ++i) {
			FutureTask<Double> futureTask = new FutureTask<Double>(new CalcThread());
			ftList.add(futureTask);
			Future<?> aa = executor.submit(futureTask);
		}
		for (Future<?> f : ftList) {
			try {
				System.out.println(f.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		end = System.currentTimeMillis();

		System.out.println((end - start) / 1000 + "s");
		executor.shutdown();

		// 第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
		FutureTask<Double> futureTask = new FutureTask<Double>(new CalcThread());
		Thread thread = new Thread(futureTask);
		thread.start();
		try {
			System.out.println(futureTask.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}

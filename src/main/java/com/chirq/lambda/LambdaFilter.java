package com.chirq.lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaFilter {

	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
	}

	public static void test1() {
		List<String> strList = Arrays.asList("abc", "bcd", "defg", "jk");

		// 创建一个字符串列表，每个字符串长度大于2
		List<String> filtered = strList.stream().filter(x -> x.length() > 2).collect(Collectors.toList());
		System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
	}

	public static void test2() {
		// 将字符串换成大写并用逗号链接起来
		List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K", "Canada");
		String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
		System.out.println(G7Countries);
	}

	/**
	 * 复制不同的值，创建一个子列表 利用流的 distinct() 方法来对集合进行去重。
	 */
	public static void test3() {
		// 用所有不同的数字创建一个正方形列表
		List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);

		int factor = 2;
		numbers.forEach(element -> {
			{
				System.out.println(factor * element);
			}
		});

		List<Integer> distinct = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
		System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
	}

	/**
	 * 计算集合元素的最大值、最小值、总和以及平均值
	 */
	public static void test4() {
		// 获取数字的个数、最小值、最大值、总和以及平均值
		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
		System.out.println("Highest prime number in List : " + stats.getMax());
		System.out.println("Lowest prime number in List : " + stats.getMin());
		System.out.println("Sum of all prime numbers : " + stats.getSum());
		System.out.println("Average of all prime numbers : " + stats.getAverage());
	}
}

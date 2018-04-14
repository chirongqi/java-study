package com.chirq.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * lambda表达式和函数式接口Predicate Java 8也添加了一个包，叫做
 * java.util.function。它包含了很多类，用来支持Java的函数式编程。其中一个便是Predicate，使用
 * java.util.function.Predicate
 * 函数式接口以及lambda表达式，可以向API方法添加逻辑，用更少的代码支持更多的动态行为。下面是Java 8 Predicate
 * 的例子，展示了过滤集合数据的多种常用方法。Predicate接口非常适用于做过滤。
 */
public class LambdaPredicate {

	public static void main(String args[]) {
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

		System.out.println("Languages which starts with J :");
		filter1(languages, (str) -> (str).startsWith("J"));

		System.out.println("Languages which ends with a ");
		filter1(languages, (str) -> (str).endsWith("a"));

		System.out.println("Print all languages :");
		filter2(languages, (str) -> true);

		System.out.println("Print no language : ");
		filter2(languages, (str) -> false);

		System.out.println("Print language whose length greater than 4:");
		filter2(languages, (str) -> str.length() > 4);

		// Java8之前
		Predicate<String> testStartsWith = new Predicate<String>() {
			public boolean test(String str) {
				return (str).startsWith("J");
			}
		};
		System.out.println(testStartsWith.test("java"));
	}

	public static void filter1(List<String> names, Predicate<String> condition) {
		names.stream();
		for (String name : names) {
			if (condition.test(name)) {
				System.out.println(name + " ");
			}
		}
	}

	// 更好的办法
	public static void filter2(List<String> names, Predicate<String> condition) {
		// names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
		names.stream().filter(condition).forEach((name) -> {
			System.out.println(name + " ");
		});
	}

	public static void testAnd(List<String> names) {
		// 甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
		// 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
		Predicate<String> startsWithJ = (n) -> n.startsWith("J");
		Predicate<String> fourLetterLong = (n) -> n.length() == 4;
		names.stream().filter(startsWithJ.and(fourLetterLong))
				.forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long is : " + n));
	}

}

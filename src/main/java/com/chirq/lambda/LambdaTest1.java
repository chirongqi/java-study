package com.chirq.lambda;

import java.io.File;
import java.util.stream.Stream;

public class LambdaTest1 {
	public static void main(String[] args) {
		File[] hidden1 = new File(".").listFiles(File::isHidden);
		File[] hidden2 = new File(".").listFiles((f) -> f.isHidden());

		Stream<Integer> integerStream = Stream.of(1, 2, 3, 5);
		Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);
	}
}

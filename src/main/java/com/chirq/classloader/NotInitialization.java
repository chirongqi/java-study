package com.chirq.classloader;

public class NotInitialization {

	public static void main(String[] args) {
		NotInitialization ntl = new NotInitialization();
		ntl.test1();
		ntl.test2();
		ntl.test3 ();
	}

	/**
	 * 非主动使用字段演示
	 * 
	 * @param args
	 */
	public void test1() {
		System.out.println(SubClass.value);
	}

	public void test2() {
		SuperClass[] sca = new SuperClass[10];
	}

	public void test3() {
		System.out.println(ConstClass.HELLOWORLD);
	}
}

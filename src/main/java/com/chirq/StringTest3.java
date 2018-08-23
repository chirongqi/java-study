package com.chirq;

public class StringTest3 {

	public static void main(String[] args) {
		stringTest1();
		
		IntegerTest1();
	}

	public static void stringTest1() {
		String str1 = "abc";
		String str2 = new String("abc");
		String str3 = "abc";
		String str4 = "ab" + "c";
		String str5 = new String("ab") + "c";
		String str6 = new String(str2).intern();
		System.out.println(str1 == str2);
		System.out.println(str1 == str3);
		System.out.println(str1 == str4);
		System.out.println(str1 == str5);
		System.out.println(str1 == str6);
	}

	public static void IntegerTest1() {
		Integer a1 = new Integer(100);
		Integer a2 = new Integer(100);
		int a3 = 100;
		System.out.println(a1 == a2);
		System.out.println(a1 == a3);
	}
}

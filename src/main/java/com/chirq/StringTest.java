package com.chirq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringTest {
    public static void main(String[] args) throws IOException {
        String str1 = "abc";
        String str2 = new String("abc");
        String str3 = "abc";
        System.out.println(str1.hashCode() + "   " + str2.hashCode());
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);

        System.out.println(System.identityHashCode(str1));
        System.out.println(System.identityHashCode(str2));

        Integer a = 1000, b = 1000;
        Integer c = 127, d = 127;
        System.out.println(a == b);
        System.out.println(c == d);

        Integer a1 = new Integer(12);
        Integer a2 = new Integer(12);
        int a3 = 12;
        System.out.println("------------------------------");
        System.out.println(a1 == a2);
        System.out.println(a1 == a3);
        System.out.println(System.identityHashCode(a1));
        System.out.println(System.identityHashCode(a2));

        System.out.println(1 << 2);
        InputStream in = System.in;
        System.out.print("请输入姓名：");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(br.readLine());

    }
}

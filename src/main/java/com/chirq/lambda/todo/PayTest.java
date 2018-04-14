package com.chirq.lambda.todo;

public class PayTest {

	public static void main(String[] args) {
		PayChannel pay1 = (String name) -> name + ": 支付成功";
		System.out.println(pay1.pay("支付宝 "));
	}
}

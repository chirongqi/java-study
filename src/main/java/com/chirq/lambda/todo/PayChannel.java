package com.chirq.lambda.todo;

@FunctionalInterface
public interface PayChannel {

	String pay(String channelName);
}

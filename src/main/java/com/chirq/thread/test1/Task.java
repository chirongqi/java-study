package com.chirq.thread.test1;

import java.util.UUID;

public class Task {
	  private String id;  // 任务的编号

	    public Task() {
	        id = UUID.randomUUID().toString();
	    }

	    @Override
	    public String toString() {
	        return "Task[" + id + "]";
	    }
}

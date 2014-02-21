package com.rabbitmq.foo.b.workqueue;

public class ProcessSimulation {
	
	private final String msg; 

	public ProcessSimulation(String msg) {
		this.msg = msg;
	}
	
	public void doWork() throws InterruptedException {
	    for (char ch: msg.toCharArray()) {
	        if (ch == '.') Thread.sleep(1000);
	    }
	}
	
}

package com.rabbitmq.foo.c.workqueue2;

public class Simulation {
	
	private final String msg; 

	public Simulation(String msg) {
		this.msg = msg;
	}
	
	public void doWork() throws InterruptedException {
	    for (char ch: msg.toCharArray()) {
	        if (ch == '.') Thread.sleep(1000);
	    }
	}
	
}

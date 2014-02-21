package com.rabbitmq.foo.e.routing_msg;

import java.io.IOException;

public class StartWorker {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		new Worker("routing", "black", "red","yellow").start();
	}

}

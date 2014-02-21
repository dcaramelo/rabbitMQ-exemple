package com.rabbitmq.foo.workqueue;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rabbitmq.foo.b.workqueue.Task;
import com.rabbitmq.foo.b.workqueue.Worker;



public class RabbitMQ_WorkQueue_Utest {

	@Test
	public void test() throws Exception {
		// Given
		Task task = new Task("TEST");
		Worker worker1 = new Worker("TEST");
		Worker worker2 = new Worker("TEST");

		// Then
		task.sendMsg("Hello World !.");
		task.sendMsg("Hello World !..");
		task.sendMsg("Hello World !...");
		task.sendMsg("Hello World !....");
		task.sendMsg("Hello World !.....");
		task.close();
		
		// When		
		assertEquals("Hello World !.", worker1.readMsg());
		assertEquals("Hello World !..", worker2.readMsg());
		assertEquals("Hello World !...", worker1.readMsg());
		assertEquals("Hello World !....", worker2.readMsg());
		assertEquals("Hello World !.....", worker1.readMsg());
	}
}

package com.rabbitmq.foo.helloworld;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;
import com.rabbitmq.foo.a.helloworld.Recv;
import com.rabbitmq.foo.a.helloworld.Sender;

public class RabbitMQ_HelloWorld_Utest {

	@Test
	public void test() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		// Given
		Sender sender = new Sender("TEST");
		Recv recv = new Recv("TEST");
		
		// Then
		sender.sendMsg("Hello World !");
		sender.sendMsg("Hello World 2 !");
		sender.close();
		
		// When
		assertEquals("Hello World !", recv.readMsg());
		assertEquals("Hello World 2 !", recv.readMsg());
	}

}

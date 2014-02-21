package com.rabbitmq.foo.a.helloworld;

import java.io.IOException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Sender {

	private static Channel channel;
	private static Connection connection;
	private final String queue;
	
	public Sender(String queue) throws IOException {
		this.queue = queue;
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();
		channel.queueDeclare(queue, false, false, false, null);
	}

	public void sendMsg(String msg) throws IOException {
		channel.basicPublish("", queue, null, msg.getBytes());

	}
	
	public void close() throws IOException {
	    channel.close();
	    connection.close();
	}
}

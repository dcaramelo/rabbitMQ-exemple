package com.rabbitmq.foo.c.workqueue2;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class SenderTask {

	private static Channel channel;
	private static Connection connection;
	private final String queue;
	
	public SenderTask(String queue) throws IOException {
		this.queue = queue;
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();
		boolean persistent = true;
		channel.queueDeclare(queue, persistent, false, false, null);
	}

	public void sendMsg(String msg) throws IOException {
		channel.basicPublish( "", queue, 
	            MessageProperties.PERSISTENT_TEXT_PLAIN,
	            msg.getBytes());
	}
	
	public void close() throws IOException {
	    channel.close();
	    connection.close();
	}
}

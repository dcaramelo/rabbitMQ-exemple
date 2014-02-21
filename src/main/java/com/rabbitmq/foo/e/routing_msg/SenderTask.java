package com.rabbitmq.foo.e.routing_msg;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SenderTask {

	private static Channel channel;
	private static Connection connection;
	private final String exchange;
	
	public SenderTask(String keyRoute) throws IOException {
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.exchangeDeclare(keyRoute, "direct");
        this.exchange = keyRoute;
	}

	public void sendMsg(String msg, String color) throws IOException {
		channel.basicPublish(exchange, color, null, msg.getBytes());
	}
	
	public void close() throws IOException {
	    channel.close();
	    connection.close();
	}
}

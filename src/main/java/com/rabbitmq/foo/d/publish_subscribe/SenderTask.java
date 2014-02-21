package com.rabbitmq.foo.d.publish_subscribe;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class SenderTask {

	private static Channel channel;
	private static Connection connection;
	private final String keyRoute;
	
	public SenderTask(String keyRoute) throws IOException {
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.exchangeDeclare(keyRoute, "fanout");
        this.keyRoute = keyRoute;
	}

	public void sendMsg(String msg) throws IOException {
		channel.basicPublish( keyRoute, "", null, msg.getBytes());
	}
	
	public void close() throws IOException {
	    channel.close();
	    connection.close();
	}
}

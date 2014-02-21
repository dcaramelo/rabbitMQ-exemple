package com.rabbitmq.foo.b.workqueue;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Worker {

	QueueingConsumer consumer;

	public Worker(String queue) throws IOException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
		channel.queueDeclare(queue, false, false, false, null);
	    consumer = new QueueingConsumer(channel);
	    channel.basicConsume(queue, true, consumer);
	}
	
	public String readMsg() throws ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		QueueingConsumer.Delivery delivery = consumer.nextDelivery();
		String message = new String(delivery.getBody());
		new ProcessSimulation(message).doWork();
		return message;
	}
}

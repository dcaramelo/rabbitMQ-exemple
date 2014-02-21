package com.rabbitmq.foo.c.workqueue2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Worker {

	private static final String TASK_QUEUE_NAME = "task_queue";

	public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException {

		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    boolean persistent = true;
	    channel.queueDeclare(TASK_QUEUE_NAME, persistent, false, false, null);
	
	    // 1 msg à la fois par consommateur - distribution des charges équitables
	    channel.basicQos(1);

	    QueueingConsumer consumer = new QueueingConsumer(channel);
	    boolean accuseDeRcptAuto = false;
	    channel.basicConsume(TASK_QUEUE_NAME, accuseDeRcptAuto, consumer);

	    while (true) {
	    	QueueingConsumer.Delivery delivery = consumer.nextDelivery();
	    	String message = new String(delivery.getBody());

	    	System.out.println(" Received msg : '" + message + "'");   
	    	new Simulation(message).doWork(); 
	    	System.out.println(" Process Done" );
	    	
	    	// Accusé de reception explicite car accuseDeRcptAuto = false
	    	channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
	    }
	}

}

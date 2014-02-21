package com.rabbitmq.foo.d.publish_subscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Worker {

	private static final String KEY_ROUTE = "logs";

	public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException {

		double id = Math.random();
		
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(KEY_ROUTE, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, KEY_ROUTE, "");

        channel.basicQos(1);
        
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            
            System.out.println(" Received msg '" + message + "' - id : " + id);
            
            new Simulation(message).doWork(); 
            System.out.println(" Process Done" );
        }
	}

}

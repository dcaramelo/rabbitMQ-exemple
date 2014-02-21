package com.rabbitmq.foo.e.routing_msg;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Worker {

	private final String exchange;
	private final String [] keyRoutes;
	
	public Worker(String exchange, String ... keyRoutes) {
		this.exchange = exchange;
		this.keyRoutes = keyRoutes;
	}

	public void start() throws java.io.IOException, java.lang.InterruptedException {

		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(exchange, "direct");
        String queueName = channel.queueDeclare().getQueue();
        
        for (String keyRoute : keyRoutes) {
        	channel.queueBind(queueName, exchange, keyRoute);
        }
        
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            
            System.out.println(" Received msg '" + message + "' - id : " + delivery.getEnvelope().getRoutingKey());

            System.out.println(" Process Done" );
        }
	}

}

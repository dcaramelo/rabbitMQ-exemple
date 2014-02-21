package com.rabbitmq.foo.e.routing_msg;

public class StartProcess {

	private static final String TASK_QUEUE_NAME = "routing";

	public static void main(String[] argv) throws java.io.IOException {
		SenderTask st = new SenderTask(TASK_QUEUE_NAME);
		st.sendMsg("Is msg red", "red");
		st.sendMsg("Is msg black", "black");
		st.sendMsg("Is msg Yellow", "yellow");
		st.sendMsg("Is msg black", "black");
		st.close();
	}      
}

package com.rabbitmq.foo.d.publish_subscribe;

public class StartProcess {

	private static final String TASK_QUEUE_NAME = "logs";

	public static void main(String[] argv) throws java.io.IOException {
		SenderTask st = new SenderTask(TASK_QUEUE_NAME);
		st.sendMsg("log 1");
		st.sendMsg("log 2.");
		st.sendMsg("log 3");
		st.sendMsg("log 4...");
		st.close();
	}      
}

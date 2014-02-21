package com.rabbitmq.foo.c.workqueue2;

public class StartProcess {

	private static final String TASK_QUEUE_NAME = "task_queue";

	public static void main(String[] argv) throws java.io.IOException {
		SenderTask st = new SenderTask(TASK_QUEUE_NAME);
		st.sendMsg("msg 1");
		st.sendMsg("msg 2.");
		st.sendMsg("msg 3");
		st.sendMsg("msg 4...");
		st.close();
	}      
}

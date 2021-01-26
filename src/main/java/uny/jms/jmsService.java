package uny.jms;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

@ApplicationScoped
public class jmsService {

@Resource(mappedName = "java:/jms/queue/watches")
private Queue watches;
@Inject
@JMSConnectionFactory("java:/ConnectionFactory")
private JMSContext context;

public void send(String message) {

	try {
		
		TextMessage textMessage=context.createTextMessage(message);
		context.createProducer().send(watches, textMessage);
		
	} catch (Exception e) {
	e.printStackTrace();
	}
	
	
}
	
}

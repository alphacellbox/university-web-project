package uny.jms;

import java.util.ArrayList;
import java.util.Enumeration;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.TextMessage;




@RequestScoped
public class jmsbrowser  {
	
ArrayList<String> list=new ArrayList<>();
ArrayList<String> id_list=new ArrayList<>();
	public ArrayList<String> getList() {
	return list;
}

public void setList(ArrayList<String> list) {
	this.list = list;
}

	@Resource(lookup = "java:/jms/queue/watches")
	private Queue watches;
	
	
	@Resource  (lookup = "java:/ConnectionFactory")     
	private ConnectionFactory connectionFactory;
	
	public void give_from_queue() {
		
try (JMSContext context = connectionFactory.createContext();){
	list.clear();
			QueueBrowser browser = context.createBrowser(watches);
		
			Enumeration<?> msgs = browser.getEnumeration();
			if ( !msgs.hasMoreElements() ) {
			    System.out.println("No messages in queue");
			} else {
			    while (msgs.hasMoreElements()) {
			    	TextMessage tempMsg = (TextMessage)msgs.nextElement();
			    	  System.out.println("Message: " + tempMsg.getText());
				        list.add(tempMsg.getText());
				        
				        tempMsg.getJMSMessageID();
				        
				    System.out.println("//");
				    System.out.println( tempMsg.getJMSMessageID());
				id_list.add(   tempMsg.getJMSMessageID())  ;
			
			      System.out.println("//");	
			    			
			    			
			      
			    }
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		

		}

		}

	public ArrayList<String> getId_list() {
		return id_list;
	}

	public void setId_list(ArrayList<String> id_list) {
		this.id_list = id_list;
	}
	
	/*if ( !msgs.hasMoreElements() ) {
	    System.out.println("No messages in queue");
	} else {
	    while (msgs.hasMoreElements()) {
	    	TextMessage tempMsg = (TextMessage)msgs.nextElement();
	        
	        System.out.println("Message: " + tempMsg.getText());
	    }
	}*/
	
}



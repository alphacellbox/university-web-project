package uny.controllers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;




@ApplicationScoped
@Named
public class index_controllers {


	public String go_to_index() {

		
return"index?faces-redirect=true";
	




		
		
		
		
		
		
		
		
	}
	public String go_to_about() {
		return "about?faces-redirect=true";
	}
	public String go_to_contact() {
		return "contact?faces-redirect=true";
	}
	
	
}

package uny.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.Part;

import uny.dao.watchdao;
import uny.entity.watch;
import uny.jms.jmsService;
@RequestScoped
@Named
public class add_watches_controller {
	@EJB
	private watchdao wdao;
	private String name;
	private String cost;
	private Part image;
	private String url;
	
	
	public String go_to_add_watches() {
		return "add_watches?faces-redirect=true";
	}
	public void send_to_jms() throws IOException {
		

	 String filename =randomIdentifier()+".jpg";
	 try(InputStream input=image.getInputStream()){
			
					
		
			 File f=new File(filename);
           url  = f.getAbsolutePath();
		 Files.copy(input,f.toPath());
		 System.out.println(f.toPath());
		
	 }catch (Exception e) {	 
	e.printStackTrace();
	}
	 watch w=new watch();
	 w.setName(name);
	 w.setCost(cost);
	 w.setUrl(url);
	
	 wdao.save(w);
	
	}
	
	final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

	final java.util.Random rand = new java.util.Random();

	// consider using a Map<String,Boolean> to say whether the identifier is being used or not 
	final Set<String> identifiers = new HashSet<String>();

	public String randomIdentifier() {
	    StringBuilder builder = new StringBuilder();
	    while(builder.toString().length() == 0) {
	        int length = rand.nextInt(5)+5;
	        for(int i = 0; i < length; i++) {
	            builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
	        }
	        if(identifiers.contains(builder.toString())) {
	            builder = new StringBuilder();
	        }
	    }
	    return builder.toString();
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public Part getImage() {
		return image;
	}
	public void setImage(Part image) {
		this.image = image;
	}
	
}

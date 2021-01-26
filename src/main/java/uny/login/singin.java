package uny.login;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import uny.dao.userdao;
import uny.entity.user;

@Named
@RequestScoped
public class singin {
    
	
	@EJB
	private userdao udao;
	 @NotEmpty
	 @Email(message = "Please provide a valid e-mail")
	private String username;
	 @NotEmpty
     @Size(min = 8, message = "Password must have at least 8 characters")
	private String password;
	public void saveuser() {
		user u=new user();
		u.setPassword(password);
		u.setUsername(username);
		udao.saveuser(u);
		
	}
	
	public String go_to_singin() {
		
		return "singin?faces-redirect=true";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

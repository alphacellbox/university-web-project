package de.rieckpil.blog.security;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import uny.dao.userdao;
import uny.entity.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CustomInMemoryIdentityStore implements IdentityStore {
	List<user> u = new ArrayList<user>();
	List<user> r = new ArrayList<user>();
	@EJB
	private userdao udao;
    @Override
    public CredentialValidationResult validate(Credential credential) {
    	u.clear();
    	u=udao.getalluseres();

        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;
        r=u.stream().filter(i ->i.getUsername().equals(login.getCaller())).collect(Collectors.toList());
			System.out.println("heyyyyyyyyyyyyyyyyyyyy");
		//	login.getCaller().equals("admin@mail.com") &&
			if (r!=null) {
				
		
        if (r.get(0).getUsername().equals(login.getCaller()) && r.get(0).getPassword().equals(login.getPasswordAsString())) {
            return new CredentialValidationResult("admin", new HashSet<>(Arrays.asList("ADMIN")));
        } else if (login.getCaller().equals("user@mail.com") && login.getPasswordAsString().equals("USER1234")) {
            return new CredentialValidationResult("user", new HashSet<>(Arrays.asList("USER")));
        } else {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
        }else {
   		 return CredentialValidationResult.NOT_VALIDATED_RESULT;
    	}
	}
}

package uny.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import uny.entity.user;

@Stateless
public class userdao {

	@PersistenceContext
	private EntityManager em;
	 public void saveuser(user user) {
		 em.persist(user);
		 
	 }
	 public List<user> getalluseres() {
		 TypedQuery<user> query=em.createQuery("From user b",user.class);
		 List<user> users=query.getResultList();
		 return users;
		 
	 }
}

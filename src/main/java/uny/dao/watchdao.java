package uny.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import uny.entity.watch;
@Stateless
public class watchdao {

	@PersistenceContext
	private EntityManager em;
	 public void save(watch watche) {
		 em.persist(watche);
		 
	 }
	 public List<watch> getallwatches() {
		 TypedQuery<watch> query=em.createQuery("From watch b",watch.class);
		 List<watch> watches=query.getResultList();
		 return watches;
		 
	 }
	 public void delete(String id) {
	watch w = em.find(watch.class, id);

	
	  em.remove(w);
	 
	 }
	 public void updatename(String id,String name) {
			watch w = em.find(watch.class, id);

			w.setName(name);
			 
			 
			 }
	 public void updatecost(String id,String cost) {
			watch w = em.find(watch.class, id);

			w.setCost(cost);
			
			 
			 }
	 public void updateurl(String id,String url) {
			watch w = em.find(watch.class, id);

			w.setUrl(url);
			 
			 
			 }
	 

		public watch findItem(String id) {
			return em.find(watch.class, id);
		}
}

package online.store.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import model.Customer;

@Repository
public class OnlineStoreDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Customer entity) {
		this.entityManager.persist(entity);
	}

	public Customer search(String id) {
		return this.entityManager.find(Customer.class, id);
	}

	public void remove(Customer studentEntity) {
		this.entityManager.remove(studentEntity);
	}

	public void update(Customer studentEntity) {
		this.entityManager.merge(studentEntity);
	}

}

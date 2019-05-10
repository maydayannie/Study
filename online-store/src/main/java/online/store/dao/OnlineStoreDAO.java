package online.store.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Customer;
import model.Customer_;
import online.store.controller.OnlineStoreController;

@Repository
public class OnlineStoreDAO {
	
	private Logger logger = LogManager.getLogger(OnlineStoreDAO.class);

	@PersistenceContext
	private EntityManager entityManager;
	
//	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
//            .createEntityManagerFactory("online-store");

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
	
	public Customer checkLogin(String cusid, String cuspwd) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
		Root<Customer> customer = criteriaQuery.from(Customer.class);
		List<Predicate> predicatesList = new ArrayList<Predicate>();
		predicatesList.add(criteriaBuilder.and(criteriaBuilder.equal(customer.get(model.Customer_.CUS_ID), cusid),
				criteriaBuilder.like(customer.get(Customer_.PASSWORD), cuspwd)));

		criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
		TypedQuery<Customer> typedQuery = this.entityManager.createQuery(criteriaQuery);
		List<Customer> result = typedQuery.getResultList();
		logger.info(result);
		return result != null ? result.get(0) : null;
	}

}

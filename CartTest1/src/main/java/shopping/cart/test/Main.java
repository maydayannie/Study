package shopping.cart.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import shopping.cart.test.Customer;

public class Main {
	// Create an EntityManagerFactory when you start the application.
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JavaHelps");
	public static void main(String[] args) {
		
	//	create("A004", "Shih", "12345");
		

		 
		// Print all the Students
        List<Customer> customers = readAll();
        if (customers != null) {
            for (Customer cus : customers) {
                System.out.println(cus);
            }
        }
        
       
        List<Cart> carts = readAllCart();
        if (carts != null) {
            for (Cart cart : carts) {
                System.out.println(cart);
            }
        }
       

        // NEVER FORGET TO CLOSE THE ENTITY_MANAGER_FACTORY
        ENTITY_MANAGER_FACTORY.close();
		

	}
	
	public static void create(String cusid, String cusname, String password) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Create a new Student object
            Customer cus = new Customer();
            cus.setCusid(cusid);
            cus.setCusname(cusname);
            cus.setPassword(password);

            // Save the student object
            manager.persist(cus);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }
	
	
	public static List<Customer> readAll() {

        List<Customer> customers = null;
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get a List of Students 
//            customers = manager.createQuery("SELECT s FROM shopping.cart.test.Customer s where cusname='Enoch'",
//            		Customer.class).getResultList();
            
            CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> customer = criteriaQuery.from(Customer.class);
            List<Predicate> predicateList = new ArrayList<Predicate>();
//            if(true) {
//            	Predicate condition = criteriaBuilder.equal(customer.get("cusname"), "Annie");
//            	predicateList.add(condition);
//            }
            
            criteriaQuery.where(predicateList.toArray(new Predicate[0]));
            TypedQuery<Customer> typedQuery = manager.createQuery(criteriaQuery);
            customers = typedQuery.getResultList();

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
        return customers;
    }

	
	public static List<Cart> readAllCart() {

        List<Cart> carts = null;
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get a List of Students 
            List aa = manager.createQuery("SELECT s FROM shopping.cart.test.Customer s , shopping.cart.test.Cart c where s.cusid=c.cusid and s.cusname='Enoch'").getResultList();
            
//            CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
//            CriteriaQuery<Cart> criteriaQuery = criteriaBuilder.createQuery(Cart.class);
//            Root<Cart> cart = criteriaQuery.from(Cart.class);
//            List<Predicate> predicateList = new ArrayList<Predicate>();
//            if(true) {
//            	Predicate condition = criteriaBuilder.equal(customer.get("cusname"), "Annie");
//            	predicateList.add(condition);
//            }
            
//            criteriaQuery.where(predicateList.toArray(new Predicate[0]));
//            TypedQuery<Cart> typedQuery = manager.createQuery(criteriaQuery);
//            carts = typedQuery.getResultList();

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
        return carts;
    }
}

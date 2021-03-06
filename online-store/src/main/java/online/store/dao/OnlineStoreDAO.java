package online.store.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import model.CartBase;
import model.CartBase_;
import model.CartDtl;
import model.CartDtlPK;
import model.CartDtl_;
import model.Customer;
import model.Customer_;
import model.Product;
import online.store.vo.ConditionProductVO;
import online.store.vo.User;

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
		return result != null && result.size() > 0 ? result.get(0) : null;
	}
	
	public List<Product> allProduct() {
		Query query = this.entityManager.createQuery("SELECT a FROM Product a ");
//		query.setFirstResult(page-1*pagesize );
//		query.setMaxResults(page*pagesize);
		
		List<Product> resultList = query.getResultList();
		return resultList;		
	}
	
	
	public CartBase getCart(String cusId) {
		Customer c = this.entityManager.find(Customer.class, cusId);
		return c.getCartBase();
	}
	

	
	
	public List<CartBase> cusCart(User user){
//		Customer c = new Customer();
//		c.setCusId("C001");
		
		
		CartBase cb = new CartBase();
		
	    Product prod = new Product();
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<CartBase> criteriaQuery = criteriaBuilder.createQuery(CartBase.class);
		Root<CartBase> cartBaseRoot = criteriaQuery.from(CartBase.class);
		Join<CartBase, CartDtl> cusJoin = cartBaseRoot.join(CartBase_.CART_ID,JoinType.LEFT);
//		Join<CartDtl, Product> cusJoin2 = CartDtl.join(CartBase_.CUSTOMER,JoinType.LEFT);
		criteriaQuery.where(criteriaBuilder.equal(cusJoin.get(CartDtl_.CART_BASE),cb));
		criteriaQuery.where(criteriaBuilder.equal(cusJoin.get(CartDtl_.PRODUCT),prod));
		TypedQuery<CartBase> typedQuery = this.entityManager.createQuery(criteriaQuery);
		List<CartBase> resultList = typedQuery.getResultList();
		return resultList;
	}

	public Product findProduct(String prodId) {
	 return this.entityManager.find(Product.class, prodId);
	}
	
	public void persistCartDtl(CartDtl cartDtl) {
		this.entityManager.persist(cartDtl);
	}
	
	public void persistProduct(Product prod) {
		this.entityManager.persist(prod);
	}
	
//	public void updateProduct(Product prod) {
//		this.entityManager.merge(prod);
//	}


	public List<CartDtl> cusCart(String cartId) {
//		CartDtlPK pk = new CartDtlPK();
//		pk.setCartId(cartId);
//		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
//		CriteriaQuery<CartDtl> criteriaQuery = criteriaBuilder.createQuery(CartDtl.class);
//		Root<CartDtl> cartBaseRoot = criteriaQuery.from(CartDtl.class);
//		criteriaQuery.where(criteriaBuilder.equal(cartBaseRoot.get(CartDtl_.ID),pk));
//		TypedQuery<CartDtl> typedQuery = this.entityManager.createQuery(criteriaQuery);
		Query q = this.entityManager.createQuery("from model.CartDtl c where c.id.cartId = :cartId");
		q.setParameter("cartId", cartId);
		List<CartDtl> resultList = q.getResultList();
		return resultList;
	}
	
	public void removeCartDtl(CartDtl cartDtl) {
//		this.entityManager.remove(cartDtl);
		this.entityManager.remove(entityManager.merge(cartDtl));
		
	}
	
	//寫法1的刪除
//	public void removeProduct(String prodId) {
//		Query q = this.entityManager.createQuery("delete from model.Product p where p.prodId = :prodId");
//		q.setParameter("prodId", prodId);
//		q.executeUpdate();
//	}
	
	//寫法2的刪除
	public void removeProduct(Product product) {
		this.entityManager.remove(entityManager.merge(product));
	}
	
	public void removeCartprod(String prodId) throws Exception{		
		Query q = this.entityManager.createQuery("delete from model.CartDtl c where c.id.prodId = :prodId");
		q.setParameter("prodId", prodId);
		q.executeUpdate();
	}

//	public List<Product> conditionProduct(Product product)	{	
//		//Query query = this.entityManager.createQuery("SELECT p FROM Product p where p.prodId = :prodId and p.prodName = :prodName");	
//		Query query = this.entityManager.createQuery("SELECT p FROM Product p where p.prodName LIKE :prodName");
//		//query.setParameter("prodId", "product.getProdId()");
//		query.setParameter("prodName", "%"+product.getProdName()+"%");	
//		List<Product> resultList = query.getResultList();
//		return resultList;
//	}	
	
	public List<Product> conditionProduct(ConditionProductVO vo){
//		Query query = this.entityManager.createQuery("SELECT p FROM Product p where 1=1 and p.price >= :minPrice and p.price <= :maxPrice");

		String QueryStr="SELECT p FROM Product p where 1=1";
//		
		if (vo.getProdId() != "") {
			QueryStr += " and p.prodId = :prodId ";
		}
		if (vo.getProdName() != "") {
		    QueryStr += " and p.prodName like :prodName ";
		}
		if (vo.getMinPrice() > 0) {
			QueryStr += " and p.price >= :minPrice and p.price <= :maxPrice";
		}
//		logger.info("str:"+QueryStr);
		Query query = this.entityManager.createQuery(QueryStr);
		if (vo.getProdId() != "") {
		  query.setParameter("prodId", vo.getProdId());
		}
		if (vo.getProdName() != "") {
		  query.setParameter("prodName", "%"+vo.getProdName()+"%");
		}	
		if (vo.getMinPrice() > 0) {
		  query.setParameter("minPrice", vo.getMinPrice());
		  query.setParameter("maxPrice", vo.getMaxPrice());
		}
		logger.info("minprice="+vo.getMinPrice()+"  and maxprice="+vo.getMaxPrice());
		
		List<Product> resultList = query.getResultList();
		return resultList;
	}
	
}

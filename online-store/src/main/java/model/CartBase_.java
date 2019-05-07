package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CartBase.class)
public abstract class CartBase_ {

	public static volatile SingularAttribute<CartBase, String> cartId;
	public static volatile SingularAttribute<CartBase, Customer> customer;
	public static volatile SingularAttribute<CartBase, String> cusId;

	public static final String CART_ID = "cartId";
	public static final String CUSTOMER = "customer";
	public static final String CUS_ID = "cusId";

}


package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ {

	public static volatile SingularAttribute<Customer, String> password;
	public static volatile SingularAttribute<Customer, String> cusId;
	public static volatile SingularAttribute<Customer, String> cusName;
	public static volatile SingularAttribute<Customer, CartBase> cartBase;

	public static final String PASSWORD = "password";
	public static final String CUS_ID = "cusId";
	public static final String CUS_NAME = "cusName";
	public static final String CART_BASE = "cartBase";

}


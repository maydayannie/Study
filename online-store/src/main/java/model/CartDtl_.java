package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CartDtl.class)
public abstract class CartDtl_ {

	public static volatile SingularAttribute<CartDtl, Product> product;
	public static volatile SingularAttribute<CartDtl, Integer> qty;
	public static volatile SingularAttribute<CartDtl, CartBase> cartBase;
	public static volatile SingularAttribute<CartDtl, CartDtlPK> id;

	public static final String PRODUCT = "product";
	public static final String QTY = "qty";
	public static final String CART_BASE = "cartBase";
	public static final String ID = "id";

}


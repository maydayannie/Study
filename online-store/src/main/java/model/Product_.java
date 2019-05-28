package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, String> notes;
	public static volatile SingularAttribute<Product, Integer> price;
	public static volatile SingularAttribute<Product, String> prodName;
	public static volatile SingularAttribute<Product, String> prodId;
	public static volatile SingularAttribute<Product, String> fileName;
	public static volatile ListAttribute<Product, CartDtl> cartDtls;

	public static final String NOTES = "notes";
	public static final String PRICE = "price";
	public static final String PROD_NAME = "prodName";
	public static final String PROD_ID = "prodId";
	public static final String FILE_NAME = "fileName";
	public static final String CART_DTLS = "cartDtls";

}


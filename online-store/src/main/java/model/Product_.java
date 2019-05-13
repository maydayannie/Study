package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)

public class Product_ {
	public static volatile SingularAttribute<Product, String> prodid;
	public static volatile SingularAttribute<Product, String> prodname;
	public static volatile SingularAttribute<Product, String> notes;
	public static volatile SingularAttribute<Product, Integer> price;

	public static final String PROD_ID = "prodid";
	public static final String PROD_NAME = "prodname";
	public static final String NOTES = "notes";
	public static final String PRICE = "price";

}

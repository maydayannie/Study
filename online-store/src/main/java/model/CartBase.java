package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CART_BASE database table.
 * 
 */
@Entity
@Table(name="CART_BASE")
@NamedQuery(name="CartBase.findAll", query="SELECT c FROM CartBase c")
public class CartBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CART_ID")
	private String cartId;
	
	@Column(name="CUS_ID")
	private String cusId;


	//uni-directional one-to-one association to Customer
	@OneToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY )
	@JoinColumn(name="CUS_ID",referencedColumnName="CUS_ID",insertable=false, updatable=false)
	private Customer customer;

	public CartBase() {
	}

	
	public String getCusId() {
		return cusId;
	}


	public void setCusId(String cusId) {
		this.cusId = cusId;
	}


	public String getCartId() {
		return this.cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CartBase [cartId=" + cartId + ", customer="  + "]";
	}

}
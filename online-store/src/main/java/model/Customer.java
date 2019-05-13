package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CUSTOMER database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUS_ID")
	private String cusId;

	@Column(name="CUS_NAME")
	private String cusName;

	private String password;

	//bi-directional one-to-one association to CartBase
	@OneToOne(mappedBy="customer", fetch=FetchType.LAZY)
	private CartBase cartBase;

	public Customer() {
	}

	public String getCusId() {
		return this.cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getCusName() {
		return this.cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CartBase getCartBase() {
		return this.cartBase;
	}

	public void setCartBase(CartBase cartBase) {
		this.cartBase = cartBase;
	}

}
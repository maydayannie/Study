package shopping.cart.test;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="CART_BASE")
public class Cart implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4624511442068184943L;

	@Id
	@Column(name="CART_ID")
	private String cartId;
	
	@Column(name="CUS_ID")
	private String cusid;
	
//	@OneToOne
//	@JoinColumn(name="CUS_ID")
//	private Customer customer;
	
	@OneToOne(mappedBy="cart")
	private Customer customer;
	

	@OneToMany(mappedBy="cartdtl")
    private List<CartDtl> phones = new ArrayList<CartDtl>();

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	
	public String getCusid() {
		return cusid;
	}

	public void setCusid(String cusid) {
		this.cusid = cusid;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", cusid=" + cusid + ", customer=" + customer + "]";
	}


	

}

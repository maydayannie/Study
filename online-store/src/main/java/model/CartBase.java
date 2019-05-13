package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	//bi-directional one-to-one association to Customer
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUS_ID")
	private Customer customer;

	//bi-directional many-to-one association to CartDtl
	@OneToMany(mappedBy="cartBase")
	private List<CartDtl> cartDtls;

	public CartBase() {
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

	public List<CartDtl> getCartDtls() {
		return this.cartDtls;
	}

	public void setCartDtls(List<CartDtl> cartDtls) {
		this.cartDtls = cartDtls;
	}

	public CartDtl addCartDtl(CartDtl cartDtl) {
		getCartDtls().add(cartDtl);
		cartDtl.setCartBase(this);

		return cartDtl;
	}

	public CartDtl removeCartDtl(CartDtl cartDtl) {
		getCartDtls().remove(cartDtl);
		cartDtl.setCartBase(null);

		return cartDtl;
	}

}
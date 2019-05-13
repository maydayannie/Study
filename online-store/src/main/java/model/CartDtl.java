package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CART_DTL database table.
 * 
 */
@Entity
@Table(name="CART_DTL")
@NamedQuery(name="CartDtl.findAll", query="SELECT c FROM CartDtl c")
public class CartDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CartDtlPK id;

	private int qty;

	//bi-directional many-to-one association to CartBase
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CART_ID")
	private CartBase cartBase;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROD_ID")
	private Product product;

	public CartDtl() {
	}

	public CartDtlPK getId() {
		return this.id;
	}

	public void setId(CartDtlPK id) {
		this.id = id;
	}

	public int getQty() {
		return this.qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public CartBase getCartBase() {
		return this.cartBase;
	}

	public void setCartBase(CartBase cartBase) {
		this.cartBase = cartBase;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CART_DTL database table.
 * 
 */
@Embeddable
public class CartDtlPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CART_ID")
	private String cartId;

	private int item;

	public CartDtlPK() {
	}
	public String getCartId() {
		return this.cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public int getItem() {
		return this.item;
	}
	public void setItem(int item) {
		this.item = item;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CartDtlPK)) {
			return false;
		}
		CartDtlPK castOther = (CartDtlPK)other;
		return 
			this.cartId.equals(castOther.cartId)
			&& (this.item == castOther.item);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cartId.hashCode();
		hash = hash * prime + this.item;
		
		return hash;
	}
}
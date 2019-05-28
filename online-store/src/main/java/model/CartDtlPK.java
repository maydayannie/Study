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
	@Column(name="PROD_ID")
	private String prodId;

	public CartDtlPK() {
	}
	public String getCartId() {
		return this.cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getProdId() {
		return this.prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
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
			&& (this.prodId == castOther.prodId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cartId.hashCode();
		hash = hash * prime + this.prodId.hashCode();
		
		return hash;
	}
}
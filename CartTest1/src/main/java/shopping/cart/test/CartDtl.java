package shopping.cart.test;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="CART_DTL")
public class CartDtl implements Serializable{

	@Id
	@Column(name="CART_ID")
	private String cartid;
	
	@Column(name="ITEM")
	private int item;
	
	@Column(name="PROD_ID")
	private String prodid;
	
	@Column(name="QTY")
	private int qty;
	
	@ManyToOne
	Cart cartdtl;

	public String getCartid() {
		return cartid;
	}

	public void setCartid(String cartid) {
		this.cartid = cartid;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Cart getCartdtl() {
		return cartdtl;
	}

	public void setCartdtl(Cart cartdtl) {
		this.cartdtl = cartdtl;
	}

	@Override
	public String toString() {
		return "CartDtl [cartid=" + cartid + ", item=" + item + ", prodid=" + prodid + ", qty=" + qty + ", cartdtl="
				+ cartdtl + "]";
	}
}

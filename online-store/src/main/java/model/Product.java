package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PRODUCT database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PROD_ID")
	private String prodId;

	private String notes;

	private int price;

	@Column(name="PROD_NAME")
	private String prodName;
	
	@Column(name="FILE_NAME")
	private String fileName;

	//bi-directional many-to-one association to CartDtl
	@OneToMany(mappedBy="product")
	private List<CartDtl> cartDtls;

	public Product() {
	}

	public String getProdId() {
		return this.prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProdName() {
		return this.prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public List<CartDtl> getCartDtls() {
		return this.cartDtls;
	}

	public void setCartDtls(List<CartDtl> cartDtls) {
		this.cartDtls = cartDtls;
	}

	public CartDtl addCartDtl(CartDtl cartDtl) {
		getCartDtls().add(cartDtl);
		cartDtl.setProduct(this);

		return cartDtl;
	}

	public CartDtl removeCartDtl(CartDtl cartDtl) {
		getCartDtls().remove(cartDtl);
		cartDtl.setProduct(null);

		return cartDtl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
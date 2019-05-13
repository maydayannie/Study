package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Product.findAll", query="SELECT c FROM Product c")
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PROD_ID")
	private String prodid;
	
	@Column(name="PROD_NAME")
	private String prodname;
	private String notes;
	private Integer price;
	
	public Product(){
		
	}

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [prodid=" + prodid + ", prodname=" + prodname + ", notes=" + notes + ", price=" + price + "]";
	}


}

package shopping.cart.test;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//基本會gen getter&setter、toString()

@Entity
@Table(name="CUSTOMER")
public class Customer implements Serializable{
	//serialVersionUID用來作為Java對象序列化中的版本標示之用、用來表明類的不同版本間的兼容性
	private static final long serialVersionUID = 412293278100772603L;  //後來助解掉就可以正常跑toString()，應無關

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CUS_ID", unique=true)
	private String cusid;
	
	@Column(name="CUS_NAME")
	private String cusname;
	
	@Column(name="PASSWORD")
	private String password;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cusid")//, referencedColumnName="CART_ID") 
	private Cart cart;

	
	public String getCusid() {
		return cusid;
	}

	public void setCusid(String cusid) {
		this.cusid = cusid;
	}

	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Customer [cusid=" + cusid + ", cusname=" + cusname + ", password=" + password + ", cart=" + cart + "]";
	}
	
}

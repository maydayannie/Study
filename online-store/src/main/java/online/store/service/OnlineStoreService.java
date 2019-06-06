package online.store.service;

import java.util.List;

import model.CartDtl;
import model.Customer;
import model.Product;
import online.store.vo.ConditionProductVO;
import online.store.vo.PrdouctVO;
import online.store.vo.User;

public interface OnlineStoreService {
//AOP最好要用Interface實作
	public void logA();
	public void logB() throws Exception;
	public void doA();
	public void doB();
	public Customer query(String cusid) throws Exception;
	public Customer checkCustomer(String cusid , String pwd) throws Exception;
	public List<Product> searchProduct();
	public void addToCart(User user, String prodId);
	public List<CartDtl> searchCartdtl(String cartId);
	public void delFromCart(User user, String prodId);
	public void saveProduct(PrdouctVO vo);
	public void adminDeleteProduct(String prodId) throws Exception;
	public List<PrdouctVO> adSearchProduct(ConditionProductVO vo);
	
}


package online.store.service;

import java.util.List;

import model.Customer;
import model.Product;
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
	
}


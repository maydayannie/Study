package online.store.service;

import model.Customer;

public interface OnlineStoreService {
//AOP最好要用Interface實作
	public void logA();
	public void logB() throws Exception;
	public void doA();
	public void doB();
	public Customer query();
}


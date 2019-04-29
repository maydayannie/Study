package online.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.store.service.OnlineStoreService;

@Service
public class OnlineStoreServiceImpl implements OnlineStoreService{
	
	@Override
	public void logA() {
		System.out.println("A has been logged");
	}

	@Override
	public void logB() throws Exception {
		System.out.println("B has been logged");	
		throw new Exception("An error is occured!");
	}

	@Override
	public void doA() {
		System.out.println("A has been done");		
	}

	@Override
	public void doB() {
		System.out.println("B has been done");		
	}
}

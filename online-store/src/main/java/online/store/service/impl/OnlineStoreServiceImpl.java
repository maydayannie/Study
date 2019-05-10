package online.store.service.impl;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import model.Customer;
import online.store.dao.OnlineStoreDAO;
import online.store.service.OnlineStoreService;
import online.store.vo.User;

@Service
public class OnlineStoreServiceImpl implements OnlineStoreService {
	Logger logger = LogManager.getLogger(OnlineStoreServiceImpl.class);

	@Autowired
	private OnlineStoreDAO onlineStoreDAO;

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

	@Transactional
	@Override
	public Customer query(String cusid) throws Exception {	
//		Customer c = this.onlineStoreDAO.search("C001");
//		logger.info(c.toString());
		Customer c = null;

		try {
			c = this.onlineStoreDAO.search(cusid);
			System.out.println("login success");
		}catch(NullPointerException ex){
			ex.printStackTrace();
			System.out.println("查無此帳號");
			throw ex;		
		}		
		return c;
	}

	@Override
	public Customer checkCustomer(String cusid, String pwd) throws Exception {
		return onlineStoreDAO.checkLogin(cusid, pwd);
	}
}













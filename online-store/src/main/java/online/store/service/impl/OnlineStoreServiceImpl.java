package online.store.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.CartBase;
import model.CartDtl;
import model.CartDtlPK;
import model.Customer;
import model.Product;
import online.store.dao.OnlineStoreDAO;
import online.store.service.OnlineStoreService;
import online.store.vo.ConditionProductVO;
import online.store.vo.PrdouctVO;
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
	
	@Override
	public List<Product> searchProduct() {
		return onlineStoreDAO.allProduct();
	}

	@Override
	@Transactional
	public void addToCart(User user, String prodId) {
		CartBase cartBase = onlineStoreDAO.getCart(user.getId());
		List<CartDtl> cartDtls = cartBase.getCartDtls();
		boolean flag = false;
		for(CartDtl cartDtl : cartDtls) {
			if(prodId.equals(cartDtl.getProduct().getProdId())) {
				cartDtl.setQty(cartDtl.getQty() + 1);
				flag = true;
			} else {
				
			}
		}
		if(!flag) {
			CartDtl cartDtlTmp = new CartDtl();
			CartDtlPK cartDtlPk = new CartDtlPK();
			cartDtlPk.setCartId(cartBase.getCartId());
			cartDtlPk.setProdId(prodId);
			cartDtlTmp.setQty(1);
			cartDtlTmp.setId(cartDtlPk);
			onlineStoreDAO.persistCartDtl(cartDtlTmp);
		}
	}
	
	
	@Override
	public List<CartDtl> searchCartdtl(String cartId) {
		return onlineStoreDAO.cusCart(cartId);
	}
	
	@Override
	@Transactional
	public void delFromCart(User user, String prodId) {
		CartBase cartBase = onlineStoreDAO.getCart(user.getId());
		CartDtl cartDtlTmp = new CartDtl();
		CartDtlPK cartDtlPk = new CartDtlPK();
		cartDtlPk.setCartId(cartBase.getCartId());
		cartDtlPk.setProdId(prodId);
		cartDtlTmp.setId(cartDtlPk);
		onlineStoreDAO.removeCartDtl(cartDtlTmp);
	}

	@Override
	@Transactional
	public void saveProduct(PrdouctVO vo) {
		Product prod = onlineStoreDAO.findProduct(vo.getProdId());
		if(prod == null) {
			prod = new Product();
			BeanUtils.copyProperties(vo, prod);  //vo複製到prod
			onlineStoreDAO.persistProduct(prod);
		} else {
			BeanUtils.copyProperties(vo, prod);
		}
	}

	//寫法1
//	@Override
//	@Transactional(rollbackFor=Throwable.class)
//	public void adminDeleteProduct(String prodId) throws Exception{		
//		onlineStoreDAO.removeProduct(prodId);
//		onlineStoreDAO.removeCartprod(prodId);	
//	}
	
	//寫法2
	@Override
	@Transactional(rollbackFor=Throwable.class)
	public void adminDeleteProduct(String prodId) throws Exception{		
		Product productTmp = new Product();
		productTmp.setProdId(prodId);
		onlineStoreDAO.removeProduct(productTmp);   
		//admin刪除商品，要連同有把此商品加入購物車的也一起刪除
		onlineStoreDAO.removeCartprod(prodId);	
	}
		
	@Override
	//public List<Product> adSearchProduct(ConditionProductVO vo) {
	public List<PrdouctVO> adSearchProduct(ConditionProductVO vo) {
//		Product productTmp = new Product();
//		productTmp.setProdId(vo.getProdId());
//		productTmp.setProdName(vo.getProdName());
//
//		logger.info("admin search prodid=" + vo.getProdId() + "and prodname=" + vo.getProdName());
//		List<Product> productList = onlineStoreDAO.conditionProduct(productTmp);
//		List<PrdouctVO> voList = new ArrayList<PrdouctVO>();
//		for(Product to : productList) {
//			PrdouctVO pvo = new PrdouctVO();
//			BeanUtils.copyProperties(to, pvo);
//			voList.add(pvo);		
//		}	
		
		List<Product> productList = onlineStoreDAO.conditionProduct(vo);
		List<PrdouctVO> voList = new ArrayList<PrdouctVO>();
		for(Product to : productList) {
			PrdouctVO pvo = new PrdouctVO();
			BeanUtils.copyProperties(to, pvo);
			voList.add(pvo);		
		}
		return voList;
	}

	
	
	
	
	
	
	
	

	
}

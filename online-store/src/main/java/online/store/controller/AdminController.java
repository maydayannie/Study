package online.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import model.Product;
import online.store.constants.OnlineShopConstant;
import online.store.service.OnlineStoreService;
import online.store.vo.ConditionProductVO;
import online.store.vo.PrdouctVO;
import online.store.vo.User;

@Controller
@RequestMapping("/admin")  
//@RequestMapping用在類上，表示類中的所有響應請求的方法都是以該地址作為父路徑，可有可無
public class AdminController {
    Logger logger = LogManager.getLogger(ProductController.class);
	
	@Autowired
	private OnlineStoreService service;
	
	
	@RequestMapping(value="/allProduct",method = RequestMethod.GET)
	public String mycartProd(@SessionAttribute("user") User user, ModelMap model) {
		logger.info("This is user "+user.getName()+ " cart");
//		model.addAttribute("products", service.searchProduct());
		return "admin.product.admin-product";		
	}
	
	@RequestMapping(value="/getProducts", produces="application/json" ,method = RequestMethod.GET)
	public @ResponseBody List<PrdouctVO> getProducts(@SessionAttribute("user") User user) {
//		model.addAttribute("products", service.searchProduct());
		List<PrdouctVO> productVOs = new ArrayList<PrdouctVO>();
		List<Product> products = service.searchProduct();	
		for(Product product : products) {
			PrdouctVO prdouctVO = new PrdouctVO();
			BeanUtils.copyProperties(product, prdouctVO);
			productVOs.add(prdouctVO);
		}
		return 	productVOs;
	}
	
//	@RequestMapping(value="/saveProduct",consumes="application/json" ,method = RequestMethod.POST)
//	public @ResponseBody boolean saveProduct(@RequestBody PrdouctVO vo) {
//		model.addAttribute("products", service.searchProduct());
//		logger.info(vo);
//		service.saveProduct(vo);
//		return true;		
//	}
	
	@RequestMapping(value="/saveProduct" ,method = RequestMethod.POST)
	public @ResponseBody boolean saveProduct(@ModelAttribute PrdouctVO vo) throws IllegalStateException, IOException {
//		model.addAttribute("products", service.searchProduct());
		logger.info(vo);
		if(vo.getFile() != null) {
			String dir =  OnlineShopConstant.UPLOAD_PATH;
			String fileName = UUID.randomUUID().toString();
			String suffix = "";
			if(vo.getFile().getContentType().endsWith("jpeg")) {
				suffix = ".jpg";
			}
			vo.getFile().transferTo(new File(dir + fileName + suffix));
			vo.setFileName(fileName + suffix);
		}
		service.saveProduct(vo);
		return true;		
	}
	
	@RequestMapping(value="/delProd/{prodId}",method = RequestMethod.GET)
	public String delProd(@SessionAttribute("user") User user,@PathVariable("prodId") String prodId, ModelMap model) {
		logger.info("User "+user.getName()+ " deletes a product to the cart which id is "+ prodId);
		service.delFromCart(user, prodId);
		model.addAttribute("cusCart", service.searchCartdtl(user.getCartId()));
		return "view.cart.cart-form";		
	}
	
	//寫法1
	@RequestMapping(value="/adminDelProduct/{prodId}", method = RequestMethod.GET)
	//@RequestMapping用於方法上，表示在類的父路徑下追加方法上註解中的地址將會訪問到該方法
	//這個必須有，所以此方法的訪問地址就是127.0.0.1:8080/online-store/admin/adminDelProduct/商品名稱
	//@ResponseBody用於方法上，表示該方法的返回結果不會被解析為跳轉路徑
	public @ResponseBody String adminDelProduct(@PathVariable("prodId") String prodId, ModelMap model) throws Exception {
		logger.info("Admin deletes product:" + prodId);
		service.adminDeleteProduct(prodId);
		return "true";
	}
	
	//寫法2
//	@RequestMapping(value="/adminDelProduct/{prodId}", method = RequestMethod.GET)
//	public String adminDelProduct(@PathVariable("prodId") String prodId, ModelMap model) throws Exception {
//		logger.info("Admin deletes product:" + prodId);
//		service.adminDeleteProduct(prodId);
//		return "admin.product.admin-product";
//	}
	
	
	@RequestMapping(value="/adminSearchProducts", produces="application/json", method = RequestMethod.POST)
	public @ResponseBody List<PrdouctVO> adminSearchProducts(@RequestBody ConditionProductVO vo) {
//		logger.info("admin search prodid=" + vo.getProdId() + "and prodname=" + vo.getProdName());
		logger.info("this is vo: "+vo+"QQQQ");
		List<PrdouctVO> strList = new ArrayList<PrdouctVO>();
		strList = service.adSearchProduct(vo);
		logger.info("the results are : "+strList+"QQQQ");
		return strList;
	}

}


















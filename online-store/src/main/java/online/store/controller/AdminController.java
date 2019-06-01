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
import online.store.vo.PrdouctVO;
import online.store.vo.User;

@Controller
@RequestMapping("/admin")
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

}


















package online.store.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Product;
import online.store.service.OnlineStoreService;
import online.store.vo.User;


@Controller
@RequestMapping("/prod")
public class ProductController {
	Logger logger = LogManager.getLogger(ProductController.class);
	
	@Autowired
	private OnlineStoreService service;
	
	@RequestMapping(value="/doProd",method = RequestMethod.POST)
	public String doProd(@ModelAttribute("prodForm") Product product, ModelMap model) {
		List<Product> p = null;
		p = service.searchProduct();
		model.addAttribute("prod", p);
		return "view.product.product-form";		
	}
	
	@RequestMapping(value="/addProd/{prodId}",method = RequestMethod.POST)
	public String doProd(@ModelAttribute("user") User user,@PathVariable String prodId, ModelMap model) {
		logger.info("User "+user.getName() + " adds a product to the cart which id is "+ prodId);
		service.addToCart(user,prodId);
		return "view.product.product-form";		
	}
}







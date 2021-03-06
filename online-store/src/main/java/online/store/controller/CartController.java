package online.store.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import model.Product;
import online.store.service.OnlineStoreService;
import online.store.vo.User;

@Controller
@RequestMapping("/cart")
public class CartController{
    Logger logger = LogManager.getLogger(ProductController.class);
	
	@Autowired
	private OnlineStoreService service;
	
	@RequestMapping(value="/mycart",method = RequestMethod.GET)
	public String mycartProd(@SessionAttribute("user") User user, ModelMap model) {
		logger.info("This is user "+user.getName()+ " cart");
		
		model.addAttribute("cusCart", service.searchCartdtl(user.getCartId()));
		return "view.cart.cart-form";		
	}
	
	@RequestMapping(value="/delProd/{prodId}",method = RequestMethod.GET)
	public String delProd(@SessionAttribute("user") User user,@PathVariable("prodId") String prodId, ModelMap model) {
		logger.info("User "+user.getName()+ " deletes a product to the cart which id is "+ prodId);
		service.delFromCart(user, prodId);
		model.addAttribute("cusCart", service.searchCartdtl(user.getCartId()));
		return "view.cart.cart-form";		
	}

}


















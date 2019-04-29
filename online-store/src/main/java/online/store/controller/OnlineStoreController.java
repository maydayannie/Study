package online.store.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import online.store.service.OnlineStoreService;
import online.store.vo.User;

@Controller
@RequestMapping("/hello")
public class OnlineStoreController {
	
	@Autowired
	private OnlineStoreService service;

	@RequestMapping(value="/print",method = RequestMethod.GET)
	public String printHello(ModelMap model) {
		model.addAttribute("message", "Hello Spring MVC Framework!");
		return "hello";   //call hello.jsp
	}

	@RequestMapping(value="/printuser",method = RequestMethod.GET)
	public String printUser(ModelMap model) {
		User user =new User();
		user.setAge("36");
		user.setName("Hannibal");
		model.addAttribute("user", user);
		return "online/printUser";    //call online file printUser.jps
	}
	
	@RequestMapping(value="/testLog",method = RequestMethod.GET)
	public String testLog(ModelMap model) {
		model.addAttribute("message", "PringArgs");
//		service.doA();
//		service.doB();
		service.logA();
		try {
			service.logB();
		} catch (Exception e) {
		}
		return "online/print";
	}
	
	@RequestMapping(value="/none",method = RequestMethod.GET)
	public String none(ModelMap model) {
		model.addAttribute("message", "PringArgs");
		return "online/print1";
	}

}

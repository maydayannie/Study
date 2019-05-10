package online.store.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import model.Customer;
import online.store.service.OnlineStoreService;
import online.store.vo.User;

@Controller
@RequestMapping("/login")
@SessionAttributes("user")
public class LoginController {
	
	@Autowired
	private OnlineStoreService service;
	private static final String[] ALLOWED_FIELDS = new String[] { "cusId", "cusName"};
	Logger logger = LogManager.getLogger(LoginController.class);
	
	
	@ModelAttribute("user")
	public User getUser() {
		return new User();
	}
	

	@RequestMapping(value="/hello",method = RequestMethod.GET)
	public String hello(@ModelAttribute("user") User user) {
		if(user != null && user.getName() != null) {
			return "show";
		}
		return "login/login-form";  
	}
	
	@RequestMapping(value="/doLogin",method = RequestMethod.POST)
	//The @SessionAttribute indicates that an instance of Form object will be saved in the session after end of createForm invocation 
	//AND RETRIEVED from the session every time when the controller receives GET or POST reques
//	public String hello(@SessionAttribute("cusForm") Customer customer) {
	public String doLogin(@ModelAttribute("cusForm") Customer customer, ModelMap model) {
		Customer c = null;
		try {
			c = service.checkCustomer(customer.getCusId(), customer.getPassword());
			if(c != null ) {
				User u = new User();
				u.setAge("10");
				u.setName(c.getCusName());
				model.addAttribute("user", u);
			} else {
				model.addAttribute("errMsg", "Can't find your information");
				return "login/login-form";  
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "login/show";  
	}
	
	@RequestMapping(value = "/doLogout", method = RequestMethod.GET)
	public String doLogout(@ModelAttribute("user") User user, WebRequest request, SessionStatus status) {
		logger.info(user.toString());
	    status.setComplete();
	    request.removeAttribute("user", WebRequest.SCOPE_SESSION);
	    return "login/login-form";
	}
}

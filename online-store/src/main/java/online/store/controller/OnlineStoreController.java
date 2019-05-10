package online.store.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import model.Customer;
import online.store.service.OnlineStoreService;
import online.store.vo.User;

@Controller
@RequestMapping("/hello")
public class OnlineStoreController {
	
	private static final String[] ALLOWED_FIELDS = new String[] { "cusId", "cusName"};
	Logger logger = LogManager.getLogger(OnlineStoreController.class);
//	@Autowired
//	private OnlineStoreValidator validator;
	
//	@InitBinder(value = { "cusForm" })
//	protected void initBinder(WebDataBinder binder) {
//		binder.addValidators(validator);
//		binder.setAllowedFields(ALLOWED_FIELDS);
//	}
	
	@Autowired
	private OnlineStoreService service;
	@Autowired
	private OnlineStoreService service2;

	@RequestMapping(value="/print",method = RequestMethod.GET)
	public String printHello(ModelMap model) {	
		return "hello";   //call hello.jsp
	}
	
	@RequestMapping(value="/show",method = RequestMethod.POST)
	public String printHello(@ModelAttribute("cusForm2") Customer customer, ModelMap model) {	
//		Customer c = service.query("C002");
		Customer c = null;
		try {
			 c = service.query(customer.getCusId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("ex",e);
			String errMsg = "An error has occured";
			model.addAttribute("errMsg", errMsg);
			e.printStackTrace();
			return "hello";
		}
		logger.info(c.toString());
		model.addAttribute("customer", c);
		return "online/print";   //call hello.jsp
	}
	
//	@RequestMapping(value="/print",method = RequestMethod.GET)
//	public String printHello(ModelMap model) {	
//		Customer c = service.query("C003");
//		logger.info(c.toString());
//		model.addAttribute("customer", c);
//		return "hello";   //call hello.jsp
//	}
	

   @RequestMapping(value = "/customer", method = RequestMethod.GET)
   public ModelAndView customer() {
      return new ModelAndView("customer", "command", new Customer());
   }   
   
   @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
   public String addCustomer(@ModelAttribute("cusForm") Customer customer, BindingResult result,
   ModelMap model) {
	   logger.info("CusId:"+customer.getCusId());
	   logger.info("CusName:"+customer.getCusName());
      model.addAttribute("ccc", customer);
//      model.addAttribute("custname", customer.getCusName());
         
      return "online/print";
   }
   
   @RequestMapping(value = "/addCustomer2", method = RequestMethod.POST)
   public String addCustomer2(@Validated @ModelAttribute("cusForm") Customer customer, BindingResult result,
   ModelMap model) {
	   logger.info("CusId:"+customer.getCusId());
	   logger.info("CusName:"+customer.getCusName());
      model.addAttribute("ccc", customer);
//      model.addAttribute("custname", customer.getCusName());
         
      return "online/print";
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

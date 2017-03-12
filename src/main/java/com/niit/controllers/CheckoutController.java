package com.niit.controllers;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.niit.validator.AddressValidator;

import com.portal.models.Client;

@Controller
public class CheckoutController extends BasicController {
	@Autowired
	AddressValidator addressValidator;
	
	/**
	 * This method will add or update suppliers. It would first list all the
	 * suppliers and then allow user to add or update the suppliers. 
	 * 
	 * @param Supplier
	 *          -- The supplier value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object  
	 */
	@RequestMapping(value = "/user_check_out", method = RequestMethod.GET)
	public ModelAndView add_checkout(@ModelAttribute("command") Client user,BindingResult result, SessionStatus status) {
		Map<String, Object> model = new HashMap<String, Object>();
		String userName = get_current_user();
		model = addProductDetailsToCart(model,cartDAOImpl.parse_product_cart(userName));
		user = clientDAOImpl.getUserByUsername(userName);
		model.put("userDetails",user);
		model = getCategoriesForLanding(model);
		model.put("currentUser", get_current_user());
		return new ModelAndView("checkout", model);
	}
	
	/**
	 * This method will add or update suppliers. It would first list all the
	 * suppliers and then allow user to add or update the suppliers. 
	 * 
	 * @param Supplier
	 *          -- The supplier value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object  
	 */
	@RequestMapping(value = "/user_save_check_out", method = RequestMethod.POST)
	public ModelAndView save_checkout(@ModelAttribute("command") Client user,BindingResult result, SessionStatus status) {
		Map<String, Object> model = new HashMap<String, Object>();
		String userName = get_current_user();
		addressValidator.validate(user,result);
		Client user_db = clientDAOImpl.getUserByUsername(userName);
		user_db.setAddressline1(user.getAddressline1());
		user_db.setAddressline1(user.getAddressline2());
		user_db.setState(user.getState());
		user_db.setEmailid(user.getEmailid());
		if(result.hasErrors())
		{
			model.put("userDetails",user_db);
			model = getCategoriesForLanding(model);
			model = addProductDetailsToCart(model,cartDAOImpl.parse_product_cart(userName));
			model.put("currentUser", get_current_user());
			return new ModelAndView("checkout", model);
			
		}
		clientDAOImpl.addUser(user_db);
		model.put("userDetails",user_db);
		model = addProductDetailsToCart(model,cartDAOImpl.parse_product_cart(userName));
		model = getCategoriesForLanding(model);
		model.put("currentUser", get_current_user());
		return new ModelAndView("checkout_confirmed", model);
	}
	
}// End of the class

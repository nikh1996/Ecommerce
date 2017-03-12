package com.niit.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.portal.models.Client;


@Controller
public class helloController extends BasicController {
	/**
	 * This method would be called for the login screen. 
	 * @return
	 *       -- Model with the data stored in it.
	 */
	@RequestMapping(value = "/all_login", method = RequestMethod.GET)
	public ModelAndView triggerLogin() {
		Map<String, Object> model = new HashMap<String, Object>();
		 model = getCategoriesForLanding(model);
		System.out.println("In Login method");
		model.put("currentUser", get_current_user());
		return new ModelAndView("login", model);
	}

	
	/**
	 * This method would be called for the signup screen. 
	 * @return
	 *       -- Model with the data stored in it.
	 */

	@RequestMapping(value = "/all_signup", method = RequestMethod.GET)
	public ModelAndView triggerSignup(@ModelAttribute("command") Client client) {
		Map<String, Object> model = new HashMap<String, Object>();
		 model = getCategoriesForLanding(model);
		model.put("currentUser", get_current_user());
		System.out.println("In triggerSignup");
		return new ModelAndView("signup", model);
	}

	/**
	 * This method would be called for the saving user from signup. 
	 * @return
	 *       -- Model with the data stored in it.
	 */

	@RequestMapping(value = "/all_addUser", method = RequestMethod.POST)
	public ModelAndView addSignup(@ModelAttribute("command") Client client,BindingResult result, SessionStatus status) {
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("In addUser");
		signupFormValidator.validate(client,result);
		if(result.hasErrors())
		{   
			model = getCategoriesForLanding(model);
			model.put("currentUser", get_current_user());
			model.put("msg", "Kindly Rectify Errors and try again!!!!");
			return new ModelAndView("signup", model);
		}
	
		System.out.println("user is " + client);
		model = getProductsLanding(model);
		model = getCategoriesForLanding(model);
		model.put("currentUser", get_current_user());
		clientDAOImpl.addUser(client);
	    model.put("msg", "Registered Successfully, Please Login!");
		return new ModelAndView("landing", model);
	}

	/**
	 * This method would be called for the logged screen. 
	 * @return
	 *       -- Model with the data stored in it.
	 * @deprecated
	 */

	@RequestMapping(value = "/user_logged", method = RequestMethod.GET)
	public ModelAndView triggerLogged() {
		System.out.println("In printLanding");
		Map<String, Object> model = new HashMap<String, Object>();
		 model = getCategoriesForLanding(model);
		model.put("currentUser", get_current_user());
		return new ModelAndView("logged", model);
	}
}	
	
	
	

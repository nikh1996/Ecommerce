package com.flow.helpers;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.niit.controllers.BasicController;
import com.portal.models.Cart;
import com.portal.models.Client;
import com.portal.models.ViewData;

@Component
public class Flow_action_helper extends BasicController{
	/**
	 * This method will populate all the needed data for the checkout page.
	 * @param userName
	 *    -- Username
	 * @param viewData
	 *    -- The model to be passed
	 * @return
	 *    -- The populated model received as parameter. 
	 */
	public ViewData populateDataCheckoutPage(String userName,ViewData viewData){
		viewData.model = addProductDetailsToCart(viewData.model,cartDAOImpl.parse_product_cart(userName));
		Client user = clientDAOImpl.getUserByUsername(userName);
		System.out.println("populateDataCheckoutPage");
		System.out.println("user details are" + user.toString());
		viewData.setUserDetails(user);
		System.out.println("user details in viewData are" + viewData.getUserDetails().toString());
		viewData.model = getCategoriesForLanding(viewData.model);
		viewData.model.put("currentUser", get_current_user());
		return viewData;
	}
	
	/**
	 * This method would save data sent to it.
	 * @param userName
	 *             -- User name
	 * @param viewData
	 *             -- Model data that has to be saved.
	 */
	public void saveDataCheckoutPage(String userName,ViewData viewData){
		Client user = viewData.getUserDetails();
		System.out.println("saveDataCheckoutPage");
		System.out.println("user from view is "+user.toString());
		System.out.println("userName is "+userName);
		clientDAOImpl.addUser(user);
	}
	
	/**
	 * Populate the data for checkout confirmed page.
	 * @param userName
	 *          -- User name 
	 * @param viewData
	 *          -- Model data that has to be loaded with
	 * @return
	 *          -- Model that is loaded with data
	 */
	public ViewData populateDataCheckoutConfirmedPage(String userName,ViewData viewData){
		Client user_db = clientDAOImpl.getUserByUsername(userName);
		viewData.model.put("userDetails",user_db);
		viewData.model = addProductDetailsToCart(viewData.model,cartDAOImpl.parse_product_cart(userName));
		viewData.model = getCategoriesForLanding(viewData.model);
		viewData.model.put("currentUser", get_current_user());
		// Change the cart from user. To user_ordered_Timestamp.
		Cart ordered_cart = cartDAOImpl.getCartById(userName);
		String id  = userName + "_ordered_" + System.currentTimeMillis(); 
		ordered_cart.setId(id);
		cartDAOImpl.addCart(ordered_cart);
		cartDAOImpl.RemoveCart(cartDAOImpl.getCartById(userName));
		return viewData;
	}
	
	/**
	 * Return a initialized hash map
	 * @return
	 *    -- The hash map
	 */
	public Map<String, Object> initializeMapModel(){
		Map<String, Object> model = new HashMap<String,Object>();
		return model ;
	}
	
	
	
	
}

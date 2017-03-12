package com.niit.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.portal.models.Cart;
import com.portal.models.CartProduct;
import com.portal.models.Product;

@Controller
public class CartController extends BasicController {
	/**
	 * This method will add or update suppliers. It would first list all the
	 * suppliers and then allow user to add or update the suppliers. 
	 * 
	 * @param Supplier
	 *          -- The supplier value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object  
	 */
	@RequestMapping(value = "/user_add_cart", method = RequestMethod.GET)
	public ModelAndView add_Cart(@ModelAttribute("command") CartProduct cart_pdt) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		HashMap<String, String> pdt_id = new HashMap<String, String>();
		String userName = get_current_user();
		cart_pdt.setCart(prepareEmptyCart(userName));
		cart_pdt.setQuantity(cartDAOImpl.parse_product_cart(userName).get(cart_pdt.getProductId()));
		Product pdt_to_cart = productDAOImpl.getProductById(cart_pdt.getProductId());
		cart_pdt.setProductName(pdt_to_cart.getName());
		model.put("currentUser", userName);
		model.put("cartpdt", cart_pdt);
		model = getCategoriesForLanding(model);
		model.put("currentUser", get_current_user());
		model.put("isAdmin", isAdmin());
		pdt_id = cartDAOImpl.parse_product_cart(userName);
	    model = addProductDetailsToCart(model,pdt_id);
	    return new ModelAndView("add_cart", model);
	}
	
	
	
	/**
	 * This method would prepare a cart to be sent to the Cart View.
	 * @param userName
	 *           -- The user name. The cart id would be the same as userid so that every user gets a cart.
	 */
	public Cart prepareEmptyCart(String userName){
		
		Cart cart = cartDAOImpl.getCartById(userName);
		  if(cart == null){
		    	cart = new Cart();
				cart.setId(userName);	
		    	cart.setCount(0);
		    	cart.setCart(new String());
		    	cart.setName(userName);
		    	cartDAOImpl.addCart(cart);
		    	cart = cartDAOImpl.getCartById(userName);
		    }
		  return cart;
	}
	
	/**
	 * This method will be used to save the cart. This controller method
	 * would be invoked to save the cart and then the add method would
	 * be called.
	 * 
	 * @param Cart
	 *          -- The cart value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object 
	 */
	@RequestMapping(value = "/user_save_cart", method = RequestMethod.POST)
	public ModelAndView save_cart(@ModelAttribute("command") CartProduct cart_pdt) {
		String userName = get_current_user();
	    Cart cart;    
	    cart = cartDAOImpl.getCartById(userName);
	    cart.setCount(cart.getCount()+1);
	    String cart_new = cartDAOImpl.get_product_cart_string(userName,cart_pdt.getProductId(),cart_pdt.getQuantity());
	    System.out.println(cart_new);
	    cart.setCart(cart_new);
	    cartDAOImpl.addCart(cart);
	    return new ModelAndView("redirect:/user_add_cart?productId="+cart_pdt.getProductId());
	}

	
	/**
	 * This method will be used to edit the cart. This controller method
	 * would be invoked to save the  cart and then the add method would
	 * be called.
	 * 
	 * @param Supplier
	 *          -- The supplier value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object 
	 */
	@RequestMapping(value = "/user_edit_cart", method = RequestMethod.GET)
	public ModelAndView edit_cart(@ModelAttribute("command") CartProduct cart_pdt) {
		Map<String, Object> model = new HashMap<String, Object>();
		String userName = get_current_user();
		Cart cart = cartDAOImpl.getCartById(userName);
		cart_pdt.setCart(cart);
		cart_pdt.setProductName(productDAOImpl.getProductById(cart_pdt.getProductId()).getName());
		cart_pdt.setQuantity(cartDAOImpl.parse_product_cart(userName).get(cart_pdt.getProductId()));
		model.put("currentUser", get_current_user());
		model.put("cartpdt", cart_pdt);
		model = getCategoriesForLanding(model);
		model.put("currentUser", get_current_user());
		model = addProductDetailsToCart(model,cartDAOImpl.parse_product_cart(userName));
		return new ModelAndView("add_cart", model);
	}

	
	/**
	 * This method will be used to delete the cart. This controller method
	 * would be invoked to save the admin cart and then the add page method
	 * would be called.
	 * 
	 * @param Cart
	 *          -- The cart value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object 
	 */
	@RequestMapping(value = "/user_delete_cart", method = RequestMethod.GET)
	public ModelAndView delete_cart(@ModelAttribute("command")CartProduct cart_pdt) {
		System.out.println(cart_pdt.getProductId());
		Map<String, Object> model = new HashMap<String, Object>();
		String userName = get_current_user();
		Cart cart = cartDAOImpl.getCartById(userName);
		cart_pdt.setProductName(productDAOImpl.getProductById(cart_pdt.getProductId()).getName());
		cart_pdt.setCart(cart);
		cart.setCart(cartDAOImpl.get_product_cart_string_delete(userName,cart_pdt.getProductId()));
		model.put("currentUser", get_current_user());
		model = addProductDetailsToCart(model,cartDAOImpl.parse_product_cart(userName));
		model = getCategoriesForLanding(model);
		model.put("currentUser", get_current_user());
		return new ModelAndView("add_cart", model);
	}

	
}// End of the class

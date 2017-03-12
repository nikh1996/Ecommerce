package com.niit.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.portal.models.Category;
import com.portal.models.Client;
import com.portal.models.Product;

@Controller
public class productController extends BasicController{
	
	/**
	 * This method will be used to edit the category. This controller method
	 * would be invoked to save the admin category and then the add method would
	 * be called.
	 * 
	 * @param Category
	 *          -- The category value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object 
	 */
	@RequestMapping(value = "/all_show_product", method = RequestMethod.GET)
	public ModelAndView edit_category(@ModelAttribute("command") Product product) {
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("Product is "+product);
		model = getCategoriesForLanding(model);
        model.put("currentUser", get_current_user());
	    model.put("isAdmin", isAdmin());
	    product = productDAOImpl.getProductById(product.getId());
		model.put("product", productDAOImpl.getProductById(product.getId()));
		String categoryID = product.getCategoryid();
		String SupplierID = product.getSupplierid();
		System.out.println("categoryID->"+categoryID);
		System.out.println("SupplierID->"+SupplierID);
		model.put("productCategory", categoryDAOImpl.getCategoryById(categoryID));
		model.put("supplierCategory", supplierDAOImpl.getSupplierById(SupplierID));
		return new ModelAndView("show_product", model);
	}

	/**
	 * This method will be used to edit the category. This controller method
	 * would be invoked to save the admin category and then the add method would
	 * be called.
	 * 
	 * @param Category
	 *          -- The category value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object 
	 */
	@RequestMapping(value = "/all_show_sub_category", method = RequestMethod.GET)
	public ModelAndView edit_category(@ModelAttribute("command") Category category) {
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("Category is "+category);
		model = getCategoriesForLanding(model);
        model.put("currentUser", get_current_user());
	    model.put("isAdmin", isAdmin());
	    model.put("products", productDAOImpl.getProductsByCategory(category.getId()));
	    model.put("category", categoryDAOImpl.getCategoryById(category.getId()));
	   return new ModelAndView("show_category", model);
		
	}




}

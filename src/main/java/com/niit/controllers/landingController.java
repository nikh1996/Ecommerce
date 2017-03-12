package com.niit.controllers;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.portal.models.Product;



@Controller
public class landingController extends BasicController{

    
	/**
	 * This method would be called for the landing screen. 
	 * @return
	 *       -- Model with the data stored in it.
	 */

	 @RequestMapping(value = "/all_landing", method = RequestMethod.GET)
	   public ModelAndView printLanding() {
	      Map<String, Object> model = new  HashMap<String, Object>();
	      model = getCategoriesForLanding(model);
	      model = getProductsLanding(model);
	      model.put("currentUser", get_current_user());
		  model.put("isAdmin", isAdmin());
		  model = getProductsSearch(model);
	      model.put("message", "Hello Spring MVC Framework!");
	      return new ModelAndView("landing", model);
	   }
	
	 /**
	  * This method would be called for landing screen. It is a get request for products. 
	  * @return
	  *       -- Model with the data stored in it.
	  */

	 @RequestMapping(value = "/all_search_pdts", method = RequestMethod.GET)
	   public @ResponseBody String get_products_search_landing() {
		 String ret_val = "[";
		 JSONObject prdts_print = new JSONObject();
		 Map<String, Object> model = new  HashMap<String, Object>();
	      model = getProductsSearch(model);
	      List<Product> product_list =   (List<Product>) model.get("products_search");
	      for (Product pdt : product_list){
	    	  if(!ret_val.equals("["))ret_val = ret_val + ",";
	    	  JSONObject current_pdt = new JSONObject();
	    	  current_pdt.put("id",pdt.getId());
	    	  current_pdt.put("name",pdt.getName());
	    	  ret_val = ret_val + current_pdt.toJSONString();
	     }
	      ret_val = ret_val + "]";
	      
	      return ret_val;
	   }
		
	}
	 



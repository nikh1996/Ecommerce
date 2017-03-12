package com.niit.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.niit.validator.SignupFormValidator;
import com.portal.daos.CartDAOImpl;
import com.portal.daos.CategoryDAOImpl;
import com.portal.daos.ClientDAOImpl;
import com.portal.daos.ProductDAOImpl;
import com.portal.daos.SupplierDAOImpl;
import com.portal.models.CartProduct;
import com.portal.models.Category;
import com.portal.models.Product;

public class BasicController {

public ClientDAOImpl clientDAOImpl = (ClientDAOImpl) application_context.getBean("clientDAOImpl");
String productPath = "C:\\Users\\vyasnikh\\Ecommerce\\src\\main\\webapp\\WEB-INF\\images\\product";
public CategoryDAOImpl categoryDAOImpl = (CategoryDAOImpl) application_context.getBean("categoryDAOImpl");
public ProductDAOImpl productDAOImpl = (ProductDAOImpl) application_context.getBean("productDAOImpl");
public SupplierDAOImpl supplierDAOImpl = (SupplierDAOImpl)application_context.getBean("supplierDAOImpl");
public CartDAOImpl cartDAOImpl = (CartDAOImpl)application_context.getBean("cartDAOImpl");
public static ClassPathXmlApplicationContext application_context = new ClassPathXmlApplicationContext("spring_beans.xml");

@Autowired
SignupFormValidator signupFormValidator;
/**
 * This method will get the current user from the spring security. 
 * @return -- String value, user name of the user. 
 */
public String get_current_user(){
	String user = "";
   Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
   System.out.println("principal"+principal);
   user = principal.toString();
   if(user.isEmpty() || user.equals("anonymousUser"))user = "Guest";
   return user;
   }

/**
 * This method would check if the user is admin and would return the value. 
 * @return
 * 	-- Boolean value, true if user is admin. 
 */
public boolean isAdmin(){
	boolean admin = false;
	@SuppressWarnings("unchecked")
Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
for (SimpleGrantedAuthority authority : authorities){
System.out.println("authority is " + authority.toString());
if(authority.toString().equals("ROLE_ADMIN")){
admin = true;
 break;
}
}
return admin;
}
	
/**
 * This method will get the products list that are needed for the landing page and would send it to the model.
 * @param model
 * @return
 */
@SuppressWarnings("unchecked")
public Map<String,Object> getProductsLanding(Map<String, Object> model){
	 ArrayList<Product> prodDealList = new ArrayList<Product>();
	 ArrayList<Product> prodFeatureList = new ArrayList<Product>();
	 List<Product> list_product = productDAOImpl.getProducts();
	 Collections.shuffle(list_product);
	 int i =0;
	 int length = list_product.size();
	 if(length>5) length = 5;
	 for(i=0;i<length;i++) prodDealList.add(list_product.get(i));
	 Collections.shuffle(list_product);
	 for(i=0;i<length;i++) prodFeatureList.add(list_product.get(i));
	 model.put("productsDealsList", prodDealList);
	 model.put("productsFeatureList", prodFeatureList);
	 for(Product disp: (ArrayList<Product>) model.get("productsDealsList")){
	 System.out.println("productsDealsList__Id -> "+ disp.getId());
 	 System.out.println("productsDealsList__Name -> "+ disp.getName());
	 }
	 for(Product disp: (ArrayList<Product>) model.get("productsFeatureList")){
	 System.out.println("productsFeatureList__Id -> "+ disp.getId());
	 System.out.println("productsFeatureListt__Name -> "+ disp.getName());
	 }
	 return model;
}


/**
 * This method will get the products list that are needed for the landing page and would send it to the model.
 * @param model
 * @return
 */
public Map<String,Object> getProductsSearch(Map<String, Object> model){
	  List<Product> products = productDAOImpl.getProducts();
	  model.put("products_search", products);
	  return model;
}




/**
 * This method would get the list of categories to be populated in the navigation bar. 
 * @param model
 * 		-- The model in which the categories have to be stored. 
 * @return
 *       -- The model, after populating the categories. 
 */
public  Map<String, Object> getCategoriesForLanding( Map<String, Object> model){
	
	 Category category;
	
	 HashMap<String, ArrayList<Category>> categories_map = new HashMap<String,ArrayList<Category>>();
	 HashMap<String, ArrayList<Category>> other_categories_map = null;
	 ArrayList<Category> values;
	 List<Category> list_categories =  categoryDAOImpl.getCategories();
	 Iterator<Category> iterator = list_categories.iterator();
	 String key;
	 
	 int i =0;
	 /**
  * The below while loop would store all the categories in the format { categoryName => [categories] }
  */
 while (iterator.hasNext()) {
	 category = iterator.next();
     key = category.getName();
	 values = categories_map.get(key);
	 if(!(values instanceof ArrayList<?>))values = new ArrayList<Category>();
	 values.add(category);
	 categories_map.put(key, values);
 }
 Iterator<String> keySetIterator = categories_map.keySet().iterator();
 while(keySetIterator.hasNext()){
   String key_category = keySetIterator.next();
   i = i + 1;
   if(i>=1 && i<=3){ 
	   model.put("category"+i,categories_map.get(key_category));
   model.put("category"+i+"Name",key_category);
   } 
   if(i>=4) {
	if(!(other_categories_map instanceof HashMap<?,?>)) other_categories_map = new HashMap<String,ArrayList<Category>>();
	other_categories_map.put(key_category,categories_map.get(key_category));
   }
  }	
  model.put("othercategory",other_categories_map);
	  return model;
	 }
	 

/**
 * This method will add product related details to the model and would return it. Thr Object in the model
 * has to be a <b> CartProduct </b> object.
 * @param model
 * @return
 */
public Map<String, Object> addProductDetailsToCart(Map<String, Object> model,HashMap<String,String> cartMap){
	int totalcost =0;
	int cost = 0;
	int totalQty = 0;
	int qty= 0;
	Map<String,CartProduct> cartpdts = new HashMap<String,CartProduct>();
	String ProductId;
	CartProduct cart_pdt = null;
    Product pdt;
	Iterator<String> iterator_obj = cartMap.keySet().iterator();
	while (iterator_obj.hasNext())
	{   
		ProductId = iterator_obj.next();
		pdt = productDAOImpl.getProductById(ProductId);
    	cart_pdt = new CartProduct();
    	cart_pdt.setProductId(ProductId);
    	cart_pdt.setProductMrp(pdt.getMrp());
    	cart_pdt.setProductName(pdt.getName());
    	cart_pdt.setProductPrice(pdt.getOfferprice());
    	cart_pdt.setQuantity(cartMap.get(ProductId));
    	qty = Integer.parseInt(cartMap.get(ProductId));
    	cost = (Integer.parseInt(pdt.getOfferprice())*qty);
    	cart_pdt.setTotalCost(String.valueOf(cost));
    	System.out.println("Cost is "+ cost);
    	cartpdts.put(ProductId,cart_pdt);
    	totalcost = totalcost+cost;
    	totalQty = totalQty + qty;
	}
	model.put("cartpdts",cartpdts);
	model.put("cartpdts_qty",totalQty);
	model.put("cartpdts_cost",totalcost);
	return model;
} 
	 
	 	
}

package com.niit.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.validator.AdminProductValidator;
import com.portal.models.Category;
import com.portal.models.Product;

@Controller
public class AdminProductController extends BasicController {
	@Autowired
	AdminProductValidator adminProductValidator;
	/**
	 * This method will add or update products. It would first list all the
	 * products and then allow user to add or update the products. 
	 * 
	 * @param Product
	 *          -- The product value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object  
	 */
	@RequestMapping(value = "/admin_add_product", method = RequestMethod.GET)
	public ModelAndView add_product(@ModelAttribute("command") Product product) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("currentUser", get_current_user());
		model.put("product",get_product_with_id(product));
		model.put("products", productDAOImpl.getProducts());
		model.put("suppliers", supplierDAOImpl.getSuppliers());
		model.put("categories", categoryDAOImpl.getCategories());
		return new ModelAndView("add_product", model);
	}
	


	
	
	/**
	 * This method will be used to save the product. This controller method
	 * would be invoked to save the admin product and then the add method would
	 * be called.
	 * 
	 * @param Product
	 *          -- The product value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object 
	 */
	@RequestMapping(value = "/admin_save_product", method = RequestMethod.POST)
	public ModelAndView save_product(@ModelAttribute("command") Product product,@RequestParam("productImage") MultipartFile file,BindingResult result, SessionStatus status) {
		String currentDir = System.getProperty("user.dir");
		
        System.out.println("Current dir using System:" +currentDir);
        System.out.println("file:" +file);
        System.out.println(AdminProductController.class.getProtectionDomain().getCodeSource().getLocation());
        System.out.println("getImagepath:" +product.getImagepath());
        System.out.println("file.getName:" +file.getName());
        product.setImage_upload_path(file.getName());
     
        adminProductValidator.validate(product,result);
		if(result.hasErrors())
		{   	
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("currentUser", get_current_user());
		model.put("product",get_product_with_id(product));
		model.put("products", productDAOImpl.getProducts());
		model.put("suppliers", supplierDAOImpl.getSuppliers());
		model.put("categories", categoryDAOImpl.getCategories());
		return new ModelAndView("add_product", model);
		}
        
        productDAOImpl.addProduct(product);
		String path= productPath+"\\"+String.valueOf(product.getId())+".jpg"; 
		if(!file.isEmpty()){
			File output_file = new File(path);
			try
			{
			  byte[] bytes=file.getBytes();
			  System.out.println(bytes.length);
			  FileOutputStream fos=new FileOutputStream(output_file);
              BufferedOutputStream bs=new BufferedOutputStream(fos);
              			bs.write(bytes);
              			bs.close();
             			 System.out.println("File Uploaded Successfully");
			}
			catch(Exception e)
			{
				System.out.println("Exception Arised"+e);
			}
			
			
		}
		
		
		return new ModelAndView("redirect:/admin_add_product");
	}

	
	
	/**
	 * This method will be used to edit the product. This controller method
	 * would be invoked to save the admin product and then the add method would
	 * be called.
	 * 
	 * @param Product
	 *          -- The product value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object 
	 */
	@RequestMapping(value = "/admin_edit_product", method = RequestMethod.GET)
	public ModelAndView edit_product(@ModelAttribute("command") Product product) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("currentUser", get_current_user());
		model.put("product", productDAOImpl.getProductById(product.getId()));
		model.put("products", productDAOImpl.getProducts());
		model.put("suppliers", supplierDAOImpl.getSuppliers());
		model.put("categories", categoryDAOImpl.getCategories());
		return new ModelAndView("add_product", model);
	}

	
	/**
	 * This method will be used to delete the product. This controller method
	 * would be invoked to save the admin product and then the add page method
	 * would be called.
	 * 
	 * @param Product
	 *          -- The product value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object 
	 */
	@RequestMapping(value = "/admin_delete_product", method = RequestMethod.GET)
	public ModelAndView delete_product(@ModelAttribute("command") Product product) {
		productDAOImpl.RemoveProduct(product);
		return new ModelAndView("redirect:/admin_add_product");
	}
	
	
	
	/**
	 * This method will set the product id for the web page.
	 * @param product
	 *          -- The model to be sent
	 * @return
	 *          -- The model with id populated
	 */
	public Product get_product_with_id(Product product){
		List<Product> product_list = productDAOImpl.getProducts();
		int[] indexes = new int[product_list.size()];
		int index = 0;
		System.out.println(product.getId());
		if(product.getId() == null || product.getId().isEmpty()){
			for(Product searchIndex : product_list){
				System.out.println(searchIndex.getId());
				indexes[index] = Integer.parseInt(searchIndex.getId());
				index = index + 1;
			}
			index = 0;
			Arrays.sort(indexes);
			for(int i : indexes){
				if(index==i){
					index = index +1;
					continue;
				}
				else{
					break;
				}
				
			}
			product.setId(String.valueOf(index));
		
		}
		return product;
	}
	
}// End of the class

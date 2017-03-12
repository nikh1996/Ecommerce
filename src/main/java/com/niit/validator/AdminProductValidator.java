package com.niit.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.portal.models.Client;
import com.portal.models.Product;
 


@Component
public class AdminProductValidator implements Validator{

	   public boolean supports(Class<?> clazz) {
	        return Client.class.isAssignableFrom(clazz);
	    }	
	
	
	   public void validate(Object target, Errors errors) {
		   System.out.println("VALIDATE");
		   Product product = (Product)target;  
		   String id = product.getId();  
		   String name= product.getName();
		   String supplierid= product.getSupplierid();
		   String categoryid= product.getCategoryid();
		   String description= product.getDescription();
		   String mrp= product.getMrp();
		   String offerprice= product.getOfferprice();
		   String quantity= product.getQuantity();
		   String image_upload_path= product.getImage_upload_path();
			
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "error.id", "Id is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name", "Name is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "supplierid", "error.supplierid", "Supplierid is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryid", "error.categoryid", "Categoryid is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description", "Description is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mrp", "error.mrp", "Mrp is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "offerprice", "error.offerprice", "Offerprice is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "error.quantity", "Quantity is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "image_upload_path", "error.image_upload_path", "Image_upload_path is required.");
		             
		  // Additional validations on length and type. 
		  Pattern notAlphaNumeric = Pattern.compile("[^a-zA-Z0-9\\s]");
		  Pattern notNumber = Pattern.compile("[^0-9]");
		  
		  if(notAlphaNumeric.matcher(id).find() || id.length()>10) errors.rejectValue("id", "id.incorrect","Id should be Alpha Numeric  less than 10 characters.");		
		  if(name.length()>30 || name.length()<3) errors.rejectValue("name", "name.incorrect","Name should be 3 - 30 characters.");
		  if(description.length()>200 || description.length()<10) errors.rejectValue("description", "description.incorrect","Description should be 10 - 200 characters.");
		  if(notNumber.matcher(mrp).find()) errors.rejectValue("mrp", "mrp.incorrect","Mrp should be Numeric.");
		  if(notNumber.matcher(offerprice).find()) errors.rejectValue("offerprice", "offerprice.incorrect","Offerprice should be Numeric.");
		  if(notNumber.matcher(quantity).find()) errors.rejectValue("quantity", "quantity.incorrect","Quantity should be Numeric.");
				 
		   }
	
	
	
	
	
	
	
	
	
	
	
	
}

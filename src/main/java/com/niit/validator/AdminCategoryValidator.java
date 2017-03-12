package com.niit.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.portal.models.Category;
 


@Component
public class AdminCategoryValidator implements Validator{

	   public boolean supports(Class<?> clazz) {
	        return Category.class.isAssignableFrom(clazz);
	    }	
	
	
	   public void validate(Object target, Errors errors) {
		   System.out.println("VALIDATE");
		   Category category = (Category)target;  

		   String id = category.getId(); 
		   String name = category.getName();
		   String subcategory= category.getSubcategory();
		   String description = category.getDescription();
		   

		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "error.id", "Id is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.firstname", "Name is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subcategory", "error.lastname", "Sub category is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.middlename", "Description is required.");
		            
		  // Additional validations on length and type. 
		  Pattern notAlphaNumeric = Pattern.compile("[^a-zA-Z0-9\\s]");
		  
		  if(notAlphaNumeric.matcher(id).find() ||  id.length()>10) errors.rejectValue("id", "id.incorrect","Id should be Alpha Numeric and < 10 characters.");		
		  if(notAlphaNumeric.matcher(name).find() || name.length()>30 || name.length()<3 ) errors.rejectValue("name", "name.incorrect","Name should be Alpha Numeric and 3 - 30 characters.");
		  if(description.length()>200 || description.length()<10) errors.rejectValue("description", "description.incorrect","Description should be 10 - 200 characters.");
		   
	   }
	
	
	
	
	
	
	
	
	
	
	
	
}

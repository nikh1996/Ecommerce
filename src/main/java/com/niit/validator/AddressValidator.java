package com.niit.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.portal.models.Client;
 


@Component
public class AddressValidator implements Validator{
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	   public boolean supports(Class<?> clazz) {
	        return Client.class.isAssignableFrom(clazz);
	    }	
	
	
	   public void validate(Object target, Errors errors) {
		  Client customer = (Client)target;  
		  String addressline1 = customer.getAddressline1();
		  String addressline2 = customer.getAddressline2();
		  String emailid = customer.getEmailid();
		  String state = customer.getState();
		   
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressline1", "error.addressline1", "Addressline 1 is required.");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressline2", "error.addressline2", "Addressline 1 is required.");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailid", "error.emailid", "Email Id is required.");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "error.state", "State is required.");
		
		           
		  // Additional validations on length and type. 
	    //  Pattern notAlphaNumeric = Pattern.compile("[^a-zA-Z0-9\\s]");
		  Pattern email = Pattern.compile(EMAIL_PATTERN);
		  
		  if(addressline1.length()<3 || addressline1.length()>50) errors.rejectValue("addressline1", "addressline1.incorrect","Address Line 1 should be  3-50 characters.");		
		  if(addressline2.length()<3 || addressline2.length()>50) errors.rejectValue("addressline2", "addressline2.incorrect","Address Line 2 should be  3-50 characters.");
		  if(!email.matcher(emailid).find() || emailid.length()>20) errors.rejectValue("emailid", "emailid.incorrect","Email Id should be a valid email address and less than 20 characters.");
		  if(state.length()>10 || state.length()<3) errors.rejectValue("state", "state.incorrect","State should be 3 - 10 characters.");
		 	 
	   }
	
	
	
	
	
	
	
	
	
	
	
	
}

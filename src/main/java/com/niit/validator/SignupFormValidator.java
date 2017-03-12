package com.niit.validator;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.niit.controllers.BasicController;
import com.portal.daos.ClientDAOImpl;
import com.portal.models.Client;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * TO DO
 * 1. Check if the username is already present. 
 * @author irzaai
 *
 */

@Component
public class SignupFormValidator implements Validator {
	public ClientDAOImpl clientDAOImpl = (ClientDAOImpl) BasicController.application_context.getBean("clientDAOImpl");

	   public boolean supports(Class<?> clazz) {
	        return Client.class.isAssignableFrom(clazz);
	    }	
	
	
	   public void validate(Object target, Errors errors) {
		   System.out.println("VALIDATE");
		   Client customer = (Client)target;  
		   String uname = customer.getUname();
		   String firstname = customer.getFirstname();
		   String lastname = customer.getLastname();
		   String middlename = customer.getMiddlename();
		   String sex = customer.getSex();
		   String personalphone = customer.getPersonalphone();
		   String password = customer.getPassword();
		   String confirmpassword = customer.getConfirmpassword();
		   
		
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uname", "error.uname", "User name name is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "error.firstname", "First name is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "error.lastname", "Last name is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "error.sex", "Sex is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personalphone", "error.personalphone", "Personal phone is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password", "Password is required.");
		   
		   List<Client> stored_user = clientDAOImpl.getClients();
		   for(Client index:stored_user){
		   if(index.getUname().equalsIgnoreCase(uname)){
			   errors.rejectValue("uname", "uname.incorrect","Username already taken, please try something else.");
			   break;
		   }
		   }
		   
		  // Additional validations on length and type. 
		  Pattern notAlphaNumeric = Pattern.compile("[^a-zA-Z0-9\\s]");
		  Pattern AlphaNumeric = Pattern.compile("[a-zA-Z0-9\\s]");
		  Pattern notNumber = Pattern.compile("[^0-9]");
		  
		  if(notAlphaNumeric.matcher(uname).find() || uname.length()<3 || uname.length()>15) errors.rejectValue("uname", "uname.incorrect","Username should be Alpha Numeric and 3-15 characters.");		
		  if((!AlphaNumeric.matcher(firstname).find()) || firstname.length()<3 || firstname.length()>15) errors.rejectValue("firstname", "firstname.incorrect","firstname should be Alpha Numeric and 3-15 characters.");
		  if((!AlphaNumeric.matcher(lastname).find()) || lastname.length()<3 || lastname.length()>15) errors.rejectValue("lastname", "lastname.incorrect","lastname should be Alpha Numeric and 3-15 characters.");
		  if(middlename.length()>0){
		  if((!AlphaNumeric.matcher(middlename).find())|| middlename.length()<3 || middlename.length()>15) errors.rejectValue("middlename", "middlename.incorrect","middlename should be Alpha Numeric and 3-15 characters.");
		  
		  }
		  if((!sex.equals("Male") && !sex.equals("Female"))) errors.rejectValue("sex", "sex.incorrect","sex should only be Male or Female");
		  if(notNumber.matcher(personalphone).find()||!(personalphone.length()==10)) errors.rejectValue("personalphone", "personalphone.incorrect","Phone Number should be a 10 digit Number only.");
		  if(password.length()<=3 || password.length()>=11) errors.rejectValue("password", "password.incorrect","Password should be from 4 - 10 characters.");
		  if (!password.equals(confirmpassword)) {errors.rejectValue("confirmpassword", "password.mismatch","Passwords dont match."); }  
			 
	   }
	
	
	
	
	
	
	
	
	
	
	
	
}

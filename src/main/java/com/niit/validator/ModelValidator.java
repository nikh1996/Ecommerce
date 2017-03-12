package com.niit.validator;
import java.util.HashMap;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.portal.models.Client;

@Component
public class ModelValidator{


/**
* Validates 'addressForm' view state after binding to address.
* Spring Web Flow activated validation ('validate' + ${state}).
*/
public void validateCheckout(HashMap<String,Object> model, ValidationContext context) {
	
	System.out.println("IN DEFAULT VALIDATOR OF WEB FLOW.");
	Client user = ((Client) model.get("userDetails"));
	MessageContext messages = context.getMessageContext();
	  if (user.getAddressline1().isEmpty()) {
          messages.addMessage(new MessageBuilder().error().source("addressline1").
              defaultText("Address Line 1 must not be empty").build());
      } 
	  if (user.getAddressline2().isEmpty()) {
          messages.addMessage(new MessageBuilder().error().source("addressline2").
              defaultText("Address Line 2 must not be empty").build());
      } 
	  if (user.getEmailid().isEmpty()) {
          messages.addMessage(new MessageBuilder().error().source("emailid").
              defaultText("Email Id must not be empty").build());
      } 
	  if (user.getState().isEmpty()) {
          messages.addMessage(new MessageBuilder().error().source("state").
              defaultText("State must not be empty").build());
      } 
	  
	  
	  
	  
}
	
	
}

package com.portal.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.niit.controllers.BasicController;
import com.portal.daos.ClientDAOImpl;

import com.portal.models.Client;

/**
 * A custom authentication manager that allows access if the user details
 * exist in the database and if the username and password are not the same.
 * Otherwise, throw a {@link BadCredentialsException}
 */
public class CustomAuthenticationManager implements AuthenticationManager {
	ClientDAOImpl clientDAOImpl = (ClientDAOImpl) BasicController.application_context.getBean("clientDAOImpl");
 
 // Our custom DAO layer
  
 // We need an Md5 encoder since our passwords in the database are Md5 encoded. 
   
 public Authentication authenticate(Authentication auth)
   throws AuthenticationException {
 
   System.out.println("Performing custom authentication");
 
  // Init a database user object
  Client client = null;
  if(auth.getName() ==null || auth.getName().isEmpty())throw new BadCredentialsException("No User!");
  client = clientDAOImpl.getUserByUsername((auth.getName()));
  if(!(client instanceof Client))
  {
   throw new BadCredentialsException("User does not exists!");
  }
  System.out.println(auth.getName());
  System.out.println(auth.getCredentials().toString());
  System.out.println(client.getFirstname());
  System.out.println(client.getPassword());
  if (!auth.getCredentials().toString().equals(client.getPassword()) ) {
	  System.out.println("Wrong password!");
      throw new BadCredentialsException("Wrong password!");
  }
   System.out.println("User details are good and ready to go");
   return new UsernamePasswordAuthenticationToken(
     auth.getName(), 
     auth.getCredentials(), 
     getAuthorities(client.isAdmin()));
  }

  
 /**
  * Retrieves the correct ROLE type depending on the access level, where access level is an Integer.
  * Basically, this interprets the access value whether it's for a regular user or admin.
  * 
  * @param access an integer value representing the access of the user
  * @return collection of granted authoriies
  */
  public Collection<GrantedAuthority> getAuthorities(boolean admin) {
   // Create a list of grants for this user
   List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
    
   // All users are granted with ROLE_USER access
   // Therefore this user gets a ROLE_USER by default
   System.out.println("Grant ROLE_USER to this user");
   
   authList.add(new SimpleGrantedAuthority("ROLE_USER"));
    
   // Check if this user has admin access 
   // We interpret Integer(1) as an admin user
   if (admin) {
    // User has admin access
	System.out.println("Grant ROLE_ADMIN to this user");
    authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
   }
 
   // Return list of granted authorities
   return authList;
   }
 
}
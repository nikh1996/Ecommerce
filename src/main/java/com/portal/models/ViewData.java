package com.portal.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ViewData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Client userDetails = new Client();
	public Map<String,Object> model = new  HashMap<String,Object>();
	public Client getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(Client userDetails) {
		this.userDetails = userDetails;
	}



	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}

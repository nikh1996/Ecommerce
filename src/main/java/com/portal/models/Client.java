package com.portal.models;

import java.io.Serializable;

public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uname;  
	private String firstname;
	private String lastname;
	private String middlename;
	private String sex;
	private String personalphone;
	private String password;
	private String confirmpassword;
	private String addressline1;
	private String addressline2;
	private String state;
	private String emailid;
	private String workphone;
	private String cart;
	private String cards;
	private boolean enabled;
	private boolean admin;
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPersonalphone() {
		return personalphone;
	}
	public void setPersonalphone(String personalphone) {
		this.personalphone = personalphone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddressline1() {
		return addressline1;
	}
	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}
	public String getAddressline2() {
		return addressline2;
	}
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getWorkphone() {
		return workphone;
	}
	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}
	public String getCart() {
		return cart;
	}
	public void setCart(String cart) {
		this.cart = cart;
	}
	public String getCards() {
		return cards;
	}
	public void setCards(String cards) {
		this.cards = cards;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

public String toString(){
	String ret = "  Client, ";
	ret = ret + "  username = "+ uname;
	ret = ret + "  firstname = "+ firstname;
	ret = ret + "  middlename = "+ middlename;
	ret = ret + "  lastname = "+ lastname;
	ret = ret + "  sex = "+ sex;
	ret = ret + "  personalphone = "+ personalphone;
	ret = ret + "  addressline1 = "+ addressline1;
	ret = ret + "  addressline2 = "+ addressline2;
	ret = ret + "  state = "+ state;
	ret = ret + "  emailid = "+ emailid;
	ret = ret + "  workphone = "+ workphone;
	ret = ret + "  enabled = "+ enabled;
	ret = ret + "  admin = "+ admin;
	return ret;

	
}
	
	
}

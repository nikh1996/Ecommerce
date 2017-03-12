package com.portal.models;

import java.io.Serializable;

public class CartProduct implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cart Cart;    
    private String productId;
    private String quantity;
    private String productName;
    private String productPrice;
    private String productMrp;
    private String totalCost;
    
    
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductMrp() {
		return productMrp;
	}
	public void setProductMrp(String productMrp) {
		this.productMrp = productMrp;
	}
	public Cart getCart() {
		return Cart;
	}
	public void setCart(Cart cart) {
		Cart = cart;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
    	}

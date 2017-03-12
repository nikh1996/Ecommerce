package com.portal.daos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import com.portal.models.Cart;

public class CartDAOImpl{

SessionFactory cartSessionFactory;
	
public SessionFactory getCartSessionFactory() {
	return cartSessionFactory;
}

public void setCartSessionFactory(SessionFactory cartSessionFactory) {
	this.cartSessionFactory = cartSessionFactory;
}

@SuppressWarnings("unchecked")
public List<Cart> getCarts(){
	List<Cart> data=null;
	try{
	Session session = cartSessionFactory.openSession();
    session.beginTransaction();
    @SuppressWarnings("deprecation")
	Criteria criteria = session.createCriteria(Cart.class);
    data= (List<Cart>)criteria.list();
    session.getTransaction().commit();
    session.close();
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return data;
}

public Cart getCartById(String id){
	Cart client=null;
	try{
	Session session = cartSessionFactory.openSession();
    session.beginTransaction();
    @SuppressWarnings("deprecation")
	Criteria criteria = session.createCriteria(Cart.class).add(Restrictions.eq("id", id));
	client = (Cart)criteria.uniqueResult();
    session.getTransaction().commit();
    session.close();
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return client;
}


public boolean addCart(Cart u){
	boolean commit = false;
	try{
	Session session = cartSessionFactory.openSession();
    session.beginTransaction();
    session.saveOrUpdate(u);
    session.getTransaction().commit();
    session.close();
    commit = true;
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return commit;
}


public boolean UpdateCart(Cart u){
	boolean commit = false;
	try{
	Session session = cartSessionFactory.openSession();
    session.beginTransaction();
    session.update(u);
    session.getTransaction().commit();
    session.close();
    commit = true;
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return commit;
}


public boolean RemoveCart(Cart u){
	boolean commit = false;
	try{
	Session session = cartSessionFactory.openSession();
    session.beginTransaction();
    session.remove(u);
    session.getTransaction().commit();
    session.close();
	commit = true;
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return commit;
}


/**
 * This method would return the product cart from the cart_string sent to database.
 * @param id
 * 		-- Cart ID
 * @return
 *      -- Hash map <pdt_id,qty>
 */
public HashMap<String,String> parse_product_cart(String id){
	HashMap<String,String> product_cart = new HashMap<String,String>();
	Cart cart = getCartById(id);
	
	String cart_details = cart.getCart();
	System.out.println("cart_details -> "+ cart_details);
	if(cart_details == null || cart_details.isEmpty()) 
		{
		System.out.println("Returning cart details empty");
		return product_cart;
		}
	String[] pdt_qty = cart_details.split(";");
	for(String pdt:pdt_qty){
		System.out.println("key -> " + pdt.split(",")[0]);
		System.out.println("value -> "+ pdt.split(",")[1]);
		
		product_cart.put(pdt.split(",")[0],pdt.split(",")[1]);
		}
	return product_cart;
}	


/**
 * This method would get the cart string that has to be added to database. 
 * @param id
 *     -- Id of the cart.
 * @param pdt_id
 *     -- Product ID that has to be added. 
 * @param qty
 *     -- Quantity of the product.
 * @return
 *     -- The cart String.
 */
public  String get_product_cart_string(String id, String pdt_id, String qty){
	HashMap<String,String> product_cart = new HashMap<String,String>();
	String cart_string = "";
	String pdt_key = "";
	Cart cart = getCartById(id);
	String cart_details = cart.getCart();
	if(cart_details!=null && !cart_details.isEmpty())
	{	String[] pdt_qty = cart_details.split(";");
	for(String pdt:pdt_qty){
		product_cart.put(pdt.split(",")[0],pdt.split(",")[1]);
	}
}
	product_cart.put(pdt_id,qty);
	Iterator<String> keySetIterator = product_cart.keySet().iterator();
	while(keySetIterator.hasNext()){
		pdt_key = keySetIterator.next();
		cart_string += pdt_key +",";
		cart_string += product_cart.get(pdt_key);
		cart_string += ";";
     }
	return cart_string;
}	


/**
 * This method would get the cart string that has to be added to database. 
 * @param id
 *     -- Id of the cart.
 * @param pdt_id
 *     -- Product ID that has to be added. 
 * @param qty
 *     -- Quantity of the product.
 * @return
 *     -- The cart String.
 */
public  String get_product_cart_string_delete(String id, String pdt_id){
	HashMap<String,String> product_cart = new HashMap<String,String>();
	String cart_string = "";
	String pdt_key = "";
	Cart cart = getCartById(id);
	String cart_details = cart.getCart();
	System.out.println("cart_details -> "+ cart_details);
	System.out.println("pdt_id -> "+ pdt_id);
	if(cart_details!=null && !cart_details.isEmpty())
	{	
	String[] pdt_qty = cart_details.split(";");
	for(String pdt:pdt_qty){
		System.out.println("pdt-> "+ pdt);
		if(pdt.isEmpty() || pdt.startsWith(",")) 
			{
			System.out.println("product empty in continue");
			continue; 
			}
		     System.out.println("split ->"+ pdt);
		     product_cart.put(pdt.split(",")[0],pdt.split(",")[1]);
	}
    }
	System.out.println("Removing->"+pdt_id);
	product_cart.remove(pdt_id);
	System.out.println("Removed");
	Iterator<String> keySetIterator = product_cart.keySet().iterator();
	System.out.println("Iterator got");
	while(keySetIterator.hasNext()){
		System.out.println("In while");
		pdt_key = keySetIterator.next();
		cart_string += pdt_key +",";
		cart_string += product_cart.get(pdt_key);
		cart_string += ";";
		System.out.println("cart_string->"+cart_string);
     }
	cart.setCart(cart_string);
	addCart(cart);
    System.out.println("After Deletion, returning "+ cart_string);
    
	return cart_string;
}	




}

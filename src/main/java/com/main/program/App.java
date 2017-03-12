package com.main.program;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.portal.daos.CartDAOImpl;
import com.portal.daos.CategoryDAOImpl;
import com.portal.daos.ClientDAOImpl;
import com.portal.daos.ProductDAOImpl;
import com.portal.daos.SupplierDAOImpl;
import com.portal.models.Cart;
import com.portal.models.Category;
import com.portal.models.Client;
import com.portal.models.Product;
import com.portal.models.Supplier;


public class App {
	static ClientDAOImpl clientDAOImpl  = (ClientDAOImpl) new ClassPathXmlApplicationContext("spring_beans.xml").getBean("clientDAOImpl");	
	static CategoryDAOImpl categoryDAOImpl  = (CategoryDAOImpl) new ClassPathXmlApplicationContext("spring_beans.xml").getBean("categoryDAOImpl");	
	static ProductDAOImpl productDAOImpl  = (ProductDAOImpl) new ClassPathXmlApplicationContext("spring_beans.xml").getBean("productDAOImpl");	
	static SupplierDAOImpl supplierDAOImpl  = (SupplierDAOImpl) new ClassPathXmlApplicationContext("spring_beans.xml").getBean("supplierDAOImpl");	
	static CartDAOImpl cartDAOImpl  = (CartDAOImpl) new ClassPathXmlApplicationContext("spring_beans.xml").getBean("cartDAOImpl");	
	  
	public static void main(String[] args) {
		System.out.println("In app.java");
		System.out.println(clientDAOImpl);
		
		
		List<Client> clients;
		clients= clientDAOImpl.getClients();
		System.out.println("User is"+clients);
		for (Client client:clients)
		{
			System.out.println("Client is "+ client.getUname());
			System.out.println("Password is "+ client.getPassword());
			System.out.println("Last name is "+ client.getLastname());
			
		}
		
		
		List<Category> categories;
		categories= categoryDAOImpl.getCategories();
		System.out.println("category is"+categories);
		for (Category category:categories)
		{
			System.out.println("Category is "+ category.getId());
			System.out.println("name is "+ category.getName());
			System.out.println("description is "+ category.getDescription());
		}
		
		
		List<Product> products;
		products= productDAOImpl.getProducts();
		System.out.println("product is"+products);
		for (Product product:products)
		{
			System.out.println("Product is "+ product.getId());
			System.out.println("name is "+ product.getName());
			System.out.println("description is "+ product.getDescription());
		}
		
		
		
		List<Supplier> suppliers;
		suppliers= supplierDAOImpl.getSuppliers();
		System.out.println("supplier is"+suppliers);
		for (Supplier supplier:suppliers)
		{
			System.out.println("Supplier is "+ supplier.getId());
			System.out.println("name is "+ supplier.getName());
			System.out.println("description is "+ supplier.getDescription());
		}
		
		
		List<Cart> carts;
		carts= cartDAOImpl.getCarts();
		System.out.println("carts is"+carts);
		for (Cart cart:carts)
		{
			System.out.println("Cart id is "+ cart.getId());
			System.out.println("name is "+ cart.getName());
			System.out.println("Cart is "+ cart.getCart());
			System.out.println("Count is "+ cart.getCount());
		}
		Cart cart = cartDAOImpl.getCartById("admin");
		cartDAOImpl.RemoveCart(cart);
	}
}
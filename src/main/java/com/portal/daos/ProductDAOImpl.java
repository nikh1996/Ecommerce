package com.portal.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import com.portal.models.Product;

public class ProductDAOImpl{

SessionFactory productSessionFactory;
	


public SessionFactory getProductSessionFactory() {
	return productSessionFactory;
}

public void setProductSessionFactory(SessionFactory productSessionFactory) {
	this.productSessionFactory = productSessionFactory;
}

@SuppressWarnings("unchecked")
public List<Product> getProductsByCategory(String CategoryId){
	List<Product> data=null;
	try{
		Session session = productSessionFactory.openSession();
	    session.beginTransaction();
	    @SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Product.class).add(Restrictions.eq("categoryid", CategoryId));
	    data= (List<Product>)criteria.list();
	    session.getTransaction().commit();
	    session.close();
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return data;
}

@SuppressWarnings("unchecked")
public List<Product> getProducts(){
	List<Product> data=null;
	try{
	Session session = productSessionFactory.openSession();
    session.beginTransaction();
    @SuppressWarnings("deprecation")
	Criteria criteria = session.createCriteria(Product.class);
    data= (List<Product>)criteria.list();
    session.getTransaction().commit();
    session.close();
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return data;
}

public Product getProductById(String id){
	Product client=null;
	try{
	Session session = productSessionFactory.openSession();
    session.beginTransaction();
    @SuppressWarnings("deprecation")
	Criteria criteria = session.createCriteria(Product.class).add(Restrictions.eq("id", id));
	client = (Product)criteria.uniqueResult();
    session.getTransaction().commit();
    session.close();
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return client;
}


public boolean addProduct(Product u){
	boolean commit = false;
	try{
	Session session = productSessionFactory.openSession();
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


public boolean UpdateProduct(Product u){
	boolean commit = false;
	try{
	Session session = productSessionFactory.openSession();
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


public boolean RemoveProduct(Product u){
	boolean commit = false;
	try{
	Session session = productSessionFactory.openSession();
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


	
}

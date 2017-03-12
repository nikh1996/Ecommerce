package com.portal.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import com.portal.models.Category;

public class CategoryDAOImpl{

SessionFactory categorySessionFactory;
	


public SessionFactory getCategorySessionFactory() {
	return categorySessionFactory;
}

public void setCategorySessionFactory(SessionFactory categorySessionFactory) {
	this.categorySessionFactory = categorySessionFactory;
}

@SuppressWarnings("unchecked")
public List<Category> getCategories(){
	List<Category> data=null;
	try{
	Session session = categorySessionFactory.openSession();
    session.beginTransaction();
    @SuppressWarnings("deprecation")
	Criteria criteria = session.createCriteria(Category.class);
    data= (List<Category>)criteria.list();
    session.getTransaction().commit();
    session.close();
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return data;
}




public Category getCategoryById(String id){
	Category client=null;
	try{
	Session session = categorySessionFactory.openSession();
    session.beginTransaction();
    @SuppressWarnings("deprecation")
	Criteria criteria = session.createCriteria(Category.class).add(Restrictions.eq("id", id));
	client = (Category)criteria.uniqueResult();
    session.getTransaction().commit();
    session.close();
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return client;
}


public boolean addCategory(Category u){
	boolean commit = false;
	try{
	Session session = categorySessionFactory.openSession();
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


public boolean UpdateCategory(Category u){
	boolean commit = false;
	try{
	Session session = categorySessionFactory.openSession();
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


public boolean RemoveCategory(Category u){
	boolean commit = false;
	try{
	Session session = categorySessionFactory.openSession();
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

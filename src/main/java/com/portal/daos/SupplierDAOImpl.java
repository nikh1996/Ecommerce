package com.portal.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import com.portal.models.Supplier;

public class SupplierDAOImpl{

SessionFactory supplierSessionFactory;
	

public SessionFactory getSupplierSessionFactory() {
	return supplierSessionFactory;
}

public void setSupplierSessionFactory(SessionFactory supplierSessionFactory) {
	this.supplierSessionFactory = supplierSessionFactory;
}

@SuppressWarnings("unchecked")
public List<Supplier> getSuppliers(){
	List<Supplier> data=null;
	try{
	Session session = supplierSessionFactory.openSession();
    session.beginTransaction();
    @SuppressWarnings("deprecation")
	Criteria criteria = session.createCriteria(Supplier.class);
    data= (List<Supplier>)criteria.list();
    session.getTransaction().commit();
    session.close();
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return data;
}

public Supplier getSupplierById(String id){
	Supplier client=null;
	try{
	Session session = supplierSessionFactory.openSession();
    session.beginTransaction();
    @SuppressWarnings("deprecation")
	Criteria criteria = session.createCriteria(Supplier.class).add(Restrictions.eq("id", id));
	client = (Supplier)criteria.uniqueResult();
    session.getTransaction().commit();
    session.close();
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return client;
}


public boolean addSupplier(Supplier u){
	boolean commit = false;
	try{
	Session session = supplierSessionFactory.openSession();
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


public boolean UpdateSupplier(Supplier u){
	boolean commit = false;
	try{
	Session session = supplierSessionFactory.openSession();
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


public boolean RemoveSupplier(Supplier u){
	boolean commit = false;
	try{
	Session session = supplierSessionFactory.openSession();
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

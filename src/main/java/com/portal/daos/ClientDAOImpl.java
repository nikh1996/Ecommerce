package com.portal.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import com.portal.models.Client;

public class ClientDAOImpl{

SessionFactory clientSessionFactory;
	
public SessionFactory getClientSessionFactory() {
	return clientSessionFactory;
}

public void setClientSessionFactory(SessionFactory clientSessionFactory) {
	this.clientSessionFactory = clientSessionFactory;
}

@SuppressWarnings("unchecked")
public List<Client> getClients(){
	List<Client> data=null;
	try{
	Session session = clientSessionFactory.openSession();
    session.beginTransaction();
    @SuppressWarnings("deprecation")
	Criteria criteria = session.createCriteria(Client.class);
    data= (List<Client>)criteria.list();
    session.getTransaction().commit();
    session.close();
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return data;
}

/**
 * checkUsernamePassword method will validate the username with the stored user details. 
 * @param uname
 *          - Username that is gotten from the website
 * @param password
 *          - Password that is gotten from the website
 * @return
 *          - Boolean value that is true if the username and password matches
 */
public boolean checkUsernamePassword(String uname, String password){
	boolean ret_value = false;
	Client client = getUserByUsername(uname);
	if(client != null && password.equals(client.getPassword()))ret_value = true;
	return ret_value;
}

public Client getUserByUsername(String uname){
	Client client=null;
	try{
	Session session = clientSessionFactory.openSession();
    session.beginTransaction();
    @SuppressWarnings("deprecation")
	Criteria criteria = session.createCriteria(Client.class).add(Restrictions.eq("uname", uname));
	client = (Client)criteria.uniqueResult();
    session.getTransaction().commit();
    session.close();
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return client;
}


public boolean addUser(Client u){
	boolean commit = false;
	try{
	Session session = clientSessionFactory.openSession();
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



public boolean UpdateUser(Client u){
	boolean commit = false;
	try{
	Session session = clientSessionFactory.openSession();
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


public boolean RemoveUser(Client u){
	boolean commit = false;
	try{
	Session session = clientSessionFactory.openSession();
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

package com.healthcare.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryProvider {
	private static SessionFactory factory;

	private SessionFactoryProvider() {
	}

	public static SessionFactory getSessionFactory() {
		if (factory == null) {
			try{
			Configuration config = new Configuration();
			//Class classVar = SessionFactory.class;
			System.out.println("path:jjjjjjjj");
			//String path=classVar.getResource("hibernate.cfg.xml").getFile();
			//System.out.println("path: "+path);
			config.configure("hibernate.cfg.xml");
			factory = config.buildSessionFactory();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return factory;
	}

	/**
	 * 
	 * @return hibernate session
	 */
	public static Session getHibernateSession() {
		return getSessionFactory().openSession();
	}
}

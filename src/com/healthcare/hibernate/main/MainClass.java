package com.healthcare.hibernate.main;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.healthcare.hibernate.bean.UserDetail;
import com.healthcare.hibernate.util.SessionFactoryProvider;

public class MainClass {

	public static void main(String[] args) {
		SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
		UserDetail userDetail = new UserDetail();
		userDetail.setUserId("mahesh_babar3");
		userDetail.setPassword("zandu");
		session.save(userDetail);
		txn.commit();
		
		Transaction txn1 = session.beginTransaction();
		//txn1.begin();
		UserDetail userDetails1 = new UserDetail();
		userDetails1.setUserId("mahesh_babar2");
		Query query = session.createQuery("from UserDetail");
		List<UserDetail> users = query.list();
		for(UserDetail user : users){
			System.out.println(user);
		}
		//Userdetails userDetails2 = (Userdetails)session.get(Userdetails.class, "mahesh_babar2");
		//System.out.println(userDetails2);
		//txn1.commit();
		session.close();
	}
}
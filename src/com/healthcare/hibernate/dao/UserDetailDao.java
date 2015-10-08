package com.healthcare.hibernate.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.healthcare.exception.HealthcareException;
import com.healthcare.hibernate.bean.UserDetail;
import com.healthcare.hibernate.util.SessionFactoryProvider;

public class UserDetailDao {
	private static UserDetailDao userDetailDao;
	
	//private UserDetailDao() {}
	
	public static UserDetailDao getInstance(){
		if(null == userDetailDao){
			userDetailDao = new UserDetailDao();
		}
		return userDetailDao;
	}
	
	
	
	public boolean addUser(UserDetail userDetail){
		if(!isValidUser(userDetail)){
			System.out.println("Invalid user!!!");
			throw new HealthcareException("UserId cannot be null! please set mandatory details for user");
		}
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		try{
			System.out.println("Saving user!!!");
			session.save(userDetail);
			System.out.println("Added user!!!");
			txn.commit();
			return true;
		} catch(Exception e){
			throw new HealthcareException("User not added please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	public boolean updateUser(UserDetail userDetail){
		if(StringUtils.isBlank(userDetail.getUserId())){
			throw new HealthcareException("Please set mandatory details for user");
		}
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		try{
			session.update(userDetail);
			txn.commit();
			return true;
		} catch(Exception e){
			throw new HealthcareException("User " + userDetail.getUserId() + " not updated please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	public UserDetail getUserById(String userId){
		System.out.println("User ID in DAO " + userId);
		Session session = SessionFactoryProvider.getHibernateSession();
		try{
			if(StringUtils.isNotBlank(userId)){
				System.out.println("11111 userId " + userId);
				UserDetail user = (UserDetail)session.get(UserDetail.class, userId);
				System.out.println("User is: " + user.toString());
				return user;
			}
			throw new HealthcareException("UserId can not be null! Error while loading User please try later! ");
		} catch(Exception e){
			throw new HealthcareException("Error while loading User " + userId + " please try later! ", e);
			
		} finally{
			session.close();
		}
	}
	
	
	
	public List<UserDetail> searchUser(String searchStr){
		Session session = SessionFactoryProvider.getHibernateSession();
		try{
			Query query = session.createQuery("from UserDetail where userId like '"+searchStr+"'  or address like '"+searchStr+"'  or contactNo like '"+searchStr+"' or city like '"+searchStr+"'  or fullName like '"+searchStr+"' or firstName like '"+searchStr+"'  or lastName like '"+searchStr+"'  or zip like '"+searchStr+"'  or  bloodGroup like '"+searchStr+"'  or  familyHistory like '"+searchStr+"'   ");
			List<UserDetail> users = query.list();
			return users;
		} catch(Exception e){
			throw new HealthcareException("Error while loading Users please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	public List<UserDetail> listUser(){
		Session session = SessionFactoryProvider.getHibernateSession();
		try{
			Query query = session.createQuery("from UserDetail");
			List<UserDetail> users = query.list();
			return users;
		} catch(Exception e){
			throw new HealthcareException("Error while loading Users please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	public boolean removeUser(UserDetail userDetail){
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		try{
			if(isValidUser(userDetail)){
				session.delete(userDetail);
				txn.commit();
				return true;
			}
			throw new HealthcareException("UserId can not be null! Error while loading User please try later! ");
		} catch(Exception e){
			throw new HealthcareException("Error while removing User " + userDetail.getUserId() + " please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	private boolean isValidUser(UserDetail user){
		if(StringUtils.isNotBlank(user.getUserId()) && StringUtils.isNotBlank(user.getPassword())){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		UserDetailDao dao = UserDetailDao.getInstance();
		UserDetail userDetail = new UserDetail();
		userDetail.setUserId("mahesh_babar4");
		userDetail.setPassword("zandu");
		//dao.addUser(userDetail);
		dao.removeUser(userDetail);
		for(UserDetail user : dao.listUser()){
			System.out.println(user);
		}
	}
}

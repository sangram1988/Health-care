package com.healthcare.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.healthcare.exception.HealthcareException;
import com.healthcare.hibernate.bean.DoctorDetail;
import com.healthcare.hibernate.bean.PharmacyDetail;
import com.healthcare.hibernate.util.SessionFactoryProvider;

public class PharmacyDetailDao {


	public boolean addPharmacy(PharmacyDetail PharmacyDetail){
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		try{
			System.out.println("Saving Pharmacy!!!");
			session.saveOrUpdate(PharmacyDetail);
			System.out.println("Added/Edited Pharmacy!!!");
			txn.commit();
			return true;
		} catch(Exception e){
			throw new HealthcareException("User not added please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	
	public PharmacyDetail getPharmacyById(String PharmacyId){
		System.out.println("Pharmacy ID in DAO " + PharmacyId);
		Session session = SessionFactoryProvider.getHibernateSession();

		PharmacyDetail PharmacyDetail=(PharmacyDetail)session.get(PharmacyDetail.class, PharmacyId);
		
		session.close();
		return PharmacyDetail;
		
	}
	
	public List<PharmacyDetail> listPharmacy(){
		Session session = SessionFactoryProvider.getHibernateSession();
		try{
			Query query = session.createQuery("from PharmacyDetail");
			List<PharmacyDetail> Pharmacys = query.list();
			return Pharmacys;
		} catch(Exception e){
			throw new HealthcareException("Error while loading Pharmacy Details please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	public List<PharmacyDetail> searchPharmacy(String searchStr){
		List<PharmacyDetail> doctorList=new ArrayList<PharmacyDetail>();
		String hql="from PharmacyDetail where ";
		
		hql+="pharmacyId like '%"+searchStr+"%' or city like '%"+searchStr+"%' or pharmacyName  like '%"+searchStr+"%'  or ownerName  like '%"+searchStr+"%' or address like '%"+searchStr+"%' or contactNumber like '%"+searchStr+"%' or state like '%"+searchStr+"%'  or zipCode like '%"+searchStr+"%' ";
		System.out.println(" hql : "+hql);
		Session session = SessionFactoryProvider.getHibernateSession();
		try{
			
			Query query = session.createQuery(hql);
			doctorList = query.list();
			return doctorList;
		} catch(Exception e){
			throw new HealthcareException("Error while loading Pharmacy Details please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	
	
	public boolean removeUser(PharmacyDetail PharmacyDetail){
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		try{
				session.delete(PharmacyDetail);
				txn.commit();
				return true;
			
		} catch(Exception e){
			throw new HealthcareException("Error while removing Pharmacy " + PharmacyDetail.getPharmacyId() + " please try later! ", e);
		} finally{
			session.close();
		}
	}
	

}

package com.healthcare.hibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.healthcare.exception.HealthcareException;
import com.healthcare.hibernate.bean.DoctorPharmacyDetail;
import com.healthcare.hibernate.util.SessionFactoryProvider;
import com.healthcare.util.Constants;

public class DoctorPharmacyDetailDao {
	private static DoctorPharmacyDetailDao doctorPharmacyDetailDao;
	
	private DoctorPharmacyDetailDao() {}
	
	public static DoctorPharmacyDetailDao getInstance(){
		if(null == doctorPharmacyDetailDao){
			doctorPharmacyDetailDao = new DoctorPharmacyDetailDao();
		}
		return doctorPharmacyDetailDao;
	}
	
	public boolean add(DoctorPharmacyDetail doctorPharmacyDetail){
		if(!isValid(doctorPharmacyDetail)){
			throw new HealthcareException("Please set mandatory details for Doctor/Pharmacy");
		}
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		try{
			session.save(doctorPharmacyDetail);
			txn.commit();
			return true;
		} catch(Exception e){
			throw new HealthcareException("Doctor/Pharmacy not added please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	public boolean update(DoctorPharmacyDetail doctorPharmacyDetail){
		if(!isValid(doctorPharmacyDetail)){
			throw new HealthcareException("Please set mandatory details for Doctor/Pharmacy");
		}
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		try{
			session.update(doctorPharmacyDetail);
			txn.commit();
			return true;
		} catch(Exception e){
			throw new HealthcareException("Error while updating details for " + doctorPharmacyDetail.getDoctorPharmacyId() + " please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	public DoctorPharmacyDetail getById(String id){
		Session session = SessionFactoryProvider.getHibernateSession();
		try{
			DoctorPharmacyDetail detail = (DoctorPharmacyDetail)session.get(DoctorPharmacyDetail.class, id);
			return detail;
		} catch(Exception e){
			throw new HealthcareException("Error while loading details for " + id + " please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	public List<DoctorPharmacyDetail> list(){
		Session session = SessionFactoryProvider.getHibernateSession();
		try{
			Query query = session.createQuery("from DoctorPharmacyDetail");
			List<DoctorPharmacyDetail> details = query.list();
			return details;
		} catch(Exception e){
			throw new HealthcareException("Error while loading details of Doctor/Pharmacy please try later! ", e);
		} finally{
			session.close();
		}
	}

	public boolean remove(DoctorPharmacyDetail doctorPharmacyDetail){
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		try{
			if(isValid(doctorPharmacyDetail)){
				session.delete(doctorPharmacyDetail);
				txn.commit();
				return true;
			}
			throw new HealthcareException("Error while removing details! Id can not be null! please try later! ");
		} catch(Exception e){
			throw new HealthcareException("Error while removing " + doctorPharmacyDetail.getDoctorPharmacyId() + " please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	private boolean isValid(DoctorPharmacyDetail doctorPharmacyDetail){
		if(null!= doctorPharmacyDetail.getDoctorPharmacyId() && null!= doctorPharmacyDetail.getCategory()){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		DoctorPharmacyDetailDao dao = DoctorPharmacyDetailDao.getInstance();
		DoctorPharmacyDetail doctorPharmacyDetail = new DoctorPharmacyDetail();
		doctorPharmacyDetail.setDoctorPharmacyId("mahesh_babar4");
		doctorPharmacyDetail.setPassword("mahu");
		doctorPharmacyDetail.setCategory(Constants.DOCTOR);
		doctorPharmacyDetail.setSpecilization("MDS");;
		//dao.add(doctorPharmacyDetail);
		//dao.update(doctorPharmacyDetail);
		dao.remove(doctorPharmacyDetail);
		for(DoctorPharmacyDetail detail : dao.list()){
			System.out.println(detail);
		}
		
		
	}
}

package com.healthcare.hibernate.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.healthcare.exception.HealthcareException;
import com.healthcare.hibernate.bean.UserAppointmentDetail;
import com.healthcare.hibernate.util.SessionFactoryProvider;
import com.healthcare.util.Constants;

public class UserAppointmentDetailDao {
	private static UserAppointmentDetailDao userAppointmentDetailDao;
	
	private UserAppointmentDetailDao() {}
	
	public static UserAppointmentDetailDao getInstance(){
		if(null == userAppointmentDetailDao){
			userAppointmentDetailDao = new UserAppointmentDetailDao();
		}
		return userAppointmentDetailDao;
	}
	
	public boolean add(UserAppointmentDetail userAppointmentDetail){
		/*if(!isValid(userAppointmentDetail)){
			throw new HealthcareException("Please set mandatory details for appointment");
		}*/
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		try{
			session.save(userAppointmentDetail);
			txn.commit();
			return true;
		} catch(Exception e){
			throw new HealthcareException("Appointment not added please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	public boolean update(UserAppointmentDetail userAppointmentDetail){
		if(!isValidAppointment(userAppointmentDetail)){
			throw new HealthcareException("Please set mandatory details for appointment!");
		}
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		try{
			session.update(userAppointmentDetail);
			txn.commit();
			return true;
		} catch(Exception e){
			throw new HealthcareException("Error while updating appointment details for please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	public UserAppointmentDetail getById(String id){
		Session session = SessionFactoryProvider.getHibernateSession();
		try{
			UserAppointmentDetail detail = (UserAppointmentDetail)session.get(UserAppointmentDetail.class, id);
			return detail;
		} catch(Exception e){
			throw new HealthcareException("Error while loading appontment details. please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	public List<UserAppointmentDetail> listAllAppointments(){
		Session session = SessionFactoryProvider.getHibernateSession();
		try{
			Query query = session.createQuery("from UserAppointmentDetail");
			List<UserAppointmentDetail> details = query.list();
			return details;
		} catch(Exception e){
			throw new HealthcareException("Error while loading appontment details. please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	public List<UserAppointmentDetail> listAppointmentsForUser(String userId){
		Session session = SessionFactoryProvider.getHibernateSession();
		try{
			Query query = session.createQuery("from UserAppointmentDetail where userId='" + userId + "'");
			List<UserAppointmentDetail> details = query.list();
			return details;
		} catch(Exception e){
			throw new HealthcareException("Error while loading appontment details. please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	public List<UserAppointmentDetail> listAppointmentsForDoctor(String doctorId){
		Session session = SessionFactoryProvider.getHibernateSession();
		try{
			Query query = session.createQuery("from UserAppointmentDetail where userId='" + doctorId + "'");
			List<UserAppointmentDetail> details = query.list();
			return details;
		} catch(Exception e){
			throw new HealthcareException("Error while loading appontment details. please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	public List<UserAppointmentDetail> listAppointmentsForPharmacy(String pharmacyId){
		Session session = SessionFactoryProvider.getHibernateSession();
		try{
			Query query = session.createQuery("from UserAppointmentDetail where userId='" + pharmacyId + "'");
			List<UserAppointmentDetail> details = query.list();
			return details;
		} catch(Exception e){
			throw new HealthcareException("Error while loading appontment details. please try later! ", e);
		} finally{
			session.close();
		}
	}

	public boolean remove(UserAppointmentDetail userAppointmentDetail){
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		try{
			if(isValidAppointment(userAppointmentDetail)){
				session.delete(userAppointmentDetail);
				txn.commit();
				return true;
			}
			throw new HealthcareException("Error while removing appointment please try later! ");
		} catch(Exception e){
			throw new HealthcareException("Error while removing appointment please try later! ", e);
		} finally{
			session.close();
		}
	}
	
	private boolean isValidAppointment(UserAppointmentDetail UserAppointmentDetail){
		if(null!= UserAppointmentDetail.getDoctorPharmacyId() && null!= UserAppointmentDetail.getUserId()){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		UserAppointmentDetailDao dao = UserAppointmentDetailDao.getInstance();
		UserAppointmentDetail userAppointmentDetail = new UserAppointmentDetail();
		userAppointmentDetail.setUserId("mahesh_babar1");
		userAppointmentDetail.setDoctorPharmacyId("mahi");
		userAppointmentDetail.setStatus(Constants.COMPLETE);
		userAppointmentDetail.setAppointmentDate(new Date());
		userAppointmentDetail.setAppointmentStartTime(10);
		userAppointmentDetail.setAppointmentEndTime(11);
		//dao.add(userAppointmentDetail);
		//dao.update(userAppointmentDetail);
		//dao.remove(userAppointmentDetail);
		for(UserAppointmentDetail detail : dao.listAppointmentsForUser("mahesh_babar1")){
			System.out.println(detail);
		}
	}
}

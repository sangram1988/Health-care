package com.healthcare.hibernate.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.healthcare.exception.HealthcareException;
import com.healthcare.form.AppointmentForm;
import com.healthcare.hibernate.bean.AppointmentDetail;
import com.healthcare.hibernate.util.Format;
import com.healthcare.hibernate.util.SessionFactoryProvider;

public class AppointmentDetailsDao {

	
	
	
	public List<AppointmentDetail> searchAppointment(String searchStr){
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		Query query = session.createQuery("from AppointmentDetail where userId='"+searchStr+"' and doctorId is not null order by appointmentDateTimeStart desc" );
		List<AppointmentDetail> appointments = query.list();
		
		return appointments;
	}
	
	public List<AppointmentDetail> listOfPharmacyAppointments(String pharmacyId){
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		Query query = session.createQuery("from AppointmentDetail where pharmacyId='"+pharmacyId+"'  and appointmentDateTimeStart >= '"
				+ Format.getStringDDMMYYYYHHMM(new Date()) +"'" );
		List<AppointmentDetail> appointments = query.list();
		
		return appointments;
	}
	
	public List<AppointmentDetail> savePharmacyAppointments(String appointmentIds[],String pharmacyId){
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		AppointmentDetail appointmentDetail=null;
		for(String appointmentId:appointmentIds){
			appointmentDetail= getAppointmentById(appointmentId);
			appointmentDetail.setAppointmentStatus("Active");
			addAppointment(appointmentDetail);
		}
		
		AppointmentForm appointmentForm=new AppointmentForm();
		appointmentForm.setDoctorId(pharmacyId);
		appointmentForm.setType("doctor");
		List<AppointmentDetail> appointments = searchAppointments(appointmentForm);
		return appointments;
	}
	
	
	public List<AppointmentDetail> listOfDoctorAppointments(String doctorId){
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		Query query = session.createQuery("from AppointmentDetail where doctorId='"+doctorId+"' and appointmentDateTimeStart >= '"
				+ Format.getStringDDMMYYYYHHMM(new Date()) +"'" );
		List<AppointmentDetail> appointments = query.list();
		
		return appointments;
	}
	
	public List<AppointmentDetail> saveDoctorAppointments(String appointmentIds[],String doctorId){
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		AppointmentDetail appointmentDetail=null;
		for(String appointmentId:appointmentIds){
			appointmentDetail= getAppointmentById(appointmentId);
			appointmentDetail.setAppointmentStatus("Active");
			addAppointment(appointmentDetail);
		}
		
		AppointmentForm appointmentForm=new AppointmentForm();
		appointmentForm.setDoctorId(doctorId);
		appointmentForm.setType("doctor");
		List<AppointmentDetail> appointments = searchAppointments(appointmentForm);
		return appointments;
	}
	
	
	public boolean addAppointment(AppointmentDetail appointmentDetail) {
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		try {
			System.out.println("Saving appointment detail!!!");
			session.saveOrUpdate(appointmentDetail);
			System.out.println("Added/Edited appointment detail!!!");
			txn.commit();
			return true;
		} catch (Exception e) {
			throw new HealthcareException("User not added please try later! ",
					e);
		} finally {
			session.close();
		}
	}

	public AppointmentDetail getAppointmentById(String appointmentId) {
		System.out.println("Appointment ID in DAO " + appointmentId);
		Session session = SessionFactoryProvider.getHibernateSession();

		AppointmentDetail appointmentDetail = (AppointmentDetail) session.get(
				AppointmentDetail.class, appointmentId);

		session.close();
		return appointmentDetail;

	}

	public List<AppointmentDetail> listAppointments(String loginId,String type) {
		Session session = SessionFactoryProvider.getHibernateSession();
		try {
			
			String hql="from AppointmentDetail where appointmentDateTimeStart >= '"
							+ Format.getStringDDMMYYYYHHMM(new Date()) + "'";
			if(!Format.isStringEmptyORNull(type) && type.equalsIgnoreCase("Patient")){
				hql+=" and userId='"+loginId+"'";
			}
			
			if(!Format.isStringEmptyORNull(type) && type.equalsIgnoreCase("Doctor")){
				hql+=" and doctorId='"+loginId+"'";
			}
			
			if(!Format.isStringEmptyORNull(type) && type.equalsIgnoreCase("Pharmacy")){
				hql+=" and pharmacyId='"+loginId+"'";
			}
			
			Query query = session.createQuery(hql);
			
			List<AppointmentDetail> appointments = query.list();
			return appointments;
		} catch (Exception e) {
			throw new HealthcareException(
					"Error while loading Appointment Details please try later! ",
					e);
		} finally {
			session.close();
		}
	}

	
	
	public List<AppointmentDetail> listDocAllAppointments() {
		Session session = SessionFactoryProvider.getHibernateSession();
		try {
			
			String hql="from AppointmentDetail";
							
			
			Query query = session.createQuery(hql);
			
			List<AppointmentDetail> appointments = query.list();
			return appointments;
		} catch (Exception e) {
			throw new HealthcareException(
					"Error while loading Appointment Details please try later! ",
					e);
		} finally {
			session.close();
		}
	}

	
	
	public List<AppointmentDetail> listAllDoctors() {
		Session session = SessionFactoryProvider.getHibernateSession();
		try {
			
			String hql="from DoctorDetail";
							
			Query query = session.createQuery(hql);
			
			List<AppointmentDetail> appointments = query.list();
			return appointments;
		} catch (Exception e) {
			throw new HealthcareException(
					"Error while loading Appointment Details please try later! ",
					e);
		} finally {
			session.close();
		}
	}

	
	
	public List<AppointmentDetail> searchDuplicateAppointments(
			AppointmentForm appointmentForm) {

		Session session = SessionFactoryProvider.getHibernateSession();
		try {
			
			List<AppointmentDetail> appointmentLst=searchAppointments(
					appointmentForm);
			
			System.out.println("Serach list :"+appointmentLst);
			if(appointmentLst!=null && !appointmentLst.isEmpty()){
				System.out.println("Serach list1 :"+appointmentLst);
				return appointmentLst;
			}
					
			String searchString = "from AppointmentDetail ";

			if (!appointmentForm.isFormEmpty())
				searchString += " where ";
			
			if (!Format.isDateNull(appointmentForm.getStartTime()) && !Format.isDateNull(appointmentForm.getEndTime()))
				searchString += "( appointmentDateTimeStart >= '"
						+ Format.getStringDDMMYYYYHHMM(appointmentForm
								.getStartTime()) + "' and appointmentDateTimeStart <= '"+Format.getStringDDMMYYYYHHMM(appointmentForm
								.getEndTime())+"' ) OR ( appointmentDateTimeEnd >= '"
						+ Format.getStringDDMMYYYYHHMM(appointmentForm
								.getStartTime()) + "' and appointmentDateTimeEnd <= '"+Format.getStringDDMMYYYYHHMM(appointmentForm
								.getEndTime())+"' )";

		
			System.out.println(" Query1 :" + searchString);

			Query query = session.createQuery(searchString);
			List<AppointmentDetail> appointments = query.list();
			
			return appointments;
		} catch (Exception e) {
			throw new HealthcareException(
					"Error while loading Appointment Details please try later! ",
					e);
		} finally {
			session.close();
		}


	}


	public List<AppointmentDetail> searchDuplicateAppointmentsBetween(
			AppointmentForm appointmentForm) {

		Session session = SessionFactoryProvider.getHibernateSession();
		try {
			
			List<AppointmentDetail> appointmentLst=searchDuplicateAppointments(
					appointmentForm);
			
			System.out.println("Serach list2 :"+appointmentLst);
			if(appointmentLst!=null && !appointmentLst.isEmpty()){
				System.out.println("Serach list3 :"+appointmentLst);
				return appointmentLst;
			}
					
			String searchString = "from AppointmentDetail ";

			if (!appointmentForm.isFormEmpty())
				searchString += " where ";
			
			if (!Format.isDateNull(appointmentForm.getStartTime()) && !Format.isDateNull(appointmentForm.getEndTime()))
				searchString += "( appointmentDateTimeStart <= '"
						+ Format.getStringDDMMYYYYHHMM(appointmentForm
								.getStartTime()) + "' and appointmentDateTimeEnd >= '"+Format.getStringDDMMYYYYHHMM(appointmentForm
								.getStartTime())+"' ) OR ( appointmentDateTimeStart <= '"
						+ Format.getStringDDMMYYYYHHMM(appointmentForm
								.getEndTime()) + "' and appointmentDateTimeEnd >= '"+Format.getStringDDMMYYYYHHMM(appointmentForm
								.getEndTime())+"' )";

		
			System.out.println(" Query2 :" + searchString);

			Query query = session.createQuery(searchString);
			List<AppointmentDetail> appointments = query.list();
			
			return appointments;
		} catch (Exception e) {
			throw new HealthcareException(
					"Error while loading Appointment Details please try later! ",
					e);
		} finally {
			session.close();
		}


	}


	
	public List<AppointmentDetail> searchAppointments(
			AppointmentForm appointmentForm) {
		Session session = SessionFactoryProvider.getHibernateSession();
		try {
			String searchString = "from AppointmentDetail where  1=1  ";

			if (!Format.isStringEmptyORNull(appointmentForm.getType())
					&& appointmentForm.getType().equalsIgnoreCase("doctor"))
				searchString += "  and doctorId like '%"
						+ appointmentForm.getDoctorId() + "%'";

			if (!Format.isStringEmptyORNull(appointmentForm.getType())
					&& appointmentForm.getType().equalsIgnoreCase("pharmacy"))
				searchString += " and  pharmacyId like '%"
						+ appointmentForm.getPharmacyId() + "%'";

			if (!Format.isStringEmptyORNull(appointmentForm.getTitle()))
				searchString += " and title like '%" + appointmentForm.getTitle()
						+ "%'";

			if (!Format.isStringEmptyORNull(appointmentForm.getDescription()))
				searchString += " and description like '%"
						+ appointmentForm.getDescription() + "%'";

			if (!Format.isDateNull(appointmentForm.getStartTime()))
				searchString += " and appointmentDateTimeStart >= '"
						+ Format.getStringDDMMYYYYHHMM(appointmentForm
								.getStartTime()) + "'";

			if (!Format.isDateNull(appointmentForm.getEndTime()))
				searchString += " and appointmentDateTimeEnd <= '"
						+ Format.getStringDDMMYYYYHHMM(appointmentForm
								.getEndTime()) + "'";

			searchString+=" and userId='"+appointmentForm.getUserId()+"' ";
			System.out.println(" Query :" + searchString);

			Query query = session.createQuery(searchString);
			List<AppointmentDetail> appointments = query.list();
			return appointments;
		} catch (Exception e) {
			throw new HealthcareException(
					"Error while loading Appointment Details please try later! ",
					e);
		} finally {
			session.close();
		}
	}

	public boolean removeAppointment(AppointmentDetail appointmentDetail) {
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		try {
			session.delete(appointmentDetail);
			txn.commit();
			return true;

		} catch (Exception e) {
			throw new HealthcareException(
					"Error while removing Appointment details "
							+ appointmentDetail.getAppointmentId()
							+ " please try later! ", e);
		} finally {
			session.close();
		}
	}

}

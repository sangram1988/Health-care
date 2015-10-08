package com.healthcare.hibernate.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.healthcare.exception.HealthcareException;
import com.healthcare.form.AppointmentForm;
import com.healthcare.hibernate.bean.AppointmentDetail;
import com.healthcare.hibernate.bean.VisitDetail;
import com.healthcare.hibernate.util.Format;
import com.healthcare.hibernate.util.SessionFactoryProvider;

public class VisitDetailsDao {


	public List<VisitDetail> searchVisit(String userId){
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		Query query = session.createQuery("from VisitDetail where userId='"+userId+"' and doctorId is not null  order by visitDateTimeStart desc" );
		List<VisitDetail> visits = query.list();
		
		return visits;
	}
	
	public List<VisitDetail> listOfDoctorVisits(String doctorId){
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		Query query = session.createQuery("from VisitDetail where doctorId='"+doctorId+"'");
		List<VisitDetail> visits = query.list();
		
		return visits;
	}
	
	public List<VisitDetail> saveDoctorVisits(String visitIds[],String doctorId){
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		VisitDetail visitDetail=null;
		for(String visitId:visitIds){
			visitDetail= getVisitById(visitId);
			addVisit(visitDetail);
		}
		
		AppointmentForm appointmentForm=new AppointmentForm();
		appointmentForm.setDoctorId(doctorId);
		appointmentForm.setType("doctor");
		List<VisitDetail> visits = searchVisits(appointmentForm);
		return visits;
	}
	
	
	public boolean addVisit(VisitDetail visitDetail) {
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		try {
			System.out.println("Saving visit detail!!!");
			session.saveOrUpdate(visitDetail);
			System.out.println("Added/Edited visit detail!!!");
			txn.commit();
			return true;
		} catch (Exception e) {
			throw new HealthcareException("Visit details not added please try later! ",
					e);
		} finally {
			session.close();
		}
	}

	public VisitDetail getVisitById(String visitId) {
		System.out.println("Visit ID in DAO " + visitId);
		Session session = SessionFactoryProvider.getHibernateSession();

		VisitDetail visitDetail = (VisitDetail) session.get(
				VisitDetail.class, visitId);

		session.close();
		return visitDetail;

	}

	public List<VisitDetail> listVisits() {
		Session session = SessionFactoryProvider.getHibernateSession();
		try {
			Query query = session
					.createQuery("from VisitDetail where visitDateTimeStart <= '"
							+ Format.getStringDDMMYYYYHHMM(new Date()) + "'");
			List<VisitDetail> visits = query.list();
			return visits;
		} catch (Exception e) {
			throw new HealthcareException(
					"Error while loading Visit Details please try later! ",
					e);
		} finally {
			session.close();
		}
	}

	public List<VisitDetail> searchDuplicateVisits(
			AppointmentForm appointmentForm) {

		Session session = SessionFactoryProvider.getHibernateSession();
		try {
			
			List<VisitDetail> visitList=searchVisits(
					appointmentForm);
			
			System.out.println("Serach list :"+visitList);
			if(visitList!=null && !visitList.isEmpty()){
				System.out.println("Serach list1 :"+visitList);
				return visitList;
			}
					
			String searchString = "from VisitDetail ";

			if (!appointmentForm.isFormEmpty())
				searchString += " where ";
			
			if (!Format.isDateNull(appointmentForm.getStartTime()) && !Format.isDateNull(appointmentForm.getEndTime()))
				searchString += "( visitDateTimeStart >= '"
						+ Format.getStringDDMMYYYYHHMM(appointmentForm
								.getStartTime()) + "' and visitDateTimeStart <= '"+Format.getStringDDMMYYYYHHMM(appointmentForm
								.getEndTime())+"' ) OR ( visitDateTimeEnd >= '"
						+ Format.getStringDDMMYYYYHHMM(appointmentForm
								.getStartTime()) + "' and visitDateTimeEnd <= '"+Format.getStringDDMMYYYYHHMM(appointmentForm
								.getEndTime())+"' )";

		
			System.out.println(" Query1 :" + searchString);

			Query query = session.createQuery(searchString);
			List<VisitDetail> visits = query.list();
			
			return visits;
		} catch (Exception e) {
			throw new HealthcareException(
					"Error while loading Visit Details please try later! ",
					e);
		} finally {
			session.close();
		}


	}


	public List<VisitDetail> searchDuplicateVisitsBetween(
			AppointmentForm appointmentForm) {

		Session session = SessionFactoryProvider.getHibernateSession();
		try {
			
			List<VisitDetail> visitList=searchDuplicateVisits(
					appointmentForm);
			
			System.out.println("Serach list2 :"+visitList);
			if(visitList!=null && !visitList.isEmpty()){
				System.out.println("Serach list3 :"+visitList);
				return visitList;
			}
					
			String searchString = "from VisitDetail ";

			if (!appointmentForm.isFormEmpty())
				searchString += " where ";
			
			if (!Format.isDateNull(appointmentForm.getStartTime()) && !Format.isDateNull(appointmentForm.getEndTime()))
				searchString += "( visitDateTimeStart <= '"
						+ Format.getStringDDMMYYYYHHMM(appointmentForm
								.getStartTime()) + "' and visitDateTimeEnd >= '"+Format.getStringDDMMYYYYHHMM(appointmentForm
								.getStartTime())+"' ) OR ( visitDateTimeStart <= '"
						+ Format.getStringDDMMYYYYHHMM(appointmentForm
								.getEndTime()) + "' and visitDateTimeEnd >= '"+Format.getStringDDMMYYYYHHMM(appointmentForm
								.getEndTime())+"' )";

		
			System.out.println(" Query2 :" + searchString);

			Query query = session.createQuery(searchString);
			List<VisitDetail> visits = query.list();
			
			return visits;
		} catch (Exception e) {
			throw new HealthcareException(
					"Error while loading Visit Details please try later! ",
					e);
		} finally {
			session.close();
		}


	}


	
	public List<VisitDetail> searchVisits(
			AppointmentForm appointmentForm) {
		Session session = SessionFactoryProvider.getHibernateSession();
		try {
			String searchString = "from VisitDetail ";

			if (!appointmentForm.isFormEmpty())
				searchString += " where 1=1 ";
			if (!Format.isStringEmptyORNull(appointmentForm.getType())
					&& appointmentForm.getType().equalsIgnoreCase("doctor"))
				searchString += "  and doctorId='"
						+ appointmentForm.getDoctorId() + "'";

			if (!Format.isStringEmptyORNull(appointmentForm.getType())
					&& appointmentForm.getType().equalsIgnoreCase("pharmacy"))
				searchString += "  and pharmacyId='"
						+ appointmentForm.getPharmacyId() + "'";

			if (!Format.isStringEmptyORNull(appointmentForm.getType())
					&& appointmentForm.getType().equalsIgnoreCase("patient"))
				searchString += "  and userId like '%"
						+ appointmentForm.getUserId() + "%'";

			if (!Format.isStringEmptyORNull(appointmentForm.getTitle()))
				searchString += " and title like '%" + appointmentForm.getTitle()
						+ "%'";

			if (!Format.isStringEmptyORNull(appointmentForm.getDescription()))
				searchString += "and description like '%"
						+ appointmentForm.getDescription() + "%'";

			if (!Format.isDateNull(appointmentForm.getStartTime()))
				searchString += " and visitDateTimeStart >= '"
						+ Format.getStringDDMMYYYYHHMM(appointmentForm
								.getStartTime()) + "'";

			if (!Format.isDateNull(appointmentForm.getEndTime()))
				searchString += " and visitDateTimeEnd <= '"
						+ Format.getStringDDMMYYYYHHMM(appointmentForm
								.getEndTime()) + "'";

			System.out.println(" Query :" + searchString);

			Query query = session.createQuery(searchString);
			List<VisitDetail> visits = query.list();
			return visits;
		} catch (Exception e) {
			throw new HealthcareException(
					"Error while loading Visit Details please try later! ",
					e);
		} finally {
			session.close();
		}
	}

	public boolean removeVisit(VisitDetail visitDetail) {
		Session session = SessionFactoryProvider.getHibernateSession();
		Transaction txn = session.beginTransaction();
		try {
			session.delete(visitDetail);
			txn.commit();
			return true;

		} catch (Exception e) {
			throw new HealthcareException(
					"Error while removing Visit details "
							+ visitDetail.getVisitId()
							+ " please try later! ", e);
		} finally {
			session.close();
		}
	}

}

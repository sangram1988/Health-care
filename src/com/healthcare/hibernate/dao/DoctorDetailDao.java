package com.healthcare.hibernate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.healthcare.exception.HealthcareException;
import com.healthcare.form.PopularDoctor;
import com.healthcare.hibernate.bean.AppointmentDetail;
import com.healthcare.hibernate.bean.DoctorDetail;
import com.healthcare.hibernate.util.SessionFactoryProvider;

public class DoctorDetailDao {

	
		
		public boolean addDoctor(DoctorDetail doctorDetail){
			Session session = SessionFactoryProvider.getHibernateSession();
			Transaction txn = session.beginTransaction();
			try{
				System.out.println("Saving Doctor!!!");
				session.saveOrUpdate(doctorDetail);
				System.out.println("Added/Edited Doctor!!!");
				txn.commit();
				return true;
			} catch(Exception e){
				throw new HealthcareException("User not added please try later! ", e);
			} finally{
				session.close();
			}
		}
		
		public List<DoctorDetail> searchDoctor(String searchStr){
			
			List<DoctorDetail> doctorList=new ArrayList<DoctorDetail>();
			String hql="from DoctorDetail where ";
			
			hql+="doctorId like '%"+searchStr+"%' or city like '%"+searchStr+"%' or firstName  like '%"+searchStr+"%'  or fullName  like '%"+searchStr+"%' or hospitalAddress like '%"+searchStr+"%' or lastName like '%"+searchStr+"%' or speciality like '%"+searchStr+"%' or state like '%"+searchStr+"%'  or zipCode like '%"+searchStr+"%' ";
			System.out.println(" hql : "+hql);
			Session session = SessionFactoryProvider.getHibernateSession();
			try{
				
				Query query = session.createQuery(hql);
				doctorList = query.list();
				return doctorList;
			} catch(Exception e){
				throw new HealthcareException("Error while loading Doctor Details please try later! ", e);
			} finally{
				session.close();
			}
			
		}
		
		public DoctorDetail getDoctorById(String doctorId){
			System.out.println("Doctor ID in DAO " + doctorId);
			Session session = SessionFactoryProvider.getHibernateSession();

			DoctorDetail doctorDetail=(DoctorDetail)session.get(DoctorDetail.class, doctorId);
			
			session.close();
			return doctorDetail;
			
		}
		
		public List<DoctorDetail> listDoctor(){
			Session session = SessionFactoryProvider.getHibernateSession();
			try{
				Query query = session.createQuery("from DoctorDetail");
				List<DoctorDetail> doctors = query.list();
				return doctors;
			} catch(Exception e){
				throw new HealthcareException("Error while loading Doctor Details please try later! ", e);
			} finally{
				session.close();
			}
		}
		
		public List<DoctorDetail> listMinFees(){
			Session session = SessionFactoryProvider.getHibernateSession();
			List<DoctorDetail> doctors=new ArrayList<DoctorDetail>();
			try{
				String sql = "SELECT doctor_id FROM doctor_detail d where fees=(select min(fees) from healthcare.doctor_detail)";
				SQLQuery query = session.createSQLQuery(sql);
				List<String> list = (List<String>) query.list();
				System.out.println(list);
				for(String appointmentDetail:list){
					
					doctors.add(getDoctorById(appointmentDetail));
				}
				
				
				
			} catch(Exception e){
				throw new HealthcareException("Error while loading Doctor Details please try later! ", e);
			} finally{
				session.close();
			}
			return doctors;
		}
		
		
		
		public List<DoctorDetail> listMostPopDoc(){
			Session session = SessionFactoryProvider.getHibernateSession();
			List<DoctorDetail> doctors=new ArrayList<DoctorDetail>();
			try{
				String sql = "select a.doctor_id from (select doctor_id,count(*) from appointment_details group by doctor_id order by 2 desc limit 1) a";
				SQLQuery query = session.createSQLQuery(sql);
				List<String> list = (List<String>) query.list();
				System.out.println(list);
				for(String appointmentDetail:list){
					
					doctors.add(getDoctorById(appointmentDetail));
				}
				
				
				
			} catch(Exception e){
				throw new HealthcareException("Error while loading Doctor Details please try later! ", e);
			} finally{
				session.close();
			}
			return doctors;
		}
		
		
		public boolean removeUser(DoctorDetail doctorDetail){
			Session session = SessionFactoryProvider.getHibernateSession();
			Transaction txn = session.beginTransaction();
			try{
					session.delete(doctorDetail);
					txn.commit();
					return true;
				
			} catch(Exception e){
				throw new HealthcareException("Error while removing Doctor " + doctorDetail.getDoctorId() + " please try later! ", e);
			} finally{
				session.close();
			}
		}
		
		

}

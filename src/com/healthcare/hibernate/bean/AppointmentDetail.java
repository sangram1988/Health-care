package com.healthcare.hibernate.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the appointment_details database table.
 * 
 */
@Entity
@Table(name="appointment_details")
public class AppointmentDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="appointment_id")
	private String appointmentId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="appointment_date_time_end")
	private Date appointmentDateTimeEnd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="appointment_date_time_start")
	private Date appointmentDateTimeStart;

	@Column(name="appointment_status")
	private String appointmentStatus;

	private String description;

	@Column(name="doctor_id")
	private String doctorId;

	@Column(name="pharmacy_id")
	private String pharmacyId;

	private String title;
	
	private String prescriptions;

	@Column(name="user_id")
	private String userId;

	public AppointmentDetail() {
	}

	public String getAppointmentId() {
		return this.appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Date getAppointmentDateTimeEnd() {
		return this.appointmentDateTimeEnd;
	}

	public void setAppointmentDateTimeEnd(Date appointmentDateTimeEnd) {
		this.appointmentDateTimeEnd = appointmentDateTimeEnd;
	}

	public Date getAppointmentDateTimeStart() {
		return this.appointmentDateTimeStart;
	}

	public void setAppointmentDateTimeStart(Date appointmentDateTimeStart) {
		this.appointmentDateTimeStart = appointmentDateTimeStart;
	}

	public String getAppointmentStatus() {
		return this.appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getPharmacyId() {
		return this.pharmacyId;
	}

	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(String prescriptions) {
		this.prescriptions = prescriptions;
	}
	
	

}
package com.healthcare.hibernate.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the visit_details database table.
 * 
 */
@Entity
@Table(name="visit_details")
public class VisitDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="visit_id")
	private String visitId;

	private String description;

	@Column(name="doctor_id")
	private String doctorId;

	@Column(name="pharmacy_id")
	private String pharmacyId;

	private String prescriptions;

	private String title;

	@Column(name="user_id")
	private String userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="visit_date_time_end")
	private Date visitDateTimeEnd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="visit_date_time_start")
	private Date visitDateTimeStart;

	public VisitDetail() {
	}

	public String getVisitId() {
		return this.visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
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

	public String getPrescriptions() {
		return this.prescriptions;
	}

	public void setPrescriptions(String prescriptions) {
		this.prescriptions = prescriptions;
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

	public Date getVisitDateTimeEnd() {
		return this.visitDateTimeEnd;
	}

	public void setVisitDateTimeEnd(java.util.Date endDT) {
		this.visitDateTimeEnd = endDT;
	}

	public Date getVisitDateTimeStart() {
		return this.visitDateTimeStart;
	}

	public void setVisitDateTimeStart(Date visitDateTimeStart) {
		this.visitDateTimeStart = visitDateTimeStart;
	}

}
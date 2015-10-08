package com.healthcare.hibernate.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the user_appointment_details database table.
 * 
 */
@Entity
@Table(name="user_appointment_details")
public class UserAppointmentDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int userAppointmentId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date appointmentDate;

	private int appointmentEndTime;

	private String appointmentPurpose;

	private int appointmentStartTime;

	private String prescriptions;

	private String status;
	
	private String userId;
	
	private String doctorPharmacyId;

	public UserAppointmentDetail() {
	}

	public int getUserAppointmentId() {
		return this.userAppointmentId;
	}

	public void setUserAppointmentId(int userAppointmentId) {
		this.userAppointmentId = userAppointmentId;
	}

	public Date getAppointmentDate() {
		return this.appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public int getAppointmentEndTime() {
		return this.appointmentEndTime;
	}

	public void setAppointmentEndTime(int appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}

	public String getAppointmentPurpose() {
		return this.appointmentPurpose;
	}

	public void setAppointmentPurpose(String appointmentPurpose) {
		this.appointmentPurpose = appointmentPurpose;
	}

	public int getAppointmentStartTime() {
		return this.appointmentStartTime;
	}

	public void setAppointmentStartTime(int appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	public String getPrescriptions() {
		return this.prescriptions;
	}

	public void setPrescriptions(String prescriptions) {
		this.prescriptions = prescriptions;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDoctorPharmacyId() {
		return doctorPharmacyId;
	}

	public void setDoctorPharmacyId(String doctorPharmacyId) {
		this.doctorPharmacyId = doctorPharmacyId;
	}

	@Override
	public String toString() {
		return "UserAppointmentDetail [userAppointmentId=" + userAppointmentId
				+ ", appointmentDate=" + appointmentDate
				+ ", appointmentEndTime=" + appointmentEndTime
				+ ", appointmentPurpose=" + appointmentPurpose
				+ ", appointmentStartTime=" + appointmentStartTime
				+ ", prescriptions=" + prescriptions + ", status=" + status
				+ ", userId=" + userId + ", doctorPharmacyId="
				+ doctorPharmacyId + "]";
	}
}
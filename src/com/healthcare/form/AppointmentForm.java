package com.healthcare.form;

import java.util.Date;

import com.healthcare.hibernate.util.Format;

public class AppointmentForm {
	private String userId;
	private String doctorId;
	private String pharmacyId;
	private Date startTime;
	private Date endTime;
	private String status;
	private String title;
	private String description;
	private String type;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getPharmacyId() {
		return pharmacyId;
	}
	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isFormEmpty(){
		
		int cnt=0;
		
		if(Format.isStringEmptyORNull(getDescription())){
			cnt++;
		}
				
		if(Format.isStringEmptyORNull(getTitle())){
			cnt++;
		}
		if(Format.isStringEmptyORNull(getType()) &&Format.isStringEmptyORNull(getDoctorId())) {
			cnt++;
		}
		if(Format.isStringEmptyORNull(getPharmacyId())){
			cnt++;
		}
		
		if(Format.isStringEmptyORNull(getPharmacyId())){
			cnt++;
		} 
		if(Format.isDateNull(startTime)){
			cnt++;
		}
		if(Format.isDateNull(endTime)){
			cnt++;
		}
		
		if(cnt==7){
			return true;
		}
		
		return false;
	}
	
}

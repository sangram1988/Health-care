package com.healthcare.hibernate.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the doctor_pharmacy_details database table.
 * 
 */
@Entity
@Table(name = "doctor_pharmacy_details")
public class DoctorPharmacyDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String doctorPharmacyId;

	private String address;

	private String category;

	private String city;

	private int closeTiming;

	private String contactNo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dob;

	private String firstName;

	private String fullName;

	private String gender;

	private String lastName;

	private int openTiming;

	private String password;

	private String specilization;

	private String state;

	private String zip;

	public DoctorPharmacyDetail() {
	}

	public String getDoctorPharmacyId() {
		return this.doctorPharmacyId;
	}

	public void setDoctorPharmacyId(String doctorPharmacyId) {
		this.doctorPharmacyId = doctorPharmacyId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCloseTiming() {
		return this.closeTiming;
	}

	public void setCloseTiming(int closeTiming) {
		this.closeTiming = closeTiming;
	}

	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getOpenTiming() {
		return this.openTiming;
	}

	public void setOpenTiming(int openTiming) {
		this.openTiming = openTiming;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSpecilization() {
		return this.specilization;
	}

	public void setSpecilization(String specilization) {
		this.specilization = specilization;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "DoctorPharmacyDetail [doctorPharmacyId=" + doctorPharmacyId
				+ ", address=" + address + ", category=" + category + ", city="
				+ city + ", closeTiming=" + closeTiming + ", contactNo="
				+ contactNo + ", dob=" + dob + ", firstName=" + firstName
				+ ", fullName=" + fullName + ", gender=" + gender
				+ ", lastName=" + lastName + ", openTiming=" + openTiming
				+ ", password=" + password + ", specilization=" + specilization
				+ ", state=" + state + ", zip=" + zip + "]";
	}
	
}
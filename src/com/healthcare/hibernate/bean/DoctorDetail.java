package com.healthcare.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the doctor_detail database table.
 * 
 */
@Entity
@Table(name = "doctor_detail")
public class DoctorDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "doctor_id")
	private String doctorId;

	private String password;

	private String city;

	private String experience;

	private double fees;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "hospital_address")
	private String hospitalAddress;

	@Column(name = "last_name")
	private String lastName;

	private String speciality;

	private String state;

	@Column(name = "zip_code")
	private String zipCode;

	public DoctorDetail() {
	}

	public String getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getExperience() {
		return this.experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public double getFees() {
		return this.fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
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

	public String getHospitalAddress() {
		return this.hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "DoctorDetail [doctorId=" + doctorId + ", password=" + password
				+ ", city=" + city + ", experience=" + experience + ", fees="
				+ fees + ", firstName=" + firstName + ", fullName=" + fullName
				+ ", hospitalAddress=" + hospitalAddress + ", lastName="
				+ lastName + ", speciality=" + speciality + ", state=" + state
				+ ", zipCode=" + zipCode + "]";
	}

}
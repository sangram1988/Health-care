package com.healthcare.hibernate.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the userdetails database table.
 * 
 */
@Entity
@Table(name = "userdetails")
public class UserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String userId;

	private String address;

	private String city;

	private String contactNo;

	@Temporal(TemporalType.DATE)
	private Date dob;

	private String firstName;

	private String middleName;

	private String fullName;

	private String gender;

	private String lastName;

	private String password;

	private String state;

	private String zip;

	private int height;

	private int weight;

	private String bloodGroup;

	private String healthInsurance;

	private String smoking;

	private String drinking;

	private String coffee;

	private String tea;

	private String dominanthand;

	private String excercise;

	private String familyHistory;

	private String alergyToMedicine;

	private String currentMeditations;

	private String surgicalHistory;

	public UserDetail() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getHealthInsurance() {
		return healthInsurance;
	}

	public void setHealthInsurance(String healthInsurance) {
		this.healthInsurance = healthInsurance;
	}

	public String getSmoking() {
		return smoking;
	}

	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}

	public String getDrinking() {
		return drinking;
	}

	public void setDrinking(String drinking) {
		this.drinking = drinking;
	}

	public String getCoffee() {
		return coffee;
	}

	public void setCoffee(String coffee) {
		this.coffee = coffee;
	}

	public String getTea() {
		return tea;
	}

	public void setTea(String tea) {
		this.tea = tea;
	}

	public String getDominanthand() {
		return dominanthand;
	}

	public void setDominanthand(String dominanthand) {
		this.dominanthand = dominanthand;
	}

	public String getExcercise() {
		return excercise;
	}

	public void setExcercise(String excercise) {
		this.excercise = excercise;
	}

	public String getFamilyHistory() {
		return familyHistory;
	}

	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}

	public String getAlergyToMedicine() {
		return alergyToMedicine;
	}

	public void setAlergyToMedicine(String alergyToMedicine) {
		this.alergyToMedicine = alergyToMedicine;
	}

	public String getCurrentMeditations() {
		return currentMeditations;
	}

	public void setCurrentMeditations(String currentMeditations) {
		this.currentMeditations = currentMeditations;
	}

	public String getSurgicalHistory() {
		return surgicalHistory;
	}

	public void setSurgicalHistory(String surgicalHistory) {
		this.surgicalHistory = surgicalHistory;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Override
	public String toString() {
		return "UserDetail [userId=" + userId + ", address=" + address
				+ ", city=" + city + ", contactNo=" + contactNo + ", dob="
				+ dob + ", firstName=" + firstName + ", fullName=" + fullName
				+ ", gender=" + gender + ", lastName=" + lastName
				+ ", password=" + password + ", state=" + state + ", zip="
				+ zip + ", height=" + height + ", weight=" + weight
				+ ", bloodGroup=" + bloodGroup + ", healthInsurance="
				+ healthInsurance + ", smoking=" + smoking + ", drinking="
				+ drinking + ", coffee=" + coffee + ", tea=" + tea
				+ ", dominanthand=" + dominanthand + ", excercise=" + excercise
				+ ", familyHistory=" + familyHistory + ", alergyToMedicine="
				+ alergyToMedicine + ", currentMeditations="
				+ currentMeditations + ", surgicalHistory=" + surgicalHistory
				+ "]";
	}

}
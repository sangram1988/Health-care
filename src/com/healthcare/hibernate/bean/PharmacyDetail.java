package com.healthcare.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the pharmacy_detail database table.
 * 
 */
@Entity
@Table(name = "pharmacy_detail")
public class PharmacyDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pharmacy_id")
	private String pharmacyId;

	private String password;

	private String address;

	private String city;

	@Column(name = "contact_number")
	private String contactNumber;

	@Column(name = "owner_name")
	private String ownerName;

	@Column(name = "pharmacy_name")
	private String pharmacyName;

	private String state;

	@Column(name = "zip_code")
	private String zipCode;

	public PharmacyDetail() {
	}

	public String getPharmacyId() {
		return this.pharmacyId;
	}

	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
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

	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPharmacyName() {
		return this.pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
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
		return "PharmacyDetail [pharmacyId=" + pharmacyId + ", password="
				+ password + ", address=" + address + ", city=" + city
				+ ", contactNumber=" + contactNumber + ", ownerName="
				+ ownerName + ", pharmacyName=" + pharmacyName + ", state="
				+ state + ", zipCode=" + zipCode + "]";
	}

}
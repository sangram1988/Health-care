package com.healthcare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.healthcare.exception.HealthcareException;
import com.healthcare.hibernate.bean.UserDetail;
import com.healthcare.hibernate.dao.UserDetailDao;
import com.healthcare.hibernate.util.Format;
import com.healthcare.util.Constants;

/**
 * Servlet implementation class PatientRegistration
 */
@WebServlet("/patientRegistration")
public class PatientRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside Patient Registration Controller.......");
		String userId = request.getParameter(Constants.USER_ID);
		String address = request.getParameter(Constants.ADDRESS);
		String city = request.getParameter(Constants.CITY);
		String contactNo = request.getParameter(Constants.CONTACT_NO);
		String firstName = request.getParameter(Constants.FIRST_NAME);
		String fullName = request.getParameter(Constants.FULL_NAME);
		String gender = request.getParameter(Constants.GENDER);
		String lastName = request.getParameter(Constants.LAST_NAME);
		String password = request.getParameter(Constants.PASSWORD);
		String state = request.getParameter(Constants.STATE);
		String zip = request.getParameter(Constants.ZIP);
		String bloodGroup = request.getParameter(Constants.BLOOD_GROUP);
		String healthInsurance = request.getParameter(Constants.HEALTH_INSURANCE);
		
		String smoking = request.getParameter(Constants.SMOKING);
		String drinking = request.getParameter(Constants.DRINKING);
		String coffee = request.getParameter(Constants.COFFEE);
		String tea = request.getParameter(Constants.TEA);
		String dominanthand = request.getParameter(Constants.DOMINANT_HAND);
		String excercise = request.getParameter(Constants.EXECISE);
		String familyHistory = request.getParameter(Constants.FAMILY_HISTORY);
		String alergyToMedicine = request.getParameter(Constants.ALERGY_TO_MEDICINES);
		String currentMeditations = request.getParameter(Constants.CURRENT_MEDITATIONS);
		String surgicalHistory = request.getParameter(Constants.SURGICAL_HISTORY);
		String heightStr = request.getParameter(Constants.HEIGHT);
		String weightStr = request.getParameter(Constants.WEIGHT);
		
		int height = 0;
		if(StringUtils.isNotBlank(heightStr)){
			height = Integer.parseInt(heightStr.trim());
		}
		
		int weight = 0;
		if(StringUtils.isNotBlank(weightStr)){
			weight = Integer.parseInt(weightStr.trim());
		}
		
		String dobStr = request.getParameter("dob");
		
		UserDetail userDetail = new UserDetail();
		userDetail.setAddress(address);
		userDetail.setAlergyToMedicine(alergyToMedicine);
		userDetail.setBloodGroup(bloodGroup);
		userDetail.setCity(city);
		userDetail.setCoffee(coffee);
		userDetail.setContactNo(contactNo);
		userDetail.setCurrentMeditations(currentMeditations);
		userDetail.setDob(Format.getDateYYYYMMDD(dobStr));
		userDetail.setDominanthand(dominanthand);
		userDetail.setDrinking(drinking);
		userDetail.setExcercise(excercise);
		userDetail.setFamilyHistory(familyHistory);
		userDetail.setFirstName(firstName);
		userDetail.setFullName(fullName);
		userDetail.setGender(gender);
		userDetail.setHealthInsurance(healthInsurance);
		userDetail.setHeight(height);
		userDetail.setLastName(lastName);
		userDetail.setPassword(password);
		userDetail.setSmoking(smoking);
		userDetail.setState(state);
		userDetail.setSurgicalHistory(surgicalHistory);
		userDetail.setTea(tea);
		userDetail.setUserId(userId);
		userDetail.setWeight(weight);
		userDetail.setZip(zip);
		
		UserDetailDao dao = UserDetailDao.getInstance();
		
		try {
			if(dao.addUser(userDetail)){
				
				request.setAttribute(Constants.MESSAGE, "User added successfully!!");
				request.setAttribute(Constants.LOGGED_IN_USER, userId);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			} 
		} catch (HealthcareException e) {
			request.setAttribute(Constants.MESSAGE, "Registration failed?? Please try again!!");
			RequestDispatcher rd = request.getRequestDispatcher("patientregistration.jsp");
			rd.forward(request, response);
		}
	}
}

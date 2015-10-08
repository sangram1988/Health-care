package com.healthcare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import com.healthcare.exception.HealthcareException;
import com.healthcare.hibernate.bean.UserDetail;
import com.healthcare.hibernate.dao.UserDetailDao;
import com.healthcare.util.Constants;

/**
 * Servlet implementation class UpdatePatientAction
 */
@WebServlet("/UpdatePatientAction")
public class UpdatePatientAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePatientAction() {
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
		System.out.println("Inside Patient Update Controller.......");
		String userId = (String)request.getSession().getAttribute(Constants.LOGGED_IN_USER);
		String address = request.getParameter(Constants.ADDRESS);
		String city = request.getParameter(Constants.CITY);
		String contactNo = request.getParameter(Constants.CONTACT_NO);
		String firstName = request.getParameter(Constants.FIRST_NAME);
		String middleName = request.getParameter(Constants.MIDDLE_NAME);
		String fullName = request.getParameter(Constants.FULL_NAME);
		String gender = request.getParameter(Constants.GENDER);
		String lastName = request.getParameter(Constants.LAST_NAME);
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
		UserDetailDao dao = UserDetailDao.getInstance();
		UserDetail userDetail = dao.getUserById(userId);
		userDetail.setAddress(address);
		userDetail.setAlergyToMedicine(alergyToMedicine);
		userDetail.setBloodGroup(bloodGroup);
		userDetail.setCity(city);
		userDetail.setCoffee(coffee);
		userDetail.setContactNo(contactNo);
		userDetail.setCurrentMeditations(currentMeditations);
		//userDetail.setDob(dob);
		userDetail.setDominanthand(dominanthand);
		userDetail.setDrinking(drinking);
		userDetail.setExcercise(excercise);
		userDetail.setFamilyHistory(familyHistory);
		userDetail.setFirstName(firstName);
		userDetail.setMiddleName(middleName);
		userDetail.setFullName(fullName);
		userDetail.setGender(gender);
		userDetail.setHealthInsurance(healthInsurance);
		userDetail.setHeight(height);
		userDetail.setLastName(lastName);
		userDetail.setSmoking(smoking);
		userDetail.setState(state);
		userDetail.setSurgicalHistory(surgicalHistory);
		userDetail.setTea(tea);
		userDetail.setWeight(weight);
		userDetail.setZip(zip);
		
		
		
		System.out.println("User Detail : "+userDetail);
		try {
			if(dao.updateUser(userDetail)){
				request.setAttribute(Constants.MESSAGE, "Patient profile updated successfully!!");
				RequestDispatcher rd = request.getRequestDispatcher("viewPatientProfile.jsp");
				rd.forward(request, response);
			} 
		} catch (HealthcareException e) {
			e.printStackTrace();
			request.setAttribute(Constants.MESSAGE, "Patient profile update failed?? Please try again!!");
			RequestDispatcher rd = request.getRequestDispatcher("updatePatientProfile.jsp");
			rd.forward(request, response);
		}
	}

}

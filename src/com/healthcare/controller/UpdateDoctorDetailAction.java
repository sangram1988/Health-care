package com.healthcare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.healthcare.exception.HealthcareException;
import com.healthcare.hibernate.bean.DoctorDetail;
import com.healthcare.hibernate.dao.DoctorDetailDao;
import com.healthcare.util.Constants;

/**
 * Servlet implementation class UpdateDoctorDetailAction
 */
@WebServlet("/UpdateDoctorDetailAction")
public class UpdateDoctorDetailAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDoctorDetailAction() {
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
		// TODO Auto-generated method stub
		
		
		String password = request.getParameter(Constants.PASSWORD);
		String experience = request.getParameter(Constants.DOCOTRBEAN_EXPERIENCE);
		String fees = request.getParameter(Constants.DOCOTRBEAN_FEES);
		String firstName = request.getParameter(Constants.DOCOTRBEAN_FIRSTNAME);
		String fullName = request.getParameter(Constants.DOCOTRBEAN_FULLNAME);
		String hospitalAddress = request.getParameter(Constants.DOCOTRBEAN_HOSPITAL_ADDRESS);
		String lastName = request.getParameter(Constants.DOCOTRBEAN_LASTNAME);
		String speciality = request.getParameter(Constants.DOCOTRBEAN_SPECIALITY);
		String state = request.getParameter(Constants.DOCOTRBEAN_STATE);
		String zipCode = request.getParameter(Constants.DOCOTRBEAN_ZIPCODE);

		DoctorDetailDao doctorDetailDao = new DoctorDetailDao();
		RequestDispatcher rd = null;
		
		String doctorId=(String) request.getSession().getAttribute(Constants.LOGGED_IN_USER);
		DoctorDetail doctorDetail = doctorDetailDao.getDoctorById(doctorId);
		try {
			request.getSession().setAttribute("message", Constants.DOCOTRBEAN_EDIT_SUCCESS_MSG);
			if (doctorDetail == null) {
				doctorDetail = new DoctorDetail();
				doctorDetail.setDoctorId(doctorId);
				doctorDetail.setPassword(password);
			}
			doctorDetail.setExperience(experience);
			doctorDetail.setFees(Double.parseDouble(fees));
			doctorDetail.setFirstName(firstName);
			doctorDetail.setFullName(fullName);
			doctorDetail.setLastName(lastName);
			doctorDetail.setHospitalAddress(hospitalAddress);
			doctorDetail.setSpeciality(speciality);
			doctorDetail.setState(state);
			doctorDetail.setZipCode(zipCode);

			if (doctorDetailDao.addDoctor(doctorDetail)) {
				request.setAttribute(Constants.LOGGED_IN_USER, doctorId);
				request.getSession().setAttribute(Constants.MESSAGE, Constants.DOCOTRBEAN_ADD_SUCCESS_MSG);
				rd = request.getRequestDispatcher("updateDoctorDetail.jsp");
				rd.forward(request, response);
			} else{
				rd = request.getRequestDispatcher("addEditDoctorDetail.jsp");
				request.setAttribute(Constants.MESSAGE, Constants.DOCOTRBEAN_FAILURE_MSG);
				rd.forward(request, response);
			}
		} catch (HealthcareException e) {
			request.getSession().setAttribute(Constants.MESSAGE, Constants.DOCOTRBEAN_FAILURE_MSG);
			rd.forward(request, response);
		}

		
	}

}

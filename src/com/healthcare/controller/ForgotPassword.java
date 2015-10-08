package com.healthcare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.healthcare.exception.HealthcareException;
import com.healthcare.hibernate.bean.DoctorDetail;
import com.healthcare.hibernate.bean.PharmacyDetail;
import com.healthcare.hibernate.bean.UserDetail;
import com.healthcare.hibernate.dao.DoctorDetailDao;
import com.healthcare.hibernate.dao.PharmacyDetailDao;
import com.healthcare.hibernate.dao.UserDetailDao;
import com.healthcare.hibernate.util.Format;
import com.healthcare.util.Constants;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword() {
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
		
		String id=request.getParameter("userId");
		String dob=request.getParameter("dateOfBirth");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		UserDetailDao patientDao = UserDetailDao.getInstance();
		DoctorDetailDao doctorDao = new DoctorDetailDao();
		PharmacyDetailDao pharmacyDao = new PharmacyDetailDao();
		System.out.println("Date of birth :"+dob+"\n type:"+type);
		UserDetail user = null;
		DoctorDetail doctor = null;
		PharmacyDetail pharmacy = null;
			try {
				if(type.equals(Constants.PATIENT)){
					user = patientDao.getUserById(id);
					System.out.println("dd:"+user.getDob()+"\n DD DB:"+Format.getStringYYYYMMDD(user.getDob()));
					if(user!=null && dob.equals(Format.getStringYYYYMMDD(user.getDob()))){
						System.out.println("Login Successful!!");
						HttpSession session = request.getSession();
						session.setAttribute(Constants.LOGGED_IN_USER, id);
						session.setAttribute(Constants.LOGGED_IN_TYPE, type);
						user.setPassword(password);
						patientDao.updateUser(user);
						response.sendRedirect("patientHome1.jsp");
					}else
					{
						request.setAttribute(Constants.MESSAGE, "You have enterd invalid date of birth !!");
						System.out.println("You have enterd invalid date of birth !!");
						RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
						rd.forward(request, response);
					}
				} else if(type.equals(Constants.DOCTOR)){
					doctor = doctorDao.getDoctorById(id);
					if(doctor!=null && dob.equals("1980-09-01")){
						HttpSession session = request.getSession();
						session.setAttribute(Constants.LOGGED_IN_USER, id);
						session.setAttribute(Constants.LOGGED_IN_TYPE, type);
						doctor.setPassword(password);
						doctorDao.addDoctor(doctor);
						response.sendRedirect("login.jsp");
					}else
					{
						request.setAttribute(Constants.MESSAGE, "You have enterd invalid date of birth !!");
						System.out.println("You have enterd invalid date of birth !!");
						RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
						rd.forward(request, response);
					}
				} else if(type.equals(Constants.PHARMACY)){
					pharmacy = pharmacyDao.getPharmacyById(id);
					if(pharmacy!=null && dob.equals("1980-07-04")){
						HttpSession session = request.getSession();
						session.setAttribute(Constants.LOGGED_IN_USER, id);
						session.setAttribute(Constants.LOGGED_IN_TYPE, type);
						pharmacy.setPassword(password);
						pharmacyDao.addPharmacy(pharmacy);
						response.sendRedirect("login.jsp");
					}else
					{
						request.setAttribute(Constants.MESSAGE, "You have enterd invalid date of birth !!");
						System.out.println("You have enterd invalid date of birth !!");
						RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
						rd.forward(request, response);
					}
				}
			} catch (HealthcareException e) {
				e.printStackTrace();
				request.setAttribute(Constants.MESSAGE, "You have enterd invalid date of birth !!");
				System.out.println("You have enterd invalid date of birth !!");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
	}

}

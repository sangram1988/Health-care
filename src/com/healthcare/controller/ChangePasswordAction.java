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
import com.healthcare.util.Constants;

/**
 * Servlet implementation class ChangePasswordAction
 */
@WebServlet("/ChangePasswordAction")
public class ChangePasswordAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordAction() {
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
		String oldPassword = request.getParameter("oldPassword");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute(Constants.LOGGED_IN_USER);
		String type = (String)session.getAttribute(Constants.LOGGED_IN_TYPE);
		RequestDispatcher rd =null;
		UserDetailDao patientDao = UserDetailDao.getInstance();
		DoctorDetailDao doctorDao = new DoctorDetailDao();
		PharmacyDetailDao pharmacyDao = new PharmacyDetailDao();
		
		UserDetail user = null;
		DoctorDetail doctor = null;
		PharmacyDetail pharmacy = null;
			try {
				if(type.equals(Constants.PATIENT)){
					user = patientDao.getUserById(id);
					if(user!=null && oldPassword.equals(user.getPassword())){
						user.setPassword(password);
						patientDao.updateUser(user);
						  rd = request.getRequestDispatcher("changepatientpassword.jsp");
						request.setAttribute(Constants.MESSAGE, "Password changed successfully.");
						System.out.println("Password changed successfully.");
						
					}else
					{
						  rd = request.getRequestDispatcher("changepatientpassword.jsp");
						request.setAttribute(Constants.MESSAGE, "Invalid credentials provided! Enter correct password!!");
						System.out.println("Login Failed! Invalid credentials provided!");
						
					}
				} else if(type.equals(Constants.DOCTOR)){
					doctor = doctorDao.getDoctorById(id);
					if(doctor!=null && oldPassword.equals(doctor.getPassword())){
						
						doctor.setPassword(password);
						doctorDao.addDoctor(doctor);
						  rd = request.getRequestDispatcher("changedoctorpassword.jsp");
						request.setAttribute(Constants.MESSAGE, "Password changed successfully.");
						System.out.println("Password changed successfully.");
						
					}else
					{
						  rd = request.getRequestDispatcher("changedoctorpassword.jsp");
						request.setAttribute(Constants.MESSAGE, "Invalid credentials provided! Enter correct password!!");
						System.out.println("Login Failed! Invalid credentials provided!");
					}
				} else if(type.equals(Constants.PHARMACY)){
					pharmacy = pharmacyDao.getPharmacyById(id);
					if(pharmacy!=null && oldPassword.equals(pharmacy.getPassword())){
						
						pharmacy.setPassword(password);
						pharmacyDao.addPharmacy(pharmacy);

						  rd = request.getRequestDispatcher("changepharmacypassword.jsp");
						request.setAttribute(Constants.MESSAGE, "Password changed successfully.");
						System.out.println("Password changed successfully.");
					}else
					{
						  rd = request.getRequestDispatcher("changepharmacypassword.jsp");
						request.setAttribute(Constants.MESSAGE, "Invalid credentials provided! Enter correct password!!");
						System.out.println("Login Failed! Invalid credentials provided!");
					}
				}else if(type.equals(Constants.ADMIN)){
					
					if(oldPassword.equals("admin")){
						  rd = request.getRequestDispatcher("changepharmacypassword.jsp");
						response.sendRedirect("admHome.jsp");
					}
					
				}else
				{
					  rd = request.getRequestDispatcher("changepharmacypassword.jsp");
					request.setAttribute(Constants.MESSAGE, "Login Failed! Invalid credentials provided!");
					System.out.println("Login Failed! Invalid credentials provided!");
				}
				
				
				rd.forward(request, response);
				
			} catch (HealthcareException e) {
				request.setAttribute(Constants.MESSAGE, "Login Failed! Invalid credentials provided!");
				System.out.println("Login Failed! Invalid credentials provided!");
				  rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}

	
		

		
	}

}

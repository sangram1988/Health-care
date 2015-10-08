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
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("userId");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		UserDetailDao patientDao = UserDetailDao.getInstance();
		DoctorDetailDao doctorDao = new DoctorDetailDao();
		PharmacyDetailDao pharmacyDao = new PharmacyDetailDao();
		
		UserDetail user = null;
		DoctorDetail doctor = null;
		PharmacyDetail pharmacy = null;
			try {
				if(type.equals(Constants.PATIENT)){
					user = patientDao.getUserById(id);
					if(user!=null && password.equals(user.getPassword())){
						System.out.println("Login Successful!!");
						HttpSession session = request.getSession();
						session.setAttribute(Constants.LOGGED_IN_USER, id);
						session.setAttribute(Constants.LOGGED_IN_TYPE, type);
						response.sendRedirect("patientHome1.jsp");
					}else
					{
						request.setAttribute(Constants.MESSAGE, "Login Failed! Invalid credentials provided!");
						System.out.println("Login Failed! Invalid credentials provided!");
						RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
						rd.forward(request, response);
					}
				} else if(type.equals(Constants.DOCTOR)){
					doctor = doctorDao.getDoctorById(id);
					if(doctor!=null && password.equals(doctor.getPassword())){
						HttpSession session = request.getSession();
						session.setAttribute(Constants.LOGGED_IN_USER, id);
						session.setAttribute(Constants.LOGGED_IN_TYPE, type);
						response.sendRedirect("docHome.jsp");
					}else
					{
						request.setAttribute(Constants.MESSAGE, "Login Failed! Invalid credentials provided!");
						System.out.println("Login Failed! Invalid credentials provided!");
						RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
						rd.forward(request, response);
					}
				} else if(type.equals(Constants.PHARMACY)){
					pharmacy = pharmacyDao.getPharmacyById(id);
					if(pharmacy!=null && password.equals(pharmacy.getPassword())){
						HttpSession session = request.getSession();
						session.setAttribute(Constants.LOGGED_IN_USER, id);
						session.setAttribute(Constants.LOGGED_IN_TYPE, type);
						response.sendRedirect("pharHome.jsp");
					}else
					{
						request.setAttribute(Constants.MESSAGE, "Login Failed! Invalid credentials provided!");
						System.out.println("Login Failed! Invalid credentials provided!");
						RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
						rd.forward(request, response);
					}
				}else if(type.equals("-Select Type-")){
					
					if(password.equals("admin"))
						response.sendRedirect("admHome.jsp");
					
				}else
				{
					request.setAttribute(Constants.MESSAGE, "Login Failed! Invalid credentials provided!");
					System.out.println("Login Failed! Invalid credentials provided!");
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}
				
			} catch (HealthcareException e) {
				request.setAttribute(Constants.MESSAGE, "Login Failed! Invalid credentials provided!");
				System.out.println("Login Failed! Invalid credentials provided!");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

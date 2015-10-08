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
import com.healthcare.hibernate.bean.UserDetail;
import com.healthcare.hibernate.dao.UserDetailDao;
import com.healthcare.util.Constants;

/**
 * Servlet implementation class PatientChangePaasswordAction
 */
@WebServlet("/PatientChangePaasswordAction")
public class PatientChangePaasswordAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientChangePaasswordAction() {
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
		HttpSession session = request.getSession();
		String patientId = (String)session.getAttribute(Constants.LOGGED_IN_USER);
		
		UserDetailDao dao = UserDetailDao.getInstance();
		
		UserDetail patient = null;
		try {
			patient=dao.getUserById(patientId);

			
			request.setAttribute(Constants.MESSAGE, "Password changed successfully!!");
			RequestDispatcher rd = request.getRequestDispatcher("changepatientpassword.jsp");
			rd.forward(request, response);
			
		} catch (HealthcareException e) {
			request.setAttribute(Constants.MESSAGE, "Old password cant not match, please enter correct password ...");
			RequestDispatcher rd = request.getRequestDispatcher("changepatientpassword.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}
		String password = request.getParameter(Constants.PASSWORD);
		
	}

}

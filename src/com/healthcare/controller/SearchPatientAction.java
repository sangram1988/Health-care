package com.healthcare.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.healthcare.exception.HealthcareException;
import com.healthcare.hibernate.bean.UserDetail;
import com.healthcare.hibernate.dao.UserDetailDao;

/**
 * Servlet implementation class SearchPatientAction
 */
@WebServlet("/SearchPatientAction")
public class SearchPatientAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPatientAction() {
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
		String searchStr = request.getParameter("searchStr");

		RequestDispatcher rd = request.getRequestDispatcher("patientSerachForDoctor.jsp");
		
		List<UserDetail> patientList=new ArrayList<UserDetail>();
		try {
			patientList=UserDetailDao.getInstance().searchUser(searchStr);
				request.setAttribute("patientList", patientList);
				rd.forward(request, response);
			
		} catch (HealthcareException e) {
			e.printStackTrace();
			rd.forward(request, response);
		}
	}

}

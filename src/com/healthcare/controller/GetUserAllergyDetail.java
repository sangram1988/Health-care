package com.healthcare.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.healthcare.hibernate.dao.UserDetailDao;

/**
 * Servlet implementation class GetUserAllergyDetail
 */
@WebServlet("/GetUserAllergyDetail")
public class GetUserAllergyDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserAllergyDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String str = request.getParameter("value");
		UserDetailDao userDetailDao=UserDetailDao.getInstance();
		
		String userAllergy="";
		if(userDetailDao.getUserById(str)!=null)
		 userAllergy=userDetailDao.getUserById(str).getAlergyToMedicine();
		
		response.getWriter().write(userAllergy);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

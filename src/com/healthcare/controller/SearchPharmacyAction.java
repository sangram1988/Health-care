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
import com.healthcare.hibernate.bean.PharmacyDetail;
import com.healthcare.hibernate.dao.PharmacyDetailDao;

/**
 * Servlet implementation class SearchPharmacyAction
 */
@WebServlet("/SearchPharmacyAction")
public class SearchPharmacyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPharmacyAction() {
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

		PharmacyDetailDao PharmacyDetailDao = new PharmacyDetailDao();
		RequestDispatcher rd = request.getRequestDispatcher("searchPharmacy.jsp");
		
		List<PharmacyDetail> pharmacyList=new ArrayList<PharmacyDetail>();
		try {
			pharmacyList=PharmacyDetailDao.searchPharmacy(searchStr);
				request.setAttribute("pharmacyList", pharmacyList);
				rd.forward(request, response);
			
		} catch (HealthcareException e) {
			e.printStackTrace();
			rd.forward(request, response);
		}
	}

}

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
import com.healthcare.hibernate.bean.AppointmentDetail;
import com.healthcare.hibernate.bean.DoctorDetail;
import com.healthcare.hibernate.dao.AppointmentDetailsDao;
import com.healthcare.hibernate.dao.DoctorDetailDao;

/**
 * Servlet implementation class searchAppointmentDetailAction
 */
@WebServlet("/searchAppointmentDetailAction")
public class searchAppointmentDetailAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchAppointmentDetailAction() {
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

		AppointmentDetailsDao appointmentDetailsDao = new AppointmentDetailsDao();
		RequestDispatcher rd = request.getRequestDispatcher("viewAppointmentPrescription.jsp");
		
		List<AppointmentDetail> appointmentList=new ArrayList<AppointmentDetail>();
		try {
			appointmentList=appointmentDetailsDao.searchAppointment(searchStr);
				request.setAttribute("appointmentList", appointmentList);
				rd.forward(request, response);
			
		} catch (HealthcareException e) {
			e.printStackTrace();
			rd.forward(request, response);
		}
	}

}

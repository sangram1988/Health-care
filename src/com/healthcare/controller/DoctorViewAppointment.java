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
import javax.servlet.http.HttpSession;

import com.healthcare.hibernate.bean.AppointmentDetail;
import com.healthcare.hibernate.dao.AppointmentDetailsDao;
import com.healthcare.util.Constants;

/**
 * Servlet implementation class DoctorViewAppointment
 */
@WebServlet("/DoctorViewAppointment")
public class DoctorViewAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorViewAppointment() {
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
		
		String submitBtn=request.getParameter("appointBtn");
		String appointIdsChecked[]=request.getParameterValues("appointment");
		
		HttpSession session = request.getSession();
		String doctorId=(String)session.getAttribute(Constants.LOGGED_IN_USER);
		
		for(String checkBx:appointIdsChecked){
			System.out.println("***********"+checkBx);
		}
		
		AppointmentDetailsDao appointmentDetailDao = new AppointmentDetailsDao();
		RequestDispatcher rd = request
				.getRequestDispatcher("doctorViewAppointments.jsp");

		if(submitBtn.equalsIgnoreCase("SAVE")){
			request.setAttribute("message",
					Constants.APPOINTMENT_EDIT_SUCCESS_MSG);
			
			List<AppointmentDetail> appointmentList=new ArrayList<AppointmentDetail>();
			appointmentList=appointmentDetailDao.saveDoctorAppointments(appointIdsChecked,doctorId);
			request.setAttribute("appointmentList",appointmentList);

			rd.forward(request, response);
			
		}else{
			List<AppointmentDetail> appointmentList=new ArrayList<AppointmentDetail>();
			appointmentList=appointmentDetailDao.listOfDoctorAppointments(doctorId);
			request.setAttribute("appointmentList",appointmentList);

			rd.forward(request, response);
		}
		
		
	}

}

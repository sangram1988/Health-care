package com.healthcare.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.healthcare.exception.HealthcareException;
import com.healthcare.form.AppointmentForm;
import com.healthcare.hibernate.bean.AppointmentDetail;
import com.healthcare.hibernate.dao.AppointmentDetailsDao;
import com.healthcare.hibernate.util.Format;
import com.healthcare.util.Constants;

/**
 * Servlet implementation class AddEditDeleteAppointmentsAction
 */
@WebServlet("/AddEditDeleteAppointmentsAction")
public class AddEditDeleteAppointmentsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddEditDeleteAppointmentsAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String id = request.getParameter(Constants.APPOINTMENT_ID);
		String startTime = request
				.getParameter(Constants.APPOINTMENT_STARTTIME);
		String endTime = request.getParameter(Constants.APPOINTMENT_ENDTIME);
		String type = request.getParameter(Constants.APPOINTMENT_TYPE);
		String title = request.getParameter(Constants.APPOINTMENT_TITLE);
		String description = request
				.getParameter(Constants.APPOINTMENT_DESCRIPTION);
		String prescriptions = request
				.getParameter(Constants.PRESCRIPTIONS);

		String buttons = request.getParameter("appointments");

		AppointmentDetailsDao appointmentDetailDao = new AppointmentDetailsDao();
		RequestDispatcher rd = request
				.getRequestDispatcher("addEditAppointmentDetails.jsp");

		String appointmentId = request.getParameter("appointmentId");
		
		if(Format.isStringEmptyORNull(appointmentId))
		    appointmentId = ""+new Date().getTime();
		
		Date startDT = null;
		Date endDT = null;
		
		if(!Format.isStringEmptyORNull(startTime))
		  startDT = Format.getDateDDMMYYYYHHMM(startTime);
		if(!Format.isStringEmptyORNull(endTime))
		  endDT = Format.getDateDDMMYYYYHHMM(endTime);
		
		
		System.out.println("startDT :"+startDT);
		System.out.println("endDT :"+endDT);
		
		HttpSession session = request.getSession();
		String userId=(String)session.getAttribute(Constants.LOGGED_IN_USER);
		String status = "pending";
		
		System.out.println("Button Name : "+buttons);
		System.out.println("Type  : "+type);

		AppointmentDetail appointmentDetail = null;
		
		if(!Format.isStringEmptyORNull(appointmentId)){
			appointmentDetail = appointmentDetailDao
				.getAppointmentById(appointmentId);
		}
		
		if (buttons.equalsIgnoreCase("SAVE")) {
				
			try {
				request.setAttribute("message",
						Constants.APPOINTMENT_EDIT_SUCCESS_MSG);
				if (appointmentDetail == null) {
					request.setAttribute("message","Appointment Allready exist for selected time.Please select another time.");
					
					AppointmentForm appointmentForm=new AppointmentForm();
					appointmentForm.setUserId(userId);
					appointmentForm.setStartTime(startDT);
					appointmentForm.setEndTime(endDT);
					
					List<AppointmentDetail> appointmentList=new ArrayList<AppointmentDetail>();
					
					appointmentList=appointmentDetailDao.searchDuplicateAppointmentsBetween(appointmentForm);
					System.out.println("list : "+appointmentList);
					if(appointmentList!=null && !appointmentList.isEmpty()){
						request.setAttribute("appointmentList",appointmentList);
						rd.forward(request, response);
						return;
					}
					
					appointmentDetail = new AppointmentDetail();
					
					appointmentDetail.setAppointmentId(appointmentId);
					request.setAttribute("message",
							Constants.APPOINTMENT_ADD_SUCCESS_MSG);
				}

				appointmentDetail.setDescription(description);
				appointmentDetail.setAppointmentDateTimeStart(startDT);
				appointmentDetail.setAppointmentDateTimeEnd(endDT);
				appointmentDetail.setAppointmentStatus(status);
				appointmentDetail.setTitle(title);
				appointmentDetail.setDescription(description);
				appointmentDetail.setPrescriptions(prescriptions);

				appointmentDetail.setUserId(userId);

				if (!Format.isStringEmptyORNull(type) &&  type.equalsIgnoreCase("doctor"))
					appointmentDetail.setDoctorId(id);

				if (!Format.isStringEmptyORNull(type) && type.equalsIgnoreCase("pharmacy"))
					appointmentDetail.setPharmacyId(id);

				if (appointmentDetailDao.addAppointment(appointmentDetail)) {
					List<AppointmentDetail> appointmentList=new ArrayList<AppointmentDetail>();
					appointmentList=appointmentDetailDao.listAppointments(userId,type);
					request.setAttribute("appointmentList",appointmentList);

					rd.forward(request, response);
				}
			} catch (HealthcareException e) {
				request.setAttribute("message",
						Constants.APPOINTMENT_FAILURE_MSG);
				e.printStackTrace();
				List<AppointmentDetail> appointmentList=new ArrayList<AppointmentDetail>();
				appointmentList=appointmentDetailDao.listAppointments(userId,type);
				request.setAttribute("appointmentList",appointmentList);
				rd.forward(request, response);
			}

		}else if(buttons.equalsIgnoreCase("DELETE")){
			
			appointmentDetailDao.removeAppointment(appointmentDetail);
			List<AppointmentDetail> appointmentList=new ArrayList<AppointmentDetail>();
			appointmentList=appointmentDetailDao.listAppointments(userId,type);
			request.setAttribute("appointmentList",appointmentList);
			
			request.setAttribute("message",
					Constants.APPOINTMENT_DELETE_MSG);
			rd.forward(request, response);
			
		}if(buttons.equalsIgnoreCase("SEARCH")){
			
			AppointmentForm appointmentForm=new AppointmentForm();
			appointmentForm.setDescription(description);
			appointmentForm.setTitle(title);
			appointmentForm.setType(type);
			appointmentForm.setStartTime(startDT);
			appointmentForm.setEndTime(endDT);

			if (!Format.isStringEmptyORNull(type) &&  type.equalsIgnoreCase("doctor"))
				appointmentDetail.setDoctorId(id);

			if (!Format.isStringEmptyORNull(type) && type.equalsIgnoreCase("pharmacy"))
				appointmentDetail.setPharmacyId(id);
			
			appointmentForm.setUserId(userId);
			
			List<AppointmentDetail> appointmentList=new ArrayList<AppointmentDetail>();
			
			if(appointmentForm.isFormEmpty())
				appointmentList=appointmentDetailDao.listAppointments(userId,type);
			else
			  appointmentList=appointmentDetailDao.searchAppointments(appointmentForm);
			
			request.setAttribute("appointmentList",appointmentList);
			rd.forward(request, response);
		}
		
	}


}
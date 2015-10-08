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
import com.healthcare.hibernate.bean.VisitDetail;
import com.healthcare.hibernate.dao.VisitDetailsDao;
import com.healthcare.hibernate.util.Format;
import com.healthcare.util.Constants;

/**
 * Servlet implementation class AddEditDeleteVisitDetailAction
 */
@WebServlet("/AddEditDeleteVisitAction")
public class AddEditDeleteDoctorVisitDetailAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEditDeleteDoctorVisitDetailAction() {
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

		String buttons = request.getParameter("visits");

		VisitDetailsDao visitDetailDao = new VisitDetailsDao();
		RequestDispatcher rd = request
				.getRequestDispatcher("addEditDoctorVisitDetails.jsp");

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
		String doctorId=(String)session.getAttribute(Constants.LOGGED_IN_USER);
		
		System.out.println("Button Name : "+buttons);
		System.out.println("Type  : "+type);

		VisitDetail visitDetail = null;
		
		if(!Format.isStringEmptyORNull(appointmentId)){
			visitDetail = visitDetailDao
				.getVisitById(appointmentId);
		}
		
		if (buttons.equalsIgnoreCase("SAVE")) {
				
			try {
				request.setAttribute("message",
						Constants.VISIT_EDIT_SUCCESS_MSG);
				if (visitDetail == null) {
					request.setAttribute("message","Visit Allready exist for selected time.Please select another time.");
					
					AppointmentForm appointmentForm=new AppointmentForm();
					appointmentForm.setDoctorId(doctorId);
					appointmentForm.setStartTime(startDT);
					appointmentForm.setEndTime(endDT);
					
					List<VisitDetail> visitList=new ArrayList<VisitDetail>();
					
					visitList=visitDetailDao.searchDuplicateVisitsBetween(appointmentForm);
					System.out.println("list : "+visitList);
					if(visitList!=null && !visitList.isEmpty()){
						request.setAttribute("visitList",visitList);
						rd.forward(request, response);
						return;
					}
					
					visitDetail = new VisitDetail();
					
					visitDetail.setVisitId(appointmentId);
					request.setAttribute("message",
							Constants.VISIT_ADD_SUCCESS_MSG);
				}

				visitDetail.setDescription(description);
				visitDetail.setVisitDateTimeStart(startDT);
				visitDetail.setVisitDateTimeEnd(endDT);
				visitDetail.setTitle(title);
				visitDetail.setDescription(description);
				visitDetail.setPrescriptions(prescriptions);

				visitDetail.setDoctorId(doctorId);

				if (!Format.isStringEmptyORNull(type) &&  type.equalsIgnoreCase("patient"))
					visitDetail.setUserId(id);

				if (!Format.isStringEmptyORNull(type) && type.equalsIgnoreCase("pharmacy"))
					visitDetail.setPharmacyId(id);

				if (visitDetailDao.addVisit(visitDetail)) {
					List<VisitDetail> visitList=new ArrayList<VisitDetail>();
					visitList=visitDetailDao.listVisits();
					request.setAttribute("visitList",visitList);

					rd.forward(request, response);
				}
			} catch (HealthcareException e) {
				request.setAttribute("message",
						Constants.VISIT_FAILURE_MSG);
				e.printStackTrace();
				List<VisitDetail> visitList=new ArrayList<VisitDetail>();
				visitList=visitDetailDao.listVisits();
				request.setAttribute("visitList",visitList);
				rd.forward(request, response);
			}

		}else if(buttons.equalsIgnoreCase("DELETE")){
			
			visitDetailDao.removeVisit(visitDetail);
			List<VisitDetail> visitList=new ArrayList<VisitDetail>();
			visitList=visitDetailDao.listVisits();
			request.setAttribute("visitList",visitList);
			
			request.setAttribute("message",
					Constants.VISIT_DELETE_MSG);
			rd.forward(request, response);
			
		}if(buttons.equalsIgnoreCase("SEARCH")){
			
			AppointmentForm appointmentForm=new AppointmentForm();
			appointmentForm.setDescription(description);
			appointmentForm.setTitle(title);
			appointmentForm.setType(type);
			appointmentForm.setStartTime(startDT);
			appointmentForm.setEndTime(endDT);
			appointmentForm.setUserId(id);
			appointmentForm.setPharmacyId(id);
			
			List<VisitDetail> visitList=new ArrayList<VisitDetail>();
			
			if(appointmentForm.isFormEmpty())
				visitList=visitDetailDao.listVisits();
			else
			  visitList=visitDetailDao.searchVisits(appointmentForm);
			
			request.setAttribute("visitList",visitList);
			rd.forward(request, response);
		}

	}

}

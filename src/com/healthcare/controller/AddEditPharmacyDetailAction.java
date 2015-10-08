package com.healthcare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.healthcare.exception.HealthcareException;
import com.healthcare.hibernate.bean.PharmacyDetail;
import com.healthcare.hibernate.dao.PharmacyDetailDao;
import com.healthcare.util.Constants;

/**
 * Servlet implementation class AddEditPharmacyDetailAction
 */
@WebServlet("/AddEditPharmacyDetailAction")
public class AddEditPharmacyDetailAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddEditPharmacyDetailAction() {
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
		String pharmacyId = request
				.getParameter(Constants.PHARMACYBEAN_PHARMACYID);
		String password = request.getParameter(Constants.PASSWORD);
		String pharmacyName = request
				.getParameter(Constants.PHARMACYBEAN_PHARMACYNAME);
		String ownerName = request
				.getParameter(Constants.PHARMACYBEAN_OWNERNAME);
		String pharmacyAddress = request
				.getParameter(Constants.PHARMACYBEAN_PHARMACY_ADDRESS);
		String city = request.getParameter(Constants.PHARMACYBEAN_CITY);
		String contactNumber = request
				.getParameter(Constants.PHARMACYBEAN_CONTACTNO);
		String state = request.getParameter(Constants.PHARMACYBEAN_STATE);
		String zipCode = request.getParameter(Constants.PHARMACYBEAN_ZIPCODE);

		PharmacyDetailDao pharmacyDetailDao = new PharmacyDetailDao();
		RequestDispatcher rd = null;

		PharmacyDetail pharmacyDetail = pharmacyDetailDao
				.getPharmacyById(pharmacyId);
		try {
			request.setAttribute(Constants.MESSAGE,
					Constants.PHARMACYBEAN_EDIT_SUCCESS_MSG);
			if (pharmacyDetail == null) {
				pharmacyDetail = new PharmacyDetail();
				pharmacyDetail.setPharmacyId(pharmacyId);
				pharmacyDetail.setPassword(password);
				
			}
			pharmacyDetail.setAddress(pharmacyAddress);
			pharmacyDetail.setCity(city);
			pharmacyDetail.setContactNumber(contactNumber);
			pharmacyDetail.setOwnerName(ownerName);
			pharmacyDetail.setPharmacyId(pharmacyId);
			pharmacyDetail.setState(state);
			pharmacyDetail.setPharmacyName(pharmacyName);
			pharmacyDetail.setZipCode(zipCode);

			if (pharmacyDetailDao.addPharmacy(pharmacyDetail)) {
				request.setAttribute(Constants.LOGGED_IN_USER, pharmacyId);
				rd = request.getRequestDispatcher("login.jsp");
				request.setAttribute(Constants.MESSAGE,
						Constants.PHARMACYBEAN_ADD_SUCCESS_MSG);
				rd.forward(request, response);
			} else {
				rd = request.getRequestDispatcher("addEditPharmacyDetail.jsp");
				request.setAttribute("message",
						Constants.PHARMACYBEAN_FAILURE_MSG);
				rd.forward(request, response);
			}
		} catch (HealthcareException e) {
			request.setAttribute("message", Constants.PHARMACYBEAN_FAILURE_MSG);
			rd.forward(request, response);
		}

	}

}

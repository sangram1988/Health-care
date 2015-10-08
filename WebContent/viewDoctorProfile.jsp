<%@page import="com.healthcare.exception.HealthcareException"%>
<%@page import="com.healthcare.hibernate.bean.DoctorDetail"%>
<%@page import="com.healthcare.hibernate.dao.DoctorDetailDao"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Doctor Detail</title>
<%
	String doctorId = (String) session
			.getAttribute(Constants.LOGGED_IN_USER);
	String message = (String)request.getAttribute(Constants.MESSAGE);
	DoctorDetailDao dao = new DoctorDetailDao();
	DoctorDetail doctor = null;
	if (StringUtils.isNotBlank(doctorId)) {
		try {
			doctor = dao.getDoctorById(doctorId);
		} catch (HealthcareException e) {
			out.println("Error while retrieving doctor details...");
		}
	} else {
		out.println("Error while retrieving doctor details... Please login and try again!!");
	}
%>
</head>
<body background="./images/body_bg.jpg">
<%@include file="doctorhome.jsp" %>
	<%
		String msg = (String) session.getAttribute("message");
		if (StringUtils.isNotBlank(msg)) {
	%>
	<div id="message"
		style="pobackground-color: white; display: block; width: 300px; height: 25px; float: center;">
		<%=session.getAttribute("message")%>
		<%
			session.setAttribute("message", "");
		%>
	</div>
	<%
		}
	%>

	<center>
		<h2>Doctor Details</h2>
	</center>
	<form action="#">
		<table border="0" align="center">
			<tr>
				<td width="130px">Doctor Id<label style="color: red;">*</label>
					:
				</td>
				<td><input type="text" name="doctorId" id="doctorId" size="30" disabled="disabled" value="<%=doctor.getDoctorId()%>"/></td>
			</tr>
			<tr>
				<td>First Name :</td>
				<td><input type="text" name="firstName" id="firstName"
					size="30" disabled="disabled" value="<%=doctor.getFirstName()%>"/></td>
			</tr>
			<tr>
				<td>Last Name :</td>
				<td><input type="text" name="lastName" id="lastName" size="30" disabled="disabled" value="<%=doctor.getLastName()%>"/></td>
			</tr>
			<tr>
				<td>Full Name :</td>
				<td><input type="text" name="fullName" id="fullName" size="30" disabled="disabled" value="<%=doctor.getFullName()%>"/></td>
			</tr>
			<tr>
				<td>Speciality :</td>
				<td><input type="text" name="speciality" id="speciality"
					size="30" disabled="disabled" value="<%=doctor.getSpeciality()%>"/></td>
			</tr>
			<tr>
				<td>Experience<label style="color: red;">*</label> :
				</td>
				<td><input type="text" name="experience" id="experience"
					size="30" disabled="disabled" value="<%=doctor.getExperience()%>"/>
					<div style="margin-left: 15px; float: right; color: gray;">Experience
						in year.</div></td>
			</tr>
			<tr>
				<td>Fees<label style="color: red;">*</label> :
				</td>
				<td><input type="text" name="fees" id="fees" size="30" disabled="disabled" value="<%=doctor.getFees()%>"/></td>
			</tr>
			<tr>
				<td>Hospital Address :</td>
				<td><input type="text" name="hospitalAddress"
					id="hospitalAddress" size="30" disabled="disabled" value="<%=doctor.getHospitalAddress()%>"/></td>
			</tr>
			<tr>
				<td>City :</td>
				<td><input type="text" name="city" id="city" size="30" disabled="disabled" value="<%=doctor.getCity()%>"/></td>
			</tr>
			<tr>
				<td>State :</td>
				<td><input type="text" name="state" id="state" size="30" disabled="disabled" value="<%=doctor.getState()%>"/></td>
			</tr>
			<tr>
				<td>Zip Code<label style="color: red;">*</label> :
				</td>
				<td><input type="text" name="zipCode" id="zipCode" size="30" disabled="disabled" value="<%=doctor.getZipCode()%>"/></td>
			</tr>

		</table>
	</form>
</body>
</html>
<%@page import="com.healthcare.exception.HealthcareException"%>
<%@page import="com.healthcare.hibernate.bean.UserDetail"%>
<%@page import="com.healthcare.hibernate.dao.UserDetailDao"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.healthcare.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
	function validate() {
		var firstName = document.getElementById('firstName').value;
		var lastName = document.getElementById('lastName').value;
		var gender = document.getElementById('gender').value;
		var zipCode = document.getElementById('zipCode').value;
		/*var height = document.getElementById('height').value;
		var weight = document.getElementById('weight').value;*/
		var add = document.getElementById('address').value;
		var contactNo=document.getElementById('contactNo').value;
		var dob = document.getElementById('dob').value;
		
		var msg = '';

		if (!firstName) {
			msg += 'Please enter First Name\n';
		}

		if (!dob || dob=="yyyy-MM-dd") {
			msg += 'Please enter valid Date of birth yyyy-MM-dd format.\n';
		}
		
		if (!contactNo) {
			msg += 'Please enter Phone No\n';
		}
		
		if (!add) {
			msg += 'Please enter Address\n';
		}
		if (!lastName) {
			msg += 'Please enter Last Name\n';
		}

		if (!gender) {
			msg += 'Please enter gender\n';
		}

		if (zipCode) {
			if (isNaN(zipCode) || zipCode.length != 6) {
				msg += 'Please enter only valid 6 digit zipcode.\n';
			}
		} else {
			msg += 'Please valid 6 digit zipcode.\n';
		}
		
		
		
		if (!password) {
			msg += "New password cannot be empty";
		}

		if (password != confirmPassword) {
			msg += 'Confirm new password must match password\n';
		}
		
		if (contactNo!=null || contactNo!="") {
			if (isNaN(contactNo)) {
				msg += 'Please enter valid Phone number.\n';
			}
		}

		
		if (msg) {
			alert(msg);
			return false;
		}
		return true;
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient Details</title>
<%
	String patientId = (String) session
			.getAttribute(Constants.LOGGED_IN_USER);
	String message = (String) request.getAttribute(Constants.MESSAGE);
	UserDetailDao dao = UserDetailDao.getInstance();
	UserDetail patient = null;
	if (StringUtils.isNotBlank(patientId)) {
		try {
			patient = dao.getUserById(patientId);
		} catch (HealthcareException e) {
			out.println("Error while retrieving patient details...");
		}
	} else {
		out.println("Error while retrieving patient details... Please login and try again!!");
	}
%>
</head>
<body background="./images/body_bg.jpg">
	<form action="UpdatePatientAction" method="post" onsubmit="return validate();">
		<%@include file="patienthome.jsp"%>
		<table>
			<tr>
				<td>
					<%
						if (StringUtils.isNotBlank(message)) {
					%> <%=message%> <%
 	}
 %>
				</td>
			</tr>
		</table>
		<table border="0" align="center">
			<tr>
				<td>
					<table border="0" align="left">
						<tr>
							<td>First Name <label style="color: red;">*</label> :
							</td>
							<td><input type="text" name="firstName" id="firstName"
								size="30" value="<%=patient.getFirstName()%>" /></td>
						</tr>
						<tr>
							<td>Middle Name :</td>
							<td><input type="text" name="middleName" id="middleName"
								size="30" value="<%=patient.getMiddleName()%>" /></td>
						</tr>
						<tr>
							<td>Last Name <label style="color: red;">*</label> :
							</td>
							<td><input type="text" name="lastName" id="lastName"
								size="30" value="<%=patient.getLastName()%>" /></td>
						</tr>

						<tr>
							<td>Address :</td>
							<td><input type="text" name="address" id="address" size="30"
								value="<%=patient.getAddress()%>" /></td>
						</tr>
						<tr>
							<td>City :</td>
							<td><input type="text" name="city" id="city" size="30"
								value="<%=patient.getCity()%>" /></td>
						</tr>
						<tr>
							<td>State :</td>
							<td><input type="text" name="state" id="state" size="30"
								value="<%=patient.getState()%>" /></td>
						</tr>
						<tr>
							<td>Zip Code<label style="color: red;">*</label> :
							</td>
							<td><input type="text" name="zipCode" id="zipCode" size="30"
								value="<%=patient.getZip()%>" /></td>
						</tr>
						<tr>
							<td>Contact No :</td>
							<td><input type="text" name="contactNo" id="contactNo"
								size="30" value="<%=patient.getContactNo()%>" /></td>
						</tr>
						<tr>
							<td>Birth Date :</td>
							<td><input type="text" name="dob" id="dob" size="30"
								value="<%=patient.getDob()%>" /></td>
						</tr>
						<tr>
							<td>Gender <label style="color: red;">*</label> :
							</td>
							<td><input type="text" name="gender" id="gender" size="30"
								value="<%=patient.getGender()%>" /></td>
						</tr>
						<tr>
							<td>User Name <label style="color: red;">*</label> :
							</td>
							<td><input type="text" name="userId" id="userId" size="30"
								disabled="disabled" value="<%=patient.getUserId()%>" /></td>
						</tr>
						<tr>
							<td>Height in cm:</td>
							<td><input type="text" name="height" id="height" size="30"
								value="<%=patient.getHeight()%>" /></td>
						</tr>
					</table>
				</td>
				<td>
					<table border="0" align="left">
						<tr>
							<td>Weight in kg :</td>
							<td><input type="text" name="weight" id="weight" size="30"
								value="<%=patient.getWeight()%>" /></td>
						</tr>
						<tr>
							<td>Blood group :</td>
							<td><input type="text" size="30"
								value="<%=patient.getBloodGroup()%>" /></td>
						</tr>
						<tr>
							<td>Health Insurance :</td>
							<td><input type="text" name="healthInsurance"
								id="healthInsurance" size="30"
								value="<%=patient.getHealthInsurance()%>" /></td>
						</tr>
						<tr>
							<td>Smoking :</td>
							<td><input type="text" name="smoking" id="smoking" size="30"
								value="<%=patient.getSmoking()%>" /></td>
						</tr>
						<tr>
							<td>Drinking :</td>
							<td><input type="text" name="drinking" id="drinking"
								size="30" value="<%=patient.getDrinking()%>" /></td>
						</tr>
						<tr>
							<td>Coffee :</td>
							<td><input type="text" name="coffee" id="coffee" size="30"
								value="<%=patient.getCoffee()%>" /></td>
						</tr>
						<tr>
							<td>Tea :</td>
							<td><input type="text" name="tea" id="tea" size="30"
								value="<%=patient.getTea()%>" /></td>
						</tr>
						<tr>
							<td>Dominant hand :</td>
							<td><input type="text" name="dominanthand" id="dominanthand"
								size="30" value="<%=patient.getDominanthand()%>" /></td>
						</tr>
						<tr>
							<td>Excercise :</td>
							<td><input type="text" name="excercise" id="excercise"
								size="30" value="<%=patient.getExcercise()%>" /></td>
						</tr>
						<tr>
							<td>Family history :</td>
							<td><input type="text" name="familyHistory"
								id="familyHistory" size="30"
								value="<%=patient.getFamilyHistory()%>" /></td>
						</tr>
						<tr>
							<td>Alergy to medicine :</td>
							<td><input type="text" name="alergyToMedicine"
								id="alergyToMedicine" size="30"
								value="<%=patient.getAlergyToMedicine()%>" /></td>
						</tr>
						<tr>
							<td>Current meditations :</td>
							<td><input type="text" name="currentMeditations"
								id="currentMeditations" size="30"
								value="<%=patient.getCurrentMeditations()%>" /></td>
						</tr>
						<tr>
							<td>Surgical history :</td>
							<td><input type="text" name="surgicalHistory"
								id="surgicalHistory" size="30"
								value="<%=patient.getSurgicalHistory()%>" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td><input type="submit" value="Update" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="org.apache.commons.lang3.StringUtils,com.healthcare.hibernate.bean.PharmacyDetail,com.healthcare.hibernate.dao.PharmacyDetailDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type="text/javascript">
	function validateForm() {

		var pharmacyId = document.getElementById('pharmacyId').value;
		var contactNumber = document.getElementById('contactNumber').value;
		var fees = document.getElementById('fees').value;
		var zipcode = document.getElementById('zipCode').value;
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;
		
		var msg = '';
		if (!pharmacyId && pharmacyId.indexOf('@') < 1) {
			msg += 'Please enter valid email id.\n';
		}
		if (isNaN(contactNumber)) {
			msg += 'Please enter valid contactNumber in year.\n';
		}

		if (isNaN(fees) && !fees.isDigit()) {
			msg += 'Please enter valid contactNumber in year.\n';
		}
		if (isNaN(zipcode) && zipcode.length != 6) {
			msg += 'Please enter valid 6 digi zipcode.\n';
		}

		if (!password) {
			msg += 'Please enter valid password.\n';
		}

		if (password != confirmPassword) {
			msg += 'Please enter valid confirm password must match password\n';
		}
		
		if (msg) {
			alert(msg);
			return false;
		}
		return true;
	}

	function close() {
		document.getElementById("message").style.display = "none";
	}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>update Pharmacy Detail</title>
</head>
<body background="./images/body_bg.jpg">
<%@include file="pharmacyhome.jsp" %>
	<%
		String msg = (String) request.getAttribute("message");
		if (msg != null && msg != "") {
	%>
	<div id="message"
		style="background-color: green; display: block; width: 300px; height: 25px; float: center;">
		<%=request.getAttribute("message")%>
	</div>
	<%
		}
		PharmacyDetailDao  pharmacyDetailDao=new PharmacyDetailDao();
		String pharmacyId=(String) request.getSession().getAttribute(Constants.LOGGED_IN_USER);
		PharmacyDetail pharmacyDetail = pharmacyDetailDao.getPharmacyById(pharmacyId);
	%>

	<center>
		<h2>Enter Pharmacy Details</h2>
	</center>
	<form action="UpdatePharmacyDetailAction" method="post"
		onsubmit="return validateForm();">
		<table border="0" align="center">
			<tr>
				<td width="130px">Pharmacy Id :<label style="color: red;">*</label>
					:
				</td>
				<td><input type="text" name="pharmacyId" id="pharmacyId" size="30" disabled="disabled" value=<%=pharmacyDetail.getPharmacyId() %>
					 ></input></td>
			</tr>
			<tr>
				<td>Pharmacy Name :</td>
				<td><input type="text" name="pharmacyName" id="pharmacyName"  size="30" disabled="disabled" value=<%=pharmacyDetail.getPharmacyName() %>
					 ></input></td>
			</tr>
			<tr>
				<td>Owner Name :</td>
				<td><input type="text" name="ownerName" id="ownerName" size="30" disabled="disabled" value=<%=pharmacyDetail.getOwnerName() %>
					 ></input></td>
			</tr>
			<tr>
				<td>Contact Number :<label style="color: red;">*</label> :
				</td>
				<td><input type="text" name="contactNumber" id="contactNumber"  size="30"  disabled="disabled" value=<%=pharmacyDetail.getContactNumber() %>
					></input></td>
			</tr>
			<tr>
				<td>Pharmacy Address :</td>
				<td><input type="text" name="pharmacyAddress" 	id="pharmacyAddress" size="30" disabled="disabled" value=<%=pharmacyDetail.getAddress() %>
				 ></input></td>
			</tr>
			<tr>
				<td>City :</td>
				<td><input type="text" name="city" id="city" size="30" disabled="disabled" value=<%=pharmacyDetail.getCity() %> ></input></td>
			</tr>
			<tr>
				<td>State :</td>
				<td><input type="text" name="state" id="state" size="30" disabled="disabled" value=<%=pharmacyDetail.getState() %> ></input></td>
			</tr>
			<tr>
				<td>Zip Code :<label style="color: red;">*</label> :
				</td>
				<td><input type="text" name="zipCode" id="zipCode" size="30"  disabled="disabled" value=<%=pharmacyDetail.getZipCode() %> ></input></td>
			</tr>

		</table>
		
	</form>
</body>
</html>
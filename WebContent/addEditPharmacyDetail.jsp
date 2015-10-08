<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="com.healthcare.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type="text/javascript">
	function validateForm() {

		var pharmacyId = document.getElementById('pharmacyId').value;
		var contactNumber = document.getElementById('contactNumber').value;
		var zipCode = document.getElementById('zipCode').value;
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;
		
		var msg = '';
		if (!pharmacyId /*|| pharmacyId.indexOf('@') < 1*/) {
			msg += 'Please enter valid email id.\n';
		}
		
		if (!contactNumber || isNaN(contactNumber) || contactNumber.length!=10) {
			msg += 'Please enter valid 10 digit contactNumber.\n';
		}

		if (zipCode) {
			if (isNaN(zipCode) || zipCode.length != 5) {
				msg += 'Please enter only valid 5 digit zipcode.\n';
			}
		} else {
			msg += 'Please valid 6 digit zipcode.\n';
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
<title>Add Edit Pharmacy Detail</title>
</head>
<body background="./images/body_bg.jpg">
	<%
		String msg = (String)request.getAttribute(Constants.MESSAGE);
		if (msg != null && msg != "") {
	%>
	<div id="message"
		style="background-color: green; display: block; width: 300px; height: 25px; float: center;">
		<%=request.getAttribute(Constants.MESSAGE)%>
	</div>
	<%
		}
	%>

	<center>
		<h2>Enter Pharmacy Details</h2>
	</center>
	<form action="AddEditPharmacyDetailAction" method="post"
		onsubmit="return validateForm();">
		<table border="0" align="center">
			<tr>
				<td width="160px">Pharmacy Id :<label style="color: red;">*</label>
					:
				</td>
				<td><input type="text" name="pharmacyId" id="pharmacyId"
					size="30" /></td>
			</tr>
			<tr>
				<td>Password <label style="color: red;">*</label> :
				</td>
				<td><input type="password" name="password" id="password"
					size="30" /></td>
			</tr>
			<tr>
				<td>Configrm Password <label style="color: red;">*</label> :
				</td>
				<td><input type="password" name="confirmPassword"
					id="confirmPassword" size="30" /></td>
			</tr>
			<tr>
				<td>Pharmacy Name :</td>
				<td><input type="text" name="pharmacyName" id="pharmacyName"
					size="30" /></td>
			</tr>
			<tr>
				<td>Owner Name :</td>
				<td><input type="text" name="ownerName" id="ownerName"
					size="30" /></td>
			</tr>
			<tr>
				<td>Contact Number :<label style="color: red;">*</label> :
				</td>
				<td><input type="text" name="contactNumber" id="contactNumber"
					size="30" /></td>
			</tr>
			<tr>
				<td>Pharmacy Address :</td>
				<td><input type="text" name="pharmacyAddress"
					id="pharmacyAddress" size="30" /></td>
			</tr>
			<tr>
				<td>City :</td>
				<td><input type="text" name="city" id="city" size="30" /></td>
			</tr>
			<tr>
				<td>State :</td>
				<td><input type="text" name="state" id="state" size="30" /></td>
			</tr>
			<tr>
				<td>Zip Code :<label style="color: red;">*</label> :
				</td>
				<td><input type="text" name="zipCode" id="zipCode" size="30" /></td>
			</tr>

		</table>
		</br> </br>
		<table align="center">
			<tr>
				<td><input type="submit" value="SAVE" height="14px" /> <input
					type="reset" value="CLEAR" height="14px" /></td>
				<td><a href="login.jsp">Login</a></td>
			</tr>
		</table>
	</form>
</body>
</html>
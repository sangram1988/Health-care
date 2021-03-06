<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.healthcare.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type="text/javascript">
	function validate() {

		var doctorId = document.getElementById('doctorId').value;
		var experience = document.getElementById('experience').value;
		var fees = document.getElementById('fees').value;
		var zipCode = document.getElementById('zipCode').value;
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;

		var msg = '';
		if (!doctorId){
			msg += 'Please enter email id.\n';
		}
	/*	if (doctorId && doctorId.indexOf('@') < 1) {
			msg += 'Please enter valid email id.\n';
		}
		*/
		if (!experience){
			msg += 'Please enter experience in year.\n';
		}
		if (experience&&isNaN(experience)) {
			msg += 'Please enter valid experience in year.\n';
		}
		if (!fees){
			msg += 'Please enter fees.\n';
		}
				
		if (fees && isNaN(fees)) {
			msg += 'Please enter valid fees.\n';
		}
		
		if (zipCode) {
			if (isNaN(zipCode) || zipCode.length != 5) {
				msg += 'Please enter only valid 5 digit zipcode.\n';
			}
		} else {
			msg += 'Please valid 5 digit zipcode.\n';
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
<title>Doctor Detail</title>
</head>
<body background="./images/body_bg.jpg">
	<%
		String msg = (String) session.getAttribute(Constants.MESSAGE);
		if (StringUtils.isNotBlank(msg)) {
	%>
	<div id="message"
		style="pobackground-color: green; display: block; width: 300px; height: 25px; float: center;">
		<%=session.getAttribute(Constants.MESSAGE)%>
		<%
			session.setAttribute(Constants.MESSAGE, "");
		%>
	</div>
	<%
		}
	%>

	<center>
		<h2>Enter Doctor Details</h2>
	</center>
	<form action="AddEditDoctorDetailAction" method="post"
		onsubmit="return validate();">
		<table border="0" align="center">
			<tr>
				<td width="160px">Doctor Id<label style="color: red;">*</label>
					:
				</td>
				<td><input type="text" name="doctorId" id="doctorId" size="30" /></td>
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
				<td>First Name :</td>
				<td><input type="text" name="firstName" id="firstName"
					size="30" /></td>
			</tr>
			<tr>
				<td>Last Name :</td>
				<td><input type="text" name="lastName" id="lastName" size="30" /></td>
			</tr>
			<tr>
				<td>Full Name :</td>
				<td><input type="text" name="fullName" id="fullName" size="30" /></td>
			</tr>
			<tr>
				<td>Speciality :</td>
				<td><input type="text" name="speciality" id="speciality"
					size="30" /></td>
			</tr>
			<tr>
				<td>Experience<label style="color: red;">*</label> :
				</td>
				<td><input type="text" name="experience" id="experience"
					size="30" />
					<div style="margin-left: 15px; float: right; color: gray;">Experience
						in year.</div></td>
			</tr>
			<tr>
				<td>Fees<label style="color: red;">*</label> :
				</td>
				<td><input type="text" name="fees" id="fees" size="30" /></td>
			</tr>
			<tr>
				<td>Hospital Address :</td>
				<td><input type="text" name="hospitalAddress"
					id="hospitalAddress" size="30" /></td>
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
				<td>Zip Code<label style="color: red;">*</label> :
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
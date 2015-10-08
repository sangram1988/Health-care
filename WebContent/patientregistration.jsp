<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.healthcare.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient Registration</title>
<%
	String userId =  (String)request.getAttribute(Constants.USER_ID);
	String message = (String) request.getAttribute(Constants.MESSAGE);
%>
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
		var userId = document.getElementById("userId").value;
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;
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
			if (isNaN(zipCode) || zipCode.length != 5) {
				msg += 'Please enter only valid 5 digit zipcode.\n';
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

		if (!userId) {
			msg += 'Please enter valid user id\n';
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
</script>
</head>
<body background="./images/body_bg.jpg">
	<form action="patientRegistration" method="post"
		onsubmit="return validate();">
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
		<br><br><br><br><br><br><br>
		<table border="2" align="center">
		<tr>
			<td><label style="font-size:large">
			User Registration!!!!!!!!!</label>
			</td>
		</tr>
		</table>
		<br><br>
		<table border="0" align="center">
			<tr>
				<td>
					<table border="0" align="left">
						<tr>
							<td>First Name <label style="color: red;">*</label> :
							</td>
							<td><input type="text" name="firstName" id="firstName"
								size="30" /></td>
						</tr>
					<!-- <tr>
							<td>Middle Name :</td>
							<td><input type="text" name="middleName" id="middleName"
								size="30" /></td>
						</tr> -->
						<tr>
							<td>Last Name <label style="color: red;">*</label> :
							</td>
							<td><input type="text" name="lastName" id="lastName"
								size="30" /></td>
						</tr>

						<tr>
							<td>Address <label style="color: red;">*</label> :
							<td><input type="text" name="address" id="address" size="30" /></td>
						</tr>
					<!-- 	<tr>
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
						</tr> -->
						<tr>
							<td>Phone No <label style="color: red;">*</label> :</td>
							<td><input type="text" name="contactNo" id="contactNo"
								size="30" /></td>
						</tr>
						<tr>
							<td>Birth Date <label style="color: red;">*</label>  :</td>
							<td><input type="text" name="dob" id="dob" size="30"  value="yyyy-MM-dd"/></td>
						</tr>
						<!--  <tr>
							<td>Gender <label style="color: red;">*</label> :
							</td>
							<td><select name="gender" id="gender">
									<option>Male</option>
									<option>Female</option>
									<option>Other</option>
							</select>
						</tr>
						<tr>
							<td>User Name <label style="color: red;">*</label> :
							</td>
							<td><input type="text" name="userId" id="userId" size="30" /></td>
						</tr>
						<tr>
							<td>Password <label style="color: red;">*</label> :
							</td>
							<td><input type="password" name="password" id="password"
								size="30" /></td>
						</tr>
						<tr>
							<td>Confirm Password <label style="color: red;">*</label> :
							</td>
							<td><input type="password" name="confirmPassword"
								id="confirmPassword" size="30" /></td>
						</tr>
						<!--  <tr>
							<td>Height :</td>
							<td><input type="text" name="height" id="height" size="30" /></td>
						</tr>-->
					</table>
				</td>
				<td>
			 		<table border="0" align="left">
			 		<tr>
							<td>Zip Code<label style="color: red;">*</label> :
							</td>
							<td><input type="text" name="zipCode" id="zipCode" size="30" /></td>
						</tr>
			 		<tr>
							<td>Gender <label style="color: red;">*</label> :
							</td>
							<td><select name="gender" id="gender">
									<option>Male</option>
									<option>Female</option>
									<option>Other</option>
							</select>
						</tr>
						<tr>
							<td>User Name <label style="color: red;">*</label> :
							</td>
							<td><input type="text" name="userId" id="userId" size="30" /></td>
						</tr>
						<tr>
							<td>Password <label style="color: red;">*</label> :
							</td>
							<td><input type="password" name="password" id="password"
								size="30" /></td>
						</tr>
						<tr>
							<td>Confirm Password <label style="color: red;">*</label> :
							</td>
							<td><input type="password" name="confirmPassword"
								id="confirmPassword" size="30" /></td>
						</tr>
			<!--			<tr>
							<td>Weight :</td>
							<td><input type="text" name="weight" id="weight" size="30" /></td>
						</tr>
						<tr>
							<td>Blood group :</td>
							<td>
								<select name="bloodGroup" id="bloodGroup">
									<option>O+</option>
									<option>O-</option>
									<option>A+</option>
									<option>A-</option>
									<option>B+</option>
									<option>B-</option>
									<option>AB+</option>
									<option>AB-</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>Health Insurance :</td>
							<td><input type="text" name="healthInsurance"
								id="healthInsurance" size="30" /></td>
						</tr>
						<tr>
							<td>Smoking :</td>
							<td><input type="text" name="smoking" id="smoking" size="30" /></td>
						</tr>
						<tr>
							<td>Drinking :</td>
							<td><input type="text" name="drinking" id="drinking"
								size="30" /></td>
						</tr>
						<tr>
							<td>Coffee :</td>
							<td><input type="text" name="coffee" id="coffee" size="30" /></td>
						</tr>
						<tr>
							<td>Tea :</td>
							<td><input type="text" name="tea" id="tea" size="30" /></td>
						</tr>
						<tr>
							<td>Dominant hand :</td>
							<td><input type="text" name="dominanthand" id="dominanthand"
								size="30" /></td>
						</tr>
						<tr>
							<td>Excercise :</td>
							<td><input type="text" name="excercise" id="excercise"
								size="30" /></td>
						</tr>
						<tr>
							<td>Family history :</td>
							<td><input type="text" name="familyHistory"
								id="familyHistory" size="30" /></td>
						</tr>
						<tr>
							<td>Alergy to medicine :</td>
							<td><input type="text" name="alergyToMedicine"
								id="alergyToMedicine" size="30" /></td>
						</tr>
						<tr>
							<td>Current meditations :</td>
							<td><input type="text" name="currentMeditations"
								id="currentMeditations" size="30" /></td>
						</tr>
						<tr>
							<td>Surgical history :</td>
							<td><input type="text" name="surgicalHistory"
								id="surgicalHistory" size="30" /></td>
						</tr>-->
					</table> 
				</td>
			</tr>
		</table>
		<br>
		<table align="center">
			<tr>
				<td>
					<input type="submit" value="Save" />
					<input type="reset" value="Clear" height="14px" />
					<a href="login.jsp">Login</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.healthcare.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<br><br><br>
<p align="center" style="font-size:xx-large;">Healthcare Point System</p>
<%
	String userId = (String) request
			.getAttribute(Constants.LOGGED_IN_USER);
	String message = (String) request.getAttribute(Constants.MESSAGE);
%>
<script type="text/javascript">
	function validate() {
		var userId = document.getElementById("userId").value;
		var password = document.getElementById("password").value;
		var type = document.getElementById("type").value;
		var msg = '';
		if (!userId) {
			msg = "UserName cannot be empty";
		}
		if (type=="-Select Type-" && userId!='admin') {
			msg += "Please select type";
		}
		if (!password) {
			msg += "Password cannot be empty";
		}
		
		if (msg) {
			alert(msg);
			return false;
		} else {
			
			return true;
		}
	}
	
	function register(){
		var type = document.getElementById("type").value;
		var registerLink = document.getElementById("registerLink");
		var msg= "";
		if(!type || type=="-Select Type-"){
			msg = "Please select type";
		} else{
			if(type=="Patient"){
				registerLink.href="patientregistration.jsp";	
			} else if(type=="Doctor"){
				registerLink.href="addEditDoctorDetail.jsp";
			} else if(type=="Pharmacy"){
				registerLink.href="addEditPharmacyDetail.jsp";
			}
		}
		
		if(msg){
			alert(msg);
			return false;
		} else{
			return true;
		}
	}
	
	function changePassword(){
		var type = document.getElementById("type").value;
		var userId = document.getElementById("userId").value;
		var changePasswordLink = document.getElementById("changePasswordLink");
		var msg= "";
		if(!type || type=="-Select Type-"){
			msg = "Please select type";
		} else{
			
			changePasswordLink.href="forgotPassword.jsp?type="+type+"&userId="+userId;
			
		}
		
		if(msg){
			alert(msg);
			return false;
		} else{
			return true;
		}
	}
</script>
</head>
<body background="./images/doc.jpg">

	<form action="login" method="get" onsubmit="return validate()">
	


	</table>
<br><br><br>
	
	
	<div align="center">
		<%
				if (StringUtils.isNotBlank(message)) {
					%>
					<label style="color: red">
					 <%=message%> 
					 </label><%
				}
 		%>
 	</div>
		<table border="1" align="center"  bordercolor="blue">
			<tr>
				<%
					if (StringUtils.isNotBlank(userId)) {
				%>
				<td width="120px" align="left">User Name<label style="color: red;">*</label> :</td><td> <input align="right"
					type="text" name="userId" id="userId" value="<%=userId%>" size="30" />
				</td>
				<%
					} else {
				%>
				<td width="120px" align="left">User Name<label style="color: red;">*</label> : </td><td> <input align="right"
					type="text" name="userId" id="userId" size="30" /> <%
 	}
 %>
			<tr>
				<td align="left">Type<label  style="color: red;">*</label> : </td><td>
					<select name="type" id="type">
						<option>-Select Type-</option>
						<option>Patient</option>
						<option>Doctor</option>
						<option>Pharmacy</option>
						
					</select>
				</td>
			</tr>
			<tr>
				<td align="left">Password<label style="color: red;">*</label> :</td><td> <input align="right"
					type="password" name="password" id="password" size="30" />
				</td>
			</tr>
		</table>
		<br><br>
		<table align="center">
			<tr>
				<td colspan="2"><input type="submit" value="Logon" /> &nbsp;&nbsp;<input type="reset" value="Clear" height="14px" /></td>
			</tr>
			<tr>
				<td><label>Forgot Password? </label> <a
					href="" onclick="return changePassword();" id="changePasswordLink">Click here</a></td>
			</tr>
			<tr>
				<td><label>New User? </label><a href="" onclick="return register();" id="registerLink">Click
						here</a></td>
			</tr>
		</table>
	</form>
</body>
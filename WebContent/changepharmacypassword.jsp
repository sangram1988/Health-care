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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
<script type="text/javascript">
	function validate() {
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;

		var msg = '';
		///////////if (!userId) {
			//msg = "UserName cannot be empty";
		//}
		if (!password) {
			msg += "New password cannot be empty";
		}

		if (password != confirmPassword) {
			msg += 'Confirm new password must match password\n';
		}
		if (msg) {
			alert(msg);
			return false;
		} else {

			return true;
		}
	}
</script>
<%
	String patientId = (String) session
			.getAttribute(Constants.LOGGED_IN_USER);
	String message = (String) request.getAttribute(Constants.MESSAGE);
%>
</head>
<body background="./images/body_bg.jpg">
	<form action="ChangePasswordAction"
		onsubmit="return validate();" method="post">
		<%@include file="pharmacyhome.jsp"%>
		<table align="center">
			<tr>
				<td align="center">
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
				<td width="120px" align="center">Old Password<label style="color: red;">*</label>
					: </td><td width="170px" ><input type="password" name="oldPassword" id="oldPassword" />
				</td>
			</tr>
			<tr>
				<td align="center">New Password<label style="color: red;">*</label>:
					</td><td><input type="password" name="password" id="password" />
				</td>
			</tr>
			<tr>
				<td align="center">Confirm New Password<label style="color: red;">*</label>:
				</td><td>	<input type="password" name="confirmPassword" id="confirmPassword" />
				</td>
			</tr>
			<tr><td></td>
				<td><input type="submit" value="Change Password" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
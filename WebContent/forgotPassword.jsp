<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.healthcare.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
<%
	String userId = (String) request.getAttribute(Constants.USER_ID);
	String message = (String) request.getAttribute(Constants.MESSAGE);
%>
<script type="text/javascript">
	function validate() {
		var dob = document.getElementById('dateOfBirth').value;
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;

		var msg = '';
		
		
		
		if (!dob || dob=="yyyy-MM-dd") {
			msg += 'Please enter valid Date of birth yyyy-MM-dd format.\n';
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

	<form action="ForgotPassword" method="post"
		onsubmit="return validate();">
		<input type="hidden" name="type"  id="type"  value=<%=request.getParameter("type") %>>
		<input type="hidden" name="userId"  id="userId"  value=<%=request.getParameter("userId") %>>
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
		<table border="0" align="left">
						<tr>
							<td>Enter Date Of Birth : <label style="color: red;">*</label> :
							
							</td>
							<td><input type="text" name="dateOfBirth" id="dateOfBirth" value="yyyy-MM-dd"
								size="30" /></td>
						</tr>
						<tr>
							<td>Password :<label style="color: red;">*</label> :
							</td>
							<td><input type="password" name="password" id="password"
								size="30" /></td>
						</tr>
						<tr>
							<td>Confirm Password :<label style="color: red;">*</label> :
							</td>
							<td><input type="password" name="confirmPassword"
								id="confirmPassword" size="30" /></td>
						</tr>
						<tr>
						<td></td>
						<td><input type="submit" value="Save" />
					<input type="reset" value="Clear" height="14px" /></td>
						</tr>
		</table>
		
			

</body>
</html>
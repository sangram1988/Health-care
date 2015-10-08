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
<title>Patitent Home</title>
</head>
<body background="./images/Patient.jpg">
	<form >
		<%@include file="patienthome.jsp"%>
			
	</form>
</body>
</html>
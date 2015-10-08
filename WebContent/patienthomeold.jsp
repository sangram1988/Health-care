<%@page import="com.healthcare.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<head>
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/script.js"></script>
<link rel="stylesheet" href="css/runnable.css" />
</head>
	<!-- Use this navigation div as your menu bar div -->
	<div class="navigation">
		<ul class="nav">
			<li><a href="#">Profile</a>
				<ul>
					<li><a href="viewpatient.jsp">View Profile</a></li>
					<li><a href="#">Update Profile</a></li>
					<li><a href="#">Change Password</a></li>
				</ul></li>
			<li><a href="#">Appointments</a>
				<ul>
					<li><a href="#">Book Appointment</a></li>
					<li><a href="#">View Appointment</a></li>
					<li><a href="#">Cancel Appointment</a></li>
				</ul></li>
			<li><a href="#">Doctors</a>
				<ul>
					<li><a href="#">Search Doctor By Id</a></li>
					<li><a href="#">Search Doctor By Name</a></li>
				</ul></li>
			<li><a href="#">Visits</a>
				<ul>
					<li><a href="#">View Visits</a></li>
				</ul></li>
			<li><a href="#">Other</a>
				<ul>
					<li><a href="#">Contact Information</a></li>
				</ul></li>
		</ul>
	</div>
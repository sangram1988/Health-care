<%@page import="com.healthcare.util.Constants"%>
<hr>
<a href="viewDoctorProfile.jsp">View Profile</a>
&nbsp;&nbsp;|
<a href="updateDoctorDetail.jsp">Update Profile</a>
&nbsp;&nbsp;|
<a href="changedoctorpassword.jsp">Change Password</a>
&nbsp;&nbsp;|
<a href="doctorViewAppointments.jsp">Appointments</a>
&nbsp;&nbsp;|
<a href="addEditDoctorVisitDetails.jsp">Add/Edit Visits</a>
&nbsp;&nbsp;|
<a href="patientSerachForDoctor.jsp">Patient Detail Search</a>
&nbsp;&nbsp;
<hr>
<div style="text-align: right;"><label style="color:white;">Welcome&nbsp;&nbsp;<%=session.getAttribute(Constants.LOGGED_IN_USER)%> &nbsp;&nbsp;</label>|
<a href="login.jsp">Logout</a></div>
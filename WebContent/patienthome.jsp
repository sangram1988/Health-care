<%@page import="com.healthcare.util.Constants"%>
<hr>
<a href="viewPatientProfile.jsp">View Profile</a>
&nbsp;&nbsp;|
<a href="updatePatientProfile.jsp">Update Profile</a>
&nbsp;&nbsp;|
<a href="changepatientpassword.jsp">Change Password</a>
&nbsp;&nbsp;|
<a href="addEditAppointmentDetails.jsp">Appointments</a>
&nbsp;&nbsp;|
<a href="searchDoctor.jsp">Doctor Search</a>
&nbsp;&nbsp;|
<a href="searchPharmacy.jsp">Pharmacy Search</a>
&nbsp;&nbsp;
<div style="text-align: right;">Welcome&nbsp;&nbsp;<%=session.getAttribute(Constants.LOGGED_IN_USER)%>&nbsp;&nbsp;|
<a href="login.jsp">Logout</a></div>
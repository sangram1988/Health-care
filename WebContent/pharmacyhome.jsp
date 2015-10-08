<%@page import="com.healthcare.util.Constants"%>
<hr>
<a href="viewPharmacyProfile.jsp">View Profile</a>
&nbsp;&nbsp;|
<a href="updatePharmacyDetail.jsp">Update Profile</a>
&nbsp;&nbsp;|
<a href="changepharmacypassword.jsp">Change Password</a>
&nbsp;&nbsp;|
<a href="pharmacyViewAppointments.jsp">View Appointments</a>
&nbsp;&nbsp;|
<a href="viewAppointmentPrescription.jsp">View Appointment Prescription</a>
&nbsp;&nbsp;|
<a href="viewVisitPrescription.jsp">View Visit Prescription</a>
&nbsp;&nbsp;|
<a href="addEditPharmacyVisitDetails.jsp">Add/Edit Visits</a>
&nbsp;&nbsp;|
<a href="patientSerachForPharmacy.jsp">Patient Detail Search</a>
&nbsp;&nbsp;
<hr>
<div style="text-align: right;">Welcome&nbsp;&nbsp;<%=session.getAttribute(Constants.LOGGED_IN_USER)%>&nbsp;&nbsp;|
<a href="login.jsp">Logout</a></div>
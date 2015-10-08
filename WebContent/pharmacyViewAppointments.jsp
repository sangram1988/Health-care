<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.*,com.healthcare.hibernate.bean.*,com.healthcare.hibernate.util.*,com.healthcare.hibernate.dao.AppointmentDetailsDao,com.healthcare.form.AppointmentForm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pharmacy View Appointments</title>
</head>
<body background="./images/body_bg.jpg">
<%@include file="pharmacyhome.jsp" %>
<%
	String msg=(String)request.getAttribute("message") ;
	if(msg!=null && msg!=""){
%>
<div id="message"  style="background-color:green;display:block;width:500px;height:25px;float:center;" >
	<%=request.getAttribute("message") %>
</div>
<%} %>
<br><br><br>
<div>
<form action="PharmacyViewAppointment" method="post" >
<table border="1" bordercolor="blue" align="center">
	<thead>
		<tr>
			<th width="100" >User ID</th>
			<th width="100">Pharmacy ID</th>
			<th width="100">Start Date Time</th>
			<th width="100">End Date Time</th>
			<th width="100">Status</th>
			<th width="100">Title</th>
			<th width="100">Description</th>
			<th width="100">Prescription</th>
			<th width="100">Accept</th>
		</tr>
	</thead>
	
	<tbody>
	<% 
		List<AppointmentDetail> appointmentList=new ArrayList<AppointmentDetail>();
	
		appointmentList=(List<AppointmentDetail>)request.getAttribute("appointmentList");
		AppointmentDetailsDao appointmentDetailDao=new AppointmentDetailsDao();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String pharmacyId=(String)session.getAttribute(Constants.LOGGED_IN_USER);
		if(appointmentList==null || appointmentList.isEmpty()){
			AppointmentForm appointmentForm=new AppointmentForm();
			appointmentList=appointmentDetailDao.listOfPharmacyAppointments(pharmacyId);
		}
		
		if(appointmentList!=null )
			for(AppointmentDetail appointmentDetail : appointmentList){
				
	%>
		<tr>
			<td width="100"><%=appointmentDetail.getUserId()%></td>
			<td width="100"><%=appointmentDetail.getPharmacyId()%></td>
			<td width="100"><%=sdf.format(appointmentDetail.getAppointmentDateTimeStart())%></td>
			<td width="100"><%=sdf.format(appointmentDetail.getAppointmentDateTimeEnd())%></td>
			<td width="100"><%=appointmentDetail.getAppointmentStatus()%></td>
			<td width="100"><%=appointmentDetail.getTitle()%></td>
			<td width="100"><%=appointmentDetail.getDescription()%> </td>
			<td width="100"><%=appointmentDetail.getPrescriptions()%> </td>
			<%
				if(appointmentDetail.getAppointmentStatus().equalsIgnoreCase("pending")){
			%>
			
			<td align="center" width="100"><input type="checkbox" name="appointment" value="<%=appointmentDetail.getAppointmentId() %>" /> </td>
			<%
				}else{
			%>
			<td align="center" width="100"><input type="checkbox" name="appointment" value="<%=appointmentDetail.getAppointmentId() %>" checked="checked"/> </td>
			<%} %>
		</tr>
	<%} %>
	</tbody>

</table>

<br><br><br>

<table align="center">
	<tr>
		<td><input type="submit" name="appointBtn" value="SAVE"/> <input value="RESET" type="reset" /> <input type="submit" name="appointBtn" value="CENCEL"></td>
	</tr>

</table>
</form>
</div>



</body>
</html>
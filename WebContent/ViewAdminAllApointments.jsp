<%@page import="com.healthcare.hibernate.dao.AppointmentDetailsDao"%>
<%@page import="com.healthcare.hibernate.dao.UserDetailDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,com.healthcare.hibernate.bean.*,com.healthcare.hibernate.util.*,com.healthcare.hibernate.dao.DoctorDetailDao" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
function validate(){
	
	if(document.getElementById("searchStr").value)
		return true;
	else
		alert('Please enter search string ....');
	return false;
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>
</head>
<body background="./images/doc2.jpg">
<%@include file="adminhome.jsp"%>
<form name="searchDoctor" action="SearchDoctorAction" method="post" onsubmit="return validate()">
	<table  align="center">
	<tr> <td width="270">Details Of All Appointment</td>		
	</tr>
	</table>
</form>
<br><br><br>
<div>
<table border="1" bordercolor="blue" align="center">
	<thead>
		<tr>
			<th width="130" >appointment Id</th>
			<th width="100">User Id</th>
			<th width="100">Doctor Id</th>
			<th width="100">Title</th>
			<th width="160">Description</th>
			<th width="170">Start Date Time</th>
			<th width="170">End Date Time</th>			
			<th width="100">Appointment Status</th>
			
		</tr>
	</thead>
	
	<tbody>
	<% 
		List<AppointmentDetail> doctorList=new ArrayList<AppointmentDetail>();
	
	//UserDetailDao udao=new UserDetailDao();
	
		AppointmentDetailsDao doctorDetailDao=new AppointmentDetailsDao();
		doctorList=doctorDetailDao.listDocAllAppointments();
		if(doctorList==null ){
			%>
		<tr>
			<td align="center" colspan="11">No record found,No Appointment are booked ...</td>
		</tr>
			
			<% 
		}
		
		if(doctorList!=null )
			for(AppointmentDetail doctorDetail : doctorList){
	%>
		<tr>
		
			<%if(!Format.isStringEmptyORNull(doctorDetail.getAppointmentId())){ %>
				<td width="100"><%=doctorDetail.getAppointmentId()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getUserId())){ %>
				<td width="100"><%=doctorDetail.getUserId()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getDoctorId())){ %>
				<td width="100"><%=doctorDetail.getDoctorId()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getTitle())){ %>
				<td width="100"><%=doctorDetail.getTitle() %></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getDescription())){ %>
				<td width="100"><%=doctorDetail.getDescription()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getAppointmentDateTimeStart().toString())){ %>
				<td width="100"><%=doctorDetail.getAppointmentDateTimeStart()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getAppointmentDateTimeEnd().toString())){ %>
				<td width="100"><%=doctorDetail.getAppointmentDateTimeEnd()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getAppointmentStatus())){ %>
				<td width="100"><%=doctorDetail.getAppointmentStatus()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
					</tr>
	<%} %>
	</tbody>

</table>

</div>


</body>
</html>
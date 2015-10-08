<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,com.healthcare.hibernate.bean.*,com.healthcare.hibernate.util.*,com.healthcare.hibernate.dao.AppointmentDetailsDao" %>
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
<title>Search Doctor Page</title>
</head>
<body background="./images/body_bg.jpg">
<%@include file="pharmacyhome.jsp"%>
<form name="searchAppointment" action="searchAppointmentDetailAction" method="post" onsubmit="return validate()">
	<table  align="center">
	<tr> <td width="170">Enter User Id : <input type="text" id="searchStr" name="searchStr" size="" /></td>
		<td width="170"><input type="submit" value="SEARCH"/></td>
	</tr>
	</table>
</form>
<br><br><br>
<div>
<table border="1" bordercolor="blue" align="center">
	<thead>
		<tr>
			<th width="100" >Doctor Id</th>
			<th width="100">User Id</th>
			<th width="100">Appointment Start Time</th>
			<th width="100">Appointment End Time</th>
			<th width="100">Title</th>
			<th width="100">Description</th>
			<th width="100">Prescription</th>
		</tr>
	</thead>
	
	<tbody>
	<% 
		List<AppointmentDetail> appointmentList=new ArrayList<AppointmentDetail>();
	
		appointmentList=(List<AppointmentDetail>)request.getAttribute("appointmentList");
		
		if(appointmentList==null ){
			%>
		<tr>
			<td align="center" colspan="11">No record found,Please change search string ...</td>
		</tr>
			
			<% 
		}
		
		if(appointmentList!=null )
			for(AppointmentDetail appointmentDetail : appointmentList){
	%>
		<tr>
			<%if(!Format.isStringEmptyORNull(appointmentDetail.getDoctorId())){ %>
				<td width="100"><%=appointmentDetail.getDoctorId()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(appointmentDetail.getUserId())){ %>
				<td width="100"><%=appointmentDetail.getUserId()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(appointmentDetail.getAppointmentDateTimeStart()!=null){ %>
				<td width="100"><%=appointmentDetail.getAppointmentDateTimeStart()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(appointmentDetail.getAppointmentDateTimeEnd()!=null){ %>
				<td width="100"><%=appointmentDetail.getAppointmentDateTimeEnd()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(appointmentDetail.getTitle())){ %>
				<td width="100"><%=appointmentDetail.getTitle()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(appointmentDetail.getDescription())){ %>
				<td width="100"><%=appointmentDetail.getDescription()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(appointmentDetail.getPrescriptions())){ %>
				<td width="100"><%=appointmentDetail.getPrescriptions()%></td>
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
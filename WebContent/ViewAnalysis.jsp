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
<body background="./images/doctor.jpg">
<%@include file="adminhome.jsp"%>
<form name="searchDoctor" action="SearchDoctorAction" method="post" onsubmit="return validate()">
<br><br>
	<table  align="center">
	<tr> <td width="470">Most Popular Of All Doctors by no of Appointments</td>		
	</tr>
	</table>
</form>
<br>
<div>
<table border="1" bordercolor="blue" align="center">
	<thead>
		<tr>
			<th width="100" >Doctor E-Mail Id</th>
			<th width="100">First Name</th>
			<th width="100">Last Name</th>
			<th width="100">Full Name</th>
			<th width="100">City</th>
			<th width="100">Address</th>
			<th width="100">State</th>
			<th width="100">Zip Code</th>
			<th width="100">Specility</th>
			<th width="100">Experience</th>
			<th width="100">Fees</th>
		</tr>
	</thead>
	
	<tbody>
	<% 
		List<DoctorDetail> doctorList=new ArrayList<DoctorDetail>();
	
	
		DoctorDetailDao doctorDetailDao=new DoctorDetailDao();
		doctorList=doctorDetailDao.listMostPopDoc();
		if(doctorList==null ){
			%>
		<tr>
			<td align="center" colspan="11">No record found,No Doctor Has Registered ...</td>
		</tr>
			
			<% 
		}
		
		if(doctorList!=null )
			for(DoctorDetail doctorDetail : doctorList){
	%>
		<tr>
			<%if(!Format.isStringEmptyORNull(doctorDetail.getDoctorId())){ %>
				<td width="100"><%=doctorDetail.getDoctorId()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getFirstName())){ %>
				<td width="100"><%=doctorDetail.getFirstName()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getLastName())){ %>
				<td width="100"><%=doctorDetail.getLastName()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getFullName())){ %>
				<td width="100"><%=doctorDetail.getFullName() %></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getCity())){ %>
				<td width="100"><%=doctorDetail.getCity()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getHospitalAddress())){ %>
				<td width="100"><%=doctorDetail.getHospitalAddress()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getState())){ %>
				<td width="100"><%=doctorDetail.getState()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getZipCode())){ %>
				<td width="100"><%=doctorDetail.getZipCode()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getSpeciality())){ %>
				<td width="100"><%=doctorDetail.getSpeciality()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getExperience())){ %>
				<td width="100"><%=doctorDetail.getExperience()%> </td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(doctorDetail.getFees()!=0){ %>
				<td width="100"><%=doctorDetail.getFees()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
		</tr>
	<%} %>
	</tbody>

</table>

	







</div>
<br><br><br><br>
<form name="searchDoctor" action="SearchDoctorAction" method="post" onsubmit="return validate()">
	<table  align="center">
	<tr> <td width="470" align="left">Doctor with Minimun Fees</td>		
	</tr>
	</table>
</form>
<br>
<div>
<table border="1" bordercolor="blue" align="center">
	<thead>
		<tr>
			<th width="100" >Doctor E-Mail Id</th>
			<th width="100">First Name</th>
			<th width="100">Last Name</th>
			<th width="100">Full Name</th>
			<th width="100">City</th>
			<th width="100">Address</th>
			<th width="100">State</th>
			<th width="100">Zip Code</th>
			<th width="100">Specility</th>
			<th width="100">Experience</th>
			<th width="100">Fees</th>
		</tr>
	</thead>
	
	<tbody>
	<% 
		 doctorList=new ArrayList<DoctorDetail>();
	
	
		 doctorDetailDao=new DoctorDetailDao();
		doctorList=doctorDetailDao.listMinFees();
		if(doctorList==null ){
			%>
		<tr>
			<td align="center" colspan="11">No record found,No Doctor Has Registered ...</td>
		</tr>
			
			<% 
		}
		
		if(doctorList!=null )
			for(DoctorDetail doctorDetail : doctorList){
	%>
		<tr>
			<%if(!Format.isStringEmptyORNull(doctorDetail.getDoctorId())){ %>
				<td width="100"><%=doctorDetail.getDoctorId()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getFirstName())){ %>
				<td width="100"><%=doctorDetail.getFirstName()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getLastName())){ %>
				<td width="100"><%=doctorDetail.getLastName()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getFullName())){ %>
				<td width="100"><%=doctorDetail.getFullName() %></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getCity())){ %>
				<td width="100"><%=doctorDetail.getCity()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getHospitalAddress())){ %>
				<td width="100"><%=doctorDetail.getHospitalAddress()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getState())){ %>
				<td width="100"><%=doctorDetail.getState()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getZipCode())){ %>
				<td width="100"><%=doctorDetail.getZipCode()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getSpeciality())){ %>
				<td width="100"><%=doctorDetail.getSpeciality()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getExperience())){ %>
				<td width="100"><%=doctorDetail.getExperience()%> </td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(doctorDetail.getFees()!=0){ %>
				<td width="100"><%=doctorDetail.getFees()%></td>
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
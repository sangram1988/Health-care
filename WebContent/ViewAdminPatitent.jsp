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
<body  background="./images/Patient.jpg">
<%@include file="adminhome.jsp"%>
<form name="searchDoctor" action="SearchDoctorAction" method="post" onsubmit="return validate()">
	<table  align="center">
	<tr> <td width="270">Details Of All Users</td>		
	</tr>
	</table>
</form>
<br><br><br>
<div>
<table border="1" bordercolor="blue" align="center">
	<thead>
		<tr>
			<th width="100" >User Id</th>
			<th width="100">First Name</th>
			<th width="100">Last Name</th>
			<th width="100">Full Name</th>
			<th width="100">Gender</th>
			<th width="100">Address</th>
			<th width="100">City</th>			
			<th width="100">State</th>
			<th width="100">Zip Code</th>
			<th width="100">Contact No</th>
			<th width="100">Blood Group</th>
			
		</tr>
	</thead>
	
	<tbody>
	<% 
		List<UserDetail> doctorList=new ArrayList<UserDetail>();
	
	//UserDetailDao udao=new UserDetailDao();
	
		UserDetailDao doctorDetailDao=new UserDetailDao();
		doctorList=doctorDetailDao.listUser();
		if(doctorList==null ){
			%>
		<tr>
			<td align="center" colspan="11">No record found,No User Has Registered ...</td>
		</tr>
			
			<% 
		}
		
		if(doctorList!=null )
			for(UserDetail doctorDetail : doctorList){
	%>
		<tr>
			<%if(!Format.isStringEmptyORNull(doctorDetail.getUserId())){ %>
				<td width="100"><%=doctorDetail.getUserId()%></td>
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
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getGender())){ %>
				<td width="100"><%=doctorDetail.getGender()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getAddress())){ %>
				<td width="100"><%=doctorDetail.getAddress()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getCity())){ %>
				<td width="100"><%=doctorDetail.getCity()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getState())){ %>
				<td width="100"><%=doctorDetail.getState()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getZip())){ %>
				<td width="100"><%=doctorDetail.getZip()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getContactNo())){ %>
				<td width="100"><%=doctorDetail.getContactNo()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getBloodGroup())){ %>
				<td width="100"><%=doctorDetail.getBloodGroup()%> </td>
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
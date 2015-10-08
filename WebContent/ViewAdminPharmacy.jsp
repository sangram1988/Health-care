<%@page import="com.healthcare.hibernate.dao.PharmacyDetailDao"%>
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
<body background="./images/pharmacy.jpg">
<%@include file="adminhome.jsp"%>
<form name="searchDoctor" action="SearchDoctorAction" method="post" onsubmit="return validate()">
	<table  align="center">
	<tr> <td width="270">Details Of All Pharmacy</td>		
	</tr>
	</table>
</form>
<br><br><br>
<div>
<table border="1" bordercolor="blue" align="center">
	<thead>
		<tr>
			<th width="100" >Pharmacy Id</th>
			<th width="100">Pharmacy Name</th>
			<th width="100">Owner Name</th>
			<th width="100">Contact No</th>
			<th width="100">Address</th>
			<th width="100">City</th>			
			<th width="100">State</th>
			<th width="100">Zip Code</th>
			
		</tr>
	</thead>
	
	<tbody>
	<% 
		List<PharmacyDetail> doctorList=new ArrayList<PharmacyDetail>();
	
	//UserDetailDao udao=new UserDetailDao();
	
		PharmacyDetailDao doctorDetailDao=new PharmacyDetailDao();
		doctorList=doctorDetailDao.listPharmacy();
		if(doctorList==null ){
			%>
		<tr>
			<td align="center" colspan="11">No record found,No Pharmacy details are Registered ...</td>
		</tr>
			
			<% 
		}
		
		if(doctorList!=null )
			for(PharmacyDetail doctorDetail : doctorList){
	%>
		<tr>
			<%if(!Format.isStringEmptyORNull(doctorDetail.getPharmacyId())){ %>
				<td width="100"><%=doctorDetail.getPharmacyId()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getPharmacyName())){ %>
				<td width="100"><%=doctorDetail.getPharmacyName()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getOwnerName())){ %>
				<td width="100"><%=doctorDetail.getOwnerName() %></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getContactNumber())){ %>
				<td width="100"><%=doctorDetail.getContactNumber()%></td>
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
			
			<%if(!Format.isStringEmptyORNull(doctorDetail.getZipCode())){ %>
				<td width="100"><%=doctorDetail.getZipCode()%></td>
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
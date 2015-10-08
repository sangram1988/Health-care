<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,com.healthcare.hibernate.bean.*,com.healthcare.hibernate.util.*,com.healthcare.hibernate.dao.UserDetailDao" %>
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
<form name="searchPatient" action="SearchPatientForPharmacy" method="post" onsubmit="return validate()">
	<table  align="center">
	<tr> <td width="170"><input type="text" id="searchStr" name="searchStr" size="" /></td>
		<td width="170"><input type="submit" value="SEARCH"/></td>
	</tr>
	</table>
</form>
<br><br><br>
<div>
<table border="1" bordercolor="blue" align="center">
	<thead>
		<tr>
			<th width="100" >Patient E-Mail Id</th>
			<th width="100">Gender</th>
			<th width="100">First Name</th>
			<th width="100">Last Name</th>
			<th width="100">Full Name</th>
			<th width="100">Contact Number</th>
			<th width="100">City</th>
			<th width="100">Address</th>
			<th width="100">State</th>
			<th width="100">Zip Code</th>
			<th width="100">Height</th>
			<th width="100">Weight</th>
			<th width="100">Blood group</th>
			<th width="100">Smoking</th>
			<th width="100">Drinking</th>
			<th width="100">Family History</th>
			<th width="100">Allergy To Medicine</th>
			<th width="100">Surgical History</th>
		</tr>
	</thead>
	
	<tbody>
	<% 
		List<UserDetail> patientList=new ArrayList<UserDetail>();
	
		patientList=(List<UserDetail>)request.getAttribute("patientList");
		
		if(patientList==null ){
			//patientList=UserDetailDao.getInstance().listUser();
			%>
		<tr>
			<td align="center" colspan="11">No record found,Please change search string ...</td>
		</tr>
			
			<% 
		}
		
		if(patientList!=null )
			for(UserDetail userDetail : patientList){
	%>
		<tr>
			<%if(!Format.isStringEmptyORNull(userDetail.getUserId())){ %>
				<td width="100"><%=userDetail.getUserId()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			<%if(!Format.isStringEmptyORNull(userDetail.getGender())){ %>
				<td width="100"><%=userDetail.getGender()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(userDetail.getFirstName())){ %>
				<td width="100"><%=userDetail.getFirstName()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(userDetail.getLastName())){ %>
				<td width="100"><%=userDetail.getLastName()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(userDetail.getFullName())){ %>
				<td width="100"><%=userDetail.getFullName() %></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(userDetail.getContactNo())){ %>
				<td width="100"><%=userDetail.getContactNo()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			
			<%if(!Format.isStringEmptyORNull(userDetail.getCity())){ %>
				<td width="100"><%=userDetail.getCity()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(userDetail.getAddress())){ %>
				<td width="100"><%=userDetail.getAddress()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(userDetail.getState())){ %>
				<td width="100"><%=userDetail.getState()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(userDetail.getZip())){ %>
				<td width="100"><%=userDetail.getZip()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(userDetail.getHeight()!=0){ %>
				<td width="100"><%=userDetail.getHeight()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(userDetail.getWeight()!=0){ %>
				<td width="100"><%=userDetail.getWeight()%> </td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(userDetail.getBloodGroup())){ %>
				<td width="100"><%=userDetail.getBloodGroup()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(userDetail.getSmoking())){ %>
				<td width="100"><%=userDetail.getSmoking()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(userDetail.getFamilyHistory())){ %>
				<td width="100"><%=userDetail.getFamilyHistory()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(userDetail.getAlergyToMedicine())){ %>
				<td width="100"><%=userDetail.getAlergyToMedicine()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(userDetail.getSurgicalHistory())){ %>
				<td width="100"><%=userDetail.getSurgicalHistory()%></td>
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
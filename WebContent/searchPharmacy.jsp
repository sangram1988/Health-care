<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,com.healthcare.hibernate.bean.*,com.healthcare.hibernate.util.*,com.healthcare.hibernate.dao.PharmacyDetailDao" %>
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
<%@include file="patienthome.jsp"%>
<form name="searchDoctor" action="SearchPharmacyAction" method="post" onsubmit="return validate()">
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
			<th width="100" >Pharmacy Owner E-Mail Id</th>
			<th width="100">Contact Number</th>
			<th width="100">Owner Name</th>
			<th width="100">Pharmacy Name</th>
			<th width="100">City</th>
			<th width="100">Address</th>
			<th width="100">State</th>
			<th width="100">Zip Code</th>
		</tr>
	</thead>
	
	<tbody>
	<% 
		List<PharmacyDetail> pharmacyList=new ArrayList<PharmacyDetail>();
	
		pharmacyList=(List<PharmacyDetail>)request.getAttribute("pharmacyList");
		PharmacyDetailDao PharmacyDetailDao=new PharmacyDetailDao();
		
		if(pharmacyList==null ){
			%>
		<tr>
			<td align="center" colspan="11">No record found,Please change search string ...</td>
		</tr>
			
			<% 
		}
		
		if(pharmacyList!=null )
			for(PharmacyDetail pharmacyDetail : pharmacyList){
	%>
		<tr>
			<%if(!Format.isStringEmptyORNull(pharmacyDetail.getPharmacyId())){ %>
				<td width="100"><%=pharmacyDetail.getPharmacyId()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(pharmacyDetail.getContactNumber())){ %>
				<td width="100"><%=pharmacyDetail.getContactNumber()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(pharmacyDetail.getOwnerName())){ %>
				<td width="100"><%=pharmacyDetail.getOwnerName()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(pharmacyDetail.getPharmacyName())){ %>
				<td width="100"><%=pharmacyDetail.getPharmacyName() %></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(pharmacyDetail.getCity())){ %>
				<td width="100"><%=pharmacyDetail.getCity()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(pharmacyDetail.getAddress())){ %>
				<td width="100"><%=pharmacyDetail.getAddress()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(pharmacyDetail.getState())){ %>
				<td width="100"><%=pharmacyDetail.getState()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(pharmacyDetail.getZipCode())){ %>
				<td width="100"><%=pharmacyDetail.getZipCode()%></td>
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
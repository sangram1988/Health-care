<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,com.healthcare.hibernate.bean.*,com.healthcare.hibernate.util.*,com.healthcare.hibernate.dao.VisitDetailsDao" %>
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
<form name="searchVisit" action="searchVisitDetailAction" method="post" onsubmit="return validate()">
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
		List<VisitDetail> visitList=new ArrayList<VisitDetail>();
	
		visitList=(List<VisitDetail>)request.getAttribute("visitList");
		
		if(visitList==null ){
			%>
		<tr>
			<td align="center" colspan="11">No record found,Please change search string ...</td>
		</tr>
			
			<% 
		}
		
		if(visitList!=null )
			for(VisitDetail visitDetail : visitList){
	%>
		<tr>
			<%if(!Format.isStringEmptyORNull(visitDetail.getDoctorId())){ %>
				<td width="100"><%=visitDetail.getDoctorId()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(visitDetail.getUserId())){ %>
				<td width="100"><%=visitDetail.getUserId()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(visitDetail.getVisitDateTimeStart()!=null){ %>
				<td width="100"><%=visitDetail.getVisitDateTimeStart()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(visitDetail.getVisitDateTimeEnd()!=null){ %>
				<td width="100"><%=visitDetail.getVisitDateTimeEnd()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(visitDetail.getTitle())){ %>
				<td width="100"><%=visitDetail.getTitle()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(visitDetail.getDescription())){ %>
				<td width="100"><%=visitDetail.getDescription()%></td>
			<%}else{ %>
				<td width="100"></td>
			<%} %>
			
			<%if(!Format.isStringEmptyORNull(visitDetail.getPrescriptions())){ %>
				<td width="100"><%=visitDetail.getPrescriptions()%></td>
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
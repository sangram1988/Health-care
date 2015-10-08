<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,com.healthcare.hibernate.bean.*,com.healthcare.hibernate.util.*,com.healthcare.hibernate.dao.VisitDetailsDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link rel="stylesheet" type="text/css" href="./css/rfnet.css" />
<link rel="stylesheet" type="text/css" href="./css/Report.css" />
<script type="text/javascript" language="JavaScript"
	src="./javaScript/datetimepicker_css.js"></script>

<script type="text/javascript">

function getTodayDate()
{
	var date=new Date();
	var mon=date.getMonth();
	if(mon<9)
		mon="0"+mon;
	var dt=date.getDate();
	if(dt<9)
		dt="0"+dt;
	var hh=date.getHours();
	if(hh<9)
		hh="0"+hh;
	var mm=date.getMinutes();
	if(mm<9)
		mm="0"+mm;
		
	return date.getFullYear()+"-"+mon+"-"+dt+" "+hh+":"+date.getMinutes();
}
function validateForm(){
	
	var id=document.getElementById('id').value;
	var startTime=document.getElementById('startTime').value;
	var endTime=document.getElementById('endTime').value;
	
	var msg='';
	if(!id && id.indexOf('@')<1){
		msg+='Please enter valid id.\n';
	}
	if(!startTime || startTime=='Enter Date'){
		msg+='Please enter valid start date time.\n';
	}
		
	if(!endTime || endTime =='Enter Date'){
		msg+='Please enter valid end date time.\n';
	} 
	var today=getTodayDate();
	
	if(startTime>=today){
		msg+='Start date time can not be greater then today date.\n';
	}
	
	if(startTime>=endTime){
		msg+='End date time must be greater then start date.\n';
	}


	if(msg){
		alert(msg);
		return false;
	}
	
	return true;
}

function showLink(rowId){
	document.getElementById('achr'+rowId).setAttribute('href','#');
	document.getElementById('achr1'+rowId).setAttribute('href','#');
	document.getElementById('achr2'+rowId).setAttribute('href','#');
	document.getElementById('achr3'+rowId).setAttribute('href','#');
	document.getElementById('achr4'+rowId).setAttribute('href','#');
	document.getElementById('achr5'+rowId).setAttribute('href','#');
	document.getElementById('achr6'+rowId).setAttribute('href','#');

}

function disableLink(rowId){
	document.getElementById('achr'+rowId).removeAttribute('href');
	document.getElementById('achr1'+rowId).removeAttribute('href');
	document.getElementById('achr2'+rowId).removeAttribute('href');
	document.getElementById('achr3'+rowId).removeAttribute('href');
	document.getElementById('achr4'+rowId).removeAttribute('href');
	document.getElementById('achr5'+rowId).removeAttribute('href');
	document.getElementById('achr6'+rowId).removeAttribute('href');
	
}

function showDeleateData(rowCnt,doctorId,userId,startTm,endTm,title,desc,prescription){
	document.getElementById('appointmentId').value=document.getElementById('appointmentId'+rowCnt).value;
	if(userId!='null'){
		document.getElementById('id').value=userId;
		var typ=document.getElementById('type');
		typ.getElementsByTagName('option')['patient'].selected='selected';
	}
	if(doctorId!='null'){
		document.getElementById('id').value=doctorId;
		var typ=document.getElementById('type');
		typ.getElementsByTagName('option')['doctor'].selected='selected';
	}
	
	
	document.getElementById('prescriptions').value=prescription;
	document.getElementById('startTime').value=startTm;
	document.getElementById('endTime').value=endTm;
	document.getElementById('title').value=title;
	document.getElementById('description').value=desc;
	
}

</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Edit Visit Details.</title>
</head>
<body background="./images/body_bg.jpg">
	<%@include file="pharmacyhome.jsp"%>
	<%
		String msg = (String) request.getAttribute("message");
		if (msg != null && msg != "") {
	%>
	<div id="message"
		style="background-color: green; display: block; width: 500px; height: 25px; float: center;">
		<%=request.getAttribute("message")%>
	</div>
	<%
		}
	%>
	<div>
		<center>
			<h2>Add/Edit Visit Details</h2>
		</center>
		<form name="appointmentForm" action="AddEditDeletePharmacyVisitDetailAction"
			method="post">
			<input type="hidden" id="appointmentId" name="appointmentId">
			<table align="center">
				<tr>
					<td width="170">Type :</td>
					<td width="200"><select id="type" name="type">
							<option value="slect" selected="selected">Select</option>
							<option id="doctor" value="doctor" name="doctor">Doctor
							</option>
							<option id="patient" value="patient" name="patient">Patient
							</option>
					</select></td>
					<td width="170">Id :</td>
					<td width="200"><input type="text" id="id" name="id" /></td>
				</tr>
				<tr>				
					<td>Start Date Time :</td>
					<td><input type="text" id="startTime" name="startTime"
						class="inputText" size="20" value="Enter Date"
						onclick="document.reportForm.startTime.value = '';" /> <a
						href="javascript:NewCssCal('startTime','yyyymmdd','arrow',true,24,false)">
					<img src="./images/cal.gif" width="16" height="16" alt="Pick a date" /></a>
					</td>
						<td>End Date Time :</td>
						<td><input type="text" id="endTime" name="endTime"
						class="inputText" size="20" value="Enter Date"
						onclick="document.reportForm.endTime.value = '';" /> <a
						href="javascript:NewCssCal('endTime','yyyymmdd','arrow',true,24,false)">
					<img src="./images/cal.gif" width="16" height="16" alt="Pick a date" /></a>
					</td>
				</tr>
				<tr>
					<td>Title :</td>
					<td><input type="text" id="title" name="title" /></td>
					<td>Description :</td>
					<td><input type="text" id="description" name="description" /></td>
				</tr>
				<tr>
					<td>Prescriptions :</td>
					<td><textarea rows="5" cols="16" id="prescriptions"
							name="prescriptions"></textarea>
				</tr>
			</table>

			<br>
			<br>
			<table align="center">
				<tr>
					<td><input type="submit" value="SAVE" name="visits"
						id="save" onclick="return validateForm()" /> <input type="submit"
						value="DELETE" name="visits" id="delete" /> <input
						type="submit" value="SEARCH" name="visits" id="search" /> <input
						type="reset" value="CLEAR" /></td>
				</tr>
			</table>
		</form>
	</div>
	<br>
	<br>
	<br>
	<div>
		<table border="1" bordercolor="blue" align="center">
			<thead>
				<tr>
					<th width="100">User ID</th>
					<th width="100">Pharmacy ID</th>
					<th width="100">Doctor ID</th>
					<th width="100">Start Date Time</th>
					<th width="100">End Date Time</th>
					<th width="100">Title</th>
					<th width="100">Description</th>
					<th width="100">Prescriptions</th>
				</tr>
			</thead>

			<tbody>
				<%
					List<VisitDetail> appointmentList = new ArrayList<VisitDetail>();

					appointmentList = (List<VisitDetail>) request
							.getAttribute("visitList");
					VisitDetailsDao visitDetailDao = new VisitDetailsDao();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					int rowCnt = 0;
					if (appointmentList == null || appointmentList.isEmpty()) {
						appointmentList = visitDetailDao.listVisits();
					}

					if (appointmentList != null)
						for (VisitDetail visitDetail : appointmentList) {
							rowCnt++;
				%>
				<tr>
					<td width="100"><a id="achr<%=rowCnt%>"
						onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=visitDetail.getPharmacyId()%>','<%=visitDetail.getDoctorId()%>','<%=visitDetail.getVisitDateTimeStart()%>','<%=visitDetail.getVisitDateTimeEnd()%>','<%=visitDetail.getTitle()%>','<%=visitDetail.getDescription()%>','<%=visitDetail.getPrescriptions()%>');"><%=visitDetail.getUserId()%></a></td>
					<%
						if (!Format.isStringEmptyORNull(visitDetail
										.getPharmacyId())) {
					%>
					<td width="100"><a id="achr1<%=rowCnt%>"
						onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=visitDetail.getPharmacyId()%>','<%=visitDetail.getDoctorId()%>','<%=visitDetail
								.getVisitDateTimeStart()%>','<%=visitDetail.getVisitDateTimeEnd()%>','<%=visitDetail.getTitle()%>','<%=visitDetail.getDescription()%>');"><%=visitDetail.getPharmacyId()%></a></td>
					<%
						} else {
					%>
					<td width="100"></td>
					<%
						}
					%>

					<%
						if (!Format.isStringEmptyORNull(visitDetail
										.getDoctorId())) {
					%>
					<td width="100"><a id="achr1<%=rowCnt%>"
						onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=visitDetail.getPharmacyId()%>','<%=visitDetail.getDoctorId()%>','<%=visitDetail
								.getVisitDateTimeStart()%>','<%=visitDetail.getVisitDateTimeEnd()%>','<%=visitDetail.getTitle()%>','<%=visitDetail.getDescription()%>');"><%=visitDetail.getDoctorId()%></a></td>
					<%
						} else {
					%>
					<td width="100"></td>
					<%
						}
					%>

					<td width="100"><input type="hidden"
						id="appointmentId<%=rowCnt%>" name="appointmentId<%=rowCnt%>"
						value="<%=visitDetail.getVisitId()%>" /> <a
						id="achr2<%=rowCnt%>" onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=visitDetail.getPharmacyId()%>','<%=visitDetail.getDoctorId()%>','<%=visitDetail.getVisitDateTimeStart()%>','<%=visitDetail.getVisitDateTimeEnd()%>','<%=visitDetail.getTitle()%>','<%=visitDetail.getDescription()%>','<%=visitDetail.getPrescriptions()%>');"><%=sdf.format(visitDetail
							.getVisitDateTimeStart())%></a></td>
					<td width="100"><a id="achr3<%=rowCnt%>"
						onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=visitDetail.getPharmacyId()%>','<%=visitDetail.getDoctorId()%>','<%=visitDetail.getVisitDateTimeStart()%>','<%=visitDetail.getVisitDateTimeEnd()%>','<%=visitDetail.getTitle()%>','<%=visitDetail.getDescription()%>','<%=visitDetail.getPrescriptions()%>');"><%=sdf.format(visitDetail
							.getVisitDateTimeEnd())%></a></td>
				<td width="100"><a id="achr5<%=rowCnt%>"
						onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=visitDetail.getPharmacyId()%>','<%=visitDetail.getDoctorId()%>','<%=visitDetail.getVisitDateTimeStart()%>','<%=visitDetail.getVisitDateTimeEnd()%>','<%=visitDetail.getTitle()%>','<%=visitDetail.getDescription()%>','<%=visitDetail.getPrescriptions()%>');"><%=visitDetail.getTitle()%></a></td>
				<td width="100"><a id="achr6<%=rowCnt%>"
						onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=visitDetail.getPharmacyId()%>','<%=visitDetail.getDoctorId()%>','<%=visitDetail.getVisitDateTimeStart()%>','<%=visitDetail.getVisitDateTimeEnd()%>','<%=visitDetail.getTitle()%>','<%=visitDetail.getDescription()%>','<%=visitDetail.getPrescriptions()%>');"><%=visitDetail.getDescription()%></a>
					</td>
				<td width="100"><a id="achr4<%=rowCnt%>"
						onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=visitDetail.getPharmacyId()%>','<%=visitDetail.getDoctorId()%>','<%=visitDetail.getVisitDateTimeStart()%>','<%=visitDetail.getVisitDateTimeEnd()%>','<%=visitDetail.getTitle()%>','<%=visitDetail.getDescription()%>','<%=visitDetail.getPrescriptions()%>');"><%=visitDetail.getPrescriptions()%></a>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>

		</table>

	</div>


</body>
</html>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,com.healthcare.hibernate.bean.*,com.healthcare.hibernate.util.*,com.healthcare.hibernate.dao.AppointmentDetailsDao"%>
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
	var mon=date.getMonth()+1;
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
		
	if(!endTime || endTime=='Enter Date'){
		msg+='Please enter valid end date time.\n';
	}
	var today=getTodayDate();
	
	
	if(startTime<=today){
		msg+='Start date time must be greater then today date.\n';
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

function showDeleateData(rowCnt,phrmacyId,doctorId,startTm,endTm,title,desc,presc){
	document.getElementById('appointmentId').value=document.getElementById('appointmentId'+rowCnt).value;
	if(doctorId!='null'){
		document.getElementById('id').value=doctorId;
		var typ=document.getElementById('type');
		typ.getElementsByTagName('option')['doctor'].selected='selected';
	}
	if(phrmacyId!='null'){
		document.getElementById('id').value=phrmacyId;
		var typ=document.getElementById('type');
		typ.getElementsByTagName('option')['pharmacy'].selected='selected';
	}
	
	
	document.getElementById('startTime').value=startTm;
	document.getElementById('endTime').value=endTm;
	document.getElementById('title').value=title;
	document.getElementById('description').value=desc;
	document.getElementById('prescriptions').value=presc;
	
}

</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Edit Appointment Details.</title>
</head>
<body background="./images/body_bg.jpg">
	<%@include file="patienthome.jsp"%>
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
			<h2>Add/Edit Appointment Details</h2>
		</center>
		<form name="appointmentForm" action="AddEditDeleteAppointmentsAction"
			method="post">
			<input type="hidden" id="appointmentId" name="appointmentId">
			<table align="center">
				<tr>
					<td width="170">Type :</td>
					<td width="200"><select id="type" name="type">
							<option value="slect" selected="selected">Select</option>
							<option id="pharmacy" value="pharmacy" name="pharmacy">Pharmacy
							</option>
							<option id="doctor" value="doctor" name="doctor">Doctor
							</option>
					</select></td>
					<td width="170">Doctor Id/Pharmacy Id :</td>
					<td width="200"><input type="text" id="id" name="id" /></td>
				</tr>
				<tr>
					<td>Start Date Time :</td>
				<td><input type="text" id="startTime" name="startTime" 
					class="inputText"  value="Enter Date"
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
					<td><input type="submit" value="SAVE" name="appointments"
						id="save" onclick="return validateForm()" /> <input type="submit"
						value="DELETE" name="appointments" id="delete" /> <input
						type="submit" value="SEARCH" name="appointments" id="search" /> <input
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
					<th width="100">Status</th>
					<th width="100">Title</th>
					<th width="100">Description</th>
					<th width="100">Prescription</th>
				</tr>
			</thead>

			<tbody>
				<%
					List<AppointmentDetail> appointmentList = new ArrayList<AppointmentDetail>();

					appointmentList = (List<AppointmentDetail>) request
							.getAttribute("appointmentList");
					AppointmentDetailsDao appointmentDetailDao = new AppointmentDetailsDao();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					int rowCnt = 0;
					if (appointmentList == null || appointmentList.isEmpty()) {
						String id=(String)session.getAttribute(Constants.LOGGED_IN_USER);
						String type=(String)session.getAttribute(Constants.LOGGED_IN_TYPE);
						appointmentList = appointmentDetailDao.listAppointments(id,type);
				}
				if (appointmentList != null)
					for (AppointmentDetail appointmentDetail : appointmentList) {
							rowCnt++;
				%>
				<tr>
					<td width="100"><a id="achr<%=rowCnt%>"
						onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=appointmentDetail.getPharmacyId()%>','<%=appointmentDetail.getDoctorId()%>','<%=appointmentDetail.getAppointmentDateTimeStart()%>','<%=appointmentDetail.getAppointmentDateTimeEnd()%>','<%=appointmentDetail.getTitle()%>','<%=appointmentDetail.getDescription()%>','<%=appointmentDetail.getPrescriptions()%>');"><%=appointmentDetail.getUserId()%></a></td>
				<%
						if (!Format.isStringEmptyORNull(appointmentDetail
										.getPharmacyId())) {
					%>
					<td width="100"><a id="achr1<%=rowCnt%>"
						onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=appointmentDetail.getPharmacyId()%>','<%=appointmentDetail.getDoctorId()%>','<%=appointmentDetail
								.getAppointmentDateTimeStart()%>','<%=appointmentDetail.getAppointmentDateTimeEnd()%>','<%=appointmentDetail.getTitle()%>','<%=appointmentDetail.getDescription()%>','<%=appointmentDetail.getPrescriptions()%>');"><%=appointmentDetail.getPharmacyId()%></a></td>
					<%
						} else {
					%>
					<td width="100"></td>
					<%
						}
					%>

					<%
						if (!Format.isStringEmptyORNull(appointmentDetail
										.getDoctorId())) {
					%>
					<td width="100"><a id="achr1<%=rowCnt%>"
						onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=appointmentDetail.getPharmacyId()%>','<%=appointmentDetail.getDoctorId()%>','<%=appointmentDetail
								.getAppointmentDateTimeStart()%>','<%=appointmentDetail.getAppointmentDateTimeEnd()%>','<%=appointmentDetail.getTitle()%>','<%=appointmentDetail.getDescription()%>','<%=appointmentDetail.getPrescriptions()%>');"><%=appointmentDetail.getDoctorId()%></a></td>
					<%
						} else {
					%>
					<td width="100"></td>
					<%
						}
					%>

					<td width="100"><input type="hidden"
						id="appointmentId<%=rowCnt%>" name="appointmentId<%=rowCnt%>"
						value="<%=appointmentDetail.getAppointmentId()%>" /> <a
						id="achr2<%=rowCnt%>" onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=appointmentDetail.getPharmacyId()%>','<%=appointmentDetail.getDoctorId()%>','<%=appointmentDetail.getAppointmentDateTimeStart()%>','<%=appointmentDetail.getAppointmentDateTimeEnd()%>','<%=appointmentDetail.getTitle()%>','<%=appointmentDetail.getDescription()%>','<%=appointmentDetail.getPrescriptions()%>');"><%=sdf.format(appointmentDetail
							.getAppointmentDateTimeStart())%></a></td>
					<td width="100"><a id="achr3<%=rowCnt%>"
						onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=appointmentDetail.getPharmacyId()%>','<%=appointmentDetail.getDoctorId()%>','<%=appointmentDetail.getAppointmentDateTimeStart()%>','<%=appointmentDetail.getAppointmentDateTimeEnd()%>','<%=appointmentDetail.getTitle()%>','<%=appointmentDetail.getDescription()%>','<%=appointmentDetail.getPrescriptions()%>');"><%=sdf.format(appointmentDetail
							.getAppointmentDateTimeEnd())%></a></td>
					<td width="100"><a id="achr4<%=rowCnt%>"
						onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=appointmentDetail.getPharmacyId()%>','<%=appointmentDetail.getDoctorId()%>','<%=appointmentDetail.getAppointmentDateTimeStart()%>','<%=appointmentDetail.getAppointmentDateTimeEnd()%>','<%=appointmentDetail.getTitle()%>','<%=appointmentDetail.getDescription()%>','<%=appointmentDetail.getPrescriptions()%>');"><%=appointmentDetail.getAppointmentStatus()%></a></td>
					<td width="100"><a id="achr5<%=rowCnt%>"
						onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=appointmentDetail.getPharmacyId()%>','<%=appointmentDetail.getDoctorId()%>','<%=appointmentDetail.getAppointmentDateTimeStart()%>','<%=appointmentDetail.getAppointmentDateTimeEnd()%>','<%=appointmentDetail.getTitle()%>','<%=appointmentDetail.getDescription()%>','<%=appointmentDetail.getPrescriptions()%>');"><%=appointmentDetail.getTitle()%></a></td>
					<td width="100"><a id="achr6<%=rowCnt%>"
						onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=appointmentDetail.getPharmacyId()%>','<%=appointmentDetail.getDoctorId()%>','<%=appointmentDetail.getAppointmentDateTimeStart()%>','<%=appointmentDetail.getAppointmentDateTimeEnd()%>','<%=appointmentDetail.getTitle()%>','<%=appointmentDetail.getDescription()%>','<%=appointmentDetail.getPrescriptions()%>');"><%=appointmentDetail.getDescription()%></a>
					</td>
					<td width="100"><a id="achr6<%=rowCnt%>"
						onmouseover="showLink(<%=rowCnt%>);"
						onmouseout="disableLink(<%=rowCnt%>);"
						onClick="showDeleateData('<%=rowCnt%>','<%=appointmentDetail.getPharmacyId()%>','<%=appointmentDetail.getDoctorId()%>','<%=appointmentDetail.getAppointmentDateTimeStart()%>','<%=appointmentDetail.getAppointmentDateTimeEnd()%>','<%=appointmentDetail.getTitle()%>','<%=appointmentDetail.getDescription()%>','<%=appointmentDetail.getPrescriptions()%>');"><%=appointmentDetail.getPrescriptions()%></a>
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
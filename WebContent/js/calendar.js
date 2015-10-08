

/**
 *  Cisco.com Calendar Component (CEC VERSION)
 *  calendar.js
 *  $Revision: 1.1.82.1 $
 */

/**
 * formats a JavaScript date object to CEC date string standards
 */
function formatDate( dateObject ) {
	var monthNames = new Array ("01","02","03","04","05","06","07","08","09","10","11","12");
	var thedate;
	var theday = dateObject.getDate();
	if ( theday < 10 ) {
		theday = "0"+theday;
	}
	thedate = monthNames[dateObject.getMonth()]+'/'+theday+'/'+dateObject.getFullYear()
    return thedate;
}

/**
 * opens a calendar at the specified screen coordinates
 * NOT A SUPPORTED API
 */
function openCalendarAtPosition( xpos, ypos, contextString ) {

  var calwindow = window.open( "html/calendar.htm?context="+contextString , "cdccalendar", "toolbar=no,status=yes,top="+ypos+",left="+xpos+",outerWidth=203,outerHeight=236,width=190,height=183,scrollbars=auto,resizable=yes,menubar=no,locationbar=no");
  calwindow.focus();
}

/**
 * opens a calendar at the current mouse position
 */
function openCalendarAtMousePosition(e, contextString ) {

	// get the current mouse position
	var mouseY = 0;
	var mouseX = 0;
	if( document.all ) {
		mouseY = event.screenY;
		mouseX = event.screenX;
	} else if( document.getElementById ) {
		mouseY = e.screenY;
		mouseX = e.screenX;
	} else if( document.layers ) {
		mouseY = e.screenY;
		mouseX = e.screenX;
	}
	openCalendarAtPosition( mouseX, mouseY, contextString );
}

/**
 * opens a calendar at the default position
 */
function openCalendar( contextString ) {

	openCalendarAtPosition( 290, 90, contextString );
}

/**
 * prints the proper code for a linked calendar widget appropriate for
 * the browser.
 */
function writeCalendarWidget(fieldName) {
  // We want to use "<img onClick...", but this does not work wit
  // Netscape 4.76.
  var bName = navigator.appName;
  var bVer = parseInt(navigator.appVersion);
  if (bName == "Netscape" && bVer < 5) {
    document.write("<a name='calendar_field_" + fieldName + "' href='#calendar_field_" + fieldName + "' onclick='openCalendarAtMousePosition(event,\"" + fieldName + "\");'><img src='../images/icon_calendar.gif' height='16' width='16' alt='Select Date' border='0' /></a>");
  }
  // Just use img onClick if possible.
  else {
    document.write("<input type='image' src='../images/icon_calendar.gif' height='16' width='16' alt='Select Date' onclick='openCalendarAtMousePosition(event,\"" + fieldName + "\"); return(false);' border='0' />");
  }
}


function getHTTPObject(){
	var xhr = false;
	if(window.XMLHttpRequest)
		{
			xhr = new XMLHttpRequest();
		}
	else if(window.ActiveXObject)
		{
			try
			{
				xhr=new ActiveXObject("Msxml2.XMLHTTP");
			}
			catch(e)
			{
				try{
					xhr = new ActiveXobject("Microsoft.XMLHTTP");
				}
				catch(e){
					xhr=false;
				}
			}
		}
	return xhr;
}


function showProcessImage()
{
	
	var dialog1 = document.getElementById('WaitDialog1'); 
	dialog1.innerHTML = "<img src='./images/processing.gif' />";
	
}

function removeProcessImage()
{
	
	var dialog1 = document.getElementById('WaitDialog1'); 
	dialog1.innerHTML = '';
	
}


function displayDocType(){
	
	
	
	var request = getHTTPObject();
	if(request)
		{
		showProcessImage();
			request.onreadystatechange = function(){
				
				displayResponse(request);
			};
			
			var val = document.getElementById("ofl").value;
			//alert(val);
			var url = "NextgenDocTypeServlet";
			
			url = url+"?value="+val;
			
			//alert(url);
			request.open("GET",url,true);
			request.send(null);
			//request.open("POST","/GoogleSuggest/suggestServlet",true);
			//request.send("value1="+val);
		
		}
	
}


function displayPartnerName(value){
	
	var request = getHTTPObject();
	if(request)
		{
		showProcessImage();
			request.onreadystatechange = function(){
				
				displayPartnerResponse(request);
			};
			
			var arr = document.getElementById("oflDocType");
			//alert(arr);
			var val='';
			//alert(arr.options[1].value);
			var counter=0;
			for(i=0;i<arr.length;i++ )
			{
				//alert(arr.options[1].value);
				if(arr.options[i].selected==true)
				{
					counter++;
					val += arr.options[i].value+'|';
					//alert(val);
				}
			}
			
			var url = "NextgenPartnerNameServlet";
			
			//alert(val);
			if(counter==0)
			{
				document.getElementById('oflPartnerNameDiv').innerHTML = "<div id='oflPartnerNameDiv'><select size =3 multiple='multiple' class='inputText'><option> Select Doc Type </option></select></div>";
				removeProcessImage();
			}
			else
			{
			url = url+"?value="+val;
			
			//alert(url);
			request.open("GET",url,true);
			request.send(null);
			}
			//request.open("POST","/GoogleSuggest/suggestServlet",true);
			//request.send("value1="+val);
		
		}
	
	
}


function displayPartnerResponse(request)
{
	if(request.readyState==4)
	{
		//alert(request.status);
		
		if(request.status == 200 || request.status == 304)
		{
			
			//alert("value from server"+request.responseText);
			document.getElementById('oflPartnerNameDiv').innerHTML = request.responseText;
			removeProcessImage();
		}
	}
}
	


function displayResponse(request)
{
	if(request.readyState==4)
	{
		//alert(request.status);
		
		if(request.status == 200 || request.status == 304)
		{
			
			//alert("value from server"+request.responseText);
			document.getElementById('oflDocTypeDiv').innerHTML = request.responseText;
			removeProcessImage();
		}
	}
	
}	








function displayCatalogDocType(){
	
	
	
	var request = getHTTPObject();
	if(request)
		{
			showProcessImage();
			request.onreadystatechange = function(){
				
				displayCatalogResponse(request);
			};
			
			var val = document.getElementById("catalog").value;
			var url = "NextgenDocTypeServlet";
			
			url = url+"?value="+val;
			
			request.open("GET",url,true);
			request.send(null);
			
		
		}
	
}



function displayCatalogResponse(request)
{
	if(request.readyState==4)
	{
		
		if(request.status == 200 || request.status == 304)
		{
			
			document.getElementById('catlogDocTypeDiv').innerHTML = request.responseText;
			removeProcessImage();
		}
	}
	
}	

function displayCatalogPartnerName(value){
	
	var request = getHTTPObject();
	if(request)
		{
			showProcessImage();
			request.onreadystatechange = function(){
				
				displayCatalogPartnerResponse(request);
			};
			
			var arr = document.getElementById("catlogDocType");
			var val='';
			var counter=0;
			for(i=0;i<arr.length;i++ )
			{
				if(arr.options[i].selected==true)
				{
					counter++;
					val += arr.options[i].value+'|';
				}
			}
			
			var url = "NextgenCatalogPartnerNameServlet";
			
			if(counter==0)
			{
				document.getElementById('catlogPartnerNameDiv').innerHTML = "<div id='catlogPartnerNameDiv'><select size =3 multiple='multiple' class='inputText'><option> Select Doc Type </option></select></div>";
				removeProcessImage();
			}
			else
			{
			url = url+"?value="+val;
			
			request.open("GET",url,true);
			request.send(null);
			}
		
		}
	
	
}


function displayCatalogPartnerResponse(request)
{
	if(request.readyState==4)
	{
		if(request.status == 200 || request.status == 304)
		{
			
			document.getElementById('catlogPartnerNameDiv').innerHTML = request.responseText;
			removeProcessImage();
		}
	}
}





function displayNGOODocType(){
	
	
	
	var request = getHTTPObject();
	if(request)
		{
			showProcessImage();
			request.onreadystatechange = function(){
				
				displayNGOOResponse(request);
			};
			
			var val = document.getElementById("ngoo").value;
			var url = "NextgenDocTypeServlet";
			
			url = url+"?value="+val;
			
			request.open("GET",url,true);
			request.send(null);
			
		
		}
	
}



function displayNGOOResponse(request)
{
	if(request.readyState==4)
	{
		
		if(request.status == 200 || request.status == 304)
		{
			
			document.getElementById('ngooDocTypeDiv').innerHTML = request.responseText;
			removeProcessImage();
		}
	}
	
}	

function displayNGOOPartnerName(value){
	
	var request = getHTTPObject();
	if(request)
		{
			showProcessImage();
			request.onreadystatechange = function(){
				
				displayNGOOPartnerResponse(request);
			};
			
			var arr = document.getElementById("ngooDocType");
			var val='';
			var counter=0;
			for(i=0;i<arr.length;i++ )
			{
				if(arr.options[i].selected==true)
				{
					counter++;
					val += arr.options[i].value+'|';
				}
			}
			
			var url = "NextgenNGOOPartnerNameServlet";
			
			if(counter==0)
			{
				document.getElementById('ngooPartnerNameDiv').innerHTML = "<div id='ngooPartnerNameDiv'><select size =3 multiple='multiple' class='inputText'><option> Select Doc Type </option></select></div>";
				removeProcessImage();
			}
			else
			{
			url = url+"?value="+val;
			
			request.open("GET",url,true);
			request.send(null);
			
			}
		
		}
	
	
}


function displayNGOOPartnerResponse(request)
{
	if(request.readyState==4)
	{
		if(request.status == 200 || request.status == 304)
		{
			
			document.getElementById('ngooPartnerNameDiv').innerHTML = request.responseText;
			removeProcessImage();
		}
	}
}



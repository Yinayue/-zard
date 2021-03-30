 function loadXMLDoc(url,success,errror)
{
	var xmlhttp;
	if (window.XMLHttpRequest)
	{
		//  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlhttp=new XMLHttpRequest();
	}
	else
	{
		// IE6, IE5 浏览器执行代码
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.open("GET",url,true);
	xmlhttp.send();
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState===4)
		{
		    if (xmlhttp.status>=200 && xmlhttp.status<300||
                xmlhttp.status===304){
		        success(xmlhttp);
            }
		    else {
		        errror(xmlhttp)
            }
			//document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
		}
	}
}

 function objtostr(obj)
 {
 	var res=[];
 	for(var key in obj){
 		res.push(key+"="+obj[key]);

	}
 	return res.join("&");
 }

function Postinfo(url,obj,timeout,success,errror)
{
	var str=objtostr(obj)
	var xmlhttp,timer;
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
	// console.log("str:"+str)
	xmlhttp.open("POST",url+"?"+str,true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");

	console.log("str:"+url+"?"+str)
	xmlhttp.send(str);
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
		}
	}
	if(timeout){
		timer=setInterval(function () {
			// "中断请求"
			xmlhttp.abort();
			clearInterval(timer)
		},timeout)
	}


}

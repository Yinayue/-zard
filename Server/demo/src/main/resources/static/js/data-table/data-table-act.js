(function ($) {
 "use strict";
	
	$(document).ready(function() {
		 $('#data-table-basic').DataTable({
			 ajax : {
				 url:"http://175.24.51.64:8081/houses/select", //访问路径，返回Json数据

				 type : "POST"   //设置请求类型
			 },
			 columns : [ //数据渲染，固定格式,下面每一个对应表格一个td
				 {
					 // "name" : "address", //后台返回json数据 这是名
					 "data" : "address"  //这是值，一定要对应
				 },
				 {
					 // "name" : "decoration",
					 "data" : "decoration"
				 },
				 {
					 // "name" : "floor",
					 "data" : "launchDate"
				 },
				 // {
					//  // "name" : "houseType",
					//  "data" : "houseType"
				 // },
				 {
					 // "name" : "price",
					 "data" : "price"

				 },
				 {
					 // "name" : "size",
					 "data" : "size"
				 },]
		 });

	});
 
})(jQuery); 
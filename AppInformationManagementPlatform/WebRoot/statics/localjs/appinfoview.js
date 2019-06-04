var pathName = window.document.location.pathname;
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);



$("#back").on("click", function() {
	history.back(-1);
});
//请求图片
$(function() {

	$.ajax({
		type : "post",
		//data:{"param1":"0001","param2":"0002"}, //参数
		data : {
			"id" : document.getElementById("id").value,
		},
		dataType : "json",
		url : projectName + "/dev/img",
		success : function(data) {
			//将图片的Base64编码设置给src
			$("#imgs").attr("src", "data:image/png;base64," + data);
		},
		error : function(data) {
			alert('响应失败！');
		}
	});
});
/*$(function() {

	$.ajax({
		type : "POST", //请求类型
		url : projectName + "/dev/img", //请求的url
		data : {
			path : document.getElementById("img").value,
		}, //请求参数
		dataType : "json", //ajax接口（请求url）返回的数据类型
		success : function(result) { //data：返回数据（json对象）
			//清空之前的内容，避免数据都叠加在一起
            $("#rview").empty();
             给指定区域写内容，也就是要显示的地方 
            $("#rview").prepend(result); 
            为标签写属性，这里我使用的是java的数据流方式传递图片到页面，类似于验证码原理 
            $("#img").attr("src","${pageContext.request.contextPath }/wc");
             处理结束后关闭遮罩层
            
		},
		error : function(result) { //当访问时候，404，500 等非200的错误状态码
			alert("失败");
			//清空之前的内容，避免数据都叠加在一起
            $("#rview").empty();
             给指定区域写内容，也就是要显示的地方 
            $("#rview").prepend(result); 
            为标签写属性，这里我使用的是java的数据流方式传递图片到页面，类似于验证码原理 
            $("#img").attr("src","${pageContext.request.contextPath }/wc");
             处理结束后关闭遮罩层
           
		}
	});
});*/